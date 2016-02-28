package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.NumberAnswerEntity;
import org.sistcoopform.manager.api.model.NumberAnswerModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class NumberAnswerAdapter extends AbstractAnswerAdapter implements NumberAnswerModel {

	private static final long serialVersionUID = 1L;

	private NumberAnswerEntity numericAnswerEntity;
	private EntityManager em;

	public NumberAnswerAdapter(EntityManager em, NumberAnswerEntity numericAnswerEntity) {
		super(em, numericAnswerEntity);
		this.em = em;
		this.numericAnswerEntity = numericAnswerEntity;
	}

	public static NumberAnswerEntity toNumericAnswerEntity(NumberAnswerModel model, EntityManager em) {
		if (model instanceof NumberAnswerAdapter) {
			return ((NumberAnswerAdapter) model).getNumericAnswerEntity();
		}
		return em.getReference(NumberAnswerEntity.class, model.getId());
	}

	public NumberAnswerEntity getNumericAnswerEntity() {
		return numericAnswerEntity;
	}

	@Override
	public double getValue() {
		return numericAnswerEntity.getValue();
	}

	@Override
	public void setValue(double value) {
		numericAnswerEntity.setValue(value);
	}

}
