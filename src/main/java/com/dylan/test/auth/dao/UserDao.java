package com.dylan.test.auth.dao;

import com.dylan.test.auth.entity.UserAuthEntity;

public interface UserDao {
    void addUser(UserAuthEntity userAuthEntity);
    void init();
}
