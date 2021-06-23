package com.hitex.evn.api.user;

import com.hitex.evn.api.baseApi.IApi;
import com.hitex.evn.constant.ApplicationCode;
import com.hitex.evn.dto.request.BaseRequestData;
import com.hitex.evn.dto.response.baseReponse.BaseResponseData;
import com.hitex.evn.dto.response.baseReponse.IResponseData;
import com.hitex.evn.dto.response.user.ListUserResponse;
import com.hitex.evn.service.UserService;
import com.hitex.evn.utils.exception.ApplicationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Chidq
 * @project evn-vinhtan
 * @created 22/05/2021 - 1:39 PM
 */

@Component("getListUser")
@Log4j2
public class GetLisUser implements IApi {
    @Autowired
    UserService userService;

    @Override
    public BaseResponseData excute(BaseRequestData request) throws ApplicationException {
        BaseResponseData<IResponseData> baseResponseData = new BaseResponseData<>();
        try {
            ListUserResponse listUserResponse = userService.findAllUser(request);
            baseResponseData.setWsResponse(listUserResponse);
            baseResponseData.setErrorCode(ApplicationCode.SUCCESS);
            baseResponseData.setMessage(ApplicationCode.getMessage(ApplicationCode.SUCCESS));
        } catch (ApplicationException e) {
            baseResponseData.setErrorCode(e.getCode());
            baseResponseData.setMessage(ApplicationCode.getMessage(e.getCode()));
        } finally {
            log.info("\n Request :" + request);
            log.info("\n Response :" + baseResponseData);
        }
        return baseResponseData;
    }
}
