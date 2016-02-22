package org.sistcoopform.manager.api.rest.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoopform.manager.api.beans.representations.idm.AnswerRepresentation;
import org.sistcoopform.manager.api.model.AnswerModel;
import org.sistcoopform.manager.api.model.AnswerProvider;
import org.sistcoopform.manager.api.model.utils.ModelToRepresentation;
import org.sistcoopform.manager.api.rest.managers.FormAnswerManager;
import org.sistcoopform.manager.api.rest.services.ErrorResponse;

@Stateless
public class AnswerResourceImpl implements AnswerResource {

	@PathParam("answerId")
	private String answerId;

	@Inject
	private AnswerProvider answerProvider;

	@Inject
	private FormAnswerManager formAnswerManager;

	private AnswerModel getAnswerModel() {
		return answerProvider.findById(answerId);
	}

	@Override
	public AnswerRepresentation toRepresentation() {
		AnswerRepresentation rep = ModelToRepresentation.toRepresentation(getAnswerModel());
		if (rep != null) {
			return rep;
		} else {
			throw new NotFoundException("Answer not found");
		}
	}

	@Override
	public void update(AnswerRepresentation rep) {
		formAnswerManager.updateAnswer(getAnswerModel(), rep);
	}

	@Override
	public Response remove() {
		AnswerModel model = getAnswerModel();
		if (model == null) {
			throw new NotFoundException("Answer not found");
		}
		boolean removed = answerProvider.remove(model);
		if (removed) {
			return Response.noContent().build();
		} else {
			return ErrorResponse.error("Answer could'nt delete", Response.Status.BAD_REQUEST);
		}
	}

}
