package org.repeid.models.jpa;

import javax.persistence.EntityManager;

import org.repeid.models.jpa.entities.OpcionSeleccionEntity;
import org.sistcoopform.models.OpcionSeleccionModel;
import org.sistcoopform.models.PreguntaSeleccionModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class OpcionSeleccionAdapter implements OpcionSeleccionModel {

    private static final long serialVersionUID = 1L;

    private OpcionSeleccionEntity opcionSeleccionEntity;
    private EntityManager em;

    public OpcionSeleccionAdapter(EntityManager em, OpcionSeleccionEntity opcionSeleccionEntity) {
        this.em = em;
        this.opcionSeleccionEntity = opcionSeleccionEntity;
    }

    public static OpcionSeleccionEntity toPreguntaTiempoEntity(OpcionSeleccionModel model, EntityManager em) {
        if (model instanceof OpcionSeleccionAdapter) {
            return ((OpcionSeleccionAdapter) model).getOpcionSeleccionEntity();
        }
        return em.getReference(OpcionSeleccionEntity.class, model.getId());
    }

    public OpcionSeleccionEntity getOpcionSeleccionEntity() {
        return opcionSeleccionEntity;
    }

    @Override
    public void commit() {
        em.merge(opcionSeleccionEntity);
    }

    @Override
    public String getId() {
        return opcionSeleccionEntity.getId();
    }

    @Override
    public String getDenominacion() {
        return opcionSeleccionEntity.getDenominacion();
    }

    @Override
    public void setDenominacion(String denominacion) {
        opcionSeleccionEntity.setDenominacion(denominacion);
    }

    @Override
    public int getNumero() {
        return opcionSeleccionEntity.getNumero();
    }

    @Override
    public void setNumero(int numero) {
        opcionSeleccionEntity.setNumero(numero);
    }

    @Override
    public boolean isEditable() {
        return opcionSeleccionEntity.isEditable();
    }

    @Override
    public void setEditable(boolean editable) {
        opcionSeleccionEntity.setEditable(editable);
    }

    @Override
    public PreguntaSeleccionModel getPreguntaSeleccion() {
        return new PreguntaSeleccionAdapter(em, opcionSeleccionEntity.getPreguntaSeleccion());
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
        if (!(obj instanceof OpcionSeleccionModel))
            return false;
        OpcionSeleccionModel other = (OpcionSeleccionModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
