package com.steph.api.endpoints.resource.data;

import com.steph.api.endpoints.resource.entity.DirectoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectoriesRepository extends JpaRepository<DirectoriesEntity, String> {
}
