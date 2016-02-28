package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.AnswerEntity;
import org.sistcoopform.manager.api.jpa.entities.DateTimeAnswerEntity;
import org.sistcoopform.manager.api.jpa.entities.GridAnswerEntity;
import org.sistcoopform.manager.api.jpa.entities.NumberAnswerEntity;
import org.sistcoopform.manager.api.jpa.entities.QuestionEntity;
import org.sistcoopform.manager.api.jpa.entities.ScaleAnswerEntity;
import org.sistcoopform.manager.api.jpa.entities.SelectAnswerEntity;
import org.sistcoopform.manager.api.jpa.entities.TextAnswerEntity;
import org.sistcoopform.manager.api.model.AnswerModel;
import org.sistcoopform.manager.api.model.FormAnswerModel;
import org.sistcoopform.manager.api.model.ModelException;
import org.sistcoopform.manager.api.model.QuestionModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public abstract class AbstractAnswerAdapter implements AnswerModel {

	private static final long serialVersionUID = 1L;

	private AnswerEntity answerEntity;
	private EntityManager em;

	public AbstractAnswerAdapter(EntityManager em, AnswerEntity answerEntity) {
		this.em = em;
		this.answerEntity = answerEntity;
	}

	public static AnswerEntity toAnswerEntity(AnswerModel model, EntityManager em) {
		if (model instanceof AbstractAnswerAdapter) {
			return ((AbstractAnswerAdapter) model).getAnswerEntity();
		}
		return em.getReference(AnswerEntity.class, model.getId());
	}

	public static AnswerModel toAnswerModel(AnswerEntity answerEntity, EntityManager em) {
		if (answerEntity instanceof TextAnswerEntity) {
			TextAnswerEntity textAnswer = (TextAnswerEntity) answerEntity;
			return new TextAnswerAdapter(em, textAnswer);
		} else if (answerEntity instanceof DateTimeAnswerEntity) {
			DateTimeAnswerEntity textAnswer = (DateTimeAnswerEntity) answerEntity;
			return new DateTimeAnswerAdapter(em, textAnswer);
		} else if (answerEntity instanceof NumberAnswerEntity) {
			NumberAnswerEntity numericAnswer = (NumberAnswerEntity) answerEntity;
			return new NumberAnswerAdapter(em, numericAnswer);
		} else if (answerEntity instanceof ScaleAnswerEntity) {
			ScaleAnswerEntity scaleAnswer = (ScaleAnswerEntity) answerEntity;
			return new ScaleAnswerAdapter(em, scaleAnswer);
		} else if (answerEntity instanceof SelectAnswerEntity) {
			SelectAnswerEntity selectAnswer = (SelectAnswerEntity) answerEntity;
			return new SelectAnswerAdapter(em, selectAnswer);
		} else if (answerEntity instanceof GridAnswerEntity) {
			GridAnswerEntity gridAnswer = (GridAnswerEntity) answerEntity;
			return new GridAnswerAdapter(em, gridAnswer);
		} else {
			throw new ModelException("Entity no encontrado");
		}
	}

	public AnswerEntity getAnswerEntity() {
		return answerEntity;
	}

	@Override
	public void commit() {
		em.merge(answerEntity);
	}

	@Override
	public String getId() {
		return answerEntity.getId();
	}

	@Override
	public FormAnswerModel getForm() {
		return new FormAnswerAdapter(em, answerEntity.getFormAnswer());
	}

	@Override
	public QuestionModel getQuestion() {
		QuestionEntity question = answerEntity.getQuestion();
		return AbstractQuestionAdapter.toQuestionModel(question, em);
	}

}
