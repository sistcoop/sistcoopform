package org.sistcoopform.manager.api.rest.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoopform.manager.api.beans.representations.idm.FormRepresentation;
import org.sistcoopform.manager.api.model.FormModel;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class FormularioManager {

	public void update(FormModel model, FormRepresentation rep) {
		model.setTitle(rep.getTitle());
		model.setDescription(rep.getDescription());
		model.commit();
	}

}