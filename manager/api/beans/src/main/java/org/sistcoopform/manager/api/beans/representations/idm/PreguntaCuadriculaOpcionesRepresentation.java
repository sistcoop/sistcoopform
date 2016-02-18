package org.sistcoopform.manager.api.beans.representations.idm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class PreguntaCuadriculaOpcionesRepresentation extends PreguntaRepresentation {

    private boolean requiereRespuestaPorFila;
    private Set<FilaCuadriculaRepresentation> filas = new HashSet<FilaCuadriculaRepresentation>();
    private Set<ColumnaCuadriculaEntity> columnas = new HashSet<ColumnaCuadriculaEntity>();

    public boolean isRequiereRespuestaPorFila() {
        return requiereRespuestaPorFila;
    }

    public void setRequiereRespuestaPorFila(boolean requiereRespuestaPorFila) {
        this.requiereRespuestaPorFila = requiereRespuestaPorFila;
    }

    public Set<FilaCuadriculaRepresentation> getFilas() {
        return filas;
    }

    public void setFilas(Set<FilaCuadriculaRepresentation> filas) {
        this.filas = filas;
    }

    public Set<ColumnaCuadriculaEntity> getColumnas() {
        return columnas;
    }

    public void setColumnas(Set<ColumnaCuadriculaEntity> columnas) {
        this.columnas = columnas;
    }

}
