package org.sistcoopform.manager.api.rest.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoopform.manager.api.beans.representations.idm.SectionRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */

@Consumes(MediaType.APPLICATION_JSON)
public interface SectionsResource {

	@Path("{sectionId}")
	public SectionResource seccion(@PathParam("sectionId") String sectionId);

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(SectionRepresentation rep);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SectionRepresentation> getAll();

}