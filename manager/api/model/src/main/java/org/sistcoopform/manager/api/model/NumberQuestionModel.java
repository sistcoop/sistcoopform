package org.sistcoopform.manager.api.model;

import org.sistcoopform.manager.api.model.enums.NumberType;

public interface NumberQuestionModel extends QuestionModel {

	String REQUIRED = "required";
	String TYPE = "type";

	boolean isRequired();

	void setRequired(boolean required);

	NumberType getType();

	void setType(NumberType type);

}