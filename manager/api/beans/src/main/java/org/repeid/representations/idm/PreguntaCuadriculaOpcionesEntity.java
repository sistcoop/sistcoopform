package org.repeid.representations.idm;

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

import org.hibernate.annotations.Type;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Entity
@Table(name = "PREGUNTA_CUADRICULA_OPCIONES")
@DiscriminatorValue("cuadricula_opciones")
public class PreguntaCuadriculaOpcionesEntity extends PreguntaEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @NotNull
    @Type(type = "org.hibernate.type.TrueFalseType")
    @Column(name = "REQUIERE_RESPUESTA_POR_FILA")
    private boolean requiereRespuestaPorFila;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "preguntaCuadricula", orphanRemoval = true, cascade = {
            CascadeType.REMOVE })
    private Set<FilaCuadriculaEntity> filas = new HashSet<FilaCuadriculaEntity>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "preguntaCuadricula", orphanRemoval = true, cascade = {
            CascadeType.REMOVE })
    private Set<ColumnaCuadriculaEntity> columnas = new HashSet<ColumnaCuadriculaEntity>();

    public boolean isRequiereRespuestaPorFila() {
        return requiereRespuestaPorFila;
    }

    public void setRequiereRespuestaPorFila(boolean requiereRespuestaPorFila) {
        this.requiereRespuestaPorFila = requiereRespuestaPorFila;
    }

    public Set<FilaCuadriculaEntity> getFilas() {
        return filas;
    }

    public void setFilas(Set<FilaCuadriculaEntity> filas) {
        this.filas = filas;
    }

    public Set<ColumnaCuadriculaEntity> getColumnas() {
        return columnas;
    }

    public void setColumnas(Set<ColumnaCuadriculaEntity> columnas) {
        this.columnas = columnas;
    }

}
