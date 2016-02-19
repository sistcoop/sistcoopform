package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.SelectOptionEntity;
import org.sistcoopform.manager.api.model.SelectOptionModel;
import org.sistcoopform.manager.api.model.SelectQuestionModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class SelectOptionAdapter implements SelectOptionModel {

	private static final long serialVersionUID = 1L;

	private SelectOptionEntity selectOptionEntity;
	private EntityManager em;

	public SelectOptionAdapter(EntityManager em, SelectOptionEntity opcionSeleccionEntity) {
		this.em = em;
		this.selectOptionEntity = opcionSeleccionEntity;
	}

	public static SelectOptionEntity toSelectOptionEntity(SelectOptionModel model, EntityManager em) {
		if (model instanceof SelectOptionAdapter) {
			return ((SelectOptionAdapter) model).getSelectOptionEntity();
		}
		return em.getReference(SelectOptionEntity.class, model.getId());
	}

	public SelectOptionEntity getSelectOptionEntity() {
		return selectOptionEntity;
	}

	@Override
	public void commit() {
		em.merge(selectOptionEntity);
	}

	@Override
	public String getId() {
		return selectOptionEntity.getId();
	}

	@Override
	public String getDenomination() {
		return selectOptionEntity.getDenomination();
	}

	@Override
	public void setDenomination(String denomination) {
		selectOptionEntity.setDenomination(denomination);
	}

	@Override
	public int getNumber() {
		return selectOptionEntity.getNumber();
	}

	@Override
	public void setNumber(int number) {
		selectOptionEntity.setNumber(number);
	}

	@Override
	public boolean isEditable() {
		return selectOptionEntity.isEditable();
	}

	@Override
	public void setEditable(boolean editable) {
		selectOptionEntity.setEditable(editable);
	}

	@Override
	public SelectQuestionModel getSelectQuestion() {
		return new SelectQuestionAdapter(em, selectOptionEntity.getSelectQuestion());
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
		if (!(obj instanceof SelectOptionModel))
			return false;
		SelectOptionModel other = (SelectOptionModel) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
