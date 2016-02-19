package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.ScaleQuestionEntity;
import org.sistcoopform.manager.api.model.ScaleQuestionModel;
import org.sistcoopform.manager.api.model.SectionModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class ScaleQuestionAdapter implements ScaleQuestionModel {

	private static final long serialVersionUID = 1L;

	private ScaleQuestionEntity scaleQuestionEntity;
	private EntityManager em;

	public ScaleQuestionAdapter(EntityManager em, ScaleQuestionEntity preguntaEscalaLinealEntity) {
		this.em = em;
		this.scaleQuestionEntity = preguntaEscalaLinealEntity;
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
	public void commit() {
		em.merge(scaleQuestionEntity);
	}

	@Override
	public String getId() {
		return scaleQuestionEntity.getId();
	}

	@Override
	public String getTitle() {
		return scaleQuestionEntity.getTitle();
	}

	@Override
	public void setTitle(String title) {
		scaleQuestionEntity.setTitle(title);
	}

	@Override
	public String getDescription() {
		return scaleQuestionEntity.getDescription();
	}

	@Override
	public void setDescription(String description) {
		scaleQuestionEntity.setDescription(description);
	}

	@Override
	public int getNumber() {
		return scaleQuestionEntity.getNumber();
	}

	@Override
	public void setNumber(int number) {
		scaleQuestionEntity.setNumber(number);
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

	@Override
	public SectionModel getSection() {
		return new SectionAdapter(em, scaleQuestionEntity.getSection());
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
		if (!(obj instanceof ScaleQuestionModel))
			return false;
		ScaleQuestionModel other = (ScaleQuestionModel) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
