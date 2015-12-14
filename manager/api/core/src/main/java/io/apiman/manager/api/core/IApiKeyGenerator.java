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
package io.apiman.manager.api.core;

/**
 * Service responsible for generating unique API keys whenever a contract
 * is created between an Application and a Service it wishes to invoke via
 * a specific plan.
 *
 * @author eric.wittmann@redhat.com
 */
public interface IApiKeyGenerator {

    /**
     * Generates a new API key.
     * @return a new API key
     */
    public String generate();

}
