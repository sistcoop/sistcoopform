package org.sistcoopform.manager.api.model.utils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoopform.manager.api.beans.representations.idm.FormRepresentation;
import org.sistcoopform.manager.api.model.FormModel;
import org.sistcoopform.manager.api.model.FormProvider;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RepresentationToModel {

	public FormModel createFormulario(FormRepresentation rep, FormProvider formularioProvider) {
		return formularioProvider.create(rep.getTitle(), rep.getDescription());
	}

}
