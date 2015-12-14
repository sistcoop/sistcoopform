package org.sistcoopform.models;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import org.sistcoopform.models.search.SearchCriteriaModel;
import org.sistcoopform.models.search.SearchResultsModel;
import org.sistcoopform.provider.Provider;

@Local
public interface FormularioProvider extends Provider {

    FormularioModel findById(String id);

    FormularioModel create(String titulo, String descripcion);

    boolean remove(FormularioModel formulario);

    List<FormularioModel> getAll();

    List<FormularioModel> getAll(int firstResult, int maxResults);

    List<FormularioModel> search(String filterText);

    List<FormularioModel> search(String filterText, int firstResult, int maxResults);

    List<FormularioModel> searchByAttributes(Map<String, Object> attributes);

    List<FormularioModel> searchByAttributes(Map<String, Object> attributes, int firstResult, int maxResults);

    SearchResultsModel<FormularioModel> search(SearchCriteriaModel criteria);

    SearchResultsModel<FormularioModel> search(SearchCriteriaModel criteria, String filterText);

}
