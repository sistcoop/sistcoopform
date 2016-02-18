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
@Table(name = "SCALE_QUESTION")
@DiscriminatorValue("scale")
public class ScaleQuestionEntity extends QuestionEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Type(type = "org.hibernate.type.TrueFalseType")
	private boolean required;

	@Size(min = 0, max = 100)
	private String tag1;

	@Size(min = 0, max = 100)
	private String tag2;

	@NotNull
	private int min;

	@NotNull
	private int max;

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getTag1() {
		return tag1;
	}

	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}

	public String getTag2() {
		return tag2;
	}

	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

}
