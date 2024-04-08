package com.dylan.test.auth.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dylan.test.auth.common.cache.UserCache;
import com.dylan.test.auth.common.consts.StringPool;
import com.dylan.test.auth.dao.UserDao;
import com.dylan.test.auth.entity.UserAuthEntity;
import com.dylan.test.auth.utils.FileUtil;
import com.dylan.test.auth.utils.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class UserDaoImpl implements UserDao {

    public static final String DB_PATH = "userDb";

    private static volatile Map<String, String> fileMap = new ConcurrentHashMap<>();

    @Override
    public void addUser(UserAuthEntity userAuthEntity) {
        saveFile(userAuthEntity.getUserId(), JSON.toJSON(userAuthEntity).toString());
    }

    @Override
    public void init() {

        // 查看目录是否存在，没有就创建
        String path = FileUtil.jarPath().concat(StringPool.SLASH).concat(DB_PATH);
        FileUtil.mkdir(path);

        // 文件列表
        List<String> files = FileUtil.listFiles(path);

        // 循环存缓存
        for (String fileName : files) {
            try {
                String filePath = path.concat(StringPool.SLASH).concat(fileName);
                String json = FileUtil.fileReadToText(filePath);
                UserAuthEntity userAuthEntity = JSONObject.parseObject(json).toJavaObject(UserAuthEntity.class);
                UserCache.userMap.put(userAuthEntity.getUserId(), json);
                fileMap.put(userAuthEntity.getUserId(), fileName);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

    }

    synchronized private void saveFile(String userId, String authJSON) {

        // 创建目录
        String path = FileUtil.jarPath().concat(StringPool.SLASH).concat(DB_PATH);
        FileUtil.mkdir(path);

        // 记录缓存
        UserCache.userMap.put(userId, authJSON);

        // 生成文件id
        String fileName = IdGenerator.getSnowflakeId();

        // 保存文件
        String filePath = path.concat(StringPool.SLASH).concat(fileName);
        FileUtil.createFileCover(filePath);
        FileUtil.fileWriter(filePath, authJSON);

        // 是否有文件已被记录
        if (fileMap.containsKey(userId)) {
            new File(path.concat(StringPool.SLASH).concat(fileMap.get(userId))).delete();
        }

        fileMap.put(userId, fileName);
    }


}
