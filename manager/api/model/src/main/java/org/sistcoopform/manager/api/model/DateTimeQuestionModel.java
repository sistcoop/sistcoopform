package org.sistcoopform.manager.api.model;

import org.sistcoopform.manager.api.model.enums.DateTimeType;

public interface DateTimeQuestionModel extends QuestionModel {

	boolean isRequired();

	void setRequired(boolean required);

	DateTimeType getType();

	void setType(DateTimeType type);

}