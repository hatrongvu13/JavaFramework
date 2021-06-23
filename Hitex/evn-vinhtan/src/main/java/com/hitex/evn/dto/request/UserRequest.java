package com.hitex.evn.dto.request;

import com.hitex.evn.model.User;
import lombok.Data;

/**
 * @author Chidq
 * @project evn-vinhtan
 * @created 22/05/2021 - 1:34 PM
 */
@Data
public class UserRequest extends User implements IRequestData {
    private int pageSize;
    private int page;
    @Override
    public boolean isValid() {
        return false;
    }
}
