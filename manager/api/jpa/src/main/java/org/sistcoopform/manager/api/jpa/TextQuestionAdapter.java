package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.TextQuestionEntity;
import org.sistcoopform.manager.api.model.QuestionModel;
import org.sistcoopform.manager.api.model.SectionModel;
import org.sistcoopform.manager.api.model.TextQuestionModel;
import org.sistcoopform.manager.api.model.enums.TextType;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class TextQuestionAdapter implements TextQuestionModel {

	private static final long serialVersionUID = 1L;

	private TextQuestionEntity textQuestionEntity;
	private EntityManager em;

	public TextQuestionAdapter(EntityManager em, TextQuestionEntity textQuestionEntity) {
		this.em = em;
		this.textQuestionEntity = textQuestionEntity;
	}

	public static TextQuestionEntity toTextQuestionEntity(TextQuestionModel model, EntityManager em) {
		if (model instanceof TextQuestionAdapter) {
			return ((TextQuestionAdapter) model).getTextQuestionEntity();
		}
		return em.getReference(TextQuestionEntity.class, model.getId());
	}

	public TextQuestionEntity getTextQuestionEntity() {
		return textQuestionEntity;
	}

	@Override
	public void commit() {
		em.merge(textQuestionEntity);
	}

	@Override
	public String getId() {
		return textQuestionEntity.getId();
	}

	@Override
	public String getTitle() {
		return textQuestionEntity.getTitle();
	}

	@Override
	public void setTitle(String title) {
		textQuestionEntity.setTitle(title);
	}

	@Override
	public String getDescription() {
		return textQuestionEntity.getDescription();
	}

	@Override
	public void setDescription(String description) {
		textQuestionEntity.setDescription(description);
	}

	@Override
	public int getNumber() {
		return textQuestionEntity.getNumber();
	}

	@Override
	public void setNumber(int number) {
		textQuestionEntity.setNumber(number);
	}

	@Override
	public boolean isRequired() {
		return textQuestionEntity.isRequired();
	}

	@Override
	public void setRequired(boolean required) {
		textQuestionEntity.setRequired(required);
	}

	@Override
	public TextType getType() {
		return TextType.valueOf(textQuestionEntity.getType());
	}

	@Override
	public void setType(TextType tipoPregunta) {
		if (tipoPregunta != null) {
			textQuestionEntity.setType(tipoPregunta.toString());
		} else {
			textQuestionEntity.setType(null);
		}
	}

	@Override
	public SectionModel getSection() {
		return new SectionAdapter(em, textQuestionEntity.getSection());
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
