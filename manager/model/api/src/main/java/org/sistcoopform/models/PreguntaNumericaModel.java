package org.sistcoopform.models;

import org.sistcoopform.models.enums.TipoPreguntaNumero;
import org.sistcoopform.models.enums.TipoPreguntaTexto;

public interface PreguntaNumericaModel extends PreguntaModel {
    String OBLIGATORIO = "obligatorio";
    String TIPO_PREGUNTA = "tipoPregunta";

    boolean isObligatorio();

    void setObligatorio();

    TipoPreguntaTexto getTipoPregunta();

    void setTipoPregunta(TipoPreguntaNumero tipoPregunta);

}