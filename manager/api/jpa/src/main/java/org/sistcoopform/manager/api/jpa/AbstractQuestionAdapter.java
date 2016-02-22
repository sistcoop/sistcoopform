package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.DateTimeQuestionEntity;
import org.sistcoopform.manager.api.jpa.entities.GridQuestionEntity;
import org.sistcoopform.manager.api.jpa.entities.NumericQuestionEntity;
import org.sistcoopform.manager.api.jpa.entities.QuestionEntity;
import org.sistcoopform.manager.api.jpa.entities.ScaleQuestionEntity;
import org.sistcoopform.manager.api.jpa.entities.SelectQuestionEntity;
import org.sistcoopform.manager.api.jpa.entities.TextQuestionEntity;
import org.sistcoopform.manager.api.model.ModelException;
import org.sistcoopform.manager.api.model.QuestionModel;
import org.sistcoopform.manager.api.model.SectionModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public abstract class AbstractQuestionAdapter implements QuestionModel {

	private static final long serialVersionUID = 1L;

	private QuestionEntity questionEntity;
	private EntityManager em;

	public AbstractQuestionAdapter(EntityManager em, QuestionEntity questionEntity) {
		this.em = em;
		this.questionEntity = questionEntity;
	}

	public static QuestionEntity toQuestionEntity(QuestionModel model, EntityManager em) {
		if (model instanceof AbstractQuestionAdapter) {
			return ((AbstractQuestionAdapter) model).getQuestionEntity();
		}
		return em.getReference(QuestionEntity.class, model.getId());
	}

	public static QuestionModel toQuestionModel(QuestionEntity questionEntity, EntityManager em) {
		if (questionEntity instanceof TextQuestionEntity) {
			TextQuestionEntity textQuestion = (TextQuestionEntity) questionEntity;
			return new TextQuestionAdapter(em, textQuestion);
		} else if (questionEntity instanceof DateTimeQuestionEntity) {
			DateTimeQuestionEntity textQuestion = (DateTimeQuestionEntity) questionEntity;
			return new DateTimeQuestionAdapter(em, textQuestion);
		} else if (questionEntity instanceof NumericQuestionEntity) {
			NumericQuestionEntity numericQuestion = (NumericQuestionEntity) questionEntity;
			return new NumericQuestionAdapter(em, numericQuestion);
		} else if (questionEntity instanceof ScaleQuestionEntity) {
			ScaleQuestionEntity scaleQuestion = (ScaleQuestionEntity) questionEntity;
			return new ScaleQuestionAdapter(em, scaleQuestion);
		} else if (questionEntity instanceof SelectQuestionEntity) {
			SelectQuestionEntity selectQuestion = (SelectQuestionEntity) questionEntity;
			return new SelectQuestionAdapter(em, selectQuestion);
		} else if (questionEntity instanceof GridQuestionEntity) {
			GridQuestionEntity gridQuestion = (GridQuestionEntity) questionEntity;
			return new GridQuestionAdapter(em, gridQuestion);
		} else {
			throw new ModelException("QuestionEntity type no encontrado");
		}
	}

	public QuestionEntity getQuestionEntity() {
		return questionEntity;
	}

	@Override
	public void commit() {
		em.merge(questionEntity);
	}

	@Override
	public String getId() {
		return questionEntity.getId();
	}

	@Override
	public String getTitle() {
		return questionEntity.getTitle();
	}

	@Override
	public void setTitle(String title) {
		questionEntity.setTitle(title);
	}

	@Override
	public String getDescription() {
		return questionEntity.getDescription();
	}

	@Override
	public void setDescription(String description) {
		questionEntity.setDescription(description);
	}

	@Override
	public int getNumber() {
		return questionEntity.getNumber();
	}

	@Override
	public void setNumber(int number) {
		questionEntity.setNumber(number);
	}

	@Override
	public SectionModel getSection() {
		return new SectionAdapter(em, questionEntity.getSection());
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
