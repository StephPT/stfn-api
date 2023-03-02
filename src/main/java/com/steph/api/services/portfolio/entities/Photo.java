package com.steph.api.services.portfolio.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "photo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Photo implements Serializable {

    @Id
    private String uuid;

    private String galleryId;

    private String name;

    private Date uploaded;

    private String filePath;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "photoId")
    private List<Attributes> attributes;

    public Photo() {

    }
    public Photo(String uuid, String galleryId, String name) {
        this.name = name;
        this.uuid = uuid;
        this.galleryId = galleryId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(String galleryId) {
        this.galleryId = galleryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUploaded() {
        return uploaded;
    }

    public void setUploaded(Date uploaded) {
        this.uploaded = uploaded;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<Attributes> getAttributes() {
        if (attributes == null) {
            attributes = new ArrayList<>();
        }
        return attributes;
    }

    public void setAttributes(List<Attributes> attributes) {
        this.attributes = attributes;
    }
}
