package org.sistcoopform.manager.api.model;

import org.sistcoopform.manager.api.model.enums.TextType;

public interface TextQuestionModel extends QuestionModel {

	String REQUIRED = "required";
	String TYPE = "type";

	boolean isRequired();

	void setRequired(boolean required);

	TextType getType();

	void setType(TextType type);

}