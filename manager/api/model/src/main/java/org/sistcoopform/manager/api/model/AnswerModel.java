package org.sistcoopform.manager.api.model;

public interface AnswerModel extends Model {

	public String getId();

	public FormAnswerModel getForm();

	public QuestionModel getQuestion();

}