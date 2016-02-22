package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.TextQuestionEntity;
import org.sistcoopform.manager.api.model.TextQuestionModel;
import org.sistcoopform.manager.api.model.enums.TextType;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class TextQuestionAdapter extends AbstractQuestionAdapter implements TextQuestionModel {

	private static final long serialVersionUID = 1L;

	private TextQuestionEntity textQuestionEntity;
	private EntityManager em;

	public TextQuestionAdapter(EntityManager em, TextQuestionEntity textQuestionEntity) {
		super(em, textQuestionEntity);
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

}
