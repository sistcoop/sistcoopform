package org.sistcoopform.manager.api.rest.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoopform.manager.api.beans.representations.idm.AnswerRepresentation;
import org.sistcoopform.manager.api.model.AnswerModel;
import org.sistcoopform.manager.api.model.AnswerProvider;
import org.sistcoopform.manager.api.model.FormAnswerModel;
import org.sistcoopform.manager.api.model.FormAnswerProvider;
import org.sistcoopform.manager.api.model.ModelDuplicateException;
import org.sistcoopform.manager.api.model.QuestionModel;
import org.sistcoopform.manager.api.model.QuestionProvider;
import org.sistcoopform.manager.api.model.utils.ModelToRepresentation;
import org.sistcoopform.manager.api.model.utils.RepresentationToModel;
import org.sistcoopform.manager.api.rest.services.ErrorResponse;

@Stateless
public class AnswersResourceImpl implements AnswersResource {

	@PathParam("formAnswerId")
	private String formAnswerId;

	@Inject
	private FormAnswerProvider formAnswerProvider;
	
	@Inject
	private AnswerProvider answerProvider;

	@Inject
	private QuestionProvider questionProvider;
	
	@Inject
	private RepresentationToModel representationToModel;

	@Context
	private UriInfo uriInfo;

	@Inject
	private AnswerResource answerResource;

	private FormAnswerModel getFormAnswerModel() {
		return formAnswerProvider.findById(formAnswerId);
	}

	@Override
	public AnswerResource answer(String answerId) {
		return answerResource;
	}

	@Override
	public Response create(AnswerRepresentation rep) {
		FormAnswerModel formAnswer = getFormAnswerModel();
		QuestionModel question = questionProvider.findById(rep.getQuestion().getId());
		try {
			AnswerModel model = representationToModel.createAnswer(formAnswer, question, rep, answerProvider);
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
	public List<AnswerRepresentation> getAll() {
		List<AnswerRepresentation> results = new ArrayList<AnswerRepresentation>();
		List<AnswerModel> models = answerProvider.getAll(getFormAnswerModel());

		for (AnswerModel model : models) {
			results.add(ModelToRepresentation.toRepresentation(model));
		}
		return results;
	}

}
