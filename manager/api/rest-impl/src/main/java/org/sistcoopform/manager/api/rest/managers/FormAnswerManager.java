package org.sistcoopform.manager.api.rest.managers;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoopform.manager.api.beans.representations.idm.AnswerRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.FormAnswerRepresentation;
import org.sistcoopform.manager.api.model.AnswerModel;
import org.sistcoopform.manager.api.model.DateTimeAnswerModel;
import org.sistcoopform.manager.api.model.FormAnswerModel;
import org.sistcoopform.manager.api.model.FormModel;
import org.sistcoopform.manager.api.model.GridAnswerModel;
import org.sistcoopform.manager.api.model.ModelException;
import org.sistcoopform.manager.api.model.NumberAnswerModel;
import org.sistcoopform.manager.api.model.QuestionModel;
import org.sistcoopform.manager.api.model.ScaleAnswerModel;
import org.sistcoopform.manager.api.model.SectionModel;
import org.sistcoopform.manager.api.model.SelectAnswerModel;
import org.sistcoopform.manager.api.model.TextAnswerModel;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class FormAnswerManager {

	public void update(FormAnswerModel model, FormAnswerRepresentation rep) {
		model.setDate(Calendar.getInstance().getTime());
		model.setNote(rep.getNote());
		model.setUser(rep.getUser());
		model.commit();
	}

	public boolean activeFormAnswer(FormAnswerModel formAnswer) {
		// verificar si puede ser activado
		/*FormModel form = formAnswer.getForm();
		Set<SectionModel> sections = form.getSections();

		Set<QuestionModel> questionRequiredByForm = new HashSet<>();
		for (SectionModel section : sections) {
			Set<QuestionModel> questions = section.getQuestions();
			for (QuestionModel question : questions) {
				if(question.is)
			}
		}*/

		formAnswer.active();
		formAnswer.commit();
		return true;
	}

	public void updateAnswer(AnswerModel model, AnswerRepresentation rep) {
		if (model instanceof TextAnswerModel) {
			TextAnswerModel textAnswer = (TextAnswerModel) model;
			textAnswer.setValue(rep.getStringValue());
		} else if (model instanceof DateTimeAnswerModel) {
			DateTimeAnswerModel datetimeAnswer = (DateTimeAnswerModel) model;
			datetimeAnswer.setDate(rep.getDateValue());
		} else if (model instanceof NumberAnswerModel) {
			NumberAnswerModel numericAnswer = (NumberAnswerModel) model;
			numericAnswer.setValue(rep.getNumberValue());
		} else if (model instanceof ScaleAnswerModel) {
			ScaleAnswerModel scaleAnswer = (ScaleAnswerModel) model;
			scaleAnswer.setValue(rep.getIntegerValue());
		} else if (model instanceof SelectAnswerModel) {
			SelectAnswerModel selectAnswer = (SelectAnswerModel) model;
			selectAnswer.setValues(rep.getListValues());
		} else if (model instanceof GridAnswerModel) {
			GridAnswerModel gridAnswer = (GridAnswerModel) model;
			gridAnswer.setValues(rep.getMapValues());
		} else {
			throw new ModelException("Question is not correct defined in updateAnswer");
		}

		model.commit();
	}

}