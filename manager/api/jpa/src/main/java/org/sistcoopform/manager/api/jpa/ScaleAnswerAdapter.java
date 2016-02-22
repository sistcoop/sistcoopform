package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.ScaleAnswerEntity;
import org.sistcoopform.manager.api.model.ScaleAnswerModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class ScaleAnswerAdapter extends AbstractAnswerAdapter implements ScaleAnswerModel {

	private static final long serialVersionUID = 1L;

	private ScaleAnswerEntity scaleAnswerEntity;
	private EntityManager em;

	public ScaleAnswerAdapter(EntityManager em, ScaleAnswerEntity scaleAnswerEntity) {
		super(em, scaleAnswerEntity);
		this.em = em;
		this.scaleAnswerEntity = scaleAnswerEntity;
	}

	public static ScaleAnswerEntity toScaleAnswerEntity(ScaleAnswerModel model, EntityManager em) {
		if (model instanceof ScaleAnswerAdapter) {
			return ((ScaleAnswerAdapter) model).getScaleAnswerEntity();
		}
		return em.getReference(ScaleAnswerEntity.class, model.getId());
	}

	public ScaleAnswerEntity getScaleAnswerEntity() {
		return scaleAnswerEntity;
	}

	@Override
	public int getValue() {
		return scaleAnswerEntity.getValue();
	}

	@Override
	public void setValue(int value) {
		scaleAnswerEntity.setValue(value);
	}

}
