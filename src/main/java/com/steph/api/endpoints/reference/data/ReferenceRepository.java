package com.steph.api.endpoints.reference.data;

import com.steph.api.endpoints.reference.entity.ReferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceRepository extends JpaRepository<ReferenceEntity, String> {
}
