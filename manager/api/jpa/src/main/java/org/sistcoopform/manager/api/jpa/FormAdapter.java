package org.sistcoopform.manager.api.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.FormEntity;
import org.sistcoopform.manager.api.jpa.entities.SectionEntity;
import org.sistcoopform.manager.api.model.FormModel;
import org.sistcoopform.manager.api.model.SectionModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class FormAdapter implements FormModel {

	private static final long serialVersionUID = 1L;

	private FormEntity formEntity;
	private EntityManager em;

	public FormAdapter(EntityManager em, FormEntity formularioEntity) {
		this.em = em;
		this.formEntity = formularioEntity;
	}

	public static FormEntity toFormEntity(FormModel model, EntityManager em) {
		if (model instanceof FormAdapter) {
			return ((FormAdapter) model).getFormularioEntity();
		}
		return em.getReference(FormEntity.class, model.getId());
	}

	public FormEntity getFormularioEntity() {
		return formEntity;
	}

	@Override
	public void commit() {
		em.merge(formEntity);
	}

	@Override
	public String getId() {
		return formEntity.getId();
	}

	@Override
	public String getTitle() {
		return formEntity.getTitle();
	}

	@Override
	public void setTitle(String titulo) {
		formEntity.setTitle(titulo);
	}

	@Override
	public String getDescription() {
		return formEntity.getDescription();
	}

	@Override
	public void setDescription(String descripcion) {
		formEntity.setDescription(descripcion);
	}

	@Override
	public boolean isActive() {
		return formEntity.isActive();
	}

	@Override
	public void active() {
		formEntity.setActive(true);
	}

	@Override
	public Set<SectionModel> getSections() {
		Set<SectionEntity> sectionEntities = formEntity.getSections();
		Set<SectionModel> result = new HashSet<>();
		for (SectionEntity sectionEntity : sectionEntities) {
			result.add(new SectionAdapter(em, sectionEntity));
		}
		return result;
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
		if (!(obj instanceof FormModel))
			return false;
		FormModel other = (FormModel) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
