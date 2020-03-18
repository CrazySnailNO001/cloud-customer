package com.xzh.customer.common.exception;

import com.xzh.customer.common.IMessageDto;
import lombok.Data;

@Data
public class BusinessResourceNotFoundException extends RuntimeException {

    private IMessageDto errorDto;
    private Object[] msgArgs;
    private Object data;

    public BusinessResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public BusinessResourceNotFoundException(String message) {
        super(message);
    }

    public BusinessResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessResourceNotFoundException(IMessageDto errorDto) {
        this.errorDto = errorDto;
    }

    public BusinessResourceNotFoundException(IMessageDto errorDto, Object data) {
        this.data = data;
        this.errorDto = errorDto;
    }

    public BusinessResourceNotFoundException(IMessageDto errorDto, Throwable cause) {
        super(cause);
        this.errorDto = errorDto;
    }

    public BusinessResourceNotFoundException(IMessageDto errorDto, Object[] msgArgs) {
        this.errorDto = errorDto;
        this.msgArgs = msgArgs;
    }

    public BusinessResourceNotFoundException(IMessageDto errorDto, Object[] msgArgs, Throwable cause) {
        super(cause);
        this.errorDto = errorDto;
        this.msgArgs = msgArgs;
    }
}
