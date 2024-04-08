package com.dylan.test.auth.service.user;

import com.dylan.test.auth.common.consts.ExceptionConstant;
import com.dylan.test.auth.entity.UserEntity;
import com.dylan.test.auth.entity.vo.AddUserVo;

public interface UserService {

    UserEntity parse(String token) throws ExceptionConstant.EncryptionException, ExceptionConstant.UserInfoParseException;

    AddUserVo addUser(AddUserVo addUserVo);
}
