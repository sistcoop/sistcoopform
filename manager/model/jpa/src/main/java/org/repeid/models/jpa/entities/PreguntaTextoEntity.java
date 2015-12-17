package org.repeid.models.jpa.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Entity
@Table(name = "PREGUNTA_TEXTO")
@DiscriminatorValue("texto")
public class PreguntaTextoEntity extends PreguntaEntity {

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

}
