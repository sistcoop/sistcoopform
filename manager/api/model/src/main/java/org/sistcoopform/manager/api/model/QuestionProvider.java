package org.sistcoopform.manager.api.model;

import java.util.List;

import javax.ejb.Local;

import org.sistcoopform.manager.api.model.enums.NumericType;
import org.sistcoopform.manager.api.model.enums.SelectType;
import org.sistcoopform.manager.api.model.enums.TextType;
import org.sistcoopform.manager.api.model.enums.DateTimeType;
import org.sistcoopform.manager.api.model.provider.Provider;

@Local
public interface QuestionProvider extends Provider {

	TextQuestionModel createTextQuestion(SectionModel section, String title, String description, int number,
			TextType type, boolean required);

	NumericQuestionModel createNumberQuestion(SectionModel section, String title, String description, int number,
			NumericType type, boolean required);

	DateTimeQuestionModel createDateTimeQuestion(SectionModel section, String title, String description, int number,
			DateTimeType type, boolean required);

	ScaleQuestionModel createScaleQuestion(SectionModel section, String title, String description, int number,
			String tag1, String tag2, int desde, int hasta, boolean required);

	SelectQuestionModel createSelectQuestion(SectionModel section, String title, String description, int number,
			SelectType type, boolean required);

	SelectOptionModel createSelectOption(SelectQuestionModel question, String denominacion, int number,
			boolean editable);

	GridQuestionModel createGridQuestion(SectionModel section, String title, String description, int number,
			boolean required);

	GridRowModel createGridRow(GridQuestionModel question, String denomination, int number, boolean editable);

	GridColumnModel createGridColumn(GridQuestionModel question, String denomination, int number, boolean editable);

	QuestionModel findById(String id);

	boolean remove(QuestionModel question);

	List<QuestionModel> getAll(SectionModel section);

	List<QuestionModel> getAll(SectionModel section, int firstResult, int maxResults);

}
