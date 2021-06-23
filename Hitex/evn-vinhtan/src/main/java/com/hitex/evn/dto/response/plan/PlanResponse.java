package com.hitex.evn.dto.response.plan;

import com.hitex.evn.dto.response.baseReponse.IResponseData;
import com.hitex.evn.model.Plan;
import com.hitex.evn.model.Work;
import lombok.Data;

import java.util.List;

@Data
public class PlanResponse extends Plan implements IResponseData {
    List<Work> works;
}
