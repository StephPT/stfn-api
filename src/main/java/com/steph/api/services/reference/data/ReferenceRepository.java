package com.steph.api.services.reference.data;

import com.steph.api.services.reference.entity.ReferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceRepository extends JpaRepository<ReferenceEntity, String> {
}
