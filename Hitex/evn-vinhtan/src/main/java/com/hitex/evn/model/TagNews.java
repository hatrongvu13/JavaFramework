package com.hitex.evn.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tag_news")
public class TagNews {
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "id_tag")
    private Integer idTag;
    @Basic
    @Column(name = "id_news")
    private Integer idNews;
}
