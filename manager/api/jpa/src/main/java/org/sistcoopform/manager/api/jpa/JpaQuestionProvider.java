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
import org.sistcoopform.manager.api.jpa.entities.TextQuestionEntity;
import org.sistcoopform.manager.api.model.DateTimeQuestionModel;
import org.sistcoopform.manager.api.model.FormModel;
import org.sistcoopform.manager.api.model.GridColumnModel;
import org.sistcoopform.manager.api.model.GridQuestionModel;
import org.sistcoopform.manager.api.model.GridRowModel;
import org.sistcoopform.manager.api.model.NumericQuestionModel;
import org.sistcoopform.manager.api.model.QuestionModel;
import org.sistcoopform.manager.api.model.QuestionProvider;
import org.sistcoopform.manager.api.model.ScaleQuestionModel;
import org.sistcoopform.manager.api.model.SectionModel;
import org.sistcoopform.manager.api.model.SectionProvider;
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

		SectionEntity SectionEntity = new SectionEntity();
		SectionEntity.setTitle(title);
		SectionEntity.setDescription(description);
		SectionEntity.setNumber(number);
		SectionEntity.setForm(formEntity);
		return new SectionAdapter(em, SectionEntity);
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
		question.setSection(sectionEntity)
		em.persist(question);
		return null;
	}

	@Override
	public NumericQuestionModel createNumberQuestion(SectionModel section, String title, String description, int number,
			NumericType type, boolean required) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DateTimeQuestionModel createDateTimeQuestion(SectionModel section, String title, String description,
			int number, DateTimeType type, boolean required) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SelectQuestionModel createSeleccion(SectionModel section, String title, String description, int number,
			SelectType type, boolean required) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SelectOptionModel createOpcionSeleccion(SelectQuestionModel preguntaSeleccion, String denominacion,
			int number, boolean editable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScaleQuestionModel createEscalaLineal(SectionModel section, String title, String description, int number,
			String etiqueta1, String etiqueta2, int desde, int hasta, boolean required) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GridQuestionModel createCuadricula(SectionModel section, String title, String description, int number,
			boolean requiereRespuestaPorFila) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GridRowModel createFilaCuadricula(GridQuestionModel preguntaCuadricula, String denominacion, int number,
			boolean editable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GridColumnModel createColumnaCuadricula(GridQuestionModel preguntaCuadricula, String denominacion,
			int number, boolean editable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionModel findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(QuestionModel question) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<QuestionModel> getAll(SectionModel section) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionModel> getAll(SectionModel section, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

}
