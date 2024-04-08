package com.dylan.test.auth.service.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.dylan.test.auth.common.consts.ExceptionConstant;
import com.dylan.test.auth.dao.UserDao;
import com.dylan.test.auth.entity.UserEntity;
import com.dylan.test.auth.entity.vo.AddUserVo;
import com.dylan.test.auth.service.encryption.EncryptionService;
import com.dylan.test.auth.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    EncryptionService encryptionService;

    @Autowired
    UserDao userDao;

    @Override
    public UserEntity parse(String token) throws ExceptionConstant.EncryptionException, ExceptionConstant.UserInfoParseException {
        String json = encryptionService.decode(token);
        try {
            UserEntity userEntity = ((JSONObject) JSONObject.parse(json)).toJavaObject(UserEntity.class);
            return userEntity;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionConstant.UserInfoParseException();
        }
    }

    @Override
    public AddUserVo addUser(AddUserVo addUserVo) {
        userDao.addUser(addUserVo);
        return addUserVo;
    }
}
