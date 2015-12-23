package org.repeid.representations.idm;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public abstract class PreguntaRepresentation {

    private String id;
    private String titulo;
    private String descripcion;
    private int numero;
    private SeccionRepresentation seccion;

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

    public SeccionRepresentation getSeccion() {
        return seccion;
    }

    public void setSeccion(SeccionRepresentation seccion) {
        this.seccion = seccion;
    }

}
