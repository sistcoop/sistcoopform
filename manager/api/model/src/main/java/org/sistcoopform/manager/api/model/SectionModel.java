package org.sistcoopform.manager.api.model;

public interface SectionModel extends Model {

	String TITLE = "title";
	String DESCRIPTION = "description";
	String NUMBER = "number";

	String getId();

	String getTitle();

	void setTitle(String title);

	String getDescription();

	void setDescription(String description);

	int getNumber();

	void setNumber(int number);

	FormModel getForm();

}