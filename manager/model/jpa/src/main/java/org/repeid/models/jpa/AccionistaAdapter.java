package org.repeid.models.jpa;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.repeid.models.AccionistaModel;
import org.repeid.models.PersonaJuridicaModel;
import org.repeid.models.PersonaNaturalModel;
import org.repeid.models.jpa.entities.AccionistaEntity;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class AccionistaAdapter implements AccionistaModel {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private AccionistaEntity accionistaEntity;
    private transient EntityManager em;

    public AccionistaAdapter(EntityManager em, AccionistaEntity accionistaEntity) {
        this.em = em;
        this.accionistaEntity = accionistaEntity;
    }

    public AccionistaEntity getAccionistaEntity() {
        return accionistaEntity;
    }

    @Override
    public String getId() {
        return accionistaEntity.getId();
    }

    @Override
    public PersonaNaturalModel getPersonaNatural() {
        return new PersonaNaturalAdapter(em, accionistaEntity.getPersonaNatural());
    }

    @Override
    public PersonaJuridicaModel getPersonaJuridica() {
        return new PersonaJuridicaAdapter(em, accionistaEntity.getPersonaJuridica());
    }

    @Override
    public BigDecimal getPorcentajeParticipacion() {
        return accionistaEntity.getPorcentajeParticipacion();
    }

    @Override
    public void setPorcentajeParticipacion(BigDecimal porcentajeParticipacion) {
        accionistaEntity.setPorcentajeParticipacion(porcentajeParticipacion);
    }

    public static AccionistaEntity toAccionistaEntity(AccionistaModel model, EntityManager em) {
        if (model instanceof AccionistaAdapter) {
            return ((AccionistaAdapter) model).getAccionistaEntity();
        }
        return em.getReference(AccionistaEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(accionistaEntity);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPersonaJuridica() == null) ? 0 : getPersonaJuridica().hashCode());
        result = prime * result + ((getPersonaNatural() == null) ? 0 : getPersonaNatural().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof AccionistaModel))
            return false;
        AccionistaModel other = (AccionistaModel) obj;
        if (getPersonaJuridica() == null) {
            if (other.getPersonaJuridica() != null)
                return false;
        } else if (!getPersonaJuridica().equals(other.getPersonaJuridica()))
            return false;
        if (getPersonaNatural() == null) {
            if (other.getPersonaNatural() != null)
                return false;
        } else if (!getPersonaNatural().equals(other.getPersonaNatural()))
            return false;
        return true;
    }

}
