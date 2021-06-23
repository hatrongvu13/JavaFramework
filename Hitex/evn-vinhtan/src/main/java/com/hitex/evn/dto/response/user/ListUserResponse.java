package com.hitex.evn.dto.response.user;

import com.hitex.evn.dto.response.baseReponse.IResponseData;
import com.hitex.evn.model.User;
import lombok.Data;

import java.util.List;

/**
 * @author Chidq
 * @project evn-vinhtan
 * @created 22/05/2021 - 1:21 PM
 */
@Data
public class ListUserResponse implements IResponseData {
    List<User> userList;
    private int totalItem;
    private int totalPage;
}
