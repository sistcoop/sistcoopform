package org.sistcoopform.manager.api.model;

public interface SelectOptionModel extends Model {

	String DENOMINATION = "denomination";
	String NUMBER = "number";
	String EDITABLE = "editable";

	String getId();

	String getDenomination();

	void setDenomination(String denomination);

	int getNumber();

	void setNumber(int number);

	boolean isEditable();

	void setEditable(boolean editable);

	SelectQuestionModel getSelectQuestion();

}