package org.sistcoopform.manager.api.jpa.entities;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Entity
@Table(name = "SELECT_ANSWER")
@DiscriminatorValue("select")
public class SelectAnswerEntity extends AnswerEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ElementCollection
	@CollectionTable(name = "SELECT_ANSWER_VALUES", joinColumns = @JoinColumn(name = "SELECT_ANSWER_ID") )
	private Set<String> values;

	public Set<String> getValues() {
		return values;
	}

	public void setValues(Set<String> values) {
		this.values = values;
	}

}
