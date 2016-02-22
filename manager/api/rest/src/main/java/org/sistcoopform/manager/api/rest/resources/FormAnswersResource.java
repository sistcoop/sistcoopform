package org.sistcoopform.manager.api.rest.resources;

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

import org.sistcoopform.manager.api.beans.representations.idm.FormAnswerRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */

@Path("formAnswers")
@Consumes(MediaType.APPLICATION_JSON)
public interface FormAnswersResource {

	@Path("{formAnswerId}")
	public FormAnswerResource form(@PathParam("formAnswerId") String formAnswerId);

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(FormAnswerRepresentation rep);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FormAnswerRepresentation> search(@QueryParam("user") String user,
			@QueryParam("filterText") String filterText, @QueryParam("first") Integer firstResult,
			@QueryParam("max") Integer maxResults);

	@POST
	@Path("search")
	@Produces(MediaType.APPLICATION_JSON)
	public SearchResultsRepresentation<FormAnswerRepresentation> search(SearchCriteriaRepresentation criteria);

}