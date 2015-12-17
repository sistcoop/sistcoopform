package org.repeid.models.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Entity
@Table(name = "OPCION_SELECCION")
public class OpcionSeleccionEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DENOMINACION")
    private String denominacion;

    @NotNull
    @Min(value = 0)
    @Column(name = "ORDEN")
    private int orden;

    @NotNull
    @Type(type = "org.hibernate.type.TrueFalseType")
    @Column(name = "EDITABLE")
    private boolean editable;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PREGUNTA_SELECCION_ID", foreignKey = @ForeignKey )
    private PreguntaSeleccionEntity preguntaSeleccion;

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public PreguntaSeleccionEntity getPreguntaSeleccion() {
        return preguntaSeleccion;
    }

    public void setPreguntaSeleccion(PreguntaSeleccionEntity preguntaSeleccion) {
        this.preguntaSeleccion = preguntaSeleccion;
    }

}
