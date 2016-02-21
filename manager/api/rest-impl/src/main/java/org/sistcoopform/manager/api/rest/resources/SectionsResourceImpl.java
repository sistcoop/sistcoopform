package org.sistcoopform.manager.api.rest.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoopform.manager.api.beans.representations.idm.SectionRepresentation;
import org.sistcoopform.manager.api.model.FormModel;
import org.sistcoopform.manager.api.model.FormProvider;
import org.sistcoopform.manager.api.model.ModelDuplicateException;
import org.sistcoopform.manager.api.model.SectionModel;
import org.sistcoopform.manager.api.model.SectionProvider;
import org.sistcoopform.manager.api.model.utils.ModelToRepresentation;
import org.sistcoopform.manager.api.model.utils.RepresentationToModel;
import org.sistcoopform.manager.api.rest.services.ErrorResponse;

@Stateless
public class SectionsResourceImpl implements SectionsResource {

	@PathParam("formId")
	private String formId;

	@Inject
	private FormProvider formProvider;

	@Inject
	private SectionProvider sectionProvider;

	@Inject
	private RepresentationToModel representationToModel;

	@Inject
	private SectionResource sectionResource;

	@Context
	private UriInfo uriInfo;

	private FormModel getFormModel() {
		return formProvider.findById(formId);
	}

	@Override
	public SectionResource seccion(String sectionId) {
		return sectionResource;
	}

	@Override
	public Response create(SectionRepresentation rep) {
		FormModel form = getFormModel();
		try {
			SectionModel model = representationToModel.createSection(form, rep, sectionProvider);
			return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId()).build())
					.header("Access-Control-Expose-Headers", "Location")
					.entity(ModelToRepresentation.toRepresentation(model)).build();
		} catch (ModelDuplicateException e) {
			return ErrorResponse.exists("Section exists");
		}
	}

	@Override
	public List<SectionRepresentation> getAll() {
		List<SectionRepresentation> results = new ArrayList<SectionRepresentation>();
		List<SectionModel> models = sectionProvider.getAll(getFormModel());

		for (SectionModel model : models) {
			results.add(ModelToRepresentation.toRepresentation(model));
		}
		return results;
	}

}
