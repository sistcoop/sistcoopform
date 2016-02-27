package org.sistcoopform.manager.api.rest.resources.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;

import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { FIELD, TYPE, METHOD })
public @interface AnswerResourceQualifer {

	AnswerResourceType value();

	enum AnswerResourceType {
		FORM_ANSWER_PARENT, QUESTION_PARENT
	}
}
