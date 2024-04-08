package com.dylan.test.auth.common.filter;

import com.alibaba.fastjson.JSONObject;
import com.dylan.test.auth.common.cache.UserCache;
import com.dylan.test.auth.common.consts.ExceptionConstant;
import com.dylan.test.auth.entity.UserAuthEntity;
import com.dylan.test.auth.entity.UserEntity;
import com.dylan.test.auth.service.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Value("${auth.admin-white-list:}")
    String adminWhiteList;

    private volatile static Set<String> adminWhiteListSet = new HashSet<String>();

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String token = httpServletRequest.getHeader("Authorization");
        if (StringUtils.isBlank(token)) {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        UserEntity user = userService.parse(token);

        if (user == null || !user.validate()) {
            throw new ExceptionConstant.UserInfoValidateException();
        }

        String path = httpServletRequest.getServletPath();
        if (adminWhiteValidate(path) && !UserEntity.AUTH_ADMIN.equals(user.getRole())) {
            httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }

        if (UserEntity.AUTH_USER.equals(user.getRole())) {
            if (!UserCache.userMap.containsKey(user.getId())) {
                httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
                return false;
            }

            UserAuthEntity userAuthEntity = JSONObject.parseObject(UserCache.userMap.get(user.getId())).toJavaObject(UserAuthEntity.class);

            if (userAuthEntity.getEndpoint().contains(path)) {
                httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
                return false;
            }
        }
        return true;
    }

    private boolean adminWhiteValidate(String path) {

        if (StringUtils.isNotBlank(adminWhiteList) && adminWhiteListSet.size() == 0) {
            initAdminWhiteListSet();
        }

        return adminWhiteListSet.contains(path);
    }

    synchronized private void initAdminWhiteListSet() {
        String[] adminWhites = adminWhiteList.split(",");
        for (String path : adminWhites) {
            adminWhiteListSet.add(path);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

}
