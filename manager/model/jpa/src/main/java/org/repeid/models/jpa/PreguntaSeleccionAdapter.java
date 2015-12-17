package org.repeid.models.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.repeid.models.jpa.entities.OpcionSeleccionEntity;
import org.repeid.models.jpa.entities.PreguntaSeleccionEntity;
import org.sistcoopform.models.OpcionSeleccionModel;
import org.sistcoopform.models.PreguntaModel;
import org.sistcoopform.models.PreguntaSeleccionModel;
import org.sistcoopform.models.SeccionModel;
import org.sistcoopform.models.enums.TipoPreguntaSeleccion;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class PreguntaSeleccionAdapter implements PreguntaSeleccionModel {

    private static final long serialVersionUID = 1L;

    private PreguntaSeleccionEntity preguntaSeleccionEntity;
    private EntityManager em;

    public PreguntaSeleccionAdapter(EntityManager em, PreguntaSeleccionEntity preguntaSeleccionEntity) {
        this.em = em;
        this.preguntaSeleccionEntity = preguntaSeleccionEntity;
    }

    public static PreguntaSeleccionEntity toPreguntaTiempoEntity(PreguntaSeleccionModel model,
            EntityManager em) {
        if (model instanceof PreguntaSeleccionAdapter) {
            return ((PreguntaSeleccionAdapter) model).getPreguntaSeleccionEntity();
        }
        return em.getReference(PreguntaSeleccionEntity.class, model.getId());
    }

    public PreguntaSeleccionEntity getPreguntaSeleccionEntity() {
        return preguntaSeleccionEntity;
    }

    @Override
    public void commit() {
        em.merge(preguntaSeleccionEntity);
    }

    @Override
    public String getId() {
        return preguntaSeleccionEntity.getId();
    }

    @Override
    public String getTitulo() {
        return preguntaSeleccionEntity.getTitulo();
    }

    @Override
    public void setTitulo(String titulo) {
        preguntaSeleccionEntity.setTitulo(titulo);
    }

    @Override
    public String getDescripcion() {
        return preguntaSeleccionEntity.getDescripcion();
    }

    @Override
    public void setDescripcion(String descripcion) {
        preguntaSeleccionEntity.setDescripcion(descripcion);
    }

    @Override
    public int getNumero() {
        return preguntaSeleccionEntity.getNumero();
    }

    @Override
    public void setNumero(int numero) {
        preguntaSeleccionEntity.setNumero(numero);
    }

    @Override
    public boolean isObligatorio() {
        return preguntaSeleccionEntity.isObligatorio();
    }

    @Override
    public void setObligatorio(boolean obligatorio) {
        preguntaSeleccionEntity.setObligatorio(obligatorio);
    }

    @Override
    public TipoPreguntaSeleccion getTipoPregunta() {
        return TipoPreguntaSeleccion.valueOf(preguntaSeleccionEntity.getTipoPregunta());
    }

    @Override
    public void setTipoPregunta(TipoPreguntaSeleccion tipoPregunta) {
        if (tipoPregunta != null) {
            preguntaSeleccionEntity.setTipoPregunta(tipoPregunta.toString());
        } else {
            preguntaSeleccionEntity.setTipoPregunta(null);
        }
    }

    @Override
    public List<OpcionSeleccionModel> getOpciones() {
        Set<OpcionSeleccionEntity> opcionesEntity = preguntaSeleccionEntity.getOpciones();
        List<OpcionSeleccionModel> models = new ArrayList<>();
        for (OpcionSeleccionEntity entity : opcionesEntity) {
            models.add(new OpcionSeleccionAdapter(em, entity));
        }
        return models;
    }

    @Override
    public SeccionModel getSeccion() {
        return new SeccionAdapter(em, preguntaSeleccionEntity.getSeccion());
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
