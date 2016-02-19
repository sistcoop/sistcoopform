package org.sistcoopform.manager.api.beans.representations.idm;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class SectionRepresentation {

	private String id;
	private String title;
	private String description;
	private int number;

	private FormRepresentation form;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public FormRepresentation getForm() {
		return form;
	}

	public void setForm(FormRepresentation form) {
		this.form = form;
	}

}
