package org.sistcoopform.manager.api.model;

public interface FormModel extends Model {

	String TITLE = "title";
	String DESCRIPTION = "description";

	String getId();

	String getTitle();

	void setTitle(String title);

	String getDescription();

	void setDescription(String description);

}