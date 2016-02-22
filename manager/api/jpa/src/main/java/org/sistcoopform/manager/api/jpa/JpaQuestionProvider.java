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

import org.sistcoopform.manager.api.jpa.entities.DateTimeQuestionEntity;
import org.sistcoopform.manager.api.jpa.entities.GridColumnEntity;
import org.sistcoopform.manager.api.jpa.entities.GridQuestionEntity;
import org.sistcoopform.manager.api.jpa.entities.GridRowEntity;
import org.sistcoopform.manager.api.jpa.entities.NumericQuestionEntity;
import org.sistcoopform.manager.api.jpa.entities.QuestionEntity;
import org.sistcoopform.manager.api.jpa.entities.ScaleQuestionEntity;
import org.sistcoopform.manager.api.jpa.entities.SectionEntity;
import org.sistcoopform.manager.api.jpa.entities.SelectOptionEntity;
import org.sistcoopform.manager.api.jpa.entities.SelectQuestionEntity;
import org.sistcoopform.manager.api.jpa.entities.TextQuestionEntity;
import org.sistcoopform.manager.api.model.DateTimeQuestionModel;
import org.sistcoopform.manager.api.model.GridColumnModel;
import org.sistcoopform.manager.api.model.GridQuestionModel;
import org.sistcoopform.manager.api.model.GridRowModel;
import org.sistcoopform.manager.api.model.NumericQuestionModel;
import org.sistcoopform.manager.api.model.QuestionModel;
import org.sistcoopform.manager.api.model.QuestionProvider;
import org.sistcoopform.manager.api.model.ScaleQuestionModel;
import org.sistcoopform.manager.api.model.SectionModel;
import org.sistcoopform.manager.api.model.SelectOptionModel;
import org.sistcoopform.manager.api.model.SelectQuestionModel;
import org.sistcoopform.manager.api.model.TextQuestionModel;
import org.sistcoopform.manager.api.model.enums.DateTimeType;
import org.sistcoopform.manager.api.model.enums.NumericType;
import org.sistcoopform.manager.api.model.enums.SelectType;
import org.sistcoopform.manager.api.model.enums.TextType;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(QuestionProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaQuestionProvider extends AbstractHibernateStorage implements QuestionProvider {

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
	public TextQuestionModel createTextQuestion(SectionModel section, String title, String description, int number,
			TextType type, boolean required) {
		SectionEntity sectionEntity = em.find(SectionEntity.class, section.getId());

		TextQuestionEntity question = new TextQuestionEntity();
		question.setTitle(title);
		question.setDescription(description);
		question.setNumber(number);
		question.setType(type.toString());
		question.setRequired(required);
		question.setSection(sectionEntity);
		em.persist(question);
		return new TextQuestionAdapter(em, question);
	}

	@Override
	public NumericQuestionModel createNumberQuestion(SectionModel section, String title, String description, int number,
			NumericType type, boolean required) {
		SectionEntity sectionEntity = em.find(SectionEntity.class, section.getId());

		NumericQuestionEntity question = new NumericQuestionEntity();
		question.setTitle(title);
		question.setDescription(description);
		question.setNumber(number);
		question.setType(type.toString());
		question.setRequired(required);
		question.setSection(sectionEntity);
		em.persist(question);
		return new NumericQuestionAdapter(em, question);
	}

	@Override
	public DateTimeQuestionModel createDateTimeQuestion(SectionModel section, String title, String description,
			int number, DateTimeType type, boolean required) {
		SectionEntity sectionEntity = em.find(SectionEntity.class, section.getId());

		DateTimeQuestionEntity question = new DateTimeQuestionEntity();
		question.setTitle(title);
		question.setDescription(description);
		question.setNumber(number);
		question.setType(type.toString());
		question.setRequired(required);
		question.setSection(sectionEntity);
		em.persist(question);
		return new DateTimeQuestionAdapter(em, question);
	}

	@Override
	public ScaleQuestionModel createScaleQuestion(SectionModel section, String title, String description, int number,
			String tag1, String tag2, int min, int max, boolean required) {
		SectionEntity sectionEntity = em.find(SectionEntity.class, section.getId());

		ScaleQuestionEntity question = new ScaleQuestionEntity();
		question.setTitle(title);
		question.setDescription(description);
		question.setNumber(number);
		question.setRequired(required);
		question.setTag1(tag1);
		question.setTag2(tag2);
		question.setMin(min);
		question.setMax(max);
		question.setSection(sectionEntity);
		em.persist(question);
		return new ScaleQuestionAdapter(em, question);
	}

	@Override
	public SelectQuestionModel createSelectQuestion(SectionModel section, String title, String description, int number,
			SelectType type, boolean required) {
		SectionEntity sectionEntity = em.find(SectionEntity.class, section.getId());

		SelectQuestionEntity question = new SelectQuestionEntity();
		question.setTitle(title);
		question.setDescription(description);
		question.setNumber(number);
		question.setType(type.toString());
		question.setRequired(required);
		question.setSection(sectionEntity);
		em.persist(question);
		return new SelectQuestionAdapter(em, question);
	}

	@Override
	public SelectOptionModel createSelectOption(SelectQuestionModel question, String denomination, int number,
			boolean editable) {
		SelectQuestionEntity questionEntity = em.find(SelectQuestionEntity.class, question.getId());

		SelectOptionEntity option = new SelectOptionEntity();
		option.setDenomination(denomination);
		option.setEditable(editable);
		option.setNumber(number);
		option.setSelectQuestion(questionEntity);
		em.persist(option);
		return new SelectOptionAdapter(em, option);
	}

	@Override
	public GridQuestionModel createGridQuestion(SectionModel section, String title, String description, int number,
			boolean required) {
		SectionEntity sectionEntity = em.find(SectionEntity.class, section.getId());

		GridQuestionEntity question = new GridQuestionEntity();
		question.setTitle(title);
		question.setDescription(description);
		question.setNumber(number);
		question.setRequired(required);
		question.setSection(sectionEntity);
		em.persist(question);
		return new GridQuestionAdapter(em, question);
	}

	@Override
	public GridRowModel createGridRow(GridQuestionModel question, String denomination, int number, boolean editable) {
		GridQuestionEntity questionEntity = em.find(GridQuestionEntity.class, question.getId());

		GridRowEntity row = new GridRowEntity();
		row.setDenomination(denomination);
		row.setEditable(editable);
		row.setNumber(number);
		row.setGridQuestion(questionEntity);
		em.persist(row);
		return new GridRowAdapter(em, row);
	}

	@Override
	public GridColumnModel createGridColumn(GridQuestionModel question, String denomination, int number,
			boolean editable) {
		GridQuestionEntity questionEntity = em.find(GridQuestionEntity.class, question.getId());

		GridColumnEntity row = new GridColumnEntity();
		row.setDenomination(denomination);
		row.setEditable(editable);
		row.setNumber(number);
		row.setGridQuestion(questionEntity);
		em.persist(row);
		return new GridColumnAdapter(em, row);
	}

	@Override
	public QuestionModel findById(String id) {
		QuestionEntity questionEntity = em.find(QuestionEntity.class, id);
		return AbstractQuestionAdapter.toQuestionModel(questionEntity, em);
	}

	@Override
	public boolean remove(QuestionModel question) {
		QuestionEntity questionEntity = em.find(QuestionEntity.class, question.getId());
		em.remove(questionEntity);
		return true;
	}

	@Override
	public List<QuestionModel> getAll(SectionModel section) {
		return getAll(section, -1, -1);
	}

	@Override
	public List<QuestionModel> getAll(SectionModel section, int firstResult, int maxResults) {
		TypedQuery<QuestionEntity> query = em.createNamedQuery("QuestionEntity.findBySectionId", QuestionEntity.class);
		query.setParameter("sectionId", section.getId());
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}
		List<QuestionEntity> entities = query.getResultList();
		List<QuestionModel> models = new ArrayList<QuestionModel>();
		for (QuestionEntity questionEntity : entities) {
			models.add(AbstractQuestionAdapter.toQuestionModel(questionEntity, em));
		}
		return models;
	}

}
