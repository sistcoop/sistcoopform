package org.sistcoopform.manager.api.model.utils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoopform.manager.api.beans.representations.enums.QuestionAvailable;
import org.sistcoopform.manager.api.beans.representations.idm.FormRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.QuestionRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.SectionRepresentation;
import org.sistcoopform.manager.api.model.FormModel;
import org.sistcoopform.manager.api.model.FormProvider;
import org.sistcoopform.manager.api.model.QuestionModel;
import org.sistcoopform.manager.api.model.QuestionProvider;
import org.sistcoopform.manager.api.model.SectionModel;
import org.sistcoopform.manager.api.model.SectionProvider;
import org.sistcoopform.manager.api.model.enums.DateTimeType;
import org.sistcoopform.manager.api.model.enums.NumericType;
import org.sistcoopform.manager.api.model.enums.TextType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RepresentationToModel {

	public FormModel createForm(FormRepresentation rep, FormProvider formProvider) {
		return formProvider.create(rep.getTitle(), rep.getDescription());
	}

	public SectionModel createSection(FormModel form, SectionRepresentation rep, SectionProvider sectionProvider) {
		return sectionProvider.create(form, rep.getTitle(), rep.getDescription(), rep.getNumber());
	}

	public QuestionModel createQuestion(SectionModel section, QuestionRepresentation rep,
			QuestionProvider questionProvider) {
		if (rep.getQuestion().equals(QuestionAvailable.TEXT)) {
			return questionProvider.createTextQuestion(section, rep.getTitle(), rep.getDescription(), rep.getNumber(),
					TextType.valueOf(rep.getType()), rep.isRequired());
		} else if (rep.getQuestion().equals(QuestionAvailable.NUMBER)) {
			return questionProvider.createNumberQuestion(section, rep.getTitle(), rep.getDescription(), rep.getNumber(),
					NumericType.valueOf(rep.getType()), rep.isRequired());
		} else if (rep.getQuestion().equals(QuestionAvailable.DATETIME)) {
			return questionProvider.createDateTimeQuestion(section, rep.getTitle(), rep.getDescription(),
					rep.getNumber(), DateTimeType.valueOf(rep.getType()), rep.isRequired());
		} else if (rep.getQuestion().equals(QuestionAvailable.TEXT)) {

		} else if (rep.getQuestion().equals(QuestionAvailable.TEXT)) {

		} else if (rep.getQuestion().equals(QuestionAvailable.TEXT)) {

		} else if (rep.getQuestion().equals(QuestionAvailable.TEXT)) {

		}
		return null;
	}

}
