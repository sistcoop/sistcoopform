package org.sistcoopform.models;

public interface SeccionModel extends Model {

    String TITULO = "titulo";
    String DESCRIPCION = "descripcion";
    String ORDEN = "orden";

    String getId();

    String getTitulo();

    void setTitulo(String titulo);

    String getDescripcion();

    void setDescripcion(String descripcion);

    int getOrden();

    void setOrden(int orden);

    FormularioModel getFormulario();

}