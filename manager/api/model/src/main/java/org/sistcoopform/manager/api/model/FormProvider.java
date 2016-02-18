package org.sistcoopform.manager.api.model;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import org.sistcoopform.manager.api.model.provider.Provider;
import org.sistcoopform.manager.api.model.search.SearchCriteriaModel;
import org.sistcoopform.manager.api.model.search.SearchResultsModel;

@Local
public interface FormProvider extends Provider {

    FormModel findById(String id);

    FormModel create(String title, String description);

    boolean remove(FormModel form);

    List<FormModel> getAll();

    List<FormModel> getAll(int firstResult, int maxResults);

    List<FormModel> search(String filterText);

    List<FormModel> search(String filterText, int firstResult, int maxResults);

    List<FormModel> searchByAttributes(Map<String, Object> attributes);

    List<FormModel> searchByAttributes(Map<String, Object> attributes, int firstResult, int maxResults);

    SearchResultsModel<FormModel> search(SearchCriteriaModel criteria);

    SearchResultsModel<FormModel> search(SearchCriteriaModel criteria, String filterText);

}
