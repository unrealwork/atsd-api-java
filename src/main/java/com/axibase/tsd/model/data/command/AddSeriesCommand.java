/*
* Copyright 2015 Axibase Corporation or its affiliates. All Rights Reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License").
* You may not use this file except in compliance with the License.
* A copy of the License is located at
*
* https://www.axibase.com/atsd/axibase-apache-2.0.pdf
*
* or in the "license" file accompanying this file. This file is distributed
* on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
* express or implied. See the License for the specific language governing
* permissions and limitations under the License.
*/
package com.axibase.tsd.model.data.command;

import com.axibase.tsd.model.data.Series;
import com.axibase.tsd.util.AtsdUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Nikolay Malevanny.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddSeriesCommand {
    @JsonProperty("entity")
    private String entityName;
    @JsonProperty("metric")
    private String metricName;
    private Map<String, String> tags;
    private List<Series> data;

    public AddSeriesCommand() {
    }

    public AddSeriesCommand(String entityName, String metricName, String... tagNamesAndValues) {
        this.entityName = entityName;
        this.metricName = metricName;
        this.tags = AtsdUtil.toMap(tagNamesAndValues);
    }

    public String getEntityName() {
        return entityName;
    }

    public String getMetricName() {
        return metricName;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public List<Series> getData() {
        return data;
    }

    public void addSeries(Series series) {
        if (data == null) {
            data = new ArrayList<Series>();
        }
        data.add(series);
    }

    public void addSeries(Series... series) {
        if (data == null) {
            data = new ArrayList<Series>();
        }
        data.addAll(Arrays.asList(series));
    }
}