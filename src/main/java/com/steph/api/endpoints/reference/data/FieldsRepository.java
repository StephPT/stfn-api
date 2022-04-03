package com.steph.api.endpoints.reference.data;

import com.steph.api.endpoints.reference.entity.FieldsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldsRepository extends JpaRepository<FieldsEntity, String> {

}
