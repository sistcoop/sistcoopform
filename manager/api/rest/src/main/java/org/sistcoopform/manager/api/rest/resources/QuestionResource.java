package org.sistcoopform.manager.api.rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoopform.manager.api.beans.representations.idm.QuestionRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */

public interface QuestionResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public QuestionRepresentation toRepresentation();

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(QuestionRepresentation rep);

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response remove();

}