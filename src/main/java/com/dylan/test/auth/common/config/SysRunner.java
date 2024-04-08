package com.dylan.test.auth.common.config;

import com.dylan.test.auth.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SysRunner implements ApplicationRunner {

    @Autowired
    UserDao userDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userDao.init();
    }
}
