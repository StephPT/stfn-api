package com.steph.api.reference.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "FieldsEntity")
@Table(name = "fields")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FieldsEntity implements Serializable {

    @Id
    @Column(name = "uuid")
    private String uuid;

    public String label;

    public String type;

    private String placeholder;

    private String prefix;

    private String suffix;

    private Boolean italic;

    public Boolean required;

    @ManyToMany()
    @JsonIgnore
    private Set<ReferenceEntity> references;

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

    public Set<ReferenceEntity> getReferences() {
        return references;
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

    public void setReferences(Set<ReferenceEntity> references) {
        this.references = references;
    }
}
