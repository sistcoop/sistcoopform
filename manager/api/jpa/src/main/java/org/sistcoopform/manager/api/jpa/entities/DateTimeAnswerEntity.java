package org.sistcoopform.manager.api.jpa.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Entity
@Table(name = "DATETIME_ANSWER")
@DiscriminatorValue("datetime")
public class DateTimeAnswerEntity extends AnswerEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
