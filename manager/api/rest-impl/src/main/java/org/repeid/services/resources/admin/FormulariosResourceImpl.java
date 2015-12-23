package org.repeid.services.resources.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.repeid.admin.client.resource.FormularioResource;
import org.repeid.admin.client.resource.FormulariosResource;
import org.repeid.representations.idm.FormularioRepresentation;
import org.repeid.representations.idm.search.OrderByRepresentation;
import org.repeid.representations.idm.search.PagingRepresentation;
import org.repeid.representations.idm.search.SearchCriteriaFilterRepresentation;
import org.repeid.representations.idm.search.SearchCriteriaRepresentation;
import org.repeid.representations.idm.search.SearchResultsRepresentation;
import org.repeid.services.ErrorResponse;
import org.sistcoopform.models.FormularioModel;
import org.sistcoopform.models.FormularioProvider;
import org.sistcoopform.models.ModelDuplicateException;
import org.sistcoopform.models.search.SearchCriteriaFilterOperator;
import org.sistcoopform.models.search.SearchCriteriaModel;
import org.sistcoopform.models.search.SearchResultsModel;
import org.sistcoopform.models.utils.ModelToRepresentation;
import org.sistcoopform.models.utils.RepresentationToModel;

@Stateless
public class FormulariosResourceImpl implements FormulariosResource {

    @Inject
    private FormularioProvider formularioProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Context
    private UriInfo uriInfo;

    @Inject
    private FormularioResource formularioResource;

    @Override
    public FormularioResource formulario(String idFormulario) {
        return formularioResource;
    }

    @Override
    public Response create(FormularioRepresentation rep) {
        // Check duplicated abreviatura
        // if (tipoDocumentoProvider.findByAbreviatura(rep.getAbreviatura()) !=
        // null) {
        // return ErrorResponse.exists("TipoDocumento existe con la misma
        // abreviatura");
        // }
        try {
            FormularioModel model = representationToModel.createFormulario(rep, formularioProvider);
            return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId()).build())
                    .header("Access-Control-Expose-Headers", "Location")
                    .entity(ModelToRepresentation.toRepresentation(model)).build();
        } catch (ModelDuplicateException e) {
            return ErrorResponse.exists("Formulario conflicto");
        }
    }

    @Override
    public List<FormularioRepresentation> search(String titulo, String filterText, Integer firstResult,
            Integer maxResults) {
        firstResult = firstResult != null ? firstResult : -1;
        maxResults = maxResults != null ? maxResults : -1;

        List<FormularioRepresentation> results = new ArrayList<FormularioRepresentation>();
        List<FormularioModel> models;
        if (filterText != null) {
            models = formularioProvider.search(filterText.trim(), firstResult, maxResults);
        } else if (titulo != null) {
            Map<String, Object> attributes = new HashMap<String, Object>();
            if (titulo != null) {
                attributes.put(FormularioModel.TITULO, titulo);
            }
            models = formularioProvider.searchByAttributes(attributes, firstResult, maxResults);
        } else {
            models = formularioProvider.getAll(firstResult, maxResults);
        }

        for (FormularioModel model : models) {
            results.add(ModelToRepresentation.toRepresentation(model));
        }
        return results;
    }

    @Override
    public SearchResultsRepresentation<FormularioRepresentation> search(
            SearchCriteriaRepresentation criteria) {
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
        SearchResultsModel<FormularioModel> results = null;
        if (filterText == null) {
            results = formularioProvider.search(criteriaModel);
        } else {
            results = formularioProvider.search(criteriaModel, filterText);
        }

        SearchResultsRepresentation<FormularioRepresentation> rep = new SearchResultsRepresentation<>();
        List<FormularioRepresentation> items = new ArrayList<>();
        for (FormularioModel model : results.getModels()) {
            items.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setItems(items);
        rep.setTotalSize(results.getTotalSize());
        return rep;
    }

}
