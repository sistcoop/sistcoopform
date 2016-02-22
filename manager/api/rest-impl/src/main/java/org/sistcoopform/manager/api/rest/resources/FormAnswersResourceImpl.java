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

import org.sistcoopform.manager.api.beans.representations.idm.FormAnswerRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.search.OrderByRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.search.PagingRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.search.SearchCriteriaFilterRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.search.SearchResultsRepresentation;
import org.sistcoopform.manager.api.model.FormAnswerModel;
import org.sistcoopform.manager.api.model.FormAnswerProvider;
import org.sistcoopform.manager.api.model.ModelDuplicateException;
import org.sistcoopform.manager.api.model.search.SearchCriteriaFilterOperator;
import org.sistcoopform.manager.api.model.search.SearchCriteriaModel;
import org.sistcoopform.manager.api.model.search.SearchResultsModel;
import org.sistcoopform.manager.api.model.utils.ModelToRepresentation;
import org.sistcoopform.manager.api.model.utils.RepresentationToModel;
import org.sistcoopform.manager.api.rest.services.ErrorResponse;

@Stateless
public class FormAnswersResourceImpl implements FormAnswersResource {

	@Inject
	private FormAnswerProvider formAnswerProvider;

	@Inject
	private RepresentationToModel representationToModel;

	@Context
	private UriInfo uriInfo;

	@Inject
	private FormAnswerResource formAnswerResource;

	@Override
	public FormAnswerResource form(String formAnswerId) {
		return formAnswerResource;
	}

	@Override
	public Response create(FormAnswerRepresentation rep) {
		try {
			FormAnswerModel model = representationToModel.createFormAnswer(rep, formAnswerProvider);
			return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId()).build())
					.header("Access-Control-Expose-Headers", "Location")
					.entity(ModelToRepresentation.toRepresentation(model)).build();
		} catch (ModelDuplicateException e) {
			return ErrorResponse.exists("FormAnswerAnswer conflicted");
		}
	}

	@Override
	public List<FormAnswerRepresentation> search(String user, String filterText, Integer firstResult, Integer maxResults) {
		firstResult = firstResult != null ? firstResult : -1;
		maxResults = maxResults != null ? maxResults : -1;

		List<FormAnswerRepresentation> results = new ArrayList<FormAnswerRepresentation>();
		List<FormAnswerModel> models;
		if (filterText != null) {
			models = formAnswerProvider.search(filterText.trim(), firstResult, maxResults);
		} else if (user != null) {
			Map<String, Object> attributes = new HashMap<String, Object>();
			if (user != null) {
				attributes.put(FormAnswerModel.USER, user);
			}
			models = formAnswerProvider.searchByAttributes(attributes, firstResult, maxResults);
		} else {
			models = formAnswerProvider.getAll(firstResult, maxResults);
		}

		for (FormAnswerModel model : models) {
			results.add(ModelToRepresentation.toRepresentation(model));
		}
		return results;
	}

	@Override
	public SearchResultsRepresentation<FormAnswerRepresentation> search(SearchCriteriaRepresentation criteria) {
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
		SearchResultsModel<FormAnswerModel> results = null;
		if (filterText == null) {
			results = formAnswerProvider.search(criteriaModel);
		} else {
			results = formAnswerProvider.search(criteriaModel, filterText);
		}

		SearchResultsRepresentation<FormAnswerRepresentation> rep = new SearchResultsRepresentation<>();
		List<FormAnswerRepresentation> items = new ArrayList<>();
		for (FormAnswerModel model : results.getModels()) {
			items.add(ModelToRepresentation.toRepresentation(model));
		}
		rep.setItems(items);
		rep.setTotalSize(results.getTotalSize());
		return rep;
	}

}
