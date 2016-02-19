package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.GridRowEntity;
import org.sistcoopform.manager.api.model.GridQuestionModel;
import org.sistcoopform.manager.api.model.GridRowModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class GridRowAdapter implements GridRowModel {

	private static final long serialVersionUID = 1L;

	private GridRowEntity gridRowEntity;
	private EntityManager em;

	public GridRowAdapter(EntityManager em, GridRowEntity gridRowEntity) {
		this.em = em;
		this.gridRowEntity = gridRowEntity;
	}

	public static GridRowEntity toGridRowEntity(GridRowModel model, EntityManager em) {
		if (model instanceof GridRowAdapter) {
			return ((GridRowAdapter) model).getGridRowEntity();
		}
		return em.getReference(GridRowEntity.class, model.getId());
	}

	public GridRowEntity getGridRowEntity() {
		return gridRowEntity;
	}

	@Override
	public void commit() {
		em.merge(gridRowEntity);
	}

	@Override
	public String getId() {
		return gridRowEntity.getId();
	}

	@Override
	public String getDenomination() {
		return gridRowEntity.getDenomination();
	}

	@Override
	public void setDenomination(String denomination) {
		gridRowEntity.setDenomination(denomination);
	}

	@Override
	public int getNumber() {
		return gridRowEntity.getNumber();
	}

	@Override
	public void setNumber(int number) {
		gridRowEntity.setNumber(number);
	}

	@Override
	public boolean isEditable() {
		return gridRowEntity.isEditable();
	}

	@Override
	public void setEditable(boolean editable) {
		gridRowEntity.setEditable(editable);
	}

	@Override
	public GridQuestionModel getGridQuestion() {
		return new GridQuestionAdapter(em, gridRowEntity.getGridQuestion());
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
		if (!(obj instanceof GridRowModel))
			return false;
		GridRowModel other = (GridRowModel) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
