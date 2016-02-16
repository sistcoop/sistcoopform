package org.sistcoopform.models;

import org.sistcoopform.models.enums.TipoPreguntaTexto;

public interface PreguntaTextoModel extends PreguntaModel {

    String OBLIGATORIO = "obligatorio";
    String TIPO_PREGUNTA = "tipoPregunta";

    boolean isObligatorio();

    void setObligatorio(boolean obligatorio);

    TipoPreguntaTexto getTipoPregunta();

    void setTipoPregunta(TipoPreguntaTexto tipoPregunta);

}