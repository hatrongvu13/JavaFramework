package com.hitex.evn.service;

import com.hitex.evn.dto.request.BaseRequestData;
import com.hitex.evn.dto.response.user.ListUserResponse;
import com.hitex.evn.dto.response.user.UserResponse;
import com.hitex.evn.utils.exception.ApplicationException;

/**
 * @author Chidq
 * @project evn-vinhtan
 * @created 22/05/2021 - 1:15 PM
 */
public interface UserService {
    ListUserResponse findAllUser(BaseRequestData baseRequestData) throws ApplicationException;

}
