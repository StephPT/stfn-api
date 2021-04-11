package com.steph.api.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ReferenceEntity")
@Table(name = "uswReference")
public class ReferenceEntity {

    private String uuid;

    private String name;

    private String format;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "uuid")
    public String getUuid() {
        return uuid;
    }

    @Column(name = "format")
    public String getFormat() {
        return format;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + format;
    }
}
