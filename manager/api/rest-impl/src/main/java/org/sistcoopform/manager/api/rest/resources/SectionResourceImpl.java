package org.sistcoopform.manager.api.rest.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoopform.manager.api.beans.representations.idm.SectionRepresentation;
import org.sistcoopform.manager.api.model.SectionModel;
import org.sistcoopform.manager.api.model.SectionProvider;
import org.sistcoopform.manager.api.model.utils.ModelToRepresentation;
import org.sistcoopform.manager.api.rest.managers.FormManager;
import org.sistcoopform.manager.api.rest.services.ErrorResponse;

@Stateless
public class SectionResourceImpl implements SectionResource {

	@PathParam("sectionId")
	private String sectionId;

	@Inject
	private SectionProvider sectionProvider;

	@Inject
	private FormManager formManager;
	
	@Inject
	private QuestionsResource questionsResource;

	private SectionModel getSectionModel() {
		return sectionProvider.findById(sectionId);
	}

	@Override
	public SectionRepresentation toRepresentation() {
		SectionRepresentation rep = ModelToRepresentation.toRepresentation(getSectionModel());
		if (rep != null) {
			return rep;
		} else {
			throw new NotFoundException("Section not found");
		}
	}

	@Override
	public void update(SectionRepresentation rep) {
		formManager.updateSection(getSectionModel(), rep);
	}

	@Override
	public Response remove() {
		SectionModel model = getSectionModel();
		if (model == null) {
			throw new NotFoundException("Section not found");
		}
		boolean removed = sectionProvider.remove(model);
		if (removed) {
			return Response.noContent().build();
		} else {
			return ErrorResponse.error("Section could'nt delete", Response.Status.BAD_REQUEST);
		}
	}

	@Override
	public QuestionsResource questions() {
		return questionsResource;
	}

}
