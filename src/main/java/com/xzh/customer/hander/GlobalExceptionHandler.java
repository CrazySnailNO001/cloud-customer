package com.xzh.customer.hander;

import com.xzh.customer.common.ApiResponseDto;
import com.xzh.customer.common.AppClientResponse;
import com.xzh.customer.common.IMessageDto;
import com.xzh.customer.common.exception.BusinessException;
import com.xzh.customer.common.exception.BusinessParamException;
import com.xzh.customer.common.exception.BusinessResourceNotFoundException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

//    @ExceptionHandler(HystrixRuntimeException.class)
//    public ResponseEntity handle(HystrixRuntimeException e) {
//        log.error("HystrixRuntimeException occurs: ", e);
//
//        if (e.getCause() instanceof FeignException) {
//            return this.handle((FeignException) e.getCause());
//        } else {
//            HttpHeaders httpHeaders = new HttpHeaders();
//            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//            return new ResponseEntity<>(e.getCause().getCause().toString(),httpHeaders,HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity handle(FeignException e) {
        log.error("feign exception occurs: ", e);

        ResponseEntity responseEntity;

        switch (e.status()) {
            case 400:
                responseEntity = buildResponseEntity(
                        AppClientResponse.GENERAL_PARAM_ERROR,
                        HttpStatus.BAD_REQUEST);
                break;
            case 404:
                responseEntity = buildResponseEntity(
                        AppClientResponse.GENERAL_NOT_FOUND_ERROR,
                        HttpStatus.NOT_FOUND);
                break;
            default:
                responseEntity = buildResponseEntity(
                        AppClientResponse.GENERAL_ERROR,
                        HttpStatus.INTERNAL_SERVER_ERROR);
                break;
        }

        return responseEntity;
    }

    @ExceptionHandler(BusinessParamException.class)
    public ResponseEntity handle(BusinessParamException e) {
        log.error("business param exception occurs: ", e);

        return buildResponseEntity(
                e.getErrorDto(),
                AppClientResponse.GENERAL_PARAM_ERROR,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessResourceNotFoundException.class)
    public ResponseEntity handle(BusinessResourceNotFoundException e) {
        log.error("business resource not found exception occurs: ", e);

        return buildResponseEntity(
                e.getErrorDto(),
                AppClientResponse.GENERAL_NOT_FOUND_ERROR,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handle(BusinessException e) {
        log.error("business exception occurs: ", e);

        return buildResponseEntity(
                e.getErrorDto(),
                AppClientResponse.GENERAL_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity exceptionHandle(Exception e) {
//        log.error("exception occurs: ", e);
//
//        return buildResponseEntity(
//                new ApiResponseDto(AppClientResponse.GENERAL_ERROR),
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    private ResponseEntity<Object> buildResponseEntity(
            IMessageDto messageDto,
            HttpStatus httpStatus) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(messageDto, httpHeaders, httpStatus);
    }

    private ResponseEntity<Object> buildResponseEntity(
            AppClientResponse message,
            HttpStatus httpStatus) {
        return this.buildResponseEntity(null, message, httpStatus);
    }

    private ResponseEntity<Object> buildResponseEntity(
            IMessageDto messageDto,
            AppClientResponse message,
            HttpStatus httpStatus) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        if (null == messageDto) {
            return buildResponseEntity(new ApiResponseDto(message), httpStatus);
        }

        return new ResponseEntity<>(messageDto, httpHeaders, httpStatus);
    }
}
