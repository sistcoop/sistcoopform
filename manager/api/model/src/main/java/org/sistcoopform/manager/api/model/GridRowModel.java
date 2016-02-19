package org.sistcoopform.manager.api.model;

public interface GridRowModel extends Model {

	String DENOMINATION = "denomination";
	String NUMBER = "number";
	String EDITABLE = "editable";

	String getId();

	String getDenominacion();

	void setDenominacion(String denomination);

	int getNumber();

	void setNumber(int number);

	boolean isEditable();

	void setEditable(boolean editable);

	GridQuestionModel getGridQuestion();

}