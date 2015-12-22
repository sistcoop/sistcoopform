package org.repeid.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.repeid.admin.client.resource.FormularioResource;
import org.repeid.representations.idm.TipoDocumentoRepresentation;
import org.repeid.services.ErrorResponse;
import org.repeid.services.managers.TipoDocumentoManager;
import org.sistcoopform.models.FormularioModel;
import org.sistcoopform.models.FormularioProvider;
import org.sistcoopform.models.utils.ModelToRepresentation;

@Stateless
public class TipoDocumentoResourceImpl implements FormularioResource {

	@PathParam("idTipoDocumento")
	private String idTipoDocumento;

	@Inject
	private FormularioProvider tipoDocumentoProvider;

	@Inject
	private TipoDocumentoManager tipoDocumentoManager;

	private FormularioModel getTipoDocumentoModel() {
		return tipoDocumentoProvider.findById(idTipoDocumento);
	}

	@Override
	public TipoDocumentoRepresentation toRepresentation() {
		TipoDocumentoRepresentation rep = ModelToRepresentation.toRepresentation(getTipoDocumentoModel());
		if (rep != null) {
			return rep;
		} else {
			throw new NotFoundException("Tipo Documento no encontrado");
		}
	}

	@Override
	public void update(TipoDocumentoRepresentation representation) {
		tipoDocumentoManager.update(getTipoDocumentoModel(), representation);
	}

	@Override
	public void enable() {
		tipoDocumentoManager.enable(getTipoDocumentoModel());
	}

	@Override
	public void disable() {
		tipoDocumentoManager.disable(getTipoDocumentoModel());
	}

	@Override
	public Response remove() {
		FormularioModel tipoDocumento = getTipoDocumentoModel();
		if (tipoDocumento == null) {
			throw new NotFoundException("Tipo Documento no encontrado");
		}
		boolean removed = tipoDocumentoProvider.remove(tipoDocumento);
		if (removed) {
			return Response.noContent().build();
		} else {
			return ErrorResponse.error("Tipo Documento no pudo ser eliminado", Response.Status.BAD_REQUEST);
		}
	}

}
