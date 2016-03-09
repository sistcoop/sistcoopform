package org.sistcoopform.manager.api.model;

import java.util.Date;
import java.util.Set;

public interface FormAnswerModel extends Model {

	static final String USER = "user";
	static final String VALID = "valid";

	String getId();

	Date getDate();

	void setDate(Date date);

	String getUser();

	void setUser(String user);

	String getNote();

	void setNote(String note);

	boolean isValid();

	void active();

	FormModel getForm();

	Set<AnswerModel> getAnswers();

}