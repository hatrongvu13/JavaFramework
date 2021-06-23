package com.hitex.evn.dto.response.baseReponse;

import lombok.Data;

/**
 * BaseResponseData
 *
 * @author Chidq
 */
@Data
public class BaseResponseData<T extends IResponseData> {
    int errorCode;
    String message;
    T wsResponse;
}
