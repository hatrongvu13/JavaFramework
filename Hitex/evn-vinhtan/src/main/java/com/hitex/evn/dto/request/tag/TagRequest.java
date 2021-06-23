package com.hitex.evn.dto.request.tag;

import com.hitex.evn.dto.request.IRequestData;
import com.hitex.evn.model.Tag;
import lombok.Data;

@Data
public class TagRequest extends Tag implements IRequestData {
    @Override
    public boolean isValid() {
        return false;
    }
}
