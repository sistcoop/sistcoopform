package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.DateTimeQuestionEntity;
import org.sistcoopform.manager.api.model.DateTimeQuestionModel;
import org.sistcoopform.manager.api.model.SectionModel;
import org.sistcoopform.manager.api.model.enums.DateTimeType;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class DateTimeQuestionAdapter extends AbstractQuestionAdapter implements DateTimeQuestionModel {

	private static final long serialVersionUID = 1L;

	private DateTimeQuestionEntity dateTimeQuestionEntity;
	private EntityManager em;

	public DateTimeQuestionAdapter(EntityManager em, DateTimeQuestionEntity preguntaTiempoEntity) {
		super(em, preguntaTiempoEntity);
		this.em = em;
		this.dateTimeQuestionEntity = preguntaTiempoEntity;
	}

	public static DateTimeQuestionEntity toDateTimeQuestionEntity(DateTimeQuestionModel model, EntityManager em) {
		if (model instanceof DateTimeQuestionAdapter) {
			return ((DateTimeQuestionAdapter) model).getDateTimeQuestionEntity();
		}
		return em.getReference(DateTimeQuestionEntity.class, model.getId());
	}

	public DateTimeQuestionEntity getDateTimeQuestionEntity() {
		return dateTimeQuestionEntity;
	}

	@Override
	public boolean isRequired() {
		return dateTimeQuestionEntity.isRequired();
	}

	@Override
	public void setRequired(boolean obligatorio) {
		dateTimeQuestionEntity.setRequired(obligatorio);
	}

	@Override
	public DateTimeType getType() {
		return DateTimeType.valueOf(dateTimeQuestionEntity.getType());
	}

	@Override
	public void setType(DateTimeType type) {
		if (type != null) {
			dateTimeQuestionEntity.setType(type.toString());
		} else {
			dateTimeQuestionEntity.setType(null);
		}
	}

	@Override
	public SectionModel getSection() {
		return new SectionAdapter(em, dateTimeQuestionEntity.getSection());
	}

}
