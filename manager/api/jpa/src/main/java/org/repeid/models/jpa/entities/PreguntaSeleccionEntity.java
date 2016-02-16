package org.repeid.models.jpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Entity
@Table(name = "PREGUNTA_SELECCION")
@DiscriminatorValue("seleccion")
public class PreguntaSeleccionEntity extends PreguntaEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @NotNull
    @Type(type = "org.hibernate.type.TrueFalseType")
    @Column(name = "OBLIGATORIO")
    private boolean obligatorio;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TIPO_PREGUNTA")
    private String tipoPregunta;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "preguntaSeleccion", orphanRemoval = true, cascade = {
            CascadeType.REMOVE })
    private Set<OpcionSeleccionEntity> opciones = new HashSet<OpcionSeleccionEntity>();

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

    public Set<OpcionSeleccionEntity> getOpciones() {
        return opciones;
    }

    public void setOpciones(Set<OpcionSeleccionEntity> opciones) {
        this.opciones = opciones;
    }

}
