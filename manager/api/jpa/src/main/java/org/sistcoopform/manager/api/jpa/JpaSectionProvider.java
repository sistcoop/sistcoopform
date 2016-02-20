package org.sistcoopform.manager.api.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sistcoopform.manager.api.jpa.entities.FormEntity;
import org.sistcoopform.manager.api.jpa.entities.SectionEntity;
import org.sistcoopform.manager.api.model.FormModel;
import org.sistcoopform.manager.api.model.SectionModel;
import org.sistcoopform.manager.api.model.SectionProvider;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(SectionProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaSectionProvider extends AbstractHibernateStorage implements SectionProvider {

	// private static final String TITULO = "titulo";

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public SectionModel create(FormModel form, String title, String description, int number) {
		FormEntity formEntity = em.find(FormEntity.class, form.getId());

		SectionEntity sectionEntity = new SectionEntity();
		sectionEntity.setTitle(title);
		sectionEntity.setDescription(description);
		sectionEntity.setNumber(number);
		sectionEntity.setForm(formEntity);
		em.persist(sectionEntity);
		return new SectionAdapter(em, sectionEntity);
	}

	@Override
	public SectionModel findById(String id) {
		SectionEntity sectionEntity = em.find(SectionEntity.class, id);
		return sectionEntity != null ? new SectionAdapter(em, sectionEntity) : null;
	}

	@Override
	public boolean remove(SectionModel section) {
		SectionEntity sectionEntity = em.find(SectionEntity.class, section.getId());
		em.remove(sectionEntity);
		return true;
	}

	@Override
	public List<SectionModel> getAll(FormModel form) {
		return getAll(form, -1, -1);
	}

	@Override
	public List<SectionModel> getAll(FormModel formulario, int firstResult, int maxResults) {
		TypedQuery<SectionEntity> query = em.createNamedQuery("SectionEntity.findByFormId", SectionEntity.class);
		query.setParameter("formId", formulario.getId());
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}
		List<SectionEntity> entities = query.getResultList();
		List<SectionModel> models = new ArrayList<SectionModel>();
		for (SectionEntity entity : entities) {
			models.add(new SectionAdapter(em, entity));
		}
		return models;
	}

}
