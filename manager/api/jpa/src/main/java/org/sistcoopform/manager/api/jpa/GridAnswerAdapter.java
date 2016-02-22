package org.sistcoopform.manager.api.jpa;

import java.util.Map;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.GridAnswerEntity;
import org.sistcoopform.manager.api.model.GridAnswerModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class GridAnswerAdapter extends AbstractAnswerAdapter implements GridAnswerModel {

	private static final long serialVersionUID = 1L;

	private GridAnswerEntity gridAnswerEntity;
	private EntityManager em;

	public GridAnswerAdapter(EntityManager em, GridAnswerEntity gridAnswerEntity) {
		super(em, gridAnswerEntity);
		this.em = em;
		this.gridAnswerEntity = gridAnswerEntity;
	}

	public static GridAnswerEntity toGridAnswerEntity(GridAnswerModel model, EntityManager em) {
		if (model instanceof GridAnswerAdapter) {
			return ((GridAnswerAdapter) model).getGridAnswerEntity();
		}
		return em.getReference(GridAnswerEntity.class, model.getId());
	}

	public GridAnswerEntity getGridAnswerEntity() {
		return gridAnswerEntity;
	}

	@Override
	public Map<String, String> getValues() {
		return gridAnswerEntity.getValues();
	}

	@Override
	public void setValues(Map<String, String> values) {
		gridAnswerEntity.setValues(values);
	}

}
