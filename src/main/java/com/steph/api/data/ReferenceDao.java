package com.steph.api.data;

import com.steph.api.entity.ReferenceEntity;
import com.steph.api.entity.ReferenceJson;

import java.util.List;

public interface ReferenceDao {

    ReferenceJson getEntityByIdentifier(String uuid);

    ReferenceEntity getEntityByName(String name);

    void save(ReferenceEntity entity);

    void delete(ReferenceEntity entity);

    List<ReferenceEntity> getAllTypes();

}
