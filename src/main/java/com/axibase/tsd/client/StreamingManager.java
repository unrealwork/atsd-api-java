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

package com.axibase.tsd.client;

import com.axibase.tsd.plain.PlainCommand;

import java.util.List;

/**
 * @author Nikolay Malevanny.
 */
public interface StreamingManager {
    void close();

    void send(PlainCommand plainCommand);

    boolean canSend();

    List<String> removeSavedPlainCommands();
}