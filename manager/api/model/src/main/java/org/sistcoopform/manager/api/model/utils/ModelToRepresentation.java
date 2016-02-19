package org.sistcoopform.manager.api.model.utils;

import org.sistcoopform.manager.api.beans.representations.idm.FormRepresentation;
import org.sistcoopform.manager.api.beans.representations.idm.SectionRepresentation;
import org.sistcoopform.manager.api.model.FormModel;
import org.sistcoopform.manager.api.model.SectionModel;

public class ModelToRepresentation {

	public static FormRepresentation toRepresentation(FormModel model) {
		if (model == null)
			return null;

		FormRepresentation rep = new FormRepresentation();
		rep.setId(model.getId());
		rep.setTitle(model.getTitle());
		rep.setDescription(model.getDescription());
		return rep;
	}

	public static SectionRepresentation toRepresentation(SectionModel model) {
		if (model == null)
			return null;

		SectionRepresentation rep = new SectionRepresentation();
		rep.setId(model.getId());
		rep.setTitle(model.getTitle());
		rep.setNumber(model.getNumber());
		rep.setDescription(model.getDescription());
		return rep;
	}

}
