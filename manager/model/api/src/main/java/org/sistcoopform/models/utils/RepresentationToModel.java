package org.sistcoopform.models.utils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.repeid.representations.idm.AccionistaRepresentation;
import org.repeid.representations.idm.PersonaJuridicaRepresentation;
import org.repeid.representations.idm.PersonaNaturalRepresentation;
import org.repeid.representations.idm.TipoDocumentoRepresentation;
import org.sistcoopform.models.AccionistaModel;
import org.sistcoopform.models.AccionistaProvider;
import org.sistcoopform.models.PersonaJuridicaModel;
import org.sistcoopform.models.PersonaJuridicaProvider;
import org.sistcoopform.models.PersonaNaturalModel;
import org.sistcoopform.models.PersonaNaturalProvider;
import org.sistcoopform.models.FormularioModel;
import org.sistcoopform.models.FormularioProvider;
import org.sistcoopform.models.enums.EstadoCivil;
import org.sistcoopform.models.enums.Sexo;
import org.sistcoopform.models.enums.TipoEmpresa;
import org.sistcoopform.models.enums.TipoPreguntaSeleccion;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RepresentationToModel {

    public FormularioModel createTipoDocumento(TipoDocumentoRepresentation rep,
            FormularioProvider provider) {
        FormularioModel model = provider.create(rep.getAbreviatura(), rep.getDenominacion(),
                rep.getCantidadCaracteres(), TipoPreguntaSeleccion.valueOf(rep.getTipoPersona()));
        return model;
    }

    public PersonaNaturalModel createPersonaNatural(PersonaNaturalRepresentation rep,
            FormularioModel tipoDocumentoModel, PersonaNaturalProvider personaNaturalProvider) {

        PersonaNaturalModel model = personaNaturalProvider.create(rep.getCodigoPais(), tipoDocumentoModel,
                rep.getNumeroDocumento(), rep.getApellidoPaterno(), rep.getApellidoMaterno(),
                rep.getNombres(), rep.getFechaNacimiento(), Sexo.valueOf(rep.getSexo().toUpperCase()));

        model.setEstadoCivil(rep.getEstadoCivil() != null
                ? EstadoCivil.valueOf(rep.getEstadoCivil().toUpperCase()) : null);
        model.setOcupacion(rep.getOcupacion());
        model.setUbigeo(rep.getUbigeo());
        model.setDireccion(rep.getDireccion());
        model.setReferencia(rep.getReferencia());
        model.setTelefono(rep.getTelefono());
        model.setCelular(rep.getCelular());
        model.setEmail(rep.getEmail());

        model.commit();
        return model;
    }

    public PersonaJuridicaModel createPersonaJuridica(PersonaJuridicaRepresentation rep,
            FormularioModel tipoDocumentoModel, PersonaNaturalModel representanteLegal,
            PersonaJuridicaProvider personaJuridicaProvider) {

        PersonaJuridicaModel model = personaJuridicaProvider.create(representanteLegal, rep.getCodigoPais(),
                tipoDocumentoModel, rep.getNumeroDocumento(), rep.getRazonSocial(),
                rep.getFechaConstitucion(), TipoEmpresa.valueOf(rep.getTipoEmpresa().toUpperCase()),
                rep.isFinLucro());

        model.setActividadPrincipal(rep.getActividadPrincipal());
        model.setNombreComercial(rep.getNombreComercial());

        model.setUbigeo(rep.getUbigeo());
        model.setDireccion(rep.getDireccion());
        model.setReferencia(rep.getReferencia());
        model.setTelefono(rep.getTelefono());
        model.setCelular(rep.getCelular());
        model.setEmail(rep.getEmail());

        model.commit();
        return model;
    }

    public AccionistaModel createAccionista(AccionistaRepresentation rep,
            PersonaJuridicaModel personaJuridica, PersonaNaturalModel personaNatural,
            AccionistaProvider accionistaProvider) {

        AccionistaModel model = accionistaProvider.create(personaJuridica, personaNatural,
                rep.getPorcentajeParticipacion());
        return model;
    }
}
