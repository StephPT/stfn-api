package com.steph.api.services.portfolio.data;

import com.steph.api.services.portfolio.entities.Gallery;
import com.steph.api.services.portfolio.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, String> {

}
