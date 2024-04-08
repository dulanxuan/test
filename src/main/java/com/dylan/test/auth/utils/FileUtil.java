package com.dylan.test.auth.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtil {

    public static void mkdir (String path) {
        File file = new File(path);    //如果文件夹不存在则创建
        if  (!file.exists()  && !file.isDirectory()) {
            file.mkdir();
        }
    }

    public static String jarPath() {
        //获取当前类所在路径
        String path = FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        //解决中文路径
        try {
            path = java.net.URLDecoder.decode(path,"UTF-8");
            File file = new File(path);
            path = file.getParentFile().getParentFile().getParentFile().getParent();
            //路径里可能会以file:\E:\xxx\ 显示 需要过虑出正确地址
            path = path.replace("file:","");
            path = path.replace("\\", "/");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return path;
    }

    public static void fileWriter(String fileName, String data){
        if (StringUtils.isBlank(data)) {
            return;
        }
        try (FileWriter fw = new FileWriter(fileName)){
            fw.write(data);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String fileReadToText(String filePath) {
        // 使用ArrayList来存储每行读取到的字符串
        StringBuffer sb = new StringBuffer();
        try (FileReader f = new FileReader(filePath);
             BufferedReader b = new BufferedReader(f)) {
            String s;
            //判断是否到一行字符串
            while ((s = b.readLine()) != null) {
                if (StringUtils.isNotBlank(sb.toString())) {
                    sb.append("\n");
                }
                sb.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static List<String> listFiles(String path) {
        File filePath = new File(path);
        File[] files = filePath.listFiles();
        List<String> fileNames = new ArrayList<>();
        if (files == null || files.length == 0) {
            return fileNames;
        }
        for (File f : files) {
            fileNames.add(f.getName());
        }
        return fileNames;
    }

    public static void createFileCover(String filePath) {
        File file = new File(filePath);
        try(FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("");// 清空
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
