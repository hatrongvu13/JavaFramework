package com.hitex.evn.service;

import com.hitex.evn.dto.request.BaseRequestData;
import com.hitex.evn.dto.response.plan.ListPlanResponse;

public interface PlanService {

    ListPlanResponse getListPlan(BaseRequestData baseRequestData);

}
