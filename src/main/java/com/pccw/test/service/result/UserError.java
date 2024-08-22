package com.pccw.test.service.result;

/**
 * @Author：xu.le
 * @Package：com.pccw.test.service.result
 * @Project：Test
 * @name：UserError
 * @Date：2024/8/20 13:40
 * @Filename：UserError
 **/
public enum UserError {

    DUPLICATE_REGISTER(01, "DUPLICATE_REGISTER"),
    UNKNOWN_ERROR(99, "UNKNOWN_ERROR");

    private final int code;
    private final String message;

    UserError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
