package org.repeid.models.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.repeid.models.jpa.entities.ColumnaCuadriculaEntity;
import org.repeid.models.jpa.entities.FilaCuadriculaEntity;
import org.repeid.models.jpa.entities.PreguntaCuadriculaOpcionesEntity;
import org.sistcoopform.models.ColumnaCuadriculaModel;
import org.sistcoopform.models.FilaCuadriculaModel;
import org.sistcoopform.models.PreguntaCuadriculaOpcionesModel;
import org.sistcoopform.models.PreguntaNumericaModel;
import org.sistcoopform.models.SeccionModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class PreguntaCuadriculaOpcionesAdapter implements PreguntaCuadriculaOpcionesModel {

    private static final long serialVersionUID = 1L;

    private PreguntaCuadriculaOpcionesEntity preguntaCuadriculaOpcionesEntity;
    private EntityManager em;

    public PreguntaCuadriculaOpcionesAdapter(EntityManager em,
            PreguntaCuadriculaOpcionesEntity preguntaCuadriculaOpcionesEntity) {
        this.em = em;
        this.preguntaCuadriculaOpcionesEntity = preguntaCuadriculaOpcionesEntity;
    }

    public static PreguntaCuadriculaOpcionesEntity toPreguntaTiempoEntity(
            PreguntaCuadriculaOpcionesModel model, EntityManager em) {
        if (model instanceof PreguntaCuadriculaOpcionesAdapter) {
            return ((PreguntaCuadriculaOpcionesAdapter) model).getPreguntaCuadriculaOpcionesEntity();
        }
        return em.getReference(PreguntaCuadriculaOpcionesEntity.class, model.getId());
    }

    public PreguntaCuadriculaOpcionesEntity getPreguntaCuadriculaOpcionesEntity() {
        return preguntaCuadriculaOpcionesEntity;
    }

    @Override
    public void commit() {
        em.merge(preguntaCuadriculaOpcionesEntity);
    }

    @Override
    public String getId() {
        return preguntaCuadriculaOpcionesEntity.getId();
    }

    @Override
    public String getTitulo() {
        return preguntaCuadriculaOpcionesEntity.getTitulo();
    }

    @Override
    public void setTitulo(String titulo) {
        preguntaCuadriculaOpcionesEntity.setTitulo(titulo);
    }

    @Override
    public String getDescripcion() {
        return preguntaCuadriculaOpcionesEntity.getDescripcion();
    }

    @Override
    public void setDescripcion(String descripcion) {
        preguntaCuadriculaOpcionesEntity.setDescripcion(descripcion);
    }

    @Override
    public int getNumero() {
        return preguntaCuadriculaOpcionesEntity.getNumero();
    }

    @Override
    public void setNumero(int numero) {
        preguntaCuadriculaOpcionesEntity.setNumero(numero);
    }

    @Override
    public boolean isRequiereRespuestaPorFila() {
        return preguntaCuadriculaOpcionesEntity.isRequiereRespuestaPorFila();
    }

    @Override
    public void setRequiereRespuestaPorFila(boolean requiereRespuestaPorFila) {
        preguntaCuadriculaOpcionesEntity.setRequiereRespuestaPorFila(requiereRespuestaPorFila);
    }

    @Override
    public SeccionModel getSeccion() {
        return new SeccionAdapter(em, preguntaCuadriculaOpcionesEntity.getSeccion());
    }

    @Override
    public List<FilaCuadriculaModel> getFilas() {
        Set<FilaCuadriculaEntity> filas = preguntaCuadriculaOpcionesEntity.getFilas();
        List<FilaCuadriculaModel> models = new ArrayList<>();
        for (FilaCuadriculaEntity entity : filas) {
            models.add(new FilaCuadriculaAdapter(em, entity));
        }
        return models;
    }

    @Override
    public List<ColumnaCuadriculaModel> getColumnas() {
        Set<ColumnaCuadriculaEntity> filas = preguntaCuadriculaOpcionesEntity.getColumnas();
        List<ColumnaCuadriculaModel> models = new ArrayList<>();
        for (ColumnaCuadriculaEntity entity : filas) {
            models.add(new ColumnaCuadriculaAdapter(em, entity));
        }
        return models;
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
