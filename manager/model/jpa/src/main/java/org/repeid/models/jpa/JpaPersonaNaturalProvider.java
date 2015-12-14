package org.repeid.models.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.repeid.models.ModelDuplicateException;
import org.repeid.models.PersonaNaturalModel;
import org.repeid.models.PersonaNaturalProvider;
import org.repeid.models.TipoDocumentoModel;
import org.repeid.models.enums.Sexo;
import org.repeid.models.jpa.entities.AccionistaEntity;
import org.repeid.models.jpa.entities.PersonaJuridicaEntity;
import org.repeid.models.jpa.entities.PersonaNaturalEntity;
import org.repeid.models.jpa.entities.TipoDocumentoEntity;
import org.repeid.models.search.SearchCriteriaModel;
import org.repeid.models.search.SearchResultsModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(PersonaNaturalProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaPersonaNaturalProvider extends AbstractHibernateStorage implements PersonaNaturalProvider {

    private static final String APELLIDO_PATERNO = "apellidoPaterno";
    private static final String APELLIDO_MATERNO = "apellidoMaterno";
    private static final String NOMBRES = "nombres";
    private static final String NUMERO_DOCUMENTO = "numeroDocumento";

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public PersonaNaturalModel create(String codigoPais, TipoDocumentoModel tipoDocumentoModel,
            String numeroDocumento, String apellidoPaterno, String apellidoMaterno, String nombres,
            Date fechaNacimiento, Sexo sexo) {
        if (findByTipoNumeroDocumento(tipoDocumentoModel, numeroDocumento) != null) {
            throw new ModelDuplicateException(
                    "PersonaNaturalEntity tipoDocumento y numeroDocumento debe ser unico, se encontro otra entidad con tipoDocumento="
                            + tipoDocumentoModel + "y numeroDocumento=" + numeroDocumento);
        }

        TipoDocumentoEntity tipoDocumentoEntity = em.find(TipoDocumentoEntity.class,
                tipoDocumentoModel.getId());

        PersonaNaturalEntity personaNaturalEntity = new PersonaNaturalEntity();
        personaNaturalEntity.setCodigoPais(codigoPais);
        personaNaturalEntity.setTipoDocumento(tipoDocumentoEntity);
        personaNaturalEntity.setNumeroDocumento(numeroDocumento);
        personaNaturalEntity.setApellidoPaterno(apellidoPaterno);
        personaNaturalEntity.setApellidoMaterno(apellidoMaterno);
        personaNaturalEntity.setNombres(nombres);
        personaNaturalEntity.setFechaNacimiento(fechaNacimiento);
        personaNaturalEntity.setSexo(sexo.toString());
        em.persist(personaNaturalEntity);
        return new PersonaNaturalAdapter(em, personaNaturalEntity);
    }

    @Override
    public boolean remove(PersonaNaturalModel personaNaturalModel) {
        TypedQuery<AccionistaEntity> query1 = em.createNamedQuery("AccionistaEntity.findByIdPersonaNatural",
                AccionistaEntity.class);
        query1.setParameter("idPersonaNatural", personaNaturalModel.getId());
        query1.setMaxResults(1);
        if (!query1.getResultList().isEmpty()) {
            return false;
        }

        TypedQuery<PersonaJuridicaEntity> query2 = em.createNamedQuery(
                "PersonaJuridicaEntity.findByIdPersonaNaturalRepresentanteLegal",
                PersonaJuridicaEntity.class);
        query2.setParameter("idPersonaNaturalRepresentanteLegal", personaNaturalModel.getId());
        query2.setMaxResults(1);
        if (!query2.getResultList().isEmpty()) {
            return false;
        }

        PersonaNaturalEntity personaNaturalEntity = em.find(PersonaNaturalEntity.class,
                personaNaturalModel.getId());
        if (personaNaturalEntity == null) {
            return false;
        }
        em.remove(personaNaturalEntity);
        return true;
    }

    @Override
    public PersonaNaturalModel findById(String id) {
        PersonaNaturalEntity personaNaturalEntity = this.em.find(PersonaNaturalEntity.class, id);
        return personaNaturalEntity != null ? new PersonaNaturalAdapter(em, personaNaturalEntity) : null;
    }

    @Override
    public PersonaNaturalModel findByTipoNumeroDocumento(TipoDocumentoModel tipoDocumento,
            String numeroDocumento) {
        TypedQuery<PersonaNaturalEntity> query = em.createNamedQuery(
                "PersonaNaturalEntity.findByTipoNumeroDocumento", PersonaNaturalEntity.class);
        query.setParameter("tipoDocumento", tipoDocumento.getAbreviatura());
        query.setParameter("numeroDocumento", numeroDocumento);
        List<PersonaNaturalEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException("Mas de una PersonaNaturalEntity con tipoDocumento="
                    + tipoDocumento + " y numeroDocumento=" + numeroDocumento + ", results=" + results);
        } else {
            return new PersonaNaturalAdapter(em, results.get(0));
        }
    }

    @Override
    public List<PersonaNaturalModel> getAll() {
        return getAll(-1, -1);
    }

    @Override
    public List<PersonaNaturalModel> getAll(int firstResult, int maxResults) {
        TypedQuery<PersonaNaturalEntity> query = em.createNamedQuery("PersonaNaturalEntity.findAll",
                PersonaNaturalEntity.class);
        if (firstResult != -1) {
            query.setFirstResult(firstResult);
        }
        if (maxResults != -1) {
            query.setMaxResults(maxResults);
        }
        List<PersonaNaturalEntity> entities = query.getResultList();
        List<PersonaNaturalModel> result = new ArrayList<PersonaNaturalModel>();
        for (PersonaNaturalEntity personaNaturalEntity : entities) {
            result.add(new PersonaNaturalAdapter(em, personaNaturalEntity));
        }
        return result;
    }

    @Override
    public List<PersonaNaturalModel> search(String filterText) {
        return search(filterText, -1, -1);
    }

    @Override
    public List<PersonaNaturalModel> search(String filterText, int firstResult, int maxResults) {
        TypedQuery<PersonaNaturalEntity> query = em.createNamedQuery("PersonaNaturalEntity.findByFilterText",
                PersonaNaturalEntity.class);
        query.setParameter("filterText", "%" + filterText.toLowerCase() + "%");
        if (firstResult != -1) {
            query.setFirstResult(firstResult);
        }
        if (maxResults != -1) {
            query.setMaxResults(maxResults);
        }
        List<PersonaNaturalEntity> entities = query.getResultList();
        List<PersonaNaturalModel> models = new ArrayList<PersonaNaturalModel>();
        for (PersonaNaturalEntity personaNaturalEntity : entities) {
            models.add(new PersonaNaturalAdapter(em, personaNaturalEntity));
        }
        return models;
    }

    @Override
    public List<PersonaNaturalModel> searchByAttributes(Map<String, String> attributes) {
        return searchByAttributes(attributes, -1, -1);
    }

    @Override
    public List<PersonaNaturalModel> searchByAttributes(Map<String, String> attributes, int firstResult,
            int maxResults) {
        StringBuilder builder = new StringBuilder("SELECT p FROM PersonaNaturalEntity");
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            String attribute = null;
            String parameterName = null;
            if (entry.getKey().equals(PersonaNaturalModel.APELLIDO_PATERNO)) {
                attribute = "lower(p.apellidoPaterno)";
                parameterName = JpaPersonaNaturalProvider.APELLIDO_PATERNO;
            } else if (entry.getKey().equalsIgnoreCase(PersonaNaturalModel.APELLIDO_MATERNO)) {
                attribute = "lower(p.apellidoMaterno)";
                parameterName = JpaPersonaNaturalProvider.APELLIDO_MATERNO;
            } else if (entry.getKey().equalsIgnoreCase(PersonaNaturalModel.NOMBRES)) {
                attribute = "lower(p.nombres)";
                parameterName = JpaPersonaNaturalProvider.NOMBRES;
            } else if (entry.getKey().equalsIgnoreCase(PersonaNaturalModel.NUMERO_DOCUMENTO)) {
                attribute = "lower(p.numeroDocumento)";
                parameterName = JpaPersonaNaturalProvider.NUMERO_DOCUMENTO;
            }

            if (attribute == null) {
                continue;
            }
            builder.append(attribute).append(" like :").append(parameterName);
        }
        builder.append(" order by p.apellidoPaterno, p.apellidoMaterno, p.nombres");
        String q = builder.toString();
        TypedQuery<PersonaNaturalEntity> query = em.createQuery(q, PersonaNaturalEntity.class);
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            String parameterName = null;
            if (entry.getKey().equals(PersonaNaturalModel.APELLIDO_PATERNO)) {
                parameterName = JpaPersonaNaturalProvider.APELLIDO_PATERNO;
            } else if (entry.getKey().equals(PersonaNaturalModel.APELLIDO_MATERNO)) {
                parameterName = JpaPersonaNaturalProvider.APELLIDO_MATERNO;
            } else if (entry.getKey().equals(PersonaNaturalModel.NOMBRES)) {
                parameterName = JpaPersonaNaturalProvider.NOMBRES;
            } else if (entry.getKey().equals(PersonaNaturalModel.NUMERO_DOCUMENTO)) {
                parameterName = JpaPersonaNaturalProvider.NUMERO_DOCUMENTO;
            }

            if (parameterName == null) {
                continue;
            }
            query.setParameter(parameterName, "%" + entry.getValue().toLowerCase() + "%");
        }
        if (firstResult != -1) {
            query.setFirstResult(firstResult);
        }
        if (maxResults != -1) {
            query.setMaxResults(maxResults);
        }
        List<PersonaNaturalEntity> results = query.getResultList();
        List<PersonaNaturalModel> personaNaturales = new ArrayList<PersonaNaturalModel>();
        for (PersonaNaturalEntity entity : results)
            personaNaturales.add(new PersonaNaturalAdapter(em, entity));
        return personaNaturales;
    }

    @Override
    public SearchResultsModel<PersonaNaturalModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<PersonaNaturalEntity> entityResult = find(criteria, PersonaNaturalEntity.class);

        SearchResultsModel<PersonaNaturalModel> modelResult = new SearchResultsModel<>();
        List<PersonaNaturalModel> list = new ArrayList<>();
        for (PersonaNaturalEntity entity : entityResult.getModels()) {
            list.add(new PersonaNaturalAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

    @Override
    public SearchResultsModel<PersonaNaturalModel> search(SearchCriteriaModel criteria, String filterText) {
        SearchResultsModel<PersonaNaturalEntity> entityResult = findFullText(criteria,
                PersonaNaturalEntity.class, filterText, "numeroDocumento", "apellidoPaterno",
                "apellidoMaterno", "nombres");

        SearchResultsModel<PersonaNaturalModel> modelResult = new SearchResultsModel<>();
        List<PersonaNaturalModel> list = new ArrayList<>();
        for (PersonaNaturalEntity entity : entityResult.getModels()) {
            list.add(new PersonaNaturalAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

}
