package org.sistcoopform.models;

public interface OpcionSeleccionModel extends Model {

    String DENOMINACION = "denominacion";
    String ORDEN = "orden";
    String EDITABLE = "editable";

    String getDenominacion();

    void setDenominacion(String denominacion);

    int getOrden();

    void setOrden(int orden);

    boolean isEditable();

    void setEditable(boolean editable);

    PreguntaSeleccionModel getPreguntaSeleccion();

}