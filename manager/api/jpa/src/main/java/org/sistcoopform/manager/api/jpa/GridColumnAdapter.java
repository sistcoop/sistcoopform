package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.GridColumnEntity;
import org.sistcoopform.manager.api.model.GridColumnModel;
import org.sistcoopform.manager.api.model.GridQuestionModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class GridColumnAdapter implements GridColumnModel {

	private static final long serialVersionUID = 1L;

	private GridColumnEntity gridColumnEntity;
	private EntityManager em;

	public GridColumnAdapter(EntityManager em, GridColumnEntity gridColumnEntity) {
		this.em = em;
		this.gridColumnEntity = gridColumnEntity;
	}

	public static GridColumnEntity toGridColumnEntity(GridColumnModel model, EntityManager em) {
		if (model instanceof GridColumnAdapter) {
			return ((GridColumnAdapter) model).getGridColumnEntity();
		}
		return em.getReference(GridColumnEntity.class, model.getId());
	}

	public GridColumnEntity getGridColumnEntity() {
		return gridColumnEntity;
	}

	@Override
	public void commit() {
		em.merge(gridColumnEntity);
	}

	@Override
	public String getId() {
		return gridColumnEntity.getId();
	}

	@Override
	public String getDenomination() {
		return gridColumnEntity.getDenomination();
	}

	@Override
	public void setDenomination(String denomination) {
		gridColumnEntity.setDenomination(denomination);
	}

	@Override
	public int getNumber() {
		return gridColumnEntity.getNumber();
	}

	@Override
	public void setNumber(int number) {
		gridColumnEntity.setNumber(number);
	}

	@Override
	public boolean isEditable() {
		return gridColumnEntity.isEditable();
	}

	@Override
	public void setEditable(boolean editable) {
		gridColumnEntity.setEditable(editable);
	}

	@Override
	public GridQuestionModel getGridQuestion() {
		return new GridQuestionAdapter(em, gridColumnEntity.getGridQuestion());
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
		if (!(obj instanceof GridColumnModel))
			return false;
		GridColumnModel other = (GridColumnModel) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
