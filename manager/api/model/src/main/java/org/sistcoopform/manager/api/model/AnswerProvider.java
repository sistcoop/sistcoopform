package org.sistcoopform.manager.api.model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Local;

import org.sistcoopform.manager.api.model.provider.Provider;

@Local
public interface AnswerProvider extends Provider {

	TextAnswerModel createTextAnswer(FormAnswerModel formAnswer, QuestionModel question, String value);

	NumericAnswerModel createNumberAnswer(FormAnswerModel formAnswer, QuestionModel question, double value);

	DateTimeAnswerModel createDateTimeAnswer(FormAnswerModel formAnswer, QuestionModel question, Date datetime);

	ScaleAnswerModel createScaleAnswer(FormAnswerModel formAnswer, QuestionModel question, int value);

	SelectAnswerModel createSelectAnswer(FormAnswerModel formAnswer, QuestionModel question, Set<String> values);

	GridAnswerModel createGridAnswer(FormAnswerModel formAnswer, QuestionModel question, Map<String, String> values);

	AnswerModel findById(String id);

	boolean remove(AnswerModel answer);

	List<AnswerModel> getAll(FormAnswerModel formAnswer);

	List<AnswerModel> getAll(FormAnswerModel formAnswer, int firstResult, int maxResults);

}
