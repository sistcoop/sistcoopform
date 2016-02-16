package org.repeid.models.jpa;

import javax.persistence.EntityManager;

import org.repeid.models.jpa.entities.FormularioEntity;
import org.sistcoopform.models.FormularioModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class FormularioAdapter implements FormularioModel {

    private static final long serialVersionUID = 1L;

    private FormularioEntity formularioEntity;
    private EntityManager em;

    public FormularioAdapter(EntityManager em, FormularioEntity formularioEntity) {
        this.em = em;
        this.formularioEntity = formularioEntity;
    }

    public static FormularioEntity toFormularioEntity(FormularioModel model, EntityManager em) {
        if (model instanceof FormularioAdapter) {
            return ((FormularioAdapter) model).getFormularioEntity();
        }
        return em.getReference(FormularioEntity.class, model.getId());
    }

    public FormularioEntity getFormularioEntity() {
        return formularioEntity;
    }

    @Override
    public void commit() {
        em.merge(formularioEntity);
    }

    @Override
    public String getId() {
        return formularioEntity.getId();
    }

    @Override
    public String getTitulo() {
        return formularioEntity.getTitulo();
    }

    @Override
    public void setTitulo(String titulo) {
        formularioEntity.setTitulo(titulo);
    }

    @Override
    public String getDescripcion() {
        return formularioEntity.getDescripcion();
    }

    @Override
    public void setDescripcion(String descripcion) {
        formularioEntity.setDescripcion(descripcion);
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
        if (!(obj instanceof FormularioModel))
            return false;
        FormularioModel other = (FormularioModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
