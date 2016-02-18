package org.sistcoopform.manager.api.beans.representations.idm;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class SectionRepresentation {

    private String id;
    private String titulo;
    private String descripcion;
    private int numero;
    private FormRepresentation formulario;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public FormRepresentation getFormulario() {
        return formulario;
    }

    public void setFormulario(FormRepresentation formulario) {
        this.formulario = formulario;
    }

}
