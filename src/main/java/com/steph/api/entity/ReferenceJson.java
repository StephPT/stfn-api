package com.steph.api.entity;

import org.thymeleaf.spring4.expression.Fields;

import java.util.ArrayList;

public class ReferenceJson {

    private String uuid;

    private String name;

    private ArrayList<FieldsEntity> fields = new ArrayList<>();

    private String format;

    public void setFields(ArrayList<FieldsEntity> fields) {
        this.fields = fields;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getFormat() {
        return format;
    }

    public ArrayList<FieldsEntity> getFields() {
        return fields;
    }
}
