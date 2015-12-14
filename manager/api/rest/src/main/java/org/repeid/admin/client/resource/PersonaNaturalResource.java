package org.repeid.admin.client.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.repeid.representations.idm.PersonaNaturalRepresentation;
import org.repeid.representations.idm.StoredFileRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */

public interface PersonaNaturalResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PersonaNaturalRepresentation toRepresentation();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(PersonaNaturalRepresentation rep);

    @GET
    @Path("foto")
    @Produces("image/png")
    public Response getFoto();

    @POST
    @Path("foto")
    @Consumes("multipart/form-data")
    public StoredFileRepresentation setFoto(MultipartFormDataInput input);

    @GET
    @Path("firma")
    @Produces("image/png")
    public Response getFirma();

    @POST
    @Path("firma")
    @Consumes("multipart/form-data")
    public StoredFileRepresentation setFirma(MultipartFormDataInput input);

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove();

}