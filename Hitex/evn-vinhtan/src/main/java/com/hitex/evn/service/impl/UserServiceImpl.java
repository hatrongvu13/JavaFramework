package com.hitex.evn.service.impl;

import com.hitex.evn.dto.request.BaseRequestData;
import com.hitex.evn.dto.request.UserRequest;
import com.hitex.evn.dto.response.user.ListUserResponse;
import com.hitex.evn.model.User;
import com.hitex.evn.repository.UserRepository;
import com.hitex.evn.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chidq
 * @project evn-vinhtan
 * @created 22/05/2021 - 1:25 PM
 */
@Service
@Log4j2
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public ListUserResponse findAllUser(BaseRequestData baseRequestData) {
        ListUserResponse listUserResponse = new ListUserResponse();
        //Xu ly ham logic su dung try catch
//        try {
        UserRequest userRequest = (UserRequest) baseRequestData.getWsRequest();
            List<User> userList = userRepository.findAll();
            int countUserList = userRepository.countUser();
            int totalPage = (int) Math.ceil((double) countUserList / (double) userRequest.getPageSize());
            listUserResponse.setUserList(userList);
            listUserResponse.setTotalItem(countUserList);
            listUserResponse.setTotalPage(totalPage);
//        }catch (ApplicationException e){
//            log.error(e);
//            throw e;
//        }
        return listUserResponse;
    }
}
