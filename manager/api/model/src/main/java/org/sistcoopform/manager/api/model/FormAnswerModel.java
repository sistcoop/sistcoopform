package org.sistcoopform.manager.api.model;

import java.util.Date;
import java.util.Set;

public interface FormAnswerModel extends Model {

	String TITLE = "title";
	String DESCRIPTION = "description";

	public String getId();

	public Date getDate();

	public void setDate(Date date);

	public String getUser();

	public void setUser(String user);

	public String getNote();

	public void setNote(String note);

	public boolean isValid();

	public Set<AnswerModel> getAnswers();

}