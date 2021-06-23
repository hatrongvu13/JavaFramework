package com.hitex.evn.dto.request;

import lombok.Data;

/**
 *
 * @author Chidq
 */
@Data
public class BaseRequestData<T extends IRequestData> {
    
    private String wsCode;
    private String sessionId;
    private String username;
    private String otp;
    private String token;
    private T wsRequest;
}
