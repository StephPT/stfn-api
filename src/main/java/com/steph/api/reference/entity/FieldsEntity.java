package com.steph.api.reference.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "FieldsEntity")
@Table(name = "fields")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FieldsEntity implements Serializable {

    @Id
    private String name;

    public String label;

    public String type;

    private String placeholder;

    private String prefix;

    private String suffix;

    private String italic;

    public Boolean required;

    public void setName(String name) {
        this.name = name;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setItalic(String italic) {
        this.italic = italic;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public Boolean getRequired() {
        return required;
    }

    public String getType() {
        return type;
    }

    public String getItalic() {
        return italic;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }
}
