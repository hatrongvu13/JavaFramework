package com.hitex.evn.repository;

import com.hitex.evn.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Integer> {

    //get list work by id plan
    List<Work> findByIdPlan(int idPlan);
}
