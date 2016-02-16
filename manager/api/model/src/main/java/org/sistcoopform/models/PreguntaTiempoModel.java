package org.sistcoopform.models;

import org.sistcoopform.models.enums.TipoPreguntaTiempo;

public interface PreguntaTiempoModel extends PreguntaModel {

    String OBLIGATORIO = "obligatorio";
    String TIPO_PREGUNTA = "tipoPregunta";

    boolean isObligatorio();

    void setObligatorio(boolean obligatorio);

    TipoPreguntaTiempo getTipoPregunta();

    void setTipoPregunta(TipoPreguntaTiempo tipoPregunta);

}