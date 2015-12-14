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
package io.apiman.manager.api.beans.audit;

/**
 * Indicates type type of auditing event is represented by an audit entry.
 *
 * @author eric.wittmann@redhat.com
 */
public enum AuditEntryType {

    // Entity events
    Create, Update, Delete, Clone, 
    // Action events
    Grant, Revoke,
    Publish, Retire,
    Register, Unregister,
    AddPolicy, RemovePolicy, UpdatePolicy, ReorderPolicies,
    CreateContract, BreakContract,
    Lock,
    UpdateDefinition, DeleteDefinition
}
