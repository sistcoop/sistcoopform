package org.sistcoopform.provider;

import java.util.Set;

import io.apiman.manager.api.core.exceptions.StorageException;

/**
 * Specific querying of the storage layer.
 *
 * @author eric.wittmann@redhat.com
 */
public interface IStorageQuery {

    /**
     * Returns a set of permissions granted to the user due to their role
     * memberships.
     * 
     * @param userId
     *            the user's id
     * @return set of permissions
     * @throws StorageException
     *             if an exception occurs during storage attempt
     */
    public Set<PermissionBean> getPermissions(String userId) throws StorageException;

}
