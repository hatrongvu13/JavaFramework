package com.hitex.evn.dto.response.baseReponse;

import lombok.Data;

/**
 *
 * @author Chidq
 */
@Data
public class ResponseError {
    
    private int code;
    private String message;
}
