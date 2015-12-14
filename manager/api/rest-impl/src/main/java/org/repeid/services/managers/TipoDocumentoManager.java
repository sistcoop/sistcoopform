package org.repeid.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.repeid.representations.idm.TipoDocumentoRepresentation;
import org.sistcoopform.models.FormularioModel;
import org.sistcoopform.models.enums.TipoPersona;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TipoDocumentoManager {

    public void update(FormularioModel model, TipoDocumentoRepresentation rep) {
        model.setDenominacion(rep.getDenominacion());
        model.setCantidadCaracteres(rep.getCantidadCaracteres());
        model.setTipoPersona(TipoPersona.valueOf(rep.getTipoPersona()));
        model.commit();
    }

    public void enable(FormularioModel model) {
        model.setEstado(true);
        model.commit();
    }

    public void disable(FormularioModel model) {
        model.setEstado(false);
        model.commit();
    }

}