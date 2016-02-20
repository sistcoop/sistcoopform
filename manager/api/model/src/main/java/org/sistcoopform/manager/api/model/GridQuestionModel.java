package org.sistcoopform.manager.api.model;

import java.util.Set;

public interface GridQuestionModel extends QuestionModel {

	boolean isRequired();

	void setRequired(boolean required);

	Set<GridRowModel> getRows();

	void setRows(Set<GridRowModel> rows);

	Set<GridColumnModel> getColumns();

	void setColumns(Set<GridColumnModel> columns);

}