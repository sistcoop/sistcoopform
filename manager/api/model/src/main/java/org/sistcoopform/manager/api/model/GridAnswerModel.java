package org.sistcoopform.manager.api.model;

import java.util.Map;

public interface GridAnswerModel extends AnswerModel {

	Map<String, String> getValues();

	void setValues(Map<String, String> values);

}