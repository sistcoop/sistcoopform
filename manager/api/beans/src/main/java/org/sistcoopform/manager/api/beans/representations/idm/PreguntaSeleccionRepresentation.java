package org.sistcoopform.manager.api.beans.representations.idm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class PreguntaSeleccionRepresentation extends PreguntaRepresentation {

    private boolean obligatorio;
    private String tipoPregunta;
    private Set<OpcionSeleccionRepresentation> opciones = new HashSet<OpcionSeleccionRepresentation>();

    public boolean isObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public Set<OpcionSeleccionRepresentation> getOpciones() {
        return opciones;
    }

    public void setOpciones(Set<OpcionSeleccionRepresentation> opciones) {
        this.opciones = opciones;
    }

}
