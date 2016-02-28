package org.sistcoopform.manager.api.jpa;

import java.util.Date;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.DateTimeAnswerEntity;
import org.sistcoopform.manager.api.model.DateTimeAnswerModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class DateTimeAnswerAdapter extends AbstractAnswerAdapter implements DateTimeAnswerModel {

	private static final long serialVersionUID = 1L;

	private DateTimeAnswerEntity dateTimeAnswerEntity;
	private EntityManager em;

	public DateTimeAnswerAdapter(EntityManager em, DateTimeAnswerEntity dateTimeAnswerEntity) {
		super(em, dateTimeAnswerEntity);
		this.em = em;
		this.dateTimeAnswerEntity = dateTimeAnswerEntity;
	}

	public static DateTimeAnswerEntity toDateTimeAnswerEntity(DateTimeAnswerModel model, EntityManager em) {
		if (model instanceof DateTimeAnswerAdapter) {
			return ((DateTimeAnswerAdapter) model).getDateTimeAnswerEntity();
		}
		return em.getReference(DateTimeAnswerEntity.class, model.getId());
	}

	public DateTimeAnswerEntity getDateTimeAnswerEntity() {
		return dateTimeAnswerEntity;
	}

	@Override
	public String getId() {
		return dateTimeAnswerEntity.getId();
	}

	@Override
	public Date getDate() {
		return dateTimeAnswerEntity.getDate();
	}

	@Override
	public void setDate(Date date) {
		dateTimeAnswerEntity.setDate(date);
	}

}
