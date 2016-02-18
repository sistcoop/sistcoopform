package org.sistcoopform.manager.api.rest.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoopform.manager.api.beans.representations.idm.FormRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.search.OrderByRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.search.PagingRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.search.SearchCriteriaFilterRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.search.SearchResultsRepresentation;
import org.sistcoopform.manager.api.model.FormModel;
import org.sistcoopform.manager.api.model.FormProvider;
import org.sistcoopform.manager.api.model.ModelDuplicateException;
import org.sistcoopform.manager.api.model.search.SearchCriteriaFilterOperator;
import org.sistcoopform.manager.api.model.search.SearchCriteriaModel;
import org.sistcoopform.manager.api.model.search.SearchResultsModel;
import org.sistcoopform.manager.api.model.utils.ModelToRepresentation;
import org.sistcoopform.manager.api.model.utils.RepresentationToModel;
import org.sistcoopform.manager.api.rest.services.ErrorResponse;

@Stateless
public class FormsResourceImpl implements FormsResource {

	@Inject
	private FormProvider formProvider;

	@Inject
	private RepresentationToModel representationToModel;

	@Context
	private UriInfo uriInfo;

	@Inject
	private FormResource formResource;

	@Override
	public FormResource form(String formId) {
		return formResource;
	}

	@Override
	public Response create(FormRepresentation rep) {
		try {
			FormModel model = representationToModel.createFormulario(rep, formProvider);
			return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId()).build())
					.header("Access-Control-Expose-Headers", "Location")
					.entity(ModelToRepresentation.toRepresentation(model)).build();
		} catch (ModelDuplicateException e) {
			return ErrorResponse.exists("Formulario conflicto");
		}
	}

	@Override
	public List<FormRepresentation> search(String titulo, String filterText, Integer firstResult, Integer maxResults) {
		firstResult = firstResult != null ? firstResult : -1;
		maxResults = maxResults != null ? maxResults : -1;

		List<FormRepresentation> results = new ArrayList<FormRepresentation>();
		List<FormModel> models;
		if (filterText != null) {
			models = formProvider.search(filterText.trim(), firstResult, maxResults);
		} else if (titulo != null) {
			Map<String, Object> attributes = new HashMap<String, Object>();
			if (titulo != null) {
				attributes.put(FormModel.TITLE, titulo);
			}
			models = formProvider.searchByAttributes(attributes, firstResult, maxResults);
		} else {
			models = formProvider.getAll(firstResult, maxResults);
		}

		for (FormModel model : models) {
			results.add(ModelToRepresentation.toRepresentation(model));
		}
		return results;
	}

	@Override
	public SearchResultsRepresentation<FormRepresentation> search(SearchCriteriaRepresentation criteria) {
		SearchCriteriaModel criteriaModel = new SearchCriteriaModel();

		// set filter and order
		for (SearchCriteriaFilterRepresentation filter : criteria.getFilters()) {
			criteriaModel.addFilter(filter.getName(), filter.getValue(),
					SearchCriteriaFilterOperator.valueOf(filter.getOperator().toString()));
		}
		for (OrderByRepresentation order : criteria.getOrders()) {
			criteriaModel.addOrder(order.getName(), order.isAscending());
		}

		// set paging
		PagingRepresentation paging = criteria.getPaging();
		if (paging == null) {
			paging = new PagingRepresentation();
			paging.setPage(1);
			paging.setPageSize(20);
		}
		criteriaModel.setPageSize(paging.getPageSize());
		criteriaModel.setPage(paging.getPage());

		// extract filterText
		String filterText = criteria.getFilterText();

		// search
		SearchResultsModel<FormModel> results = null;
		if (filterText == null) {
			results = formProvider.search(criteriaModel);
		} else {
			results = formProvider.search(criteriaModel, filterText);
		}

		SearchResultsRepresentation<FormRepresentation> rep = new SearchResultsRepresentation<>();
		List<FormRepresentation> items = new ArrayList<>();
		for (FormModel model : results.getModels()) {
			items.add(ModelToRepresentation.toRepresentation(model));
		}
		rep.setItems(items);
		rep.setTotalSize(results.getTotalSize());
		return rep;
	}

}
