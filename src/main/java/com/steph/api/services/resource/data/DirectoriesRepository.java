package com.steph.api.services.resource.data;

import com.steph.api.services.resource.entity.DirectoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectoriesRepository extends JpaRepository<DirectoriesEntity, String> {
}
