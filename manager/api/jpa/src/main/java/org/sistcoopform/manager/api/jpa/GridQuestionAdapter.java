package org.sistcoopform.manager.api.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.GridColumnEntity;
import org.sistcoopform.manager.api.jpa.entities.GridQuestionEntity;
import org.sistcoopform.manager.api.jpa.entities.GridRowEntity;
import org.sistcoopform.manager.api.model.GridColumnModel;
import org.sistcoopform.manager.api.model.GridQuestionModel;
import org.sistcoopform.manager.api.model.GridRowModel;
import org.sistcoopform.manager.api.model.SectionModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class GridQuestionAdapter implements GridQuestionModel {

	private static final long serialVersionUID = 1L;

	private GridQuestionEntity gridQuestionEntity;
	private EntityManager em;

	public GridQuestionAdapter(EntityManager em, GridQuestionEntity gridQuestionEntity) {
		this.em = em;
		this.gridQuestionEntity = gridQuestionEntity;
	}

	public static GridQuestionEntity toGridQuestionEntity(GridQuestionModel model, EntityManager em) {
		if (model instanceof GridQuestionAdapter) {
			return ((GridQuestionAdapter) model).getGridQuestionEntity();
		}
		return em.getReference(GridQuestionEntity.class, model.getId());
	}

	public GridQuestionEntity getGridQuestionEntity() {
		return gridQuestionEntity;
	}

	@Override
	public void commit() {
		em.merge(gridQuestionEntity);
	}

	@Override
	public String getId() {
		return gridQuestionEntity.getId();
	}

	@Override
	public String getTitle() {
		return gridQuestionEntity.getTitle();
	}

	@Override
	public void setTitle(String title) {
		gridQuestionEntity.setTitle(title);
	}

	@Override
	public String getDescription() {
		return gridQuestionEntity.getDescription();
	}

	@Override
	public void setDescription(String description) {
		gridQuestionEntity.setDescription(description);
	}

	@Override
	public int getNumber() {
		return gridQuestionEntity.getNumber();
	}

	@Override
	public void setNumber(int number) {
		gridQuestionEntity.setNumber(number);
	}

	@Override
	public boolean isRequired() {
		return gridQuestionEntity.isRequired();
	}

	@Override
	public void setRequired(boolean required) {
		gridQuestionEntity.setRequired(required);
	}

	@Override
	public SectionModel getSection() {
		return new SectionAdapter(em, gridQuestionEntity.getSection());
	}

	@Override
	public Set<GridRowModel> getRows() {
		Set<GridRowEntity> rows = gridQuestionEntity.getRows();
		Set<GridRowModel> models = new HashSet<>();
		for (GridRowEntity row : rows) {
			models.add(new GridRowAdapter(em, row));
		}
		return models;
	}

	@Override
	public void setRows(Set<GridRowModel> rows) {
		Set<GridRowEntity> rowsEntity = new HashSet<>();
		for (GridRowModel rowModel : rows) {
			rowsEntity.add(GridRowAdapter.toGridRowEntity(rowModel, em));
		}
		gridQuestionEntity.setRows(rowsEntity);
	}

	@Override
	public Set<GridColumnModel> getColumns() {
		Set<GridColumnEntity> columns = gridQuestionEntity.getColumns();
		Set<GridColumnModel> models = new HashSet<>();
		for (GridColumnEntity column : columns) {
			models.add(new GridColumnAdapter(em, column));
		}
		return models;
	}

	@Override
	public void setColumns(Set<GridColumnModel> columns) {
		Set<GridColumnEntity> columnsEntity = new HashSet<>();
		for (GridColumnModel columnModel : columns) {
			columnsEntity.add(GridColumnAdapter.toGridColumnEntity(columnModel, em));
		}
		gridQuestionEntity.setColumns(columnsEntity);
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
		if (!(obj instanceof GridQuestionModel))
			return false;
		GridQuestionModel other = (GridQuestionModel) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
}
