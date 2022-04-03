package com.steph.api.endpoints.reference.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "LinkedFields")
@Table(name = "linkedField")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LinkedFieldsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "referenceUuid")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String referenceUuid;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "fieldUuid")
    @JsonUnwrapped
    public FieldsEntity options;

    private int position;

    public void setReferenceUuid(String referenceUuid) {
        this.referenceUuid = referenceUuid;
    }

    public void setOptions(FieldsEntity options) {
        this.options = options;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public String getReferenceUuid() {
        return referenceUuid;
    }

    public FieldsEntity getOptions() {
        return options;
    }
}
