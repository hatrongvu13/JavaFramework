package com.tdt.service;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Data
@Component
public class EntityManagerService {
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @PersistenceContext(unitName = "slaveEntityManagerFactory")
    private EntityManager slaveEntityManager;
}
