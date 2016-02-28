package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.NumberQuestionEntity;
import org.sistcoopform.manager.api.model.NumberQuestionModel;
import org.sistcoopform.manager.api.model.enums.NumberType;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class NumberQuestionAdapter extends AbstractQuestionAdapter implements NumberQuestionModel {

	private static final long serialVersionUID = 1L;

	private NumberQuestionEntity numericQuestionEntity;
	private EntityManager em;

	public NumberQuestionAdapter(EntityManager em, NumberQuestionEntity numericQuestionEntity) {
		super(em, numericQuestionEntity);
		this.em = em;
		this.numericQuestionEntity = numericQuestionEntity;
	}

	public static NumberQuestionEntity toNumericQuestionEntity(NumberQuestionModel model, EntityManager em) {
		if (model instanceof NumberQuestionAdapter) {
			return ((NumberQuestionAdapter) model).getNumericQuestionEntity();
		}
		return em.getReference(NumberQuestionEntity.class, model.getId());
	}

	public NumberQuestionEntity getNumericQuestionEntity() {
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
	public NumberType getType() {
		return NumberType.valueOf(numericQuestionEntity.getType());
	}

	@Override
	public void setType(NumberType tipoPregunta) {
		if (tipoPregunta != null) {
			numericQuestionEntity.setType(tipoPregunta.toString());
		} else {
			numericQuestionEntity.setType(null);
		}
	}

}
