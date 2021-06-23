package com.hitex.evn.api.baseApi;


import com.hitex.evn.dto.request.BaseRequestData;
import com.hitex.evn.dto.response.baseReponse.BaseResponseData;
import com.hitex.evn.utils.exception.ApplicationException;

public interface IApi {
    BaseResponseData excute(BaseRequestData request) throws ApplicationException;
}
