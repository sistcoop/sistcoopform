package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.DateTimeQuestionEntity;
import org.sistcoopform.manager.api.model.DateTimeQuestionModel;
import org.sistcoopform.manager.api.model.QuestionModel;
import org.sistcoopform.manager.api.model.SectionModel;
import org.sistcoopform.manager.api.model.enums.DateTimeType;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class DateTimeQuestionAdapter implements DateTimeQuestionModel {

	private static final long serialVersionUID = 1L;

	private DateTimeQuestionEntity dateTimeQuestionEntity;
	private EntityManager em;

	public DateTimeQuestionAdapter(EntityManager em, DateTimeQuestionEntity preguntaTiempoEntity) {
		this.em = em;
		this.dateTimeQuestionEntity = preguntaTiempoEntity;
	}

	public static DateTimeQuestionEntity toDateTimeQuestionEntity(SectionModel model, EntityManager em) {
		if (model instanceof DateTimeQuestionAdapter) {
			return ((DateTimeQuestionAdapter) model).getDateTimeQuestionEntity();
		}
		return em.getReference(DateTimeQuestionEntity.class, model.getId());
	}

	public DateTimeQuestionEntity getDateTimeQuestionEntity() {
		return dateTimeQuestionEntity;
	}

	@Override
	public void commit() {
		em.merge(dateTimeQuestionEntity);
	}

	@Override
	public String getId() {
		return dateTimeQuestionEntity.getId();
	}

	@Override
	public String getTitle() {
		return dateTimeQuestionEntity.getTitle();
	}

	@Override
	public void setTitle(String title) {
		dateTimeQuestionEntity.setTitle(title);
	}

	@Override
	public String getDescription() {
		return dateTimeQuestionEntity.getDescription();
	}

	@Override
	public void setDescription(String description) {
		dateTimeQuestionEntity.setDescription(description);
	}

	@Override
	public int getNumber() {
		return dateTimeQuestionEntity.getNumber();
	}

	@Override
	public void setNumber(int numero) {
		dateTimeQuestionEntity.setNumber(numero);
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
