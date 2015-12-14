package org.repeid.models;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import org.repeid.models.enums.TipoEmpresa;
import org.repeid.models.search.SearchCriteriaModel;
import org.repeid.models.search.SearchResultsModel;
import org.repeid.provider.Provider;

@Local
public interface PersonaJuridicaProvider extends Provider {

    PersonaJuridicaModel findById(String id);

    PersonaJuridicaModel findByTipoNumeroDocumento(TipoDocumentoModel tipoDocumento, String numeroDocumento);

    PersonaJuridicaModel create(PersonaNaturalModel representanteLegal, String codigoPais,
            TipoDocumentoModel tipoDocumentoModel, String numeroDocumento, String razonSocial,
            Date fechaConstitucion, TipoEmpresa tipoEmpresa, boolean finLucro);

    boolean remove(PersonaJuridicaModel personaJuridicaModel);

    List<PersonaJuridicaModel> getAll();

    List<PersonaJuridicaModel> getAll(int firstResult, int maxResults);

    List<PersonaJuridicaModel> search(String filterText);

    List<PersonaJuridicaModel> search(String filterText, int firstResult, int maxResults);

    List<PersonaJuridicaModel> searchByAttributes(Map<String, String> attributes);

    List<PersonaJuridicaModel> searchByAttributes(Map<String, String> attributes, int firstResult,
            int maxResults);

    SearchResultsModel<PersonaJuridicaModel> search(SearchCriteriaModel criteria);

    SearchResultsModel<PersonaJuridicaModel> search(SearchCriteriaModel criteria, String filterText);

}
