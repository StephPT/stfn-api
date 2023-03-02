package com.steph.api.services.portfolio.data;

import com.steph.api.services.portfolio.entities.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery, String> {

}
