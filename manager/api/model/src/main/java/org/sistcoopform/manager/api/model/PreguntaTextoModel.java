package org.sistcoopform.manager.api.model;

import org.sistcoopform.manager.api.model.enums.TipoPreguntaTexto;

public interface PreguntaTextoModel extends PreguntaModel {

    String OBLIGATORIO = "obligatorio";
    String TIPO_PREGUNTA = "tipoPregunta";

    boolean isObligatorio();

    void setObligatorio(boolean obligatorio);

    TipoPreguntaTexto getTipoPregunta();

    void setTipoPregunta(TipoPreguntaTexto tipoPregunta);

}