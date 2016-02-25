package org.sistcoopform.manager.api.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.SelectOptionEntity;
import org.sistcoopform.manager.api.jpa.entities.SelectQuestionEntity;
import org.sistcoopform.manager.api.model.SelectOptionModel;
import org.sistcoopform.manager.api.model.SelectQuestionModel;
import org.sistcoopform.manager.api.model.enums.SelectType;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class SelectQuestionAdapter extends AbstractQuestionAdapter implements SelectQuestionModel {

	private static final long serialVersionUID = 1L;

	private SelectQuestionEntity selectQuestionEntity;
	private EntityManager em;

	public SelectQuestionAdapter(EntityManager em, SelectQuestionEntity selectQuestionEntity) {
		super(em, selectQuestionEntity);
		this.em = em;
		this.selectQuestionEntity = selectQuestionEntity;
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
	public Set<SelectOptionModel> getOptions() {
		Set<SelectOptionEntity> optionsEntity = selectQuestionEntity.getOptions();
		Set<SelectOptionModel> models = new HashSet<>();
		for (SelectOptionEntity entity : optionsEntity) {
			models.add(new SelectOptionAdapter(em, entity));
		}
		return models;
	}

	@Override
	public void setOptions(Set<SelectOptionModel> options) {
		Set<SelectOptionEntity> optionsEntity = new HashSet<>();
		for (SelectOptionModel optionModel : options) {
			optionsEntity.add(SelectOptionAdapter.toSelectOptionEntity(optionModel, em));
		}
		selectQuestionEntity.getOptions().clear();
		selectQuestionEntity.getOptions().addAll(optionsEntity);
	}

}
