/*
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.apiman.manager.api.beans.audit.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The data saved along with the audit entry when an entity is
 * updated.
 *
 * @author eric.wittmann@redhat.com
 */
public class EntityUpdatedData implements Serializable {

    private static final long serialVersionUID = 3009506122267996076L;

    private List<EntityFieldChange> changes = new ArrayList<>();

    /**
     * Constructor.
     */
    public EntityUpdatedData() {
    }

    /**
     * Adds a single change.
     * @param name the name
     * @param before the before state
     * @param after the after state
     */
    public void addChange(String name, String before, String after) {
        addChange(new EntityFieldChange(name, before, after));
    }

    /**
     * Adds a single change.
     * @param name the name
     * @param before the before state
     * @param after the after state
     */
    public void addChange(String name, Enum<?> before, Enum<?> after) {
        String beforeStr = before != null ? before.name() : null;
        String afterStr = after != null ? after.name() : null;
        addChange(name, beforeStr, afterStr);
    }

    /**
     * Adds a single change.
     * @param change change to add
     */
    public void addChange(EntityFieldChange change) {
        changes.add(change);
    }

    /**
     * @return the changes
     */
    public List<EntityFieldChange> getChanges() {
        return changes;
    }

    /**
     * @param changes the changes to set
     */
    public void setChanges(List<EntityFieldChange> changes) {
        this.changes = changes;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    @SuppressWarnings("nls")
    public String toString() {
        return "EntityUpdatedData [changes=" + changes + "]";
    }

}
