package org.sistcoopform.manager.api.jpa;

import java.util.ArrayList;
import java.util.Calendar;
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

import org.sistcoopform.manager.api.jpa.entities.FormAnswerEntity;
import org.sistcoopform.manager.api.jpa.entities.FormEntity;
import org.sistcoopform.manager.api.model.FormAnswerModel;
import org.sistcoopform.manager.api.model.FormAnswerProvider;
import org.sistcoopform.manager.api.model.FormModel;
import org.sistcoopform.manager.api.model.search.SearchCriteriaModel;
import org.sistcoopform.manager.api.model.search.SearchResultsModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(FormAnswerProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaFormAnswerProvider extends AbstractHibernateStorage implements FormAnswerProvider {

	private static final String USER = "user";
	private static final String VALID = "valid";

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
	public FormAnswerModel findById(String id) {
		FormAnswerEntity formAnswerEntity = em.find(FormAnswerEntity.class, id);
		return formAnswerEntity != null ? new FormAnswerAdapter(em, formAnswerEntity) : null;
	}

	@Override
	public FormAnswerModel create(FormModel form, String user) {
		FormEntity formEntity = em.find(FormEntity.class, form.getId());
		
		FormAnswerEntity formAnswerEntity = new FormAnswerEntity();
		formAnswerEntity.setUser(user);
		formAnswerEntity.setDate(Calendar.getInstance().getTime());
		formAnswerEntity.setValid(false);
		formAnswerEntity.setForm(formEntity);
		em.persist(formAnswerEntity);
		return new FormAnswerAdapter(em, formAnswerEntity);
	}

	@Override
	public boolean remove(FormAnswerModel formAnswer) {
		FormAnswerEntity formEntity = em.find(FormAnswerEntity.class, formAnswer.getId());
		em.remove(formEntity);
		return true;
	}

	@Override
	public List<FormAnswerModel> getAll() {
		return getAll(-1, -1);
	}

	@Override
	public List<FormAnswerModel> getAll(int firstResult, int maxResults) {
		TypedQuery<FormAnswerEntity> query = em.createNamedQuery("FormAnswerEntity.findAll", FormAnswerEntity.class);
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}
		List<FormAnswerEntity> entities = query.getResultList();
		List<FormAnswerModel> models = new ArrayList<FormAnswerModel>();
		for (FormAnswerEntity formAnswerEntity : entities) {
			models.add(new FormAnswerAdapter(em, formAnswerEntity));
		}
		return models;
	}

	@Override
	public List<FormAnswerModel> search(String filterText) {
		return search(filterText, -1, -1);
	}

	@Override
	public List<FormAnswerModel> search(String filterText, int firstResult, int maxResults) {
		TypedQuery<FormAnswerEntity> query = em.createNamedQuery("FormAnswerEntity.findByFilterText", FormAnswerEntity.class);
		query.setParameter("filterText", "%" + filterText.toLowerCase() + "%");
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}
		List<FormAnswerEntity> entities = query.getResultList();
		List<FormAnswerModel> models = new ArrayList<FormAnswerModel>();
		for (FormAnswerEntity formAnswerEntity : entities) {
			models.add(new FormAnswerAdapter(em, formAnswerEntity));
		}

		return models;
	}

	@Override
	public List<FormAnswerModel> searchByAttributes(Map<String, Object> attributes) {
		return searchByAttributes(attributes, -1, -1);
	}

	@Override
	public List<FormAnswerModel> searchByAttributes(Map<String, Object> attributes, int firstResult, int maxResults) {
		StringBuilder builder = new StringBuilder("SELECT t FROM FormAnswerEntity");
		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attribute = null;
			String parameterName = null;
			if (entry.getKey().equals(FormAnswerModel.USER)) {
				attribute = "lower(t.user)";
				parameterName = JpaFormAnswerProvider.USER;
			}
			if (entry.getKey().equals(FormAnswerModel.VALID)) {
				attribute = "t.valid";
				parameterName = JpaFormAnswerProvider.VALID;
			}

			if (attribute != null) {
				builder.append(" and ");
				builder.append(attribute).append(" like :").append(parameterName);
			} else {
				if (entry.getKey().equalsIgnoreCase(FormAnswerModel.USER)) {
					attribute = "lower(t.user)";
					parameterName = JpaFormAnswerProvider.USER;
				}
				if (entry.getKey().equalsIgnoreCase(FormAnswerModel.VALID)) {
					attribute = "t.valid";
					parameterName = JpaFormAnswerProvider.VALID;
				}

				if (attribute == null) {
					continue;
				}
				builder.append(" and ");
				builder.append(attribute).append(" = :").append(parameterName);
			}
		}
		builder.append(" order by t.date");
		String q = builder.toString();
		TypedQuery<FormAnswerEntity> query = em.createQuery(q, FormAnswerEntity.class);
		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String parameterName = null;
			if (entry.getKey().equals(FormAnswerModel.USER)) {
				parameterName = JpaFormAnswerProvider.USER;
			}
			if (entry.getKey().equals(FormAnswerModel.VALID)) {
				parameterName = JpaFormAnswerProvider.VALID;
			}

			if (parameterName != null) {
				query.setParameter(parameterName, "%" + String.valueOf(entry.getValue()).toLowerCase() + "%");
			} else {
				if (entry.getKey().equalsIgnoreCase(FormAnswerModel.USER)) {
					parameterName = JpaFormAnswerProvider.USER;
				}
				if (entry.getKey().equalsIgnoreCase(FormAnswerModel.VALID)) {
					parameterName = JpaFormAnswerProvider.VALID;
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
		List<FormAnswerEntity> results = query.getResultList();
		List<FormAnswerModel> formAnswerModels = new ArrayList<FormAnswerModel>();
		for (FormAnswerEntity entity : results) {
			formAnswerModels.add(new FormAnswerAdapter(em, entity));
		}
			
		return formAnswerModels;
	}

	@Override
	public SearchResultsModel<FormAnswerModel> search(SearchCriteriaModel criteria) {
		SearchResultsModel<FormAnswerEntity> entityResult = find(criteria, FormAnswerEntity.class);

		SearchResultsModel<FormAnswerModel> modelResult = new SearchResultsModel<>();
		List<FormAnswerModel> list = new ArrayList<>();
		for (FormAnswerEntity entity : entityResult.getModels()) {
			list.add(new FormAnswerAdapter(em, entity));
		}
		modelResult.setTotalSize(entityResult.getTotalSize());
		modelResult.setModels(list);
		return modelResult;
	}

	@Override
	public SearchResultsModel<FormAnswerModel> search(SearchCriteriaModel criteria, String filterText) {
		SearchResultsModel<FormAnswerEntity> entityResult = findFullText(criteria, FormAnswerEntity.class, filterText, USER, VALID);

		SearchResultsModel<FormAnswerModel> modelResult = new SearchResultsModel<>();
		List<FormAnswerModel> list = new ArrayList<>();
		for (FormAnswerEntity entity : entityResult.getModels()) {
			list.add(new FormAnswerAdapter(em, entity));
		}
		modelResult.setTotalSize(entityResult.getTotalSize());
		modelResult.setModels(list);
		return modelResult;
	}

}
