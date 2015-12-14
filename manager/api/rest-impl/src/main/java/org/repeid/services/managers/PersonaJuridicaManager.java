package org.repeid.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.repeid.representations.idm.PersonaJuridicaRepresentation;
import org.repeid.representations.idm.PersonaNaturalRepresentation;
import org.sistcoopform.models.PersonaJuridicaModel;
import org.sistcoopform.models.PersonaNaturalModel;
import org.sistcoopform.models.PersonaNaturalProvider;
import org.sistcoopform.models.FormularioModel;
import org.sistcoopform.models.FormularioProvider;
import org.sistcoopform.models.enums.TipoEmpresa;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PersonaJuridicaManager {

    @Inject
    private FormularioProvider tipoDocumentoProvider;

    @Inject
    private PersonaNaturalProvider personaNaturalProvider;

    public void update(PersonaJuridicaModel model, PersonaJuridicaRepresentation rep) {
        model.setCodigoPais(rep.getCodigoPais());
        model.setRazonSocial(rep.getRazonSocial());
        model.setFechaConstitucion(rep.getFechaConstitucion());
        model.setActividadPrincipal(rep.getActividadPrincipal());
        model.setNombreComercial(rep.getNombreComercial());
        model.setFinLucro(rep.isFinLucro());
        model.setTipoEmpresa(TipoEmpresa.valueOf(rep.getTipoEmpresa()));

        model.setUbigeo(rep.getUbigeo());
        model.setDireccion(rep.getDireccion());
        model.setReferencia(rep.getReferencia());
        model.setTelefono(rep.getTelefono());
        model.setCelular(rep.getCelular());
        model.setEmail(rep.getEmail());

        PersonaNaturalRepresentation representanteRep = rep.getRepresentanteLegal();
        if (representanteRep != null) {
            PersonaNaturalRepresentation representanteRepresentation = rep.getRepresentanteLegal();
            FormularioModel tipoDocumentoRepresentanteModel = tipoDocumentoProvider
                    .findByAbreviatura(representanteRepresentation.getTipoDocumento());
            PersonaNaturalModel representanteModel = personaNaturalProvider.findByTipoNumeroDocumento(
                    tipoDocumentoRepresentanteModel, representanteRepresentation.getNumeroDocumento());

            model.setRepresentanteLegal(representanteModel);
        }

        model.commit();
    }

}