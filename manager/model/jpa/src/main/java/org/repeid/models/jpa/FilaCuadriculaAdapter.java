package org.repeid.models.jpa;

import javax.persistence.EntityManager;

import org.repeid.models.jpa.entities.FilaCuadriculaEntity;
import org.sistcoopform.models.FilaCuadriculaModel;
import org.sistcoopform.models.OpcionSeleccionModel;
import org.sistcoopform.models.PreguntaCuadriculaOpcionesModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class FilaCuadriculaAdapter implements FilaCuadriculaModel {

    private static final long serialVersionUID = 1L;

    private FilaCuadriculaEntity filaCuadriculaEntity;
    private EntityManager em;

    public FilaCuadriculaAdapter(EntityManager em, FilaCuadriculaEntity filaCuadriculaEntity) {
        this.em = em;
        this.filaCuadriculaEntity = filaCuadriculaEntity;
    }

    public static FilaCuadriculaEntity toPreguntaTiempoEntity(FilaCuadriculaModel model, EntityManager em) {
        if (model instanceof FilaCuadriculaAdapter) {
            return ((FilaCuadriculaAdapter) model).getFilaCuadriculaEntity();
        }
        return em.getReference(FilaCuadriculaEntity.class, model.getId());
    }

    public FilaCuadriculaEntity getFilaCuadriculaEntity() {
        return filaCuadriculaEntity;
    }

    @Override
    public void commit() {
        em.merge(filaCuadriculaEntity);
    }

    @Override
    public String getId() {
        return filaCuadriculaEntity.getId();
    }

    @Override
    public String getDenominacion() {
        return filaCuadriculaEntity.getDenominacion();
    }

    @Override
    public void setDenominacion(String denominacion) {
        filaCuadriculaEntity.setDenominacion(denominacion);
    }

    @Override
    public int getNumero() {
        return filaCuadriculaEntity.getNumero();
    }

    @Override
    public void setNumero(int numero) {
        filaCuadriculaEntity.setNumero(numero);
    }

    @Override
    public boolean isEditable() {
        return filaCuadriculaEntity.isEditable();
    }

    @Override
    public void setEditable(boolean editable) {
        filaCuadriculaEntity.setEditable(editable);
    }

    @Override
    public PreguntaCuadriculaOpcionesModel getPreguntaCuadricula() {
        return new PreguntaCuadriculaOpcionesAdapter(em, filaCuadriculaEntity.getPreguntaCuadricula());
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
