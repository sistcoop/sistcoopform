package org.sistcoopform.manager.api.model;

public interface PreguntaEscalaLinealModel extends PreguntaModel {

    String OBLIGATORIO = "obligatorio";
    String ETIQUETA1 = "etiqueta1";
    String ETIQUETA2 = "etiqueta2";
    String desde = "desde";
    String hasta = "hasta";

    boolean isObligatorio();

    void setObligatorio(boolean obligatorio);

    String getEtiqueta1();

    void setEtiqueta1(String etiqueta);

    String getEtiqueta2();

    void setEtiqueta2(String etiqueta);

    int getDesde();

    void setDesde(int desde);

    int getHasta();

    void setHasta(int hasta);

}