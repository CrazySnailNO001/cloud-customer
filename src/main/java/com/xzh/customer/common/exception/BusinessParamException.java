package com.xzh.customer.common.exception;

import com.xzh.customer.common.IMessageDto;
import lombok.Data;

@Data
public class BusinessParamException extends RuntimeException {

    private IMessageDto errorDto;
    private Object[] msgArgs;
    private Object data;

    public BusinessParamException(Throwable cause) {
        super(cause);
    }

    public BusinessParamException(String message) {
        super(message);
    }

    public BusinessParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessParamException(IMessageDto errorDto) {
        this.errorDto = errorDto;
    }

    public BusinessParamException(IMessageDto errorDto, Object data) {
        this.data = data;
        this.errorDto = errorDto;
    }

    public BusinessParamException(IMessageDto errorDto, Throwable cause) {
        super(cause);
        this.errorDto = errorDto;
    }

    public BusinessParamException(IMessageDto errorDto, Object[] msgArgs) {
        this.errorDto = errorDto;
        this.msgArgs = msgArgs;
    }

    public BusinessParamException(IMessageDto errorDto, Object[] msgArgs, Throwable cause) {
        super(cause);
        this.errorDto = errorDto;
        this.msgArgs = msgArgs;
    }
}
