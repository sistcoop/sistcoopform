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


/**
 * Models a change in an entity field's value for auditing purposes.
 *
 * @author eric.wittmann@redhat.com
 */
public class EntityFieldChange {

    private String name;
    private String before;
    private String after;

    /**
     * Constructor.
     */
    public EntityFieldChange() {
    }

    /**
     * Constructor.
     * @param name the name
     * @param before the before state
     * @param after the after state
     */
    public EntityFieldChange(String name, String before, String after) {
        this.name = name;
        this.setBefore(before);
        this.setAfter(after);
    }

    /**
     * @return the before
     */
    public String getBefore() {
        return before;
    }

    /**
     * @param before the before to set
     */
    public void setBefore(String before) {
        this.before = before;
    }

    /**
     * @return the after
     */
    public String getAfter() {
        return after;
    }

    /**
     * @param after the after to set
     */
    public void setAfter(String after) {
        this.after = after;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    @SuppressWarnings("nls")
    public String toString() {
        return "EntityFieldChange [name=" + name + ", before=" + before + ", after=" + after + "]";
    }

}
