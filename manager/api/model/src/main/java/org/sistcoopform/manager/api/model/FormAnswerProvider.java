package org.sistcoopform.manager.api.model;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import org.sistcoopform.manager.api.model.provider.Provider;
import org.sistcoopform.manager.api.model.search.SearchCriteriaModel;
import org.sistcoopform.manager.api.model.search.SearchResultsModel;

@Local
public interface FormAnswerProvider extends Provider {	
	
	FormAnswerModel findById(String id);

	FormAnswerModel create(FormModel form, String user);

	boolean remove(FormAnswerModel formAnswer);

	List<FormAnswerModel> getAll();

	List<FormAnswerModel> getAll(int firstResult, int maxResults);

	List<FormAnswerModel> search(String filterText);

	List<FormAnswerModel> search(String filterText, int firstResult, int maxResults);

	List<FormAnswerModel> searchByAttributes(Map<String, Object> attributes);

	List<FormAnswerModel> searchByAttributes(Map<String, Object> attributes, int firstResult, int maxResults);

	SearchResultsModel<FormAnswerModel> search(SearchCriteriaModel criteria);

	SearchResultsModel<FormAnswerModel> search(SearchCriteriaModel criteria, String filterText);

}
