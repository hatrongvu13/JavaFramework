package com.hitex.evn.dto.response.plan;

import com.hitex.evn.dto.response.baseReponse.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListPlanResponse implements IResponseData {
    List<PlanResponse> planList;
    private int currentPage;
}
