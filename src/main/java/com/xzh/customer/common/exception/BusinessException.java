package com.xzh.customer.common.exception;

import com.xzh.customer.common.IMessageDto;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

    private IMessageDto errorDto;
    private Object[] msgArgs;
    private Object data;

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(IMessageDto errorDto) {
        this.errorDto = errorDto;
    }

    public BusinessException(IMessageDto errorDto, Object data) {
        this.data = data;
        this.errorDto = errorDto;
    }

    public BusinessException(IMessageDto errorDto, Throwable cause) {
        super(cause);
        this.errorDto = errorDto;
    }

    public BusinessException(IMessageDto errorDto, Object[] msgArgs) {
        this.errorDto = errorDto;
        this.msgArgs = msgArgs;
    }

    public BusinessException(IMessageDto errorDto, Object[] msgArgs, Throwable cause) {
        super(cause);
        this.errorDto = errorDto;
        this.msgArgs = msgArgs;
    }
}
