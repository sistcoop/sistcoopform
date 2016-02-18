package org.sistcoopform.manager.api.model;

import java.util.List;

import org.sistcoopform.manager.api.model.enums.TipoPreguntaSeleccion;

public interface PreguntaSeleccionModel extends PreguntaModel {

    String OBLIGATORIO = "obligatorio";
    String TIPO_PREGUNTA = "tipoPregunta";

    boolean isObligatorio();

    void setObligatorio(boolean obligatorio);

    TipoPreguntaSeleccion getTipoPregunta();

    void setTipoPregunta(TipoPreguntaSeleccion tipoPregunta);

    List<OpcionSeleccionModel> getOpciones();

}