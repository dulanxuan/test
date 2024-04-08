package com.dylan.test.auth.utils;

import cn.hutool.core.util.IdUtil;

import java.util.UUID;

public class IdGenerator {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static final String getSnowflakeId(){
        return IdUtil.getSnowflake().nextIdStr();
    }

}
