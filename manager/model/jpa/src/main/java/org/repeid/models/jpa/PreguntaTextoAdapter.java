package org.repeid.models.jpa;

import javax.persistence.EntityManager;

import org.repeid.models.jpa.entities.PreguntaTextoEntity;
import org.sistcoopform.models.PreguntaModel;
import org.sistcoopform.models.PreguntaTextoModel;
import org.sistcoopform.models.SeccionModel;
import org.sistcoopform.models.enums.TipoPreguntaTexto;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class PreguntaTextoAdapter implements PreguntaTextoModel {

    private static final long serialVersionUID = 1L;

    private PreguntaTextoEntity preguntaTextoEntity;
    private EntityManager em;

    public PreguntaTextoAdapter(EntityManager em, PreguntaTextoEntity preguntaTextoEntity) {
        this.em = em;
        this.preguntaTextoEntity = preguntaTextoEntity;
    }

    public static PreguntaTextoEntity toPreguntaTiempoEntity(PreguntaTextoModel model, EntityManager em) {
        if (model instanceof PreguntaTextoAdapter) {
            return ((PreguntaTextoAdapter) model).getPreguntaTextoEntity();
        }
        return em.getReference(PreguntaTextoEntity.class, model.getId());
    }

    public PreguntaTextoEntity getPreguntaTextoEntity() {
        return preguntaTextoEntity;
    }

    @Override
    public void commit() {
        em.merge(preguntaTextoEntity);
    }

    @Override
    public String getId() {
        return preguntaTextoEntity.getId();
    }

    @Override
    public String getTitulo() {
        return preguntaTextoEntity.getTitulo();
    }

    @Override
    public void setTitulo(String titulo) {
        preguntaTextoEntity.setTitulo(titulo);
    }

    @Override
    public String getDescripcion() {
        return preguntaTextoEntity.getDescripcion();
    }

    @Override
    public void setDescripcion(String descripcion) {
        preguntaTextoEntity.setDescripcion(descripcion);
    }

    @Override
    public int getNumero() {
        return preguntaTextoEntity.getNumero();
    }

    @Override
    public void setNumero(int numero) {
        preguntaTextoEntity.setNumero(numero);
    }

    @Override
    public boolean isObligatorio() {
        return preguntaTextoEntity.isObligatorio();
    }

    @Override
    public void setObligatorio(boolean obligatorio) {
        preguntaTextoEntity.setObligatorio(obligatorio);
    }

    @Override
    public TipoPreguntaTexto getTipoPregunta() {
        return TipoPreguntaTexto.valueOf(preguntaTextoEntity.getTipoPregunta());
    }

    @Override
    public void setTipoPregunta(TipoPreguntaTexto tipoPregunta) {
        if (tipoPregunta != null) {
            preguntaTextoEntity.setTipoPregunta(tipoPregunta.toString());
        } else {
            preguntaTextoEntity.setTipoPregunta(null);
        }
    }

    @Override
    public SeccionModel getSeccion() {
        return new SeccionAdapter(em, preguntaTextoEntity.getSeccion());
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
        if (!(obj instanceof PreguntaModel))
            return false;
        PreguntaModel other = (PreguntaModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
