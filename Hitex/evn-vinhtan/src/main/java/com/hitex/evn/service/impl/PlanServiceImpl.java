package com.hitex.evn.service.impl;

import com.hitex.evn.dto.request.BaseRequestData;
import com.hitex.evn.dto.response.plan.ListPlanResponse;
import com.hitex.evn.dto.response.plan.PlanResponse;
import com.hitex.evn.model.Plan;
import com.hitex.evn.model.Work;
import com.hitex.evn.repository.PlanRepository;
import com.hitex.evn.repository.WorkRepository;
import com.hitex.evn.service.PlanService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Log4j2
public class PlanServiceImpl implements PlanService {

    @Autowired
    PlanRepository planRepository;

    @Autowired
    WorkRepository workRepository;

    @Override
    public ListPlanResponse getListPlan(BaseRequestData baseRequestData) {
        ListPlanResponse listPlanResponse = new ListPlanResponse();
        List<PlanResponse> planList = new ArrayList<>();
        // get plan
        List<Plan> plans = planRepository.findAll();

        // get Work of plan
        if(Objects.isNull(plans)){
            log.warn("List plan is empty !");
        }
        plans.forEach(plan ->{
            PlanResponse planResponse = new PlanResponse();
            List<Work> works = workRepository.findByIdPlan(plan.getId());

            //set Plan information
            planResponse.setId(plan.getId());
            planResponse.setTypePlan(plan.getTypePlan());
            planResponse.setCreated(plan.getCreated());
            planResponse.setUpdated(plan.getUpdated());
            planResponse.setCreatedBy(plan.getCreatedBy());
            planResponse.setUpdateBy(plan.getUpdateBy());
            planResponse.setStatus(plan.getStatus());
            planResponse.setComment(plan.getComment());

            // set list work of plan
            planResponse.setWorks(works);

            // add to List Plan response
            planList.add(planResponse);
        });
        if (Objects.isNull(planList)){
            log.warn("List plan response is empty, please check again !");
        }
        listPlanResponse.setPlanList(planList);
        return listPlanResponse;
    }
}
