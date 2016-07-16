package com.trio.vmalogo.sys.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础异常
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }
}
