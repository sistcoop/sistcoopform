package org.sistcoopform.manager.api.rest.managers;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.sistcoopform.manager.api.beans.representations.idm.FormRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.GridColumnRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.GridRowRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.QuestionRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.SectionRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.SelectOptionRepresentation;
import org.sistcoopform.manager.api.model.DateTimeQuestionModel;
import org.sistcoopform.manager.api.model.FormModel;
import org.sistcoopform.manager.api.model.GridColumnModel;
import org.sistcoopform.manager.api.model.GridQuestionModel;
import org.sistcoopform.manager.api.model.GridRowModel;
import org.sistcoopform.manager.api.model.ModelException;
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

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class FormManager {

	@Inject
	private QuestionProvider questionProvider;

	public void update(FormModel model, FormRepresentation rep) {
		model.setTitle(rep.getTitle());
		model.setDescription(rep.getDescription());
		model.commit();
	}

	public void updateSection(SectionModel model, SectionRepresentation rep) {
		model.setTitle(rep.getTitle());
		model.setDescription(rep.getDescription());
		model.setNumber(rep.getNumber());
		model.commit();
	}

	public void updateQuestion(QuestionModel model, QuestionRepresentation rep) {
		model.setTitle(rep.getTitle());
		model.setNumber(rep.getNumber());
		model.setDescription(rep.getDescription());

		if (model instanceof TextQuestionModel) {			
			TextQuestionModel textQuestion = (TextQuestionModel) model;
			textQuestion.setRequired(rep.isRequired());
			textQuestion.setType(TextType.valueOf(rep.getType()));
		} else if (model instanceof DateTimeQuestionModel) {
			DateTimeQuestionModel datetimeQuestion = (DateTimeQuestionModel) model;
			datetimeQuestion.setRequired(rep.isRequired());
			datetimeQuestion.setType(DateTimeType.valueOf(rep.getType()));
		} else if (model instanceof NumericQuestionModel) {			
			NumericQuestionModel numericQuestion = (NumericQuestionModel) model;
			numericQuestion.setRequired(rep.isRequired());
			numericQuestion.setType(NumericType.valueOf(rep.getType()));
		} else if (model instanceof ScaleQuestionModel) {			
			ScaleQuestionModel scaleQuestion = (ScaleQuestionModel) model;
			scaleQuestion.setTag1(rep.getTag1());
			scaleQuestion.setTag2(rep.getTag2());
			scaleQuestion.setMin(rep.getMin());
			scaleQuestion.setMax(rep.getMax());
		} else if (model instanceof SelectQuestionModel) {			
			SelectQuestionModel selectQuestion = (SelectQuestionModel) model;
			selectQuestion.setRequired(rep.isRequired());
			selectQuestion.setType(SelectType.valueOf(rep.getType()));

			selectQuestion.setOptions(new HashSet<SelectOptionModel>());
			Set<SelectOptionRepresentation> options = rep.getOptions();
			for (SelectOptionRepresentation option : options) {
				SelectOptionModel optionModel = questionProvider.createSelectOption(selectQuestion,
						option.getDenomination(), option.getNumber(), option.isEditable());
				selectQuestion.getOptions().add(optionModel);
			}
		} else if (model instanceof GridQuestionModel) {			
			GridQuestionModel gridQuestion = (GridQuestionModel) model;
			gridQuestion.setRequired(rep.isRequired());

			gridQuestion.setRows(new HashSet<GridRowModel>());
			Set<GridRowRepresentation> rows = rep.getRows();
			for (GridRowRepresentation row : rows) {
				GridRowModel rowModel = questionProvider.createGridRow(gridQuestion, row.getDenomination(),
						row.getNumber(), row.isEditable());
				gridQuestion.getRows().add(rowModel);
			}

			gridQuestion.setColumns(new HashSet<GridColumnModel>());
			Set<GridColumnRepresentation> columns = rep.getColumns();
			for (GridColumnRepresentation column : columns) {
				GridColumnModel columnModel = questionProvider.createGridColumn(gridQuestion, column.getDenomination(),
						column.getNumber(), column.isEditable());
				gridQuestion.getColumns().add(columnModel);
			}
		} else {
			throw new ModelException("No se reconoce el tipo de la pregunta");
		}		

		model.commit();
	}

}