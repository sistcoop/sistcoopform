package org.sistcoopform.manager.api.model.utils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoopform.manager.api.beans.representations.idm.FormRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.SectionRepresentation;
import org.sistcoopform.manager.api.model.FormModel;
import org.sistcoopform.manager.api.model.FormProvider;
import org.sistcoopform.manager.api.model.SectionModel;
import org.sistcoopform.manager.api.model.SectionProvider;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RepresentationToModel {

	public FormModel createForm(FormRepresentation rep, FormProvider formProvider) {
		return formProvider.create(rep.getTitle(), rep.getDescription());
	}

	public SectionModel createSection(FormModel form, SectionRepresentation rep, SectionProvider sectionProvider) {
		return sectionProvider.create(form, rep.getTitle(), rep.getDescription(), rep.getNumber());
	}

}
