package com.steph.api.data;

import com.steph.api.entity.ReferenceEntity;

public interface ReferenceDao {

    ReferenceEntity getEntityByIdentifier(String uuid);

    ReferenceEntity getEntityByName(String name);

    void save(ReferenceEntity entity);

    void delete(ReferenceEntity entity);

}
