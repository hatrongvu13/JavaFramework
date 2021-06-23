package com.hitex.evn.controller;

import com.hitex.evn.dto.request.BaseRequestData;
import com.hitex.evn.dto.request.UserRequest;
import com.hitex.evn.dto.response.baseReponse.BaseResponseData;
import com.hitex.evn.utils.exception.ApplicationException;
import com.mangofactory.swagger.annotations.ApiIgnore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chidq
 * @project evn-vinhtan
 * @created 22/05/2021 - 1:42 PM
 */
@RestController

@RequestMapping("/api/")
public class UserController extends BaseController {

    @PostMapping(value = "getListUser")
    @ResponseBody
    public ResponseEntity<?> addApi(@RequestBody BaseRequestData<UserRequest> request) throws ApplicationException {
        BaseResponseData response = handle("getListUser", request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
