package com.xzh.customer.technical.juc.async.asyncDiResponseEntity;

import com.xzh.customer.common.ApiResponseDto;
import com.xzh.customer.common.AppClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-17 11:01
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/asyncAndDi")
public class AsyncAndDiController {
    @Autowired
    private AsyncAndDiServiceA asyncAndDiServiceA;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        String test = asyncAndDiServiceA.test();
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @GetMapping("/test2")
    public ResponseEntity<ApiResponseDto> test2() {
        String test = asyncAndDiServiceA.test();
        ApiResponseDto apiResponseDto = ApiResponseDto.success(test);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/test3", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ApiResponseDto> test3() {
        ApiResponseDto apiResponseDto = new ApiResponseDto(AppClientResponse.GENERAL_SUCC);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
