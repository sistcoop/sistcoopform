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
package io.apiman.manager.api.beans.services;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Models a single version of a service "impl".  Every service in
 * APIMan has basic meta-data stored in {@link ServiceBean}.  All
 * other specifics of the service, such as endpoint information
 * and configured policies are associated with a particular version
 * of that Service.  This class represents that version.
 *
 * @author eric.wittmann@redhat.com
 */
@Entity
@Table(name = "service_versions",
       uniqueConstraints = { @UniqueConstraint(columnNames = { "service_id", "service_org_id", "version" }) })
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ServiceVersionBean implements Serializable {

    private static final long serialVersionUID = -2218697175049442690L;

    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="service_id", referencedColumnName="id"),
        @JoinColumn(name="service_org_id", referencedColumnName="organization_id")
    })
    private ServiceBean service;
    @Column(updatable=true, nullable=false)
    @Enumerated(EnumType.STRING)
    private ServiceStatus status;
    private String endpoint;
    @Column(name = "endpoint_type")
    @Enumerated(EnumType.STRING)
    private EndpointType endpointType;
    @Column(name = "endpoint_ct")
    @Enumerated(EnumType.STRING)
    private EndpointContentType endpointContentType;
    @ElementCollection(fetch=FetchType.EAGER)
    @MapKeyColumn(name="name")
    @Column(name="value")
    @CollectionTable(name="endpoint_properties", joinColumns=@JoinColumn(name="service_version_id"))
    private Map<String, String> endpointProperties = new HashMap<>();
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="svc_gateways", joinColumns=@JoinColumn(name="service_version_id"))
    private Set<ServiceGatewayBean> gateways;
    @Column(name = "public_service", updatable=true, nullable=false)
    private boolean publicService;
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="svc_plans", joinColumns=@JoinColumn(name="service_version_id"))
    private Set<ServicePlanBean> plans;
    @Column(updatable=false)
    private String version;
    @Column(name = "created_by", updatable=false, nullable=false)
    private String createdBy;
    @Column(name = "created_on", updatable=false, nullable=false)
    private Date createdOn;
    @Column(name = "modified_by", updatable=true, nullable=false)
    private String modifiedBy;
    @Column(name = "modified_on", updatable=true, nullable=false)
    private Date modifiedOn;
    @Column(name = "published_on")
    private Date publishedOn;
    @Column(name = "retired_on")
    private Date retiredOn;
    @Column(name = "definition_type")
    @Enumerated(EnumType.STRING)
    private ServiceDefinitionType definitionType;

    /**
     * Constructor.
     */
    public ServiceVersionBean() {
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the service
     */
    public ServiceBean getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(ServiceBean service) {
        this.service = service;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the createdOn
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn the createdOn to set
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * @return the status
     */
    public ServiceStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ServiceStatus status) {
        this.status = status;
    }

    /**
     * @return the endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * @param endpoint the endpoint to set
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * @return the endpointType
     */
    public EndpointType getEndpointType() {
        return endpointType;
    }

    /**
     * @param endpointType the endpointType to set
     */
    public void setEndpointType(EndpointType endpointType) {
        this.endpointType = endpointType;
    }

    /**
     * @return the endpointContentType
     */
    public EndpointContentType getEndpointContentType() {
        return endpointContentType;
    }

    /**
     * @param endpointContentType the endpointContentType to set
     */
    public void setEndpointContentType(EndpointContentType endpointContentType) {
        this.endpointContentType = endpointContentType;
    }

    /**
     * @return the publishedOn
     */
    public Date getPublishedOn() {
        return publishedOn;
    }

    /**
     * @param publishedOn the publishedOn to set
     */
    public void setPublishedOn(Date publishedOn) {
        this.publishedOn = publishedOn;
    }

    /**
     * @return the retiredOn
     */
    public Date getRetiredOn() {
        return retiredOn;
    }

    /**
     * @param retiredOn the retiredOn to set
     */
    public void setRetiredOn(Date retiredOn) {
        this.retiredOn = retiredOn;
    }

    /**
     * @return the plans
     */
    public Set<ServicePlanBean> getPlans() {
        return plans;
    }

    /**
     * @param plans the plans to set
     */
    public void setPlans(Set<ServicePlanBean> plans) {
        this.plans = plans;
    }

    /**
     * @return the gateways
     */
    public Set<ServiceGatewayBean> getGateways() {
        return gateways;
    }

    /**
     * @param gateways the gateways to set
     */
    public void setGateways(Set<ServiceGatewayBean> gateways) {
        this.gateways = gateways;
    }

    /**
     * @param plan the plan
     */
    public void addPlan(ServicePlanBean plan) {
        this.plans.add(plan);
    }

    /**
     * @return the modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * @return the modifiedOn
     */
    public Date getModifiedOn() {
        return modifiedOn;
    }

    /**
     * @param modifiedOn the modifiedOn to set
     */
    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    /**
     * @return the publicService
     */
    public boolean isPublicService() {
        return publicService;
    }

    /**
     * @param publicService the publicService to set
     */
    public void setPublicService(boolean publicService) {
        this.publicService = publicService;
    }

    /**
     * @return the definitionType
     */
    public ServiceDefinitionType getDefinitionType() {
        return definitionType;
    }

    /**
     * @param definitionType the definitionType to set
     */
    public void setDefinitionType(ServiceDefinitionType definitionType) {
        this.definitionType = definitionType;
    }

    /**
     * @return the endpointProperties
     */
    public Map<String, String> getEndpointProperties() {
        return endpointProperties;
    }

    /**
     * @param endpointProperties the endpointProperties to set
     */
    public void setEndpointProperties(Map<String, String> endpointProperties) {
        this.endpointProperties = endpointProperties;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ServiceVersionBean other = (ServiceVersionBean) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    @SuppressWarnings("nls")
    public String toString() {
        return "ServiceVersionBean [id=" + id + ", service=" + service + ", status=" + status + ", endpoint="
                + endpoint + ", endpointType=" + endpointType + ", gateways=" + gateways + ", publicService="
                + publicService + ", plans=" + plans + ", version=" + version + ", createdBy=" + createdBy
                + ", createdOn=" + createdOn + ", modifiedBy=" + modifiedBy + ", modifiedOn=" + modifiedOn
                + ", publishedOn=" + publishedOn + ", retiredOn=" + retiredOn + ", definitionType="
                + definitionType + "]";
    }
}
