package org.sistcoopform.models;

public interface SeccionModel extends Model {

    String TITULO = "titulo";
    String DESCRIPCION = "descripcion";
    String NUMERO = "numero";

    String getId();

    String getTitulo();

    void setTitulo(String titulo);

    String getDescripcion();

    void setDescripcion(String descripcion);

    int getNumero();

    void setNumero(int numero);

    FormularioModel getFormulario();

}