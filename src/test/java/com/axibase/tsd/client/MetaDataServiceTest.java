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

import com.axibase.tsd.TestUtil;
import com.axibase.tsd.model.meta.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static com.axibase.tsd.TestUtil.TTT_ENTITY;
import static com.axibase.tsd.TestUtil.TTT_METRIC;
import static org.junit.Assert.*;

/**
 * @author Nikolay Malevanny.
 */
public class MetaDataServiceTest {
    private MetaDataService metaDataService;
    private HttpClientManager httpClientManager;

    @Before
    public void setUp() {
        metaDataService = new MetaDataService();
        httpClientManager = TestUtil.buildHttpClientManager();
        metaDataService.setHttpClientManager(httpClientManager);
    }

    @Test
    public void testRetrieveMetrics() throws Exception {
        List metrics = metaDataService.retrieveMetrics(true, "name like '*'", TagAppender.ALL, 1);
        assertTrue(metrics.get(0) instanceof Metric);
        assertEquals(1, metrics.size());
    }

    @Test
    public void testRetrieveMetricsByEntity() throws Exception {
        List metrics = metaDataService.retrieveMetrics(TTT_ENTITY, null, "name like '*'", null, 10);
        assertTrue(metrics.get(0) instanceof Metric);
        assertEquals(1, metrics.size());
    }

    @Test
    public void testRetrieveMetric() throws Exception {
        Metric metric = metaDataService.retrieveMetric(TTT_METRIC);
        assertNotNull(metric);
        assertEquals(TTT_METRIC, metric.getName());
    }

    @Test
    public void testRetrieveEntities() throws Exception {
        {
            List<Entity> entities = metaDataService.retrieveEntities(null, "name like 'ttt-*'", TagAppender.ALL, 1);
            assertTrue(entities.get(0) instanceof Entity);
            assertEquals(1, entities.size());
        }

        {
            List<Entity> entities = metaDataService.retrieveEntities(null, "name = 'ttt-entity'", TagAppender.ALL, 1);
            assertTrue(entities.get(0) instanceof Entity);
            assertEquals(1, entities.size());
        }
    }

    @Test
    public void testRetrieveEntity() throws Exception {
        Entity entity = metaDataService.retrieveEntity(TTT_ENTITY);
        assertEquals(TTT_ENTITY, entity.getName());
    }

    @Test
    public void testRetrieveEntityAndTags() throws Exception {
        List<EntityAndTags> entityAndTagsList = metaDataService.retrieveEntityAndTags(TTT_METRIC, null);
        EntityAndTags entityAndTags = entityAndTagsList.get(0);
        assertTrue(entityAndTagsList.size() > 0);
        assertEquals(TTT_ENTITY, entityAndTags.getEntityName());
        assertTrue(entityAndTags.getTags().containsKey("ttt-tag-1"));
        assertTrue(entityAndTags.getTags().size()>0);
    }

    @Test
    public void testRetrieveEntityAndTagsByMetricAndEntity() throws Exception {
        List<EntityAndTags> entityAndTagsList = metaDataService.retrieveEntityAndTags(TTT_METRIC, TTT_ENTITY);
        EntityAndTags entityAndTags = entityAndTagsList.get(0);
        assertTrue(entityAndTagsList.size()>0);
        assertEquals(TTT_ENTITY, entityAndTags.getEntityName());

        try {
            metaDataService.retrieveEntityAndTags(" ", " ");
            fail();
        } catch (IllegalArgumentException e) {
            // OK
        }
    }

    @Test
    public void testRetrieveEntityGroups() throws Exception {
        List<EntityGroup> entityGroups = metaDataService.retrieveEntityGroups();
        assertTrue(entityGroups.get(0) instanceof EntityGroup);
        assertTrue(entityGroups.size()>0);
        boolean containsTestEntityGroup = false;
        for (Iterator<EntityGroup> iterator = entityGroups.iterator(); iterator.hasNext() && !containsTestEntityGroup; ) {
            EntityGroup entityGroup = iterator.next();
            containsTestEntityGroup = TestUtil.TTT_ENTITY_GROUP.equals(entityGroup.getName());
        }
        assertTrue(containsTestEntityGroup);
    }

    @Test
    public void testRetrieveEntityGroup() throws Exception {
        EntityGroup entityGroup = metaDataService.retrieveEntityGroup(TestUtil.TTT_ENTITY_GROUP);
        assertEquals(TestUtil.TTT_ENTITY_GROUP, entityGroup.getName());
    }

    @Test
    public void testRetrieveGroupEntities() throws Exception {
        List<Entity> entityList = metaDataService
                .retrieveGroupEntities(TestUtil.TTT_ENTITY_GROUP, null, null, TagAppender.ALL, null);
        Entity entity = entityList.get(0);
        assertEquals(1, entityList.size());
        assertEquals(TTT_ENTITY, entity.getName());
    }

    @After
    public void tearDown() {
        httpClientManager.close();
    }
}
