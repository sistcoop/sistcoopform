package org.repeid.models.jpa;

import javax.persistence.EntityManager;

import org.repeid.models.jpa.entities.PreguntaEscalaLinealEntity;
import org.sistcoopform.models.PreguntaEscalaLinealModel;
import org.sistcoopform.models.SeccionModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class PreguntaEscalaLinealAdapter implements PreguntaEscalaLinealModel {

    private static final long serialVersionUID = 1L;

    private PreguntaEscalaLinealEntity preguntaEscalaLinealEntity;
    private EntityManager em;

    public PreguntaEscalaLinealAdapter(EntityManager em,
            PreguntaEscalaLinealEntity preguntaEscalaLinealEntity) {
        this.em = em;
        this.preguntaEscalaLinealEntity = preguntaEscalaLinealEntity;
    }

    public static PreguntaEscalaLinealEntity toPreguntaEscalaLinealEntity(PreguntaEscalaLinealModel model,
            EntityManager em) {
        if (model instanceof PreguntaEscalaLinealAdapter) {
            return ((PreguntaEscalaLinealAdapter) model).getPreguntaEscalaLinealEntity();
        }
        return em.getReference(PreguntaEscalaLinealEntity.class, model.getId());
    }

    public PreguntaEscalaLinealEntity getPreguntaEscalaLinealEntity() {
        return preguntaEscalaLinealEntity;
    }

    @Override
    public void commit() {
        em.merge(preguntaEscalaLinealEntity);
    }

    @Override
    public String getId() {
        return preguntaEscalaLinealEntity.getId();
    }

    @Override
    public String getTitulo() {
        return preguntaEscalaLinealEntity.getTitulo();
    }

    @Override
    public void setTitulo(String titulo) {
        preguntaEscalaLinealEntity.setTitulo(titulo);
    }

    @Override
    public String getDescripcion() {
        return preguntaEscalaLinealEntity.getDescripcion();
    }

    @Override
    public void setDescripcion(String descripcion) {
        preguntaEscalaLinealEntity.setDescripcion(descripcion);
    }

    @Override
    public int getNumero() {
        return preguntaEscalaLinealEntity.getNumero();
    }

    @Override
    public void setNumero(int numero) {
        preguntaEscalaLinealEntity.setNumero(numero);
    }

    @Override
    public boolean isObligatorio() {
        return preguntaEscalaLinealEntity.isObligatorio();
    }

    @Override
    public void setObligatorio(boolean obligatorio) {
        preguntaEscalaLinealEntity.setObligatorio(obligatorio);
    }

    @Override
    public String getEtiqueta1() {
        return preguntaEscalaLinealEntity.getEtiqueta1();
    }

    @Override
    public void setEtiqueta1(String etiqueta) {
        preguntaEscalaLinealEntity.setEtiqueta1(etiqueta);
    }

    @Override
    public String getEtiqueta2() {
        return preguntaEscalaLinealEntity.getEtiqueta2();
    }

    @Override
    public void setEtiqueta2(String etiqueta) {
        preguntaEscalaLinealEntity.setEtiqueta2(etiqueta);
    }

    @Override
    public int getDesde() {
        return preguntaEscalaLinealEntity.getDesde();
    }

    @Override
    public void setDesde(int desde) {
        preguntaEscalaLinealEntity.setDesde(desde);
    }

    @Override
    public int getHasta() {
        return preguntaEscalaLinealEntity.getHasta();
    }

    @Override
    public void setHasta(int hasta) {
        preguntaEscalaLinealEntity.setHasta(hasta);
    }

    @Override
    public SeccionModel getSeccion() {
        return new SeccionAdapter(em, preguntaEscalaLinealEntity.getSeccion());
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
        if (!(obj instanceof PreguntaEscalaLinealModel))
            return false;
        PreguntaEscalaLinealModel other = (PreguntaEscalaLinealModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
