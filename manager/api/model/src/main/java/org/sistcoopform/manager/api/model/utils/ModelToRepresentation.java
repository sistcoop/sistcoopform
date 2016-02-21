package org.sistcoopform.manager.api.model.utils;

import java.util.Set;

import org.sistcoopform.manager.api.beans.representations.enums.QuestionAvailable;
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
import org.sistcoopform.manager.api.model.NumericQuestionModel;
import org.sistcoopform.manager.api.model.QuestionModel;
import org.sistcoopform.manager.api.model.ScaleQuestionModel;
import org.sistcoopform.manager.api.model.SectionModel;
import org.sistcoopform.manager.api.model.SelectOptionModel;
import org.sistcoopform.manager.api.model.SelectQuestionModel;
import org.sistcoopform.manager.api.model.TextQuestionModel;

public class ModelToRepresentation {

	public static FormRepresentation toRepresentation(FormModel model) {
		if (model == null)
			return null;

		FormRepresentation rep = new FormRepresentation();
		rep.setId(model.getId());
		rep.setTitle(model.getTitle());
		rep.setDescription(model.getDescription());
		return rep;
	}

	public static SectionRepresentation toRepresentation(SectionModel model) {
		if (model == null)
			return null;

		SectionRepresentation rep = new SectionRepresentation();
		rep.setId(model.getId());
		rep.setTitle(model.getTitle());
		rep.setNumber(model.getNumber());
		rep.setDescription(model.getDescription());
		return rep;
	}

	public static QuestionRepresentation toRepresentation(QuestionModel model) {
		if (model == null)
			return null;

		QuestionRepresentation rep = new QuestionRepresentation();
		rep.setId(model.getId());
		rep.setTitle(model.getTitle());
		rep.setNumber(model.getNumber());
		rep.setDescription(model.getDescription());

		if (model instanceof TextQuestionModel) {
			TextQuestionModel textQuestion = (TextQuestionModel) model;
			rep.setQuestion(QuestionAvailable.TEXT);
			rep.setRequired(textQuestion.isRequired());
			rep.setType(textQuestion.getType().toString());
		} else if (model instanceof DateTimeQuestionModel) {
			DateTimeQuestionModel datetimeQuestion = (DateTimeQuestionModel) model;
			rep.setQuestion(QuestionAvailable.DATETIME);
			rep.setRequired(datetimeQuestion.isRequired());
			rep.setType(datetimeQuestion.getType().toString());
		} else if (model instanceof NumericQuestionModel) {
			NumericQuestionModel numericQuestion = (NumericQuestionModel) model;
			rep.setQuestion(QuestionAvailable.NUMBER);
			rep.setRequired(numericQuestion.isRequired());
			rep.setType(numericQuestion.getType().toString());
		} else if (model instanceof ScaleQuestionModel) {
			ScaleQuestionModel scaleQuestion = (ScaleQuestionModel) model;
			rep.setQuestion(QuestionAvailable.SCALE);
			rep.setTag1(scaleQuestion.getTag1());
			rep.setTag2(scaleQuestion.getTag2());
			rep.setMin(scaleQuestion.getMin());
			rep.setMax(scaleQuestion.getMax());
		} else if (model instanceof SelectQuestionModel) {
			SelectQuestionModel selectQuestion = (SelectQuestionModel) model;
			rep.setQuestion(QuestionAvailable.SELECT);
			rep.setRequired(selectQuestion.isRequired());
			rep.setType(selectQuestion.getType().toString());

			Set<SelectOptionModel> options = selectQuestion.getOptions();
			for (SelectOptionModel selectOptionModel : options) {
				rep.getOptions().add(toRepresentation(selectOptionModel));
			}
		} else if (model instanceof GridQuestionModel) {
			GridQuestionModel gridQuestion = (GridQuestionModel) model;
			rep.setQuestion(QuestionAvailable.GRID);
			rep.setRequired(gridQuestion.isRequired());

			Set<GridRowModel> rows = gridQuestion.getRows();
			for (GridRowModel gridRowModel : rows) {
				rep.getRows().add(toRepresentation(gridRowModel));
			}

			Set<GridColumnModel> columns = gridQuestion.getColumns();
			for (GridColumnModel gridColumnModel : columns) {
				rep.getColumns().add(toRepresentation(gridColumnModel));
			}
		}

		return rep;
	}

	public static SelectOptionRepresentation toRepresentation(SelectOptionModel model) {
		if (model == null)
			return null;

		SelectOptionRepresentation rep = new SelectOptionRepresentation();
		rep.setId(model.getId());
		rep.setDenomination(model.getDenomination());
		rep.setNumber(model.getNumber());
		rep.setEditable(model.isEditable());
		return rep;
	}

	public static GridRowRepresentation toRepresentation(GridRowModel model) {
		if (model == null)
			return null;

		GridRowRepresentation rep = new GridRowRepresentation();
		rep.setId(model.getId());
		rep.setDenomination(model.getDenomination());
		rep.setNumber(model.getNumber());
		rep.setEditable(model.isEditable());
		return rep;
	}

	public static GridColumnRepresentation toRepresentation(GridColumnModel model) {
		if (model == null)
			return null;

		GridColumnRepresentation rep = new GridColumnRepresentation();
		rep.setId(model.getId());
		rep.setDenomination(model.getDenomination());
		rep.setNumber(model.getNumber());
		rep.setEditable(model.isEditable());
		return rep;
	}

}
