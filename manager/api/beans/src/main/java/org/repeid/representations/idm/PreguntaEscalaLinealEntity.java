package org.repeid.representations.idm;

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
@Table(name = "PREGUNTA_ESCALA_LINEAL")
@DiscriminatorValue("escala_lineal")
public class PreguntaEscalaLinealEntity extends PreguntaEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @NotNull
    @Type(type = "org.hibernate.type.TrueFalseType")
    @Column(name = "OBLIGATORIO")
    private boolean obligatorio;

    @Size(min = 0, max = 100)
    @Column(name = "ETIQUETA1")
    private String etiqueta1;

    @Size(min = 0, max = 100)
    @Column(name = "ETIQUETA2")
    private String etiqueta2;

    @NotNull
    @Column(name = "DESDE")
    private int desde;

    @NotNull
    @Column(name = "HASTA")
    private int hasta;

    public boolean isObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
    }

    public String getEtiqueta1() {
        return etiqueta1;
    }

    public void setEtiqueta1(String etiqueta1) {
        this.etiqueta1 = etiqueta1;
    }

    public String getEtiqueta2() {
        return etiqueta2;
    }

    public void setEtiqueta2(String etiqueta2) {
        this.etiqueta2 = etiqueta2;
    }

    public int getDesde() {
        return desde;
    }

    public void setDesde(int desde) {
        this.desde = desde;
    }

    public int getHasta() {
        return hasta;
    }

    public void setHasta(int hasta) {
        this.hasta = hasta;
    }

}
