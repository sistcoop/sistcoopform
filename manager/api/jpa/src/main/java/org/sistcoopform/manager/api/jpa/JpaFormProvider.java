package org.sistcoopform.manager.api.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sistcoopform.manager.api.jpa.entities.FormEntity;
import org.sistcoopform.manager.api.model.FormModel;
import org.sistcoopform.manager.api.model.FormProvider;
import org.sistcoopform.manager.api.model.search.SearchCriteriaModel;
import org.sistcoopform.manager.api.model.search.SearchResultsModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(FormProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaFormProvider extends AbstractHibernateStorage implements FormProvider {

	private static final String TITLE = "title";

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
	public FormModel create(String title, String description) {
		FormEntity formEntity = new FormEntity();
		formEntity.setTitle(title);
		formEntity.setDescription(description);
		formEntity.setActive(false);
		em.persist(formEntity);
		return new FormAdapter(em, formEntity);
	}

	@Override
	public FormModel findById(String id) {
		FormEntity formularioEntity = em.find(FormEntity.class, id);
		return formularioEntity != null ? new FormAdapter(em, formularioEntity) : null;
	}

	@Override
	public boolean remove(FormModel form) {
		FormEntity formEntity = em.find(FormEntity.class, form.getId());
		em.remove(formEntity);
		return true;
	}

	@Override
	public List<FormModel> getAll() {
		return getAll(-1, -1);
	}

	@Override
	public List<FormModel> getAll(int firstResult, int maxResults) {
		TypedQuery<FormEntity> query = em.createNamedQuery("FormEntity.findAll", FormEntity.class);
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}
		List<FormEntity> entities = query.getResultList();
		List<FormModel> models = new ArrayList<FormModel>();
		for (FormEntity formEntity : entities) {
			models.add(new FormAdapter(em, formEntity));
		}
		return models;
	}

	@Override
	public List<FormModel> search(String filterText) {
		return search(filterText, -1, -1);
	}

	@Override
	public List<FormModel> search(String filterText, int firstResult, int maxResults) {
		TypedQuery<FormEntity> query = em.createNamedQuery("FormEntity.findByFilterText", FormEntity.class);
		query.setParameter("filterText", "%" + filterText.toLowerCase() + "%");
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}
		List<FormEntity> entities = query.getResultList();
		List<FormModel> models = new ArrayList<FormModel>();
		for (FormEntity formEntity : entities) {
			models.add(new FormAdapter(em, formEntity));
		}

		return models;
	}

	@Override
	public List<FormModel> searchByAttributes(Map<String, Object> attributes) {
		return searchByAttributes(attributes, -1, -1);
	}

	@Override
	public List<FormModel> searchByAttributes(Map<String, Object> attributes, int firstResult, int maxResults) {
		StringBuilder builder = new StringBuilder("SELECT t FROM FormEntity");
		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attribute = null;
			String parameterName = null;
			if (entry.getKey().equals(FormModel.TITLE)) {
				attribute = "lower(t.title)";
				parameterName = JpaFormProvider.TITLE;
			}

			if (attribute != null) {
				builder.append(" and ");
				builder.append(attribute).append(" like :").append(parameterName);
			} else {
				if (entry.getKey().equalsIgnoreCase(FormModel.TITLE)) {
					attribute = "lower(t.title)";
					parameterName = JpaFormProvider.TITLE;
				}

				if (attribute == null) {
					continue;
				}
				builder.append(" and ");
				builder.append(attribute).append(" = :").append(parameterName);
			}
		}
		builder.append(" order by t.title");
		String q = builder.toString();
		TypedQuery<FormEntity> query = em.createQuery(q, FormEntity.class);
		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String parameterName = null;
			if (entry.getKey().equals(FormModel.TITLE)) {
				parameterName = JpaFormProvider.TITLE;
			}

			if (parameterName != null) {
				query.setParameter(parameterName, "%" + String.valueOf(entry.getValue()).toLowerCase() + "%");
			} else {
				if (entry.getKey().equalsIgnoreCase(FormModel.TITLE)) {
					parameterName = JpaFormProvider.TITLE;
				}

				if (parameterName == null) {
					continue;
				}
				query.setParameter(parameterName, entry.getValue());
			}
		}
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}
		List<FormEntity> results = query.getResultList();
		List<FormModel> formModels = new ArrayList<FormModel>();
		for (FormEntity entity : results){
			formModels.add(new FormAdapter(em, entity));
		}
			
		return formModels;
	}

	@Override
	public SearchResultsModel<FormModel> search(SearchCriteriaModel criteria) {
		SearchResultsModel<FormEntity> entityResult = find(criteria, FormEntity.class);

		SearchResultsModel<FormModel> modelResult = new SearchResultsModel<>();
		List<FormModel> list = new ArrayList<>();
		for (FormEntity entity : entityResult.getModels()) {
			list.add(new FormAdapter(em, entity));
		}
		modelResult.setTotalSize(entityResult.getTotalSize());
		modelResult.setModels(list);
		return modelResult;
	}

	@Override
	public SearchResultsModel<FormModel> search(SearchCriteriaModel criteria, String filterText) {
		SearchResultsModel<FormEntity> entityResult = findFullText(criteria, FormEntity.class, filterText, TITLE);

		SearchResultsModel<FormModel> modelResult = new SearchResultsModel<>();
		List<FormModel> list = new ArrayList<>();
		for (FormEntity entity : entityResult.getModels()) {
			list.add(new FormAdapter(em, entity));
		}
		modelResult.setTotalSize(entityResult.getTotalSize());
		modelResult.setModels(list);
		return modelResult;
	}

}
