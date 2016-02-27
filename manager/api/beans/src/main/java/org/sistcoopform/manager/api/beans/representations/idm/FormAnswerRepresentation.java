package org.sistcoopform.manager.api.beans.representations.idm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class FormAnswerRepresentation {

	private String id;
	private Date date;
	private String user;
	private String note;
	private boolean valid;

	private FormRepresentation form;

	private Set<AnswerRepresentation> answers = new HashSet<AnswerRepresentation>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Set<AnswerRepresentation> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<AnswerRepresentation> answers) {
		this.answers = answers;
	}

	public FormRepresentation getForm() {
		return form;
	}

	public void setForm(FormRepresentation form) {
		this.form = form;
	}

}
