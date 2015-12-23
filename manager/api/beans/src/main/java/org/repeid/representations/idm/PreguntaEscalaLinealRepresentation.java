package org.repeid.representations.idm;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class PreguntaEscalaLinealRepresentation extends PreguntaRepresentation {

    private boolean obligatorio;
    private String etiqueta1;
    private String etiqueta2;
    private int desde;
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
