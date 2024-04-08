package com.dylan.test.auth.common.consts;

public class ExceptionConstant {

    /**
     * 解码错误
     */
    public static class EncryptionException extends Exception {
        public static final String MSG_ENCODE = "Encryption error";
        public static final String MSG_DECODE = "Decryption error";

        public EncryptionException(String msgEncode) {
            super(msgEncode);
        }
    }

    /**
     * 解析错误
     */
    public static class UserInfoParseException extends Exception {
        public static final String MSG = "User info parse error";
        public UserInfoParseException() {
            super(MSG);
        }
    }

    /**
     * 校验用户信息为空
     */
    public static class UserInfoValidateException extends Exception {
        public static final String MSG = "User information validation failed";
        public UserInfoValidateException() {
            super(MSG);
        }
    }



}
