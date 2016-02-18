package org.sistcoopform.manager.api.model;

import java.util.List;

public interface PreguntaCuadriculaOpcionesModel extends PreguntaModel {

    String REQUIERE_RESPUESTA_POR_FILA = "requiereRespuestaPorFila";

    boolean isRequiereRespuestaPorFila();

    void setRequiereRespuestaPorFila(boolean requiereRespuestaPorFila);

    List<FilaCuadriculaModel> getFilas();

    List<ColumnaCuadriculaModel> getColumnas();

}