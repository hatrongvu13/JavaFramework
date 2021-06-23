package com.hitex.evn.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "work")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "id_plan")
    private int idPlan;

    @Column(name = "result")
    private int result;

    @Column(name = "work_name")
    private String workName;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "updated")
    private Timestamp updated;

    @Column(name = "target")
    private int target;

    @Column(name = "id_unit")
    private int idUnit;
}
