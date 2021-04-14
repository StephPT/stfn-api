package com.steph.api.entity;

import javax.persistence.*;

@Entity(name = "LinkedFields")
@Table(name = "linkedField")
public class LinkedFieldsEntity {

    private String uuid;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    @Id
    public String getUuid() {
        return uuid;
    }
}
