package org.sistcoopform.models;

import org.sistcoopform.models.enums.TipoPreguntaTemporal;
import org.sistcoopform.models.enums.TipoPreguntaTexto;

public interface PreguntaTiempoModel extends PreguntaModel {

    String OBLIGATORIO = "obligatorio";
    String TIPO_PREGUNTA = "tipoPregunta";

    boolean isObligatorio();

    void setObligatorio();

    TipoPreguntaTexto getTipoPregunta();

    void setTipoPregunta(TipoPreguntaTemporal tipoPregunta);

}