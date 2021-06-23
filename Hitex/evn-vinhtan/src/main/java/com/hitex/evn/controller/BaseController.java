package com.hitex.evn.controller;



import com.hitex.evn.api.baseApi.IApi;
import com.hitex.evn.dto.request.BaseRequestData;
import com.hitex.evn.dto.response.baseReponse.BaseResponseData;
import com.hitex.evn.dto.response.baseReponse.IResponseData;
import com.hitex.evn.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author MaiPH
 */
public class BaseController {
    
    @Autowired
    private ApplicationContext context;

    protected BaseResponseData<IResponseData> handle(String apiName, BaseRequestData request) throws ApplicationException {
        IApi api = context.getBean(apiName, IApi.class);
        if(api == null){
        }
        return api.excute(request);
    }
}
