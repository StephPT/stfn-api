package com.steph.api.reference.data;

import com.steph.api.reference.entity.ReferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceRepository extends JpaRepository<ReferenceEntity, String> {
}
