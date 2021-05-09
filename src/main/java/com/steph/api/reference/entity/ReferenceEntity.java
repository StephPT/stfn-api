package com.steph.api.reference.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "ReferenceEntity")
@Table(name = "uswReference")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReferenceEntity implements Serializable {

    @Id
    @Column(name = "uuid")
    public String uuid;

    @Column(name = "name")
    public String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "uuid", referencedColumnName = "uuid")
    public Set<LinkedFieldsEntity> fields;

    @Column(name = "example")
    public String example;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public void setFields(Set<LinkedFieldsEntity> fields) {
        this.fields = fields;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getExample() {
        return example;
    }

    public Set<LinkedFieldsEntity> getFields() {
        return fields;
    }
}
