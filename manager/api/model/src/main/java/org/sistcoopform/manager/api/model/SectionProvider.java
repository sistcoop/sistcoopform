package org.sistcoopform.manager.api.model;

import java.util.List;

import javax.ejb.Local;

import org.sistcoopform.manager.api.model.provider.Provider;

@Local
public interface SectionProvider extends Provider {

	SectionModel findById(String id);

	SectionModel create(FormModel form, String title, String description, int number);

	boolean remove(SectionModel section);

	List<SectionModel> getAll(FormModel form);

	List<SectionModel> getAll(FormModel form, int firstResult, int maxResults);

}
