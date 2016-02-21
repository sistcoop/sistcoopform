package org.sistcoopform.manager.api.model;

import java.util.Set;

public interface SelectAnswerModel extends AnswerModel {

	Set<String> getValues();

	void setValues(Set<String> values);

}