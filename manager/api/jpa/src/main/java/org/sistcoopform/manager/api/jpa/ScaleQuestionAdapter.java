package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.ScaleQuestionEntity;
import org.sistcoopform.manager.api.model.ScaleQuestionModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class ScaleQuestionAdapter extends AbstractQuestionAdapter implements ScaleQuestionModel {

	private static final long serialVersionUID = 1L;

	private ScaleQuestionEntity scaleQuestionEntity;
	private EntityManager em;

	public ScaleQuestionAdapter(EntityManager em, ScaleQuestionEntity scaleQuestionEntity) {
		super(em, scaleQuestionEntity);
		this.em = em;
		this.scaleQuestionEntity = scaleQuestionEntity;
	}

	public static ScaleQuestionEntity toScaleQuestionEntity(ScaleQuestionModel model, EntityManager em) {
		if (model instanceof ScaleQuestionAdapter) {
			return ((ScaleQuestionAdapter) model).getScaleQuestionEntity();
		}
		return em.getReference(ScaleQuestionEntity.class, model.getId());
	}

	public ScaleQuestionEntity getScaleQuestionEntity() {
		return scaleQuestionEntity;
	}

	@Override
	public boolean isRequired() {
		return scaleQuestionEntity.isRequired();
	}

	@Override
	public void setRequired(boolean required) {
		scaleQuestionEntity.setRequired(required);
	}

	@Override
	public String getTag1() {
		return scaleQuestionEntity.getTag1();
	}

	@Override
	public void setTag1(String tag) {
		scaleQuestionEntity.setTag1(tag);
	}

	@Override
	public String getTag2() {
		return scaleQuestionEntity.getTag2();
	}

	@Override
	public void setTag2(String tag) {
		scaleQuestionEntity.setTag2(tag);
	}

	@Override
	public int getMin() {
		return scaleQuestionEntity.getMin();
	}

	@Override
	public void setMin(int min) {
		scaleQuestionEntity.setMin(min);
	}

	@Override
	public int getMax() {
		return scaleQuestionEntity.getMax();
	}

	@Override
	public void setMax(int max) {
		scaleQuestionEntity.setMax(max);
	}

}
