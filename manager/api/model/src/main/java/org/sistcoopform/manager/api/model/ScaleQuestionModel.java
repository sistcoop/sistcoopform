package org.sistcoopform.manager.api.model;

public interface ScaleQuestionModel extends QuestionModel {

	String REQUIRED = "required";
	String TAG1 = "tag1";
	String TAG2 = "tag2";
	String MIN = "min";
	String MAX = "max";

	boolean isRequired();

	void setRequired(boolean required);

	String getTag1();

	void setTag1(String tag1);

	String getTag2();

	void setTag2(String tag2);

	int getMin();

	void setMin(int min);

	int getMax();

	void setMax(int max);

}