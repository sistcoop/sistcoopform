package org.sistcoopform.manager.api.rest.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoopform.manager.api.beans.representations.idm.QuestionRepresentation;
import org.sistcoopform.manager.api.model.ModelDuplicateException;
import org.sistcoopform.manager.api.model.QuestionModel;
import org.sistcoopform.manager.api.model.QuestionProvider;
import org.sistcoopform.manager.api.model.SectionModel;
import org.sistcoopform.manager.api.model.SectionProvider;
import org.sistcoopform.manager.api.model.utils.ModelToRepresentation;
import org.sistcoopform.manager.api.model.utils.RepresentationToModel;
import org.sistcoopform.manager.api.rest.services.ErrorResponse;

@Stateless
public class QuestionsResourceImpl implements QuestionsResource {

	@PathParam("sectionId")
	private String sectionId;

	@Inject
	private SectionProvider sectionProvider;

	@Inject
	private QuestionProvider questionProvider;

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
		SectionModel section = getSectionModel();
		try {
			QuestionModel model = representationToModel.createQuestion(section, rep, questionProvider);
			return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId()).build())
					.header("Access-Control-Expose-Headers", "Location")
					.entity(ModelToRepresentation.toRepresentation(model)).build();
		} catch (ModelDuplicateException e) {
			return ErrorResponse.exists("Question exists");
		} catch (Exception e) {
			return ErrorResponse.exists(e.getMessage());
		}
	}

	@Override
	public List<QuestionRepresentation> getAll() {
		List<QuestionRepresentation> results = new ArrayList<QuestionRepresentation>();
		List<QuestionModel> models = questionProvider.getAll(getSectionModel());

		for (QuestionModel model : models) {
			results.add(ModelToRepresentation.toRepresentation(model));
		}
		return results;
	}

}
