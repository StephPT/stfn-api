package com.steph.api.reference.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "LinkedFields")
@Table(name = "linkedField")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LinkedFieldsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "name")
    public FieldsEntity options;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setOptions(FieldsEntity options) {
        this.options = options;
    }

    public String getUuid() {
        return uuid;
    }

    public FieldsEntity getOptions() {
        return options;
    }
}
