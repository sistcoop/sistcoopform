package org.sistcoopform.manager.api.jpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Entity
@Table(name = "SELECT_QUESTION")
@DiscriminatorValue("select")
public class SelectQuestionEntity extends QuestionEntity {

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "selectQuestion", orphanRemoval = true, cascade = { CascadeType.ALL })
	private Set<SelectOptionEntity> options = new HashSet<SelectOptionEntity>();

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

	public Set<SelectOptionEntity> getOptions() {
		return options;
	}

	public void setOptions(Set<SelectOptionEntity> options) {
		this.options = options;
	}

}
