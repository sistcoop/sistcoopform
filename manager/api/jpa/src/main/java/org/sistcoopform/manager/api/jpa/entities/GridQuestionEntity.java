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

import org.hibernate.annotations.Type;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Entity
@Table(name = "GRID_QUESTION")
@DiscriminatorValue("grid")
public class GridQuestionEntity extends QuestionEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @NotNull
    @Type(type = "org.hibernate.type.TrueFalseType")
    private boolean required;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gridQuestion", orphanRemoval = true, cascade = { CascadeType.REMOVE })
    private Set<GridRowEntity> rows = new HashSet<GridRowEntity>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gridQuestion", orphanRemoval = true, cascade = { CascadeType.REMOVE })
    private Set<GridColumnEntity> columns = new HashSet<GridColumnEntity>();

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public Set<GridRowEntity> getRows() {
		return rows;
	}

	public void setRows(Set<GridRowEntity> rows) {
		this.rows = rows;
	}

	public Set<GridColumnEntity> getColumns() {
		return columns;
	}

	public void setColumns(Set<GridColumnEntity> columns) {
		this.columns = columns;
	}   

}
