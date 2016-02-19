package org.sistcoopform.manager.api.model;

import java.util.List;

import org.sistcoopform.manager.api.model.enums.SelectType;

public interface SelectQuestionModel extends QuestionModel {

	String REQUIRED = "required";
	String TYPE = "type";

	boolean isRequired();

	void setRequired(boolean required);

	SelectType getType();

	void setType(SelectType type);

	List<SelectOptionModel> getOptions();

}