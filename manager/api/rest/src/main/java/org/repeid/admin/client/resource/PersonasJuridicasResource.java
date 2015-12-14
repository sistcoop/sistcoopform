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

import org.repeid.representations.idm.PersonaJuridicaRepresentation;
import org.repeid.representations.idm.search.SearchCriteriaRepresentation;
import org.repeid.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */

@Path("/personas/juridicas")
@Consumes(MediaType.APPLICATION_JSON)
public interface PersonasJuridicasResource {

    @Path("{idPersonaJuridica}")
    public PersonaJuridicaResource personaJuridica(@PathParam("idPersonaJuridica") String idPersonaJuridica);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(PersonaJuridicaRepresentation rep);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PersonaJuridicaRepresentation> search(@QueryParam("tipoDocumento") String tipoDocumento,
            @QueryParam("numeroDocumento") String numeroDocumento,
            @QueryParam("razonSocial") String razonSocial,
            @QueryParam("nombreComercial") String nombreComercial,
            @QueryParam("filterText") String filterText, @QueryParam("first") Integer firstResult,
            @QueryParam("max") Integer maxResults);

    /**
     * Este endpoint provee una forma de buscar direccionesRegionales. Los
     * criterios de busqueda estan definidos por los parametros enviados.
     * 
     * @summary Search for DireccionesRegionales
     * @param criteria
     *            Criterio de busqueda.
     * @statuscode 200 Si la busqueda fue realizada satisfactoriamente.
     * @return Los resultados de la busqueda (una pagina de
     *         direccionesRegionales).
     */
    @POST
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<PersonaJuridicaRepresentation> search(
            SearchCriteriaRepresentation criteria);

}