package com.steph.api.test.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.steph.api.entity.ReferenceEntity;
import com.steph.api.util.JsonUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

public class JsonConvertionTest extends Assert {

    private final static String JSON = "{\"uuid\":\"Test\",\"name\":\"Test Reference\",\"format\":\"[@d]\"}";

    private ReferenceEntity entity;

    @Before
    public void setup() {
        entity = new ReferenceEntity();
        entity.setUuid("Test");
        entity.setFormat("[@d]");
        entity.setName("Test Reference");
    }

    @Test
    public void jsonCastTest() throws JsonProcessingException {
        JsonUtil<ReferenceEntity> jsonUtil = new JsonUtil<>();
        String result = jsonUtil.jsonConverter(entity);
        assertEquals(JSON, result);
    }

    @Test
    public void objectCastTest() throws IOException {
        JsonUtil<ReferenceEntity> jsonUtil = new JsonUtil<>();
        ReferenceEntity entity = jsonUtil.objectConverter(JSON, ReferenceEntity.class);
        assertEquals(this.entity.toString(), entity.toString());
    }

}
