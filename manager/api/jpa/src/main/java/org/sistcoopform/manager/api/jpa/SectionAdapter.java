package org.sistcoopform.manager.api.jpa;

import javax.persistence.EntityManager;

import org.sistcoopform.manager.api.jpa.entities.SectionEntity;
import org.sistcoopform.manager.api.model.FormModel;
import org.sistcoopform.manager.api.model.SectionModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class SectionAdapter implements SectionModel {

	private static final long serialVersionUID = 1L;

	private SectionEntity sectionEntity;
	private EntityManager em;

	public SectionAdapter(EntityManager em, SectionEntity SectionEntity) {
		this.em = em;
		this.sectionEntity = SectionEntity;
	}

	public static SectionEntity toSectionEntity(SectionModel model, EntityManager em) {
		if (model instanceof SectionAdapter) {
			return ((SectionAdapter) model).getSectionEntity();
		}
		return em.getReference(SectionEntity.class, model.getId());
	}

	public SectionEntity getSectionEntity() {
		return sectionEntity;
	}

	@Override
	public void commit() {
		em.merge(sectionEntity);
	}

	@Override
	public String getId() {
		return sectionEntity.getId();
	}

	@Override
	public String getTitle() {
		return sectionEntity.getTitle();
	}

	@Override
	public void setTitle(String title) {
		sectionEntity.setTitle(title);
	}

	@Override
	public String getDescription() {
		return sectionEntity.getDescription();
	}

	@Override
	public void setDescription(String description) {
		sectionEntity.setDescription(description);
	}

	@Override
	public int getNumber() {
		return sectionEntity.getNumber();
	}

	@Override
	public void setNumber(int number) {
		sectionEntity.setNumber(number);
	}

	@Override
	public FormModel getForm() {
		return new FormAdapter(em, sectionEntity.getForm());
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
		if (!(obj instanceof SectionModel))
			return false;
		SectionModel other = (SectionModel) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
