package org.repeid.models.jpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.repeid.models.jpa.entities.idm.RoleEntity;
import org.repeid.models.jpa.entities.idm.RoleMembershipEntity;
import org.sistcoopform.provider.IStorageQuery;
import org.sistcoopform.provider.PermissionBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.apiman.common.util.crypt.IDataEncrypter;
import io.apiman.manager.api.beans.idm.PermissionType;
import io.apiman.manager.api.core.exceptions.StorageException;

/**
 * A JPA implementation of the storage interface.
 *
 * @author eric.wittmann@redhat.com
 */
@Named
@Stateless
@Local(IStorageQuery.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaStorage extends AbstractJpaStorage implements IStorageQuery {

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
            CriteriaQuery<RoleMembershipEntity> criteriaQuery = builder
                    .createQuery(RoleMembershipEntity.class);
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

    /**
     * @param roleId
     * @return a role by id
     * @throws StorageException
     * @throws DoesNotExistException
     */
    protected RoleEntity getRoleInternal(String roleId) throws StorageException {
        return super.get(roleId, RoleEntity.class);
    }

}
