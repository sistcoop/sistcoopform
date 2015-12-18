package org.repeid.models.jpa;

import javax.persistence.EntityManager;

import org.repeid.models.jpa.entities.ColumnaCuadriculaEntity;
import org.sistcoopform.models.ColumnaCuadriculaModel;
import org.sistcoopform.models.OpcionSeleccionModel;
import org.sistcoopform.models.PreguntaCuadriculaOpcionesModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class ColumnaCuadriculaAdapter implements ColumnaCuadriculaModel {

    private static final long serialVersionUID = 1L;

    private ColumnaCuadriculaEntity columnaCuadriculaEntity;
    private EntityManager em;

    public ColumnaCuadriculaAdapter(EntityManager em, ColumnaCuadriculaEntity columnaCuadriculaEntity) {
        this.em = em;
        this.columnaCuadriculaEntity = columnaCuadriculaEntity;
    }

    public static ColumnaCuadriculaEntity toColumnaCuadriculaEntity(ColumnaCuadriculaModel model,
            EntityManager em) {
        if (model instanceof ColumnaCuadriculaAdapter) {
            return ((ColumnaCuadriculaAdapter) model).getColumnaCuadriculaEntity();
        }
        return em.getReference(ColumnaCuadriculaEntity.class, model.getId());
    }

    public ColumnaCuadriculaEntity getColumnaCuadriculaEntity() {
        return columnaCuadriculaEntity;
    }

    @Override
    public void commit() {
        em.merge(columnaCuadriculaEntity);
    }

    @Override
    public String getId() {
        return columnaCuadriculaEntity.getId();
    }

    @Override
    public String getDenominacion() {
        return columnaCuadriculaEntity.getDenominacion();
    }

    @Override
    public void setDenominacion(String denominacion) {
        columnaCuadriculaEntity.setDenominacion(denominacion);
    }

    @Override
    public int getNumero() {
        return columnaCuadriculaEntity.getNumero();
    }

    @Override
    public void setNumero(int numero) {
        columnaCuadriculaEntity.setNumero(numero);
    }

    @Override
    public boolean isEditable() {
        return columnaCuadriculaEntity.isEditable();
    }

    @Override
    public void setEditable(boolean editable) {
        columnaCuadriculaEntity.setEditable(editable);
    }

    @Override
    public PreguntaCuadriculaOpcionesModel getPreguntaCuadricula() {
        return new PreguntaCuadriculaOpcionesAdapter(em, columnaCuadriculaEntity.getPreguntaCuadricula());
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
