package org.repeid.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.repeid.admin.client.resource.FormularioResource;
import org.repeid.admin.client.resource.SeccionesResource;
import org.repeid.representations.idm.FormularioRepresentation;
import org.repeid.services.ErrorResponse;
import org.repeid.services.managers.FormularioManager;
import org.sistcoopform.models.FormularioModel;
import org.sistcoopform.models.FormularioProvider;
import org.sistcoopform.models.utils.ModelToRepresentation;

@Stateless
public class FormularioResourceImpl implements FormularioResource {

    @PathParam("idFormulario")
    private String idFormulario;

    @Inject
    private FormularioProvider formularioProvider;

    @Inject
    private FormularioManager tipoDocumentoManager;

    private FormularioModel getFormularioModel() {
        return formularioProvider.findById(idFormulario);
    }

    @Override
    public FormularioRepresentation toRepresentation() {
        FormularioRepresentation rep = ModelToRepresentation.toRepresentation(getFormularioModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("Formulario no encontrado");
        }
    }

    @Override
    public void update(FormularioRepresentation rep) {
        tipoDocumentoManager.update(getFormularioModel(), rep);
    }

    @Override
    public Response remove() {
        FormularioModel model = getFormularioModel();
        if (model == null) {
            throw new NotFoundException("Formulario no encontrado");
        }
        boolean removed = formularioProvider.remove(model);
        if (removed) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Formulario no pudo ser eliminado", Response.Status.BAD_REQUEST);
        }
    }

    @Override
    public SeccionesResource secciones() {
        // TODO Auto-generated method stub
        return null;
    }

}
