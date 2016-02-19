package org.sistcoopform.manager.api.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.SelectOptionEntity;
import org.sistcoopform.manager.api.jpa.entities.SelectQuestionEntity;
import org.sistcoopform.manager.api.model.QuestionModel;
import org.sistcoopform.manager.api.model.SectionModel;
import org.sistcoopform.manager.api.model.SelectOptionModel;
import org.sistcoopform.manager.api.model.SelectQuestionModel;
import org.sistcoopform.manager.api.model.enums.SelectType;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class SelectQuestionAdapter implements SelectQuestionModel {

	private static final long serialVersionUID = 1L;

	private SelectQuestionEntity selectQuestionEntity;
	private EntityManager em;

	public SelectQuestionAdapter(EntityManager em, SelectQuestionEntity preguntaSeleccionEntity) {
		this.em = em;
		this.selectQuestionEntity = preguntaSeleccionEntity;
	}

	public static SelectQuestionEntity toSelectQuestionEntity(SelectQuestionModel model, EntityManager em) {
		if (model instanceof SelectQuestionAdapter) {
			return ((SelectQuestionAdapter) model).getSelectQuestionEntity();
		}
		return em.getReference(SelectQuestionEntity.class, model.getId());
	}

	public SelectQuestionEntity getSelectQuestionEntity() {
		return selectQuestionEntity;
	}

	@Override
	public void commit() {
		em.merge(selectQuestionEntity);
	}

	@Override
	public String getId() {
		return selectQuestionEntity.getId();
	}

	@Override
	public String getTitle() {
		return selectQuestionEntity.getTitle();
	}

	@Override
	public void setTitle(String title) {
		selectQuestionEntity.setTitle(title);
	}

	@Override
	public String getDescription() {
		return selectQuestionEntity.getDescription();
	}

	@Override
	public void setDescription(String description) {
		selectQuestionEntity.setDescription(description);
	}

	@Override
	public int getNumber() {
		return selectQuestionEntity.getNumber();
	}

	@Override
	public void setNumber(int number) {
		selectQuestionEntity.setNumber(number);
	}

	@Override
	public boolean isRequired() {
		return selectQuestionEntity.isRequired();
	}

	@Override
	public void setRequired(boolean required) {
		selectQuestionEntity.setRequired(required);
	}

	@Override
	public SelectType getType() {
		return SelectType.valueOf(selectQuestionEntity.getType());
	}

	@Override
	public void setType(SelectType type) {
		if (type != null) {
			selectQuestionEntity.setType(type.toString());
		} else {
			selectQuestionEntity.setType(null);
		}
	}

	@Override
	public List<SelectOptionModel> getOptions() {
		Set<SelectOptionEntity> optionsEntity = selectQuestionEntity.getOptions();
		List<SelectOptionModel> models = new ArrayList<>();
		for (SelectOptionEntity entity : optionsEntity) {
			models.add(new SelectOptionAdapter(em, entity));
		}
		return models;
	}

	@Override
	public SectionModel getSection() {
		return new SectionAdapter(em, selectQuestionEntity.getSection());
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
		if (!(obj instanceof QuestionModel))
			return false;
		QuestionModel other = (QuestionModel) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
