package org.sistcoopform.manager.api.rest.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoopform.manager.api.beans.representations.idm.FormAnswerRepresentation;
import org.sistcoopform.manager.api.model.FormAnswerModel;
import org.sistcoopform.manager.api.model.FormAnswerProvider;
import org.sistcoopform.manager.api.model.utils.ModelToRepresentation;
import org.sistcoopform.manager.api.rest.managers.FormAnswerManager;
import org.sistcoopform.manager.api.rest.services.ErrorResponse;

@Stateless
public class FormAnswerResourceImpl implements FormAnswerResource {

	@PathParam("formAnswerId")
	private String formAnswerId;

	@Inject
	private FormAnswerProvider formAnswerProvider;

	@Inject
	private FormAnswerManager formAnswerManager;

	@Inject
	private AnswersResource answersResource;

	private FormAnswerModel getFormAnswerularioModel() {
		return formAnswerProvider.findById(formAnswerId);
	}

	@Override
	public FormAnswerRepresentation toRepresentation() {
		FormAnswerRepresentation rep = ModelToRepresentation.toRepresentation(getFormAnswerularioModel());
		if (rep != null) {
			return rep;
		} else {
			throw new NotFoundException("FormAnswer not found");
		}
	}

	@Override
	public void update(FormAnswerRepresentation rep) {
		formAnswerManager.update(getFormAnswerularioModel(), rep);
	}

	@Override
	public Response remove() {
		FormAnswerModel model = getFormAnswerularioModel();
		if (model == null) {
			throw new NotFoundException("FormAnswer not found");
		}
		boolean removed = formAnswerProvider.remove(model);
		if (removed) {
			return Response.noContent().build();
		} else {
			return ErrorResponse.error("FormAnswer could'nt delete", Response.Status.BAD_REQUEST);
		}
	}

	@Override
	public AnswersResource answers() {
		return answersResource;
	}

}
