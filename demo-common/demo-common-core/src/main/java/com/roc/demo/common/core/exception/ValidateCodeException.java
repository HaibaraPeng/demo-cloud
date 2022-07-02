package com.roc.demo.common.core.exception;

/**
 * @Description ValidateCodeException
 * @Author penn
 * @Date 2022/7/2 10:37
 */
public class ValidateCodeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ValidateCodeException() {
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
