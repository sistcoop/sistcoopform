package org.sistcoopform.models.utils;

import org.repeid.representations.idm.FormularioRepresentation;
import org.sistcoopform.models.FormularioModel;

public class ModelToRepresentation {

    public static FormularioRepresentation toRepresentation(FormularioModel model) {
        if (model == null)
            return null;

        FormularioRepresentation rep = new FormularioRepresentation();
        rep.setId(model.getId());
        rep.setTitulo(model.getTitulo());
        rep.setDescripcion(model.getDescripcion());
        return rep;
    }

}
