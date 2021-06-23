package com.hitex.evn.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Tag {
    @Id
    @Column(name = "id_tag")
    private int idTag;
    @Basic
    @Column(name = "title")
    private String title;
}
