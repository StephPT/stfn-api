package com.steph.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "FieldsEntity")
@Table(name = "fields")
public class FieldsEntity {

    private String name;

    private String label;

    private String type;

    private String required;

    public void setName(String name) {
        this.name = name;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    @Id
    public String getName() {
        return name;
    }

    public String getRequired() {
        return required;
    }

    public String getType() {
        return type;
    }
}
