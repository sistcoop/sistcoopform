package org.sistcoopform.manager.api.model;

import org.sistcoopform.manager.api.model.enums.NumericType;

public interface NumericQuestionModel extends QuestionModel {

	String REQUIRED = "required";
	String TYPE = "type";

	boolean isRequired();

	void setRequired(boolean required);

	NumericType getType();

	void setType(NumericType type);

}