package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.NumericQuestionEntity;
import org.sistcoopform.manager.api.model.NumericQuestionModel;
import org.sistcoopform.manager.api.model.enums.NumericType;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class NumericQuestionAdapter extends AbstractQuestionAdapter implements NumericQuestionModel {

	private static final long serialVersionUID = 1L;

	private NumericQuestionEntity numericQuestionEntity;
	private EntityManager em;

	public NumericQuestionAdapter(EntityManager em, NumericQuestionEntity numericQuestionEntity) {
		super(em, numericQuestionEntity);
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

}
