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

package com.axibase.tsd.plain;

import com.axibase.tsd.util.AtsdUtil;

import java.util.Collections;
import java.util.Map;

/**
 * @author Nikolay Malevanny.
 */
public class PropertyInsertCommand extends AbstractInsertCommand {
    private final String typeName;
    private final Map<String, String> values;

    public PropertyInsertCommand(String entityName, String typeName, Long timeMillis, Map<String, String> tags,
                                 Map<String, String> values) {
        super(entityName, timeMillis, tags);
        AtsdUtil.check(typeName, "Type name is null");
        this.typeName = typeName;
        this.values = (values == null) ? Collections.<String, String>emptyMap() : values;
    }

    @Override
    protected void appendValues(StringBuilder sb) {
        // property e:abc001 t:disk k:name=sda v:size=203459 v:fs_type=nfs
        sb.append(" t:").append(clean(typeName));
        appendKeysAndValues(sb, " v:", values);
    }
}
