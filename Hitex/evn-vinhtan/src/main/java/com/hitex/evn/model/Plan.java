package com.hitex.evn.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type_plan")
    private int typePlan;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "updated")
    private Timestamp updated;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updateBy;

    @Column(name = "status")
    private int status;

    @Column(name = "comment")
    private String comment;
}
