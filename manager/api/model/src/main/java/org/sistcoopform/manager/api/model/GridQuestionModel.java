package org.sistcoopform.manager.api.model;

import java.util.List;

public interface GridQuestionModel extends QuestionModel {

	boolean isRequired();

	void setRequired(boolean required);

	List<GridRowModel> getRows();

	List<GridColumnModel> getColumns();

}