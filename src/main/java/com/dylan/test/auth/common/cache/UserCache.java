package com.dylan.test.auth.common.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserCache {
    public static volatile Map<String, String> userMap = new ConcurrentHashMap<>();
}
