package org.repeid.admin.client.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.repeid.representations.idm.SeccionRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */

public interface SeccionResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SeccionRepresentation toRepresentation();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(SeccionRepresentation rep);

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove();

    @Path("preguntas")
    public PreguntasResource preguntas();

}