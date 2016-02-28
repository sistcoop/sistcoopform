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

import org.sistcoopform.manager.api.beans.representations.idm.AnswerRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */

@Consumes(MediaType.APPLICATION_JSON)
public interface AnswersResource {

	@Path("{answerId}")
	public AnswerResource answer(@PathParam("answerId") String answerId);

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(AnswerRepresentation rep);

	@POST
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(AnswerRepresentation[] reps);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AnswerRepresentation> getAll(@QueryParam("formAnswerId") String formAnswerId,
			@QueryParam("questionId") String questionId);

}