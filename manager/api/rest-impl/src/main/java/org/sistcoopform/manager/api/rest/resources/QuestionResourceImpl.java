package org.sistcoopform.manager.api.rest.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoopform.manager.api.beans.representations.idm.QuestionRepresentation;
import org.sistcoopform.manager.api.model.QuestionModel;
import org.sistcoopform.manager.api.model.QuestionProvider;
import org.sistcoopform.manager.api.model.utils.ModelToRepresentation;
import org.sistcoopform.manager.api.rest.managers.FormManager;
import org.sistcoopform.manager.api.rest.resources.config.AnswerResourceQualifer;
import org.sistcoopform.manager.api.rest.resources.config.AnswerResourceQualifer.AnswerResourceType;
import org.sistcoopform.manager.api.rest.services.ErrorResponse;

@Stateless
public class QuestionResourceImpl implements QuestionResource {

	@PathParam("questionId")
	private String questionId;

	@Inject
	private QuestionProvider questionProvider;

	@Inject
	private FormManager formManager;

	@Inject
	@AnswerResourceQualifer(AnswerResourceType.QUESTION_PARENT)
	private AnswersResource answersResource;

	private QuestionModel getQuestionModel() {
		return questionProvider.findById(questionId);
	}

	@Override
	public QuestionRepresentation toRepresentation() {
		QuestionRepresentation rep = ModelToRepresentation.toRepresentation(getQuestionModel());
		if (rep != null) {
			return rep;
		} else {
			throw new NotFoundException("Question not found");
		}
	}

	@Override
	public void update(QuestionRepresentation rep) {
		formManager.updateQuestion(getQuestionModel(), rep);
	}

	@Override
	public Response remove() {
		QuestionModel model = getQuestionModel();
		if (model == null) {
			throw new NotFoundException("Question not found");
		}
		boolean removed = questionProvider.remove(model);
		if (removed) {
			return Response.noContent().build();
		} else {
			return ErrorResponse.error("Question could'nt delete", Response.Status.BAD_REQUEST);
		}
	}

	@Override
	public AnswersResource answers() {
		return answersResource;
	}

}
