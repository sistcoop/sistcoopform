package org.sistcoopform.models;

public interface FormularioModel extends Model {

    String TITULO = "titulo";
    String DESCRIPCION = "descripcion";

    String getId();

    String getTitulo();

    void setTitulo(String titulo);

    String getDescripcion();

    void setDescripcion(String descripcion);

}