/*
 * Copyright 2015 JBoss Inc
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

import io.apiman.manager.api.beans.metrics.AppUsagePerServiceBean;
import io.apiman.manager.api.beans.metrics.HistogramIntervalType;
import io.apiman.manager.api.beans.metrics.ResponseStatsHistogramBean;
import io.apiman.manager.api.beans.metrics.ResponseStatsPerAppBean;
import io.apiman.manager.api.beans.metrics.ResponseStatsPerPlanBean;
import io.apiman.manager.api.beans.metrics.ResponseStatsSummaryBean;
import io.apiman.manager.api.beans.metrics.UsageHistogramBean;
import io.apiman.manager.api.beans.metrics.UsagePerAppBean;
import io.apiman.manager.api.beans.metrics.UsagePerPlanBean;

import org.joda.time.DateTime;

/**
 * An interface used to access metrics information.  Typically metrics are
 * recorded at runtime by the API Gateway into some sort of data store (e.g.
 * a time series database like influxdb).  Implementations of this interface
 * must know how to extract the metrics information in useful and common ways
 * from that data store.  So for example there should be an Elasticsearch
 * implementation of this interface that is able to extract metrics recorded
 * in ES.
 *
 * @author eric.wittmann@redhat.com
 */
public interface IMetricsAccessor {

    /**
     * Query the metrics store for the total # of requests made to the service
     * per time period within the date range.  This will return an array with one
     * entry per bucket in the requested interval, even if the bucket has zero
     * results.  So, for example, if the request is for the last 90 days with an
     * interval of 'day', the result will be an array with 90 entries, each entry
     * with a label and a value >= 0.
     *
     * @param organizationId
     * @param serviceId
     * @param version
     * @param interval
     * @param from
     * @param to
     */
    UsageHistogramBean getUsage(String organizationId, String serviceId, String version,
            HistogramIntervalType interval, DateTime from, DateTime to);

    /**
     * Query the metrics store for # of requests made to a service broken
     * down by Application.
     *
     * @param organizationId
     * @param serviceId
     * @param version
     * @param from
     * @param to
     */
    UsagePerAppBean getUsagePerApp(String organizationId, String serviceId, String version,
            DateTime from, DateTime to);

    /**
     * Query the metrics store for # of requests made to a service broken
     * down by plan.  For exclusively public services this will return no data.
     *
     * @param organizationId
     * @param serviceId
     * @param version
     * @param from
     * @param to
     */
    UsagePerPlanBean getUsagePerPlan(String organizationId, String serviceId, String version,
            DateTime from, DateTime to);

    /**
     * Query the metrics store for a histogram of response statistics, including total
     * number of responses, # of error responses,
     * @param organizationId
     * @param serviceId
     * @param version
     * @param interval
     * @param from
     * @param to
     */
    ResponseStatsHistogramBean getResponseStats(String organizationId, String serviceId, String version,
            HistogramIntervalType interval, DateTime from, DateTime to);

    /**
     * Query the metrics store for response type stats (total, errors, failures) for a given
     * service over a specified time range.  Returns a total summary of stats.
     * @param organizationId
     * @param serviceId
     * @param version
     * @param from
     * @param to
     */
    ResponseStatsSummaryBean getResponseStatsSummary(String organizationId, String serviceId, String version,
            DateTime from, DateTime to);

    /**
     * Query the metrics store for response type stats (total, errors, failures) for a given
     * service over a specified time range per application.
     * @param organizationId
     * @param serviceId
     * @param version
     * @param from
     * @param to
     */
    ResponseStatsPerAppBean getResponseStatsPerApp(String organizationId, String serviceId, String version,
            DateTime from, DateTime to);

    /**
     * Query the metrics store for response type stats (total, errors, failures) for a given
     * service over a specified time range per plan.
     * @param organizationId
     * @param serviceId
     * @param version
     * @param from
     * @param to
     */
    ResponseStatsPerPlanBean getResponseStatsPerPlan(String organizationId, String serviceId, String version,
            DateTime from, DateTime to);

    /**
     * Query the metrics store for # of requests made to a service broken
     * down by plan.  For exclusively public services this will return no data.
     *
     * @param organizationId
     * @param applicationId
     * @param version
     * @param from
     * @param to
     */
    AppUsagePerServiceBean getAppUsagePerService(String organizationId, String applicationId, String version,
            DateTime from, DateTime to);

}
