package org.sistcoopform.manager.api.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Entity
@Table(name = "DATETIME_QUESTION")
@DiscriminatorValue("datetime")
public class DateTimeQuestionEntity extends QuestionEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Type(type = "org.hibernate.type.TrueFalseType")
	private boolean required;

	@NotNull
	@Size(min = 1, max = 100)
	private String type;

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
