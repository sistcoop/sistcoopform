package org.sistcoopform.manager.api.rest.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoopform.manager.api.beans.representations.idm.FormRepresentation;
import org.sistcoopform.manager.api.model.FormModel;
import org.sistcoopform.manager.api.model.FormProvider;
import org.sistcoopform.manager.api.model.utils.ModelToRepresentation;
import org.sistcoopform.manager.api.rest.managers.FormularioManager;
import org.sistcoopform.manager.api.rest.services.ErrorResponse;

@Stateless
public class FormResourceImpl implements FormResource {

	@PathParam("formId")
	private String formId;

	@Inject
	private FormProvider formProvider;

	@Inject
	private FormularioManager formManager;

	private FormModel getFormularioModel() {
		return formProvider.findById(formId);
	}

	@Override
	public FormRepresentation toRepresentation() {
		FormRepresentation rep = ModelToRepresentation.toRepresentation(getFormularioModel());
		if (rep != null) {
			return rep;
		} else {
			throw new NotFoundException("Form not found");
		}
	}

	@Override
	public void update(FormRepresentation rep) {
		formManager.update(getFormularioModel(), rep);
	}

	@Override
	public Response remove() {
		FormModel model = getFormularioModel();
		if (model == null) {
			throw new NotFoundException("Form not found");
		}
		boolean removed = formProvider.remove(model);
		if (removed) {
			return Response.noContent().build();
		} else {
			return ErrorResponse.error("Form could'nt delete", Response.Status.BAD_REQUEST);
		}
	}

	@Override
	public SectionsResource sections() {
		// TODO Auto-generated method stub
		return null;
	}

}