package org.sistcoopform.manager.api.beans.representations.idm;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class AnswerRepresentation {

	private String id;

	private String stringValue;
	private Date dateValue;
	private double numberValue;
	private int integerValue;

	private Set<String> listValues;
	private Map<String, String> mapValues;

	private FormAnswerRepresentation formAnswer;
	private QuestionRepresentation question;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	public double getNumberValue() {
		return numberValue;
	}

	public void setNumberValue(double numberValue) {
		this.numberValue = numberValue;
	}

	public int getIntegerValue() {
		return integerValue;
	}

	public void setIntegerValue(int integerValue) {
		this.integerValue = integerValue;
	}

	public Set<String> getListValues() {
		return listValues;
	}

	public void setListValues(Set<String> listValues) {
		this.listValues = listValues;
	}

	public Map<String, String> getMapValues() {
		return mapValues;
	}

	public void setMapValues(Map<String, String> mapValues) {
		this.mapValues = mapValues;
	}

	public FormAnswerRepresentation getFormAnswer() {
		return formAnswer;
	}

	public void setFormAnswer(FormAnswerRepresentation formAnswer) {
		this.formAnswer = formAnswer;
	}

	public QuestionRepresentation getQuestion() {
		return question;
	}

	public void setQuestion(QuestionRepresentation question) {
		this.question = question;
	}

}
