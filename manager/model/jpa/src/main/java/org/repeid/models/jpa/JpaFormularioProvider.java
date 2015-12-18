package org.repeid.models.jpa;

import java.util.ArrayList;
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

import org.repeid.models.jpa.entities.FormularioEntity;
import org.sistcoopform.models.FormularioModel;
import org.sistcoopform.models.FormularioProvider;
import org.sistcoopform.models.search.SearchCriteriaModel;
import org.sistcoopform.models.search.SearchResultsModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(FormularioProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaFormularioProvider extends AbstractHibernateStorage implements FormularioProvider {

    private static final String TITULO = "titulo";

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
    public FormularioModel create(String titulo, String descripcion) {
        FormularioEntity formularioEntity = new FormularioEntity();
        formularioEntity.setTitulo(titulo);
        formularioEntity.setDescripcion(descripcion);
        em.persist(formularioEntity);
        return new FormularioAdapter(em, formularioEntity);
    }

    @Override
    public FormularioModel findById(String id) {
        FormularioEntity formularioEntity = em.find(FormularioEntity.class, id);
        return formularioEntity != null ? new FormularioAdapter(em, formularioEntity) : null;
    }

    @Override
    public boolean remove(FormularioModel tipoDocumentoModel) {
        return false;
    }

    @Override
    public List<FormularioModel> getAll() {
        return getAll(-1, -1);
    }

    @Override
    public List<FormularioModel> getAll(int firstResult, int maxResults) {
        TypedQuery<FormularioEntity> query = em.createNamedQuery("FormularioEntity.findAll",
                FormularioEntity.class);
        if (firstResult != -1) {
            query.setFirstResult(firstResult);
        }
        if (maxResults != -1) {
            query.setMaxResults(maxResults);
        }
        List<FormularioEntity> entities = query.getResultList();
        List<FormularioModel> models = new ArrayList<FormularioModel>();
        for (FormularioEntity tipoDocumentoEntity : entities) {
            models.add(new FormularioAdapter(em, tipoDocumentoEntity));
        }
        return models;
    }

    @Override
    public List<FormularioModel> search(String filterText) {
        return search(filterText, -1, -1);
    }

    @Override
    public List<FormularioModel> search(String filterText, int firstResult, int maxResults) {
        TypedQuery<FormularioEntity> query = em.createNamedQuery("FormularioEntity.findByFilterText",
                FormularioEntity.class);
        query.setParameter("filterText", "%" + filterText.toLowerCase() + "%");
        if (firstResult != -1) {
            query.setFirstResult(firstResult);
        }
        if (maxResults != -1) {
            query.setMaxResults(maxResults);
        }
        List<FormularioEntity> entities = query.getResultList();
        List<FormularioModel> models = new ArrayList<FormularioModel>();
        for (FormularioEntity tipoDocumentoEntity : entities) {
            models.add(new FormularioAdapter(em, tipoDocumentoEntity));
        }

        return models;
    }

    @Override
    public List<FormularioModel> searchByAttributes(Map<String, Object> attributes) {
        return searchByAttributes(attributes, -1, -1);
    }

    @Override
    public List<FormularioModel> searchByAttributes(Map<String, Object> attributes, int firstResult,
            int maxResults) {
        StringBuilder builder = new StringBuilder("SELECT t FROM FormularioEntity");
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            String attribute = null;
            String parameterName = null;
            if (entry.getKey().equals(FormularioModel.TITULO)) {
                attribute = "lower(t.titulo)";
                parameterName = JpaFormularioProvider.TITULO;
            }

            if (attribute != null) {
                builder.append(" and ");
                builder.append(attribute).append(" like :").append(parameterName);
            } else {
                if (entry.getKey().equalsIgnoreCase(FormularioModel.TITULO)) {
                    attribute = "lower(t.titulo)";
                    parameterName = JpaFormularioProvider.TITULO;
                }

                if (attribute == null) {
                    continue;
                }
                builder.append(" and ");
                builder.append(attribute).append(" = :").append(parameterName);
            }
        }
        builder.append(" order by t.titulo");
        String q = builder.toString();
        TypedQuery<FormularioEntity> query = em.createQuery(q, FormularioEntity.class);
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            String parameterName = null;
            if (entry.getKey().equals(FormularioModel.TITULO)) {
                parameterName = JpaFormularioProvider.TITULO;
            }

            if (parameterName != null) {
                query.setParameter(parameterName, "%" + String.valueOf(entry.getValue()).toLowerCase() + "%");
            } else {
                if (entry.getKey().equalsIgnoreCase(FormularioModel.TITULO)) {
                    parameterName = JpaFormularioProvider.TITULO;
                }

                if (parameterName == null) {
                    continue;
                }
                query.setParameter(parameterName, entry.getValue());
            }
        }
        if (firstResult != -1) {
            query.setFirstResult(firstResult);
        }
        if (maxResults != -1) {
            query.setMaxResults(maxResults);
        }
        List<FormularioEntity> results = query.getResultList();
        List<FormularioModel> formularioModels = new ArrayList<FormularioModel>();
        for (FormularioEntity entity : results)
            formularioModels.add(new FormularioAdapter(em, entity));
        return formularioModels;
    }

    @Override
    public SearchResultsModel<FormularioModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<FormularioEntity> entityResult = find(criteria, FormularioEntity.class);

        SearchResultsModel<FormularioModel> modelResult = new SearchResultsModel<>();
        List<FormularioModel> list = new ArrayList<>();
        for (FormularioEntity entity : entityResult.getModels()) {
            list.add(new FormularioAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

    @Override
    public SearchResultsModel<FormularioModel> search(SearchCriteriaModel criteria, String filterText) {
        SearchResultsModel<FormularioEntity> entityResult = findFullText(criteria, FormularioEntity.class,
                filterText, "titulo");

        SearchResultsModel<FormularioModel> modelResult = new SearchResultsModel<>();
        List<FormularioModel> list = new ArrayList<>();
        for (FormularioEntity entity : entityResult.getModels()) {
            list.add(new FormularioAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

}
