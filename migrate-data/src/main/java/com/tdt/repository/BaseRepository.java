package com.tdt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<ENTITY, ID extends Serializable> extends JpaRepository<ENTITY, ID> {

    void bulkInsert(List<ENTITY> entities);
}
