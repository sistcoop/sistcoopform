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
import org.sistcoopform.manager.api.rest.resources.config.AnswerResourceQualifer;
import org.sistcoopform.manager.api.rest.resources.config.AnswerResourceQualifer.AnswerResourceType;
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
	@AnswerResourceQualifer(AnswerResourceType.FORM_ANSWER_PARENT)
	private AnswersResource answersResource;

	private FormAnswerModel getFormAnswerModel() {
		return formAnswerProvider.findById(formAnswerId);
	}

	@Override
	public FormAnswerRepresentation toRepresentation() {
		FormAnswerRepresentation rep = ModelToRepresentation.toRepresentation(getFormAnswerModel());
		if (rep != null) {
			return rep;
		} else {
			throw new NotFoundException("FormAnswer not found");
		}
	}

	@Override
	public Response active() {
		boolean result = formAnswerManager.activeFormAnswer(getFormAnswerModel());
		if (result) {
			return Response.noContent().build();
		} else {
			return ErrorResponse.error("FormAnswer couldn't not active", Response.Status.BAD_REQUEST);
		}
	}

	@Override
	public void update(FormAnswerRepresentation rep) {
		formAnswerManager.update(getFormAnswerModel(), rep);
	}

	@Override
	public Response remove() {
		FormAnswerModel model = getFormAnswerModel();
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
