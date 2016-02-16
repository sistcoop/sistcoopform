package org.sistcoopform.models;

public interface OpcionSeleccionModel extends Model {

    String DENOMINACION = "denominacion";
    String NUMERO = "numero";
    String EDITABLE = "editable";

    String getId();
    
    String getDenominacion();

    void setDenominacion(String denominacion);

    int getNumero();

    void setNumero(int numero);

    boolean isEditable();

    void setEditable(boolean editable);

    PreguntaSeleccionModel getPreguntaSeleccion();

}