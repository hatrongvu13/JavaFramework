package com.hitex.evn.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Chidq
 * @project evn-vinhtan
 * @created 22/05/2021 - 1:12 PM
 */
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
}
