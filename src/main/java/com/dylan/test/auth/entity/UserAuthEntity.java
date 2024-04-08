package com.dylan.test.auth.entity;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Set;

@Data
public class UserAuthEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private Set<String> endpoint;

    public boolean validate() {
        if (StringUtils.isBlank(this.userId) ||
                this.endpoint == null || this.endpoint.size() == 0) {
            return false;
        }
        return true;
    }


}
