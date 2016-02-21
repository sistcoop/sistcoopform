package org.sistcoopform.manager.api.beans.representations.idm;

import java.util.HashSet;
import java.util.Set;

import org.sistcoopform.manager.api.beans.representations.enums.QuestionAvailable;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class QuestionRepresentation {

	private QuestionAvailable question;

	// Basic
	private String id;
	private String title;
	private String description;
	private int number;

	// General
	private boolean required;
	private String type;

	// Scale
	private String tag1;
	private String tag2;
	private int min;
	private int max;

	// Select
	private Set<SelectOptionRepresentation> selectOptions = new HashSet<SelectOptionRepresentation>();

	// Grid
	private Set<GridRowRepresentation> rows = new HashSet<GridRowRepresentation>();
	private Set<GridColumnRepresentation> columns = new HashSet<GridColumnRepresentation>();

	private SectionRepresentation section;

	public QuestionAvailable getQuestion() {
		return question;
	}

	public void setQuestion(QuestionAvailable question) {
		this.question = question;
	}

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

	public Set<SelectOptionRepresentation> getSelectOptions() {
		return selectOptions;
	}

	public void setSelectOptions(Set<SelectOptionRepresentation> selectOptions) {
		this.selectOptions = selectOptions;
	}

	public Set<GridRowRepresentation> getRows() {
		return rows;
	}

	public void setRows(Set<GridRowRepresentation> rows) {
		this.rows = rows;
	}

	public Set<GridColumnRepresentation> getColumns() {
		return columns;
	}

	public void setColumns(Set<GridColumnRepresentation> columns) {
		this.columns = columns;
	}

	public SectionRepresentation getSection() {
		return section;
	}

	public void setSection(SectionRepresentation section) {
		this.section = section;
	}

	

}
