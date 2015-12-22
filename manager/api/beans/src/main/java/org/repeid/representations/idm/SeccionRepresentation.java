package org.repeid.representations.idm;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class SeccionRepresentation {

    private String id;
    private String titulo;
    private String descripcion;
    private int numero;
    private FormularioRepresentation formulario;

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

    public FormularioRepresentation getFormulario() {
        return formulario;
    }

    public void setFormulario(FormularioRepresentation formulario) {
        this.formulario = formulario;
    }

}
