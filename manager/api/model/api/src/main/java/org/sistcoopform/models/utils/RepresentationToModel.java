package org.sistcoopform.models.utils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.repeid.representations.idm.FormularioRepresentation;
import org.sistcoopform.models.FormularioModel;
import org.sistcoopform.models.FormularioProvider;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RepresentationToModel {

    public FormularioModel createFormulario(FormularioRepresentation rep,
            FormularioProvider formularioProvider) {
        return formularioProvider.create(rep.getTitulo(), rep.getDescripcion());
    }

}
