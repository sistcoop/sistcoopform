package org.repeid.models.jpa;

import javax.persistence.EntityManager;

import org.repeid.models.jpa.entities.PreguntaTiempoEntity;
import org.sistcoopform.models.PreguntaModel;
import org.sistcoopform.models.PreguntaTextoModel;
import org.sistcoopform.models.SeccionModel;
import org.sistcoopform.models.enums.TipoPreguntaTexto;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class PreguntaTextoAdapter implements PreguntaTextoModel {

    private static final long serialVersionUID = 1L;

    private PreguntaTiempoEntity preguntaTiempoEntity;
    private EntityManager em;

    public PreguntaTextoAdapter(EntityManager em, PreguntaTiempoEntity preguntaTiempoEntity) {
        this.em = em;
        this.preguntaTiempoEntity = preguntaTiempoEntity;
    }

    public static PreguntaTiempoEntity toPreguntaTiempoEntity(SeccionModel model, EntityManager em) {
        if (model instanceof PreguntaTextoAdapter) {
            return ((PreguntaTextoAdapter) model).getPreguntaTiempoEntity();
        }
        return em.getReference(PreguntaTiempoEntity.class, model.getId());
    }

    public PreguntaTiempoEntity getPreguntaTiempoEntity() {
        return preguntaTiempoEntity;
    }

    @Override
    public void commit() {
        em.merge(preguntaTiempoEntity);
    }

    @Override
    public String getId() {
        return preguntaTiempoEntity.getId();
    }

    @Override
    public String getTitulo() {
        return preguntaTiempoEntity.getTitulo();
    }

    @Override
    public void setTitulo(String titulo) {
        preguntaTiempoEntity.setTitulo(titulo);
    }

    @Override
    public String getDescripcion() {
        return preguntaTiempoEntity.getDescripcion();
    }

    @Override
    public void setDescripcion(String descripcion) {
        preguntaTiempoEntity.setDescripcion(descripcion);
    }

    @Override
    public int getNumero() {
        return preguntaTiempoEntity.getNumero();
    }

    @Override
    public void setNumero(int numero) {
        preguntaTiempoEntity.setNumero(numero);
    }

    @Override
    public boolean isObligatorio() {
        return preguntaTiempoEntity.isObligatorio();
    }

    @Override
    public void setObligatorio(boolean obligatorio) {
        preguntaTiempoEntity.setObligatorio(obligatorio);
    }

    @Override
    public TipoPreguntaTexto getTipoPregunta() {
        return TipoPreguntaTexto.valueOf(preguntaTiempoEntity.getTipoPregunta());
    }

    @Override
    public void setTipoPregunta(TipoPreguntaTexto tipoPregunta) {
        if (tipoPregunta != null) {
            preguntaTiempoEntity.setTipoPregunta(tipoPregunta.toString());
        } else {
            preguntaTiempoEntity.setTipoPregunta(null);
        }
    }

    @Override
    public SeccionModel getSeccion() {
        return new SeccionAdapter(em, preguntaTiempoEntity.getSeccion());
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
