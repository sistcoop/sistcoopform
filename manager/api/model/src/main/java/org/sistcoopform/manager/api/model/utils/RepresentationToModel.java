package org.sistcoopform.manager.api.model.utils;

import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoopform.manager.api.beans.representations.enums.QuestionAvailable;
import org.sistcoopform.manager.api.beans.representations.idm.AnswerRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.FormAnswerRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.FormRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.GridColumnRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.GridRowRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.QuestionRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.SectionRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.SelectOptionRepresentation;
import org.sistcoopform.manager.api.model.AnswerModel;
import org.sistcoopform.manager.api.model.AnswerProvider;
import org.sistcoopform.manager.api.model.DateTimeQuestionModel;
import org.sistcoopform.manager.api.model.FormAnswerModel;
import org.sistcoopform.manager.api.model.FormAnswerProvider;
import org.sistcoopform.manager.api.model.FormModel;
import org.sistcoopform.manager.api.model.FormProvider;
import org.sistcoopform.manager.api.model.GridColumnModel;
import org.sistcoopform.manager.api.model.GridQuestionModel;
import org.sistcoopform.manager.api.model.GridRowModel;
import org.sistcoopform.manager.api.model.ModelException;
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

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RepresentationToModel {
	
	public FormModel createForm(FormRepresentation rep, FormProvider formProvider) {
		return formProvider.create(rep.getTitle(), rep.getDescription());
	}

	public SectionModel createSection(FormModel form, SectionRepresentation rep, SectionProvider sectionProvider) {
		return sectionProvider.create(form, rep.getTitle(), rep.getDescription(), rep.getNumber());
	}

	public QuestionModel createQuestion(SectionModel section, QuestionRepresentation rep,
			QuestionProvider questionProvider) throws Exception {
		if (rep.getQuestion().equals(QuestionAvailable.TEXT)) {
			return questionProvider.createTextQuestion(section, rep.getTitle(), rep.getDescription(), rep.getNumber(),
					TextType.valueOf(rep.getType()), rep.isRequired());
		} else if (rep.getQuestion().equals(QuestionAvailable.NUMBER)) {
			return questionProvider.createNumberQuestion(section, rep.getTitle(), rep.getDescription(), rep.getNumber(),
					NumericType.valueOf(rep.getType()), rep.isRequired());
		} else if (rep.getQuestion().equals(QuestionAvailable.DATETIME)) {
			return questionProvider.createDateTimeQuestion(section, rep.getTitle(), rep.getDescription(),
					rep.getNumber(), DateTimeType.valueOf(rep.getType()), rep.isRequired());
		} else if (rep.getQuestion().equals(QuestionAvailable.SCALE)) {
			return questionProvider.createScaleQuestion(section, rep.getTitle(), rep.getDescription(), rep.getNumber(),
					rep.getTag1(), rep.getTag2(), rep.getMin(), rep.getMax(), rep.isRequired());
		} else if (rep.getQuestion().equals(QuestionAvailable.SELECT)) {
			SelectQuestionModel question = questionProvider.createSelectQuestion(section, rep.getTitle(),
					rep.getDescription(), rep.getNumber(), SelectType.valueOf(rep.getType()), rep.isRequired());

			Set<SelectOptionRepresentation> options = rep.getSelectOptions();
			for (SelectOptionRepresentation option : options) {
				SelectOptionModel optionModel = questionProvider.createSelectOption(question, option.getDenomination(),
						option.getNumber(), option.isEditable());
				question.getOptions().add(optionModel);
			}
			question.commit();

			return question;
		} else if (rep.getQuestion().equals(QuestionAvailable.GRID)) {
			GridQuestionModel question = questionProvider.createGridQuestion(section, rep.getTitle(),
					rep.getDescription(), rep.getNumber(), rep.isRequired());

			Set<GridRowRepresentation> rows = rep.getRows();
			for (GridRowRepresentation row : rows) {
				GridRowModel rowModel = questionProvider.createGridRow(question, row.getDenomination(), row.getNumber(),
						row.isEditable());
				question.getRows().add(rowModel);
			}

			Set<GridColumnRepresentation> columns = rep.getColumns();
			for (GridColumnRepresentation column : columns) {
				GridColumnModel columnModel = questionProvider.createGridColumn(question, column.getDenomination(),
						column.getNumber(), column.isEditable());
				question.getColumns().add(columnModel);
			}
			question.commit();

			return question;
		} else {
			throw new Exception("Question is not supported by the system");
		}
	}

	public FormAnswerModel createFormAnswer(FormModel form, FormAnswerRepresentation rep, FormAnswerProvider provider) {
		return provider.create(form, rep.getUser());
	}

	public AnswerModel createAnswer(FormAnswerModel formAnswer, QuestionModel question, AnswerRepresentation rep,
			AnswerProvider answerProvider) {
		if (question instanceof TextQuestionModel) {			
			return answerProvider.createTextAnswer(formAnswer, question, rep.getStringValue());
		} else if (question instanceof DateTimeQuestionModel) {
			return answerProvider.createDateTimeAnswer(formAnswer, question, rep.getDateValue());
		} else if (question instanceof NumericQuestionModel) {			
			return answerProvider.createNumberAnswer(formAnswer, question, rep.getNumberValue());
		} else if (question instanceof ScaleQuestionModel) {			
			return answerProvider.createScaleAnswer(formAnswer, question, rep.getIntegerValue());
		} else if (question instanceof SelectQuestionModel) {			
			return answerProvider.createSelectAnswer(formAnswer, question, rep.getListValues());
		} else if (question instanceof GridQuestionModel) {			
			return answerProvider.createGridAnswer(formAnswer, question, rep.getMapValues());
		} else {
			throw new ModelException("Question type is not valid on createAnswer");
		}	
	}

}
