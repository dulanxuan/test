package com.dylan.test.auth.entity;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

@Data
public class UserEntity {

    private String id;

    private String accountName;

    private String role;

    public static final String AUTH_ADMIN = "admin";

    public static final String AUTH_USER = "user";

    public boolean validate() {
        if (StringUtils.isBlank(id) || StringUtils.isBlank(accountName) || StringUtils.isBlank(role)) {
            return false;
        }
        if (!AUTH_ADMIN.equals(role) && !AUTH_USER.equals(AUTH_USER)) {
            return false;
        }
        return true;
    }
}
