package com.steph.api.services.resource.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity(name = "DirectoriesEntity")
@Table(name = "directories")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DirectoriesEntity implements Serializable {

    @Id
    private String uuid;

    private String name;

    private String path;

    private String url;

    private boolean active;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
