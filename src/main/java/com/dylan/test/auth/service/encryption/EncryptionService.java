package com.dylan.test.auth.service.encryption;

import com.dylan.test.auth.common.consts.ExceptionConstant;

import java.io.UnsupportedEncodingException;

public interface EncryptionService {

    String encode(String str) throws ExceptionConstant.EncryptionException;

    String decode(String base64Str) throws ExceptionConstant.EncryptionException;

}