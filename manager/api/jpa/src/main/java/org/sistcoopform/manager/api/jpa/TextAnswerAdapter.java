package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.TextAnswerEntity;
import org.sistcoopform.manager.api.model.TextAnswerModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class TextAnswerAdapter extends AbstractAnswerAdapter implements TextAnswerModel {

	private static final long serialVersionUID = 1L;

	private TextAnswerEntity textAnswerEntity;
	private EntityManager em;

	public TextAnswerAdapter(EntityManager em, TextAnswerEntity textAnswerEntity) {
		super(em, textAnswerEntity);
		this.em = em;
		this.textAnswerEntity = textAnswerEntity;
	}

	public static TextAnswerEntity toTextAnswerEntity(TextAnswerModel model, EntityManager em) {
		if (model instanceof TextAnswerAdapter) {
			return ((TextAnswerAdapter) model).getTextAnswerEntity();
		}
		return em.getReference(TextAnswerEntity.class, model.getId());
	}

	public TextAnswerEntity getTextAnswerEntity() {
		return textAnswerEntity;
	}

	@Override
	public String getValue() {
		return textAnswerEntity.getValue();
	}

	@Override
	public void setValue(String value) {
		textAnswerEntity.setValue(value);
	}

}
