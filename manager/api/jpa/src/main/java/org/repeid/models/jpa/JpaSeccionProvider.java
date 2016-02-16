package org.repeid.models.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.repeid.models.jpa.entities.FormularioEntity;
import org.repeid.models.jpa.entities.SeccionEntity;
import org.sistcoopform.models.FormularioModel;
import org.sistcoopform.models.SeccionModel;
import org.sistcoopform.models.SeccionProvider;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(SeccionProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaSeccionProvider extends AbstractHibernateStorage implements SeccionProvider {

    // private static final String TITULO = "titulo";

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
    public SeccionModel create(FormularioModel formulario, String titulo, String descripcion, int numero) {
        FormularioEntity formularioEntity = em.find(FormularioEntity.class, formulario.getId());

        SeccionEntity seccionEntity = new SeccionEntity();
        seccionEntity.setTitulo(titulo);
        seccionEntity.setDescripcion(descripcion);
        seccionEntity.setNumero(numero);
        seccionEntity.setFormulario(formularioEntity);
        return new SeccionAdapter(em, seccionEntity);
    }

    @Override
    public SeccionModel findById(String id) {
        SeccionEntity seccionEntity = em.find(SeccionEntity.class, id);
        return seccionEntity != null ? new SeccionAdapter(em, seccionEntity) : null;
    }

    @Override
    public boolean remove(SeccionModel seccion) {
        SeccionEntity seccionEntity = em.find(SeccionEntity.class, seccion.getId());
        em.remove(seccionEntity);
        return true;
    }

    @Override
    public List<SeccionModel> getAll(FormularioModel formulario) {
        return getAll(formulario, -1, -1);
    }

    @Override
    public List<SeccionModel> getAll(FormularioModel formulario, int firstResult, int maxResults) {
        TypedQuery<SeccionEntity> query = em.createNamedQuery("SeccionEntity.findByIdFormulario",
                SeccionEntity.class);
        query.setParameter("idFormulario", formulario.getId());
        if (firstResult != -1) {
            query.setFirstResult(firstResult);
        }
        if (maxResults != -1) {
            query.setMaxResults(maxResults);
        }
        List<SeccionEntity> entities = query.getResultList();
        List<SeccionModel> models = new ArrayList<SeccionModel>();
        for (SeccionEntity entity : entities) {
            models.add(new SeccionAdapter(em, entity));
        }
        return models;
    }

}
