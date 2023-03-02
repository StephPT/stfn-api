package com.steph.api.services.portfolio.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gallery")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Gallery implements Serializable {

    @Id
    private String uuid;

    private String name;

    private Date dateCreated;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "galleryId")
    @JsonUnwrapped
    private List<Photo> photos;

    public Gallery() {

    }

    public Gallery(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.dateCreated = Date.valueOf(LocalDate.now());
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date date) {
        this.dateCreated = date;
    }

    public List<Photo> getPhotos() {
        if (photos == null) {
            photos = new ArrayList<>();
        }
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
