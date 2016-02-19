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

	SelectQuestionModel createSeleccion(SectionModel section, String title, String description, int number,
			SelectType type, boolean required);

	SelectOptionModel createOpcionSeleccion(SelectQuestionModel preguntaSeleccion, String denominacion, int number,
			boolean editable);

	ScaleQuestionModel createEscalaLineal(SectionModel section, String title, String description, int number,
			String etiqueta1, String etiqueta2, int desde, int hasta, boolean required);

	GridQuestionModel createCuadricula(SectionModel section, String title, String description, int number,
			boolean requiereRespuestaPorFila);

	GridRowModel createFilaCuadricula(GridQuestionModel preguntaCuadricula, String denominacion, int number,
			boolean editable);

	GridColumnModel createColumnaCuadricula(GridQuestionModel preguntaCuadricula, String denominacion, int number,
			boolean editable);

	QuestionModel findById(String id);

	boolean remove(QuestionModel question);

	List<QuestionModel> getAll(SectionModel section);

	List<QuestionModel> getAll(SectionModel section, int firstResult, int maxResults);

}
