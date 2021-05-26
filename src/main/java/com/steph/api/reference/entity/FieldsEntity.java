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
    private String uuid;

    public String label;

    public String type;

    private String placeholder;

    private String prefix;

    private String suffix;

    private Boolean italic;

    public Boolean required;

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public void setItalic(Boolean italic) {
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

    public String getUuid() {
        return uuid;
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

    public Boolean getItalic() {
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
