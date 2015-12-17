package org.sistcoopform.models;

public interface PreguntaCuadriculaOpcionesModel extends PreguntaModel {

    String REQUIERE_RESPUESTA_POR_FILA = "requiereRespuestaPorFila";

    boolean isRequiereRespuestaPorFila();

    void setRequiereRespuestaPorFila(boolean requiereRespuestaPorFila);

}