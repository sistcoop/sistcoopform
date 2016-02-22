package org.sistcoopform.manager.api.jpa;

import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.SelectAnswerEntity;
import org.sistcoopform.manager.api.model.SelectAnswerModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class SelectAnswerAdapter extends AbstractAnswerAdapter implements SelectAnswerModel {

	private static final long serialVersionUID = 1L;

	private SelectAnswerEntity selectAnswerEntity;
	private EntityManager em;

	public SelectAnswerAdapter(EntityManager em, SelectAnswerEntity selectAnswerEntity) {
		super(em, selectAnswerEntity);
		this.em = em;
		this.selectAnswerEntity = selectAnswerEntity;
	}

	public static SelectAnswerEntity toSelectAnswerEntity(SelectAnswerModel model, EntityManager em) {
		if (model instanceof SelectAnswerAdapter) {
			return ((SelectAnswerAdapter) model).getSelectAnswerEntity();
		}
		return em.getReference(SelectAnswerEntity.class, model.getId());
	}

	public SelectAnswerEntity getSelectAnswerEntity() {
		return selectAnswerEntity;
	}

	@Override
	public Set<String> getValues() {
		return selectAnswerEntity.getValues();
	}

	@Override
	public void setValues(Set<String> values) {
		selectAnswerEntity.setValues(values);
	}

}
