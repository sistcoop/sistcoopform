package org.repeid.models.jpa;

import javax.persistence.EntityManager;

import org.repeid.models.jpa.entities.SeccionEntity;
import org.sistcoopform.models.FormularioModel;
import org.sistcoopform.models.SeccionModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class SeccionAdapter implements SeccionModel {

    private static final long serialVersionUID = 1L;

    private SeccionEntity seccionEntity;
    private EntityManager em;

    public SeccionAdapter(EntityManager em, SeccionEntity seccionEntity) {
        this.em = em;
        this.seccionEntity = seccionEntity;
    }

    public static SeccionEntity toFormularioEntity(SeccionModel model, EntityManager em) {
        if (model instanceof SeccionAdapter) {
            return ((SeccionAdapter) model).getSeccionEntity();
        }
        return em.getReference(SeccionEntity.class, model.getId());
    }

    public SeccionEntity getSeccionEntity() {
        return seccionEntity;
    }

    @Override
    public void commit() {
        em.merge(seccionEntity);
    }

    @Override
    public String getId() {
        return seccionEntity.getId();
    }

    @Override
    public String getTitulo() {
        return seccionEntity.getTitulo();
    }

    @Override
    public void setTitulo(String titulo) {
        seccionEntity.setTitulo(titulo);
    }

    @Override
    public String getDescripcion() {
        return seccionEntity.getDescripcion();
    }

    @Override
    public void setDescripcion(String descripcion) {
        seccionEntity.setDescripcion(descripcion);
    }

    @Override
    public int getNumero() {
        return seccionEntity.getOrden();
    }

    @Override
    public void setNumero(int numero) {
        seccionEntity.setOrden(numero);
    }

    @Override
    public FormularioModel getFormulario() {
        return new FormularioAdapter(em, seccionEntity.getFormulario());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof SeccionModel))
            return false;
        SeccionModel other = (SeccionModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
