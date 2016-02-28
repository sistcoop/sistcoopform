package org.sistcoopform.manager.api.jpa.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Entity
@Table(name = "NUMBER_ANSWER")
@DiscriminatorValue("number")
public class NumberAnswerEntity extends AnswerEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double value;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
