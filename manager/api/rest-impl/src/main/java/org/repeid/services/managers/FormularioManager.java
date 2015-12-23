package org.repeid.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.repeid.representations.idm.FormularioRepresentation;
import org.sistcoopform.models.FormularioModel;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class FormularioManager {

    public void update(FormularioModel model, FormularioRepresentation rep) {
        model.setTitulo(rep.getTitulo());
        model.setDescripcion(rep.getDescripcion());
        model.commit();
    }

}