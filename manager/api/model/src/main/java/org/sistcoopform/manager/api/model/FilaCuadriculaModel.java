package org.sistcoopform.manager.api.model;

public interface FilaCuadriculaModel extends Model {

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

    PreguntaCuadriculaOpcionesModel getPreguntaCuadricula();

}