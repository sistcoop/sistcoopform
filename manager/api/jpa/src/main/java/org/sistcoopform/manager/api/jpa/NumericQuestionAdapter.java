package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.NumericQuestionEntity;
import org.sistcoopform.manager.api.model.NumericQuestionModel;
import org.sistcoopform.manager.api.model.SectionModel;
import org.sistcoopform.manager.api.model.enums.NumericType;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class NumericQuestionAdapter implements NumericQuestionModel {

	private static final long serialVersionUID = 1L;

	private NumericQuestionEntity numericQuestionEntity;
	private EntityManager em;

	public NumericQuestionAdapter(EntityManager em, NumericQuestionEntity numericQuestionEntity) {
		this.em = em;
		this.numericQuestionEntity = numericQuestionEntity;
	}

	public static NumericQuestionEntity toNumericQuestionEntity(NumericQuestionModel model, EntityManager em) {
		if (model instanceof NumericQuestionAdapter) {
			return ((NumericQuestionAdapter) model).getNumericQuestionEntity();
		}
		return em.getReference(NumericQuestionEntity.class, model.getId());
	}

	public NumericQuestionEntity getNumericQuestionEntity() {
		return numericQuestionEntity;
	}

	@Override
	public void commit() {
		em.merge(numericQuestionEntity);
	}

	@Override
	public String getId() {
		return numericQuestionEntity.getId();
	}

	@Override
	public String getTitle() {
		return numericQuestionEntity.getTitle();
	}

	@Override
	public void setTitle(String title) {
		numericQuestionEntity.setTitle(title);
	}

	@Override
	public String getDescription() {
		return numericQuestionEntity.getDescription();
	}

	@Override
	public void setDescription(String descripcion) {
		numericQuestionEntity.setDescription(descripcion);
	}

	@Override
	public int getNumber() {
		return numericQuestionEntity.getNumber();
	}

	@Override
	public void setNumber(int number) {
		numericQuestionEntity.setNumber(number);
	}

	@Override
	public boolean isRequired() {
		return numericQuestionEntity.isRequired();
	}

	@Override
	public void setRequired(boolean required) {
		numericQuestionEntity.setRequired(required);
	}

	@Override
	public NumericType getType() {
		return NumericType.valueOf(numericQuestionEntity.getType());
	}

	@Override
	public void setType(NumericType tipoPregunta) {
		if (tipoPregunta != null) {
			numericQuestionEntity.setType(tipoPregunta.toString());
		} else {
			numericQuestionEntity.setType(null);
		}
	}

	@Override
	public SectionModel getSection() {
		return new SectionAdapter(em, numericQuestionEntity.getSection());
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
		if (!(obj instanceof NumericQuestionModel))
			return false;
		NumericQuestionModel other = (NumericQuestionModel) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
