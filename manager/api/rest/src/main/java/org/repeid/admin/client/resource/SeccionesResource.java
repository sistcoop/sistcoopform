package org.repeid.admin.client.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.repeid.representations.idm.SeccionRepresentation;
import org.repeid.representations.idm.search.SearchCriteriaRepresentation;
import org.repeid.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */

@Consumes(MediaType.APPLICATION_JSON)
public interface SeccionesResource {

    @Path("{idSeccion}")
    public SeccionResource seccion(@PathParam("idSeccion") String idSeccion);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(SeccionRepresentation rep);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SeccionRepresentation> search(@QueryParam("titulo") String denominacion,
            @QueryParam("numero") int numero, @QueryParam("filterText") String filterText,
            @QueryParam("first") Integer firstResult, @QueryParam("max") Integer maxResults);

    @POST
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<SeccionRepresentation> search(SearchCriteriaRepresentation criteria);

}