package org.repeid.models.jpa;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.io.IOUtils;
import org.repeid.models.jpa.entities.idm.RoleEntity;
import org.repeid.models.jpa.entities.idm.RoleMembershipEntity;
import org.sistcoopform.provider.IStorage;
import org.sistcoopform.provider.IStorageQuery;
import org.sistcoopform.provider.PermissionBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.apiman.common.util.crypt.IDataEncrypter;
import io.apiman.manager.api.beans.idm.PermissionType;
import io.apiman.manager.api.beans.search.PagingBean;
import io.apiman.manager.api.beans.search.SearchCriteriaBean;
import io.apiman.manager.api.beans.search.SearchCriteriaFilterOperator;
import io.apiman.manager.api.beans.search.SearchResultsBean;
import io.apiman.manager.api.core.exceptions.StorageException;

/**
 * A JPA implementation of the storage interface.
 *
 * @author eric.wittmann@redhat.com
 */
@ApplicationScoped
@Alternative
public class JpaStorage extends AbstractJpaStorage implements IStorage, IStorageQuery {

    private static Logger logger = LoggerFactory.getLogger(JpaStorage.class);

    @Inject
    IDataEncrypter encrypter;

    @PostConstruct
    public void postConstruct() {
        // Kick the encrypter, causing it to be loaded/resolved in CDI
        encrypter.encrypt(""); //$NON-NLS-1$
    }

    /**
     * Constructor.
     */
    public JpaStorage() {
    }

    /**
     * @see io.apiman.manager.api.core.IStorageQuery#getPermissions(java.lang.String)
     */
    @Override
    public Set<PermissionBean> getPermissions(String userId) throws StorageException {
        Set<PermissionBean> permissions = new HashSet<>();      
        try {
            EntityManager entityManager = getActiveEntityManager();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<RoleMembershipEntity> criteriaQuery = builder.createQuery(RoleMembershipEntity.class);
            Root<RoleMembershipEntity> from = criteriaQuery.from(RoleMembershipEntity.class);
            criteriaQuery.where(builder.equal(from.get("userId"), userId)); //$NON-NLS-1$
            TypedQuery<RoleMembershipEntity> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setMaxResults(500);
            List<RoleMembershipEntity> resultList = typedQuery.getResultList();
            for (RoleMembershipEntity membership : resultList) {
                RoleEntity role = getRoleInternal(membership.getRoleId());
                String qualifier = membership.getOrganizationId();
                for (PermissionType permission : role.getPermissions()) {
                    PermissionBean p = new PermissionBean();
                    p.setName(permission);
                    p.setOrganizationId(qualifier);
                    permissions.add(p);
                }
            }
            return permissions;
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            throw new StorageException(t);
        }
    }

}
