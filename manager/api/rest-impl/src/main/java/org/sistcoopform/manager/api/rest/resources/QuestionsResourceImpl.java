package org.sistcoopform.manager.api.rest.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoopform.manager.api.beans.representations.idm.FormRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.QuestionRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.search.OrderByRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.search.PagingRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.search.SearchCriteriaFilterRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.search.SearchResultsRepresentation;
import org.sistcoopform.manager.api.model.FormModel;
import org.sistcoopform.manager.api.model.FormProvider;
import org.sistcoopform.manager.api.model.ModelDuplicateException;
import org.sistcoopform.manager.api.model.SectionModel;
import org.sistcoopform.manager.api.model.SectionProvider;
import org.sistcoopform.manager.api.model.search.SearchCriteriaFilterOperator;
import org.sistcoopform.manager.api.model.search.SearchCriteriaModel;
import org.sistcoopform.manager.api.model.search.SearchResultsModel;
import org.sistcoopform.manager.api.model.utils.ModelToRepresentation;
import org.sistcoopform.manager.api.model.utils.RepresentationToModel;
import org.sistcoopform.manager.api.rest.services.ErrorResponse;

@Stateless
public class QuestionsResourceImpl implements QuestionsResource {

	@PathParam("sectionId")
	private String sectionId;

	@Inject
	private FormProvider formProvider;

	@Inject
	private  sectionProvider;

	@Inject
	private RepresentationToModel representationToModel;

	@Context
	private UriInfo uriInfo;

	@Inject
	private QuestionResource questionResource;

	private SectionModel getSectionModel() {
		return sectionProvider.findById(sectionId);
	}

	@Override
	public QuestionResource question(String questionId) {
		return questionResource;
	}

	@Override
	public Response create(QuestionRepresentation rep) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionRepresentation> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
