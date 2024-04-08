package com.dylan.test.auth.service.encryption.impl;

import com.dylan.test.auth.common.consts.ExceptionConstant;
import com.dylan.test.auth.service.encryption.EncryptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;

@Service
@Slf4j
public class Base64EncryptionServiceImpl implements EncryptionService {

    @Override
    public String encode(String str) throws ExceptionConstant.EncryptionException{
        // base64字符串
        String base64Str = "";
        try {
            // 非字符串才进行编码
            if (str != null && str.length() > 0) {
                // String 转 byte[]
                byte[] bytes = str.getBytes("utf-8");
                // 编码
                base64Str = DatatypeConverter.printBase64Binary(bytes);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionConstant.EncryptionException(ExceptionConstant.EncryptionException.MSG_ENCODE);
        }
        return base64Str;
    }

    @Override
    public String decode(String base64Str) throws ExceptionConstant.EncryptionException {
        // 解码后的字符串
        String str = "";
        try {
            // 非空字符串才进行解码
            if (base64Str != null && base64Str.length() > 0) {
                // 解码
                byte[] base64Bytes = DatatypeConverter.parseBase64Binary(base64Str);
                // byte[] 转 String
                str = new String(base64Bytes, "utf-8");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionConstant.EncryptionException(ExceptionConstant.EncryptionException.MSG_DECODE);
        }
        return str;
    }
}
