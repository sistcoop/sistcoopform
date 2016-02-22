package org.sistcoopform.manager.api.model;

import java.util.Date;

public interface FormAnswerModel extends Model {

	static final String USER = "user";
	static final String VALID = "valid";

	public String getId();

	public Date getDate();

	public void setDate(Date date);

	public String getUser();

	public void setUser(String user);

	public String getNote();

	public void setNote(String note);

	public boolean isValid();

}