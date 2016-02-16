package org.repeid.models.jpa;

import javax.persistence.EntityManager;

import org.repeid.models.jpa.entities.PreguntaNumericaEntity;
import org.sistcoopform.models.PreguntaNumericaModel;
import org.sistcoopform.models.SeccionModel;
import org.sistcoopform.models.enums.TipoPreguntaNumero;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class PreguntaNumericaAdapter implements PreguntaNumericaModel {

    private static final long serialVersionUID = 1L;

    private PreguntaNumericaEntity preguntaNumericaEntity;
    private EntityManager em;

    public PreguntaNumericaAdapter(EntityManager em, PreguntaNumericaEntity preguntaNumericaEntity) {
        this.em = em;
        this.preguntaNumericaEntity = preguntaNumericaEntity;
    }

    public static PreguntaNumericaEntity toPreguntaTiempoEntity(PreguntaNumericaModel model,
            EntityManager em) {
        if (model instanceof PreguntaNumericaAdapter) {
            return ((PreguntaNumericaAdapter) model).getPreguntaNumericaEntity();
        }
        return em.getReference(PreguntaNumericaEntity.class, model.getId());
    }

    public PreguntaNumericaEntity getPreguntaNumericaEntity() {
        return preguntaNumericaEntity;
    }

    @Override
    public void commit() {
        em.merge(preguntaNumericaEntity);
    }

    @Override
    public String getId() {
        return preguntaNumericaEntity.getId();
    }

    @Override
    public String getTitulo() {
        return preguntaNumericaEntity.getTitulo();
    }

    @Override
    public void setTitulo(String titulo) {
        preguntaNumericaEntity.setTitulo(titulo);
    }

    @Override
    public String getDescripcion() {
        return preguntaNumericaEntity.getDescripcion();
    }

    @Override
    public void setDescripcion(String descripcion) {
        preguntaNumericaEntity.setDescripcion(descripcion);
    }

    @Override
    public int getNumero() {
        return preguntaNumericaEntity.getNumero();
    }

    @Override
    public void setNumero(int numero) {
        preguntaNumericaEntity.setNumero(numero);
    }

    @Override
    public boolean isObligatorio() {
        return preguntaNumericaEntity.isObligatorio();
    }

    @Override
    public void setObligatorio(boolean obligatorio) {
        preguntaNumericaEntity.setObligatorio(obligatorio);
    }

    @Override
    public TipoPreguntaNumero getTipoPregunta() {
        return TipoPreguntaNumero.valueOf(preguntaNumericaEntity.getTipoPregunta());
    }

    @Override
    public void setTipoPregunta(TipoPreguntaNumero tipoPregunta) {
        if (tipoPregunta != null) {
            preguntaNumericaEntity.setTipoPregunta(tipoPregunta.toString());
        } else {
            preguntaNumericaEntity.setTipoPregunta(null);
        }
    }

    @Override
    public SeccionModel getSeccion() {
        return new SeccionAdapter(em, preguntaNumericaEntity.getSeccion());
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
        if (!(obj instanceof PreguntaNumericaModel))
            return false;
        PreguntaNumericaModel other = (PreguntaNumericaModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
