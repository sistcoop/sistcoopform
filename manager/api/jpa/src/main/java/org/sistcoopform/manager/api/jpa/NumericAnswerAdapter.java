package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.NumericAnswerEntity;
import org.sistcoopform.manager.api.model.NumericAnswerModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class NumericAnswerAdapter extends AbstractAnswerAdapter implements NumericAnswerModel {

	private static final long serialVersionUID = 1L;

	private NumericAnswerEntity numericAnswerEntity;
	private EntityManager em;

	public NumericAnswerAdapter(EntityManager em, NumericAnswerEntity numericAnswerEntity) {
		super(em, numericAnswerEntity);
		this.em = em;
		this.numericAnswerEntity = numericAnswerEntity;
	}

	public static NumericAnswerEntity toNumericAnswerEntity(NumericAnswerModel model, EntityManager em) {
		if (model instanceof NumericAnswerAdapter) {
			return ((NumericAnswerAdapter) model).getNumericAnswerEntity();
		}
		return em.getReference(NumericAnswerEntity.class, model.getId());
	}

	public NumericAnswerEntity getNumericAnswerEntity() {
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
