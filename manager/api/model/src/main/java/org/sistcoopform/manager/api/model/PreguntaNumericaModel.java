package org.sistcoopform.manager.api.model;

import org.sistcoopform.manager.api.model.enums.TipoPreguntaNumero;

public interface PreguntaNumericaModel extends PreguntaModel {
    String OBLIGATORIO = "obligatorio";
    String TIPO_PREGUNTA = "tipoPregunta";

    boolean isObligatorio();

    void setObligatorio(boolean obligatorio);

    TipoPreguntaNumero getTipoPregunta();

    void setTipoPregunta(TipoPreguntaNumero tipoPregunta);

}