package org.repeid.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.repeid.admin.client.resource.AccionistaResource;
import org.repeid.admin.client.resource.AccionistasResource;
import org.repeid.representations.idm.AccionistaRepresentation;
import org.repeid.representations.idm.PersonaNaturalRepresentation;
import org.repeid.services.ErrorResponse;
import org.sistcoopform.models.AccionistaModel;
import org.sistcoopform.models.AccionistaProvider;
import org.sistcoopform.models.PersonaJuridicaModel;
import org.sistcoopform.models.PersonaJuridicaProvider;
import org.sistcoopform.models.PersonaNaturalModel;
import org.sistcoopform.models.PersonaNaturalProvider;
import org.sistcoopform.models.FormularioModel;
import org.sistcoopform.models.FormularioProvider;
import org.sistcoopform.models.utils.ModelToRepresentation;
import org.sistcoopform.models.utils.RepresentationToModel;

@Stateless
public class AccionistasResourceImpl implements AccionistasResource {

	@PathParam("idPersonaJuridica")
	private String idPersonaJuridica;

	@Inject
	private FormularioProvider tipoDocumentoProvider;

	@Inject
	private PersonaJuridicaProvider personaJuridicaProvider;

	@Inject
	private PersonaNaturalProvider personaNaturalProvider;

	@Inject
	private AccionistaProvider accionistaProvider;

	@Inject
	private AccionistaResource accionistaResource;

	@Inject
	private RepresentationToModel representationToModel;

	@Context
	private UriInfo uriInfo;

	private PersonaJuridicaModel getPersonaJuridicaModel() {
		return personaJuridicaProvider.findById(idPersonaJuridica);
	}

	@Override
	public AccionistaResource accionista(String accionista) {
		return accionistaResource;
	}

	@Override
	public Response create(AccionistaRepresentation rep) {
		PersonaJuridicaModel personaJuridica = getPersonaJuridicaModel();

		PersonaNaturalRepresentation personaNaturalRep = rep.getPersonaNatural();
		FormularioModel tipoDocumento = tipoDocumentoProvider
				.findByAbreviatura(personaNaturalRep.getTipoDocumento());
		PersonaNaturalModel personaNatural = personaNaturalProvider.findByTipoNumeroDocumento(tipoDocumento,
				personaNaturalRep.getNumeroDocumento());

		// Check duplicated personaJuridica y personaNatural
		if (accionistaProvider.findByPersonaJuridicaNatural(personaJuridica, personaNatural) != null) {
			return ErrorResponse.exists("Accionista existe con la misma persona juridica y natural");
		}

		AccionistaModel model = representationToModel.createAccionista(rep, personaJuridica, personaNatural,
				accionistaProvider);

		return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId()).build())
				.header("Access-Control-Expose-Headers", "Location")
				.entity(ModelToRepresentation.toRepresentation(model)).build();
	}

	@Override
	public List<AccionistaRepresentation> getAll() {
		List<AccionistaModel> results = accionistaProvider.getAll(getPersonaJuridicaModel());
		List<AccionistaRepresentation> representations = new ArrayList<>();
		for (AccionistaModel model : results) {
			representations.add(ModelToRepresentation.toRepresentation(model));
		}
		return representations;
	}

}
