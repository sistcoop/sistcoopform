package org.sistcoopform.manager.api.jpa;

import java.util.Date;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.FormAnswerEntity;
import org.sistcoopform.manager.api.model.FormAnswerModel;
import org.sistcoopform.manager.api.model.FormModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class FormAnswerAdapter implements FormAnswerModel {

	private static final long serialVersionUID = 1L;

	private FormAnswerEntity formAnswerEntity;
	private EntityManager em;

	public FormAnswerAdapter(EntityManager em, FormAnswerEntity formAnswerEntity) {
		this.em = em;
		this.formAnswerEntity = formAnswerEntity;
	}

	public static FormAnswerEntity toFormAnswerEntity(FormAnswerModel model, EntityManager em) {
		if (model instanceof FormAnswerAdapter) {
			return ((FormAnswerAdapter) model).getFormAnswerEntity();
		}
		return em.getReference(FormAnswerEntity.class, model.getId());
	}

	public FormAnswerEntity getFormAnswerEntity() {
		return this.formAnswerEntity;
	}

	@Override
	public void commit() {
		em.persist(formAnswerEntity);
	}

	@Override
	public String getId() {
		return formAnswerEntity.getId();
	}

	@Override
	public Date getDate() {
		return formAnswerEntity.getDate();
	}

	@Override
	public void setDate(Date date) {
		formAnswerEntity.setDate(date);
	}

	@Override
	public String getUser() {
		return formAnswerEntity.getUser();
	}

	@Override
	public void setUser(String user) {
		formAnswerEntity.setUser(user);
	}

	@Override
	public String getNote() {
		return formAnswerEntity.getNote();
	}

	@Override
	public void setNote(String note) {
		formAnswerEntity.setNote(note);
	}

	@Override
	public boolean isValid() {
		return formAnswerEntity.isValid();
	}
	
	@Override
	public FormModel getForm() {
		return new FormAdapter(em, formAnswerEntity.getForm());
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
		if (!(obj instanceof FormAnswerModel))
			return false;
		FormAnswerModel other = (FormAnswerModel) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
