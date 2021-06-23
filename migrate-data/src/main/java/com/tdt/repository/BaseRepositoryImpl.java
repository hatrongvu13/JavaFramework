package com.tdt.repository;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

@Slf4j
public class BaseRepositoryImpl<ENTITY, ID extends Serializable> extends SimpleJpaRepository<ENTITY, ID>
        implements BaseRepository<ENTITY, ID> {
    private static final int BATCH_SIZE = 100;
    private final JpaEntityInformation<ENTITY, ID> entityInformation;
    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<ENTITY, ID> entityInformation,
                              @Qualifier("entityManagerFactory") EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void bulkInsert(List<ENTITY> entities) {
        entityManager.unwrap(Session.class).setJdbcBatchSize(BATCH_SIZE);
        entities.forEach(entity -> {
            entityManager.persist(entity);
        });
        entityManager.flush();
        entityManager.clear();
    }
}
