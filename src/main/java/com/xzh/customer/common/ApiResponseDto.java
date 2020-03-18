package com.xzh.customer.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.xzh.customer.common.helper.LocalDateTimeDeserializer;
import com.xzh.customer.common.helper.LocalDateTimeSerializer;
import com.xzh.customer.common.helper.LowerCaseClassNameResolver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Yuanqin DENG
 * @Date: 2018/11/22 11:56 AM
 */

@NoArgsConstructor
@AllArgsConstructor
@ApiModel
@Data
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseDto<T> implements IMessageDto, Serializable {

    private static final long serialVersionUID = 5207090021128839503L;

    @ApiModelProperty(value = "status")
    @JsonProperty("status")
    private String status;
    @ApiModelProperty(value = "status_name")
    @JsonProperty("status_name")
    private String statusName;
    @ApiModelProperty(value = "status_description")
    @JsonProperty("status_description")
    private String statusDesc;
    @ApiModelProperty(value = "detail_message")
    @JsonProperty("detail_message")
    private List<ApiSubErrorDto> subErrors;
    @ApiModelProperty(value = "call_time")
    @JsonProperty("call_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private ZonedDateTime timestamp = ZonedDateTime.now();
    @ApiModelProperty(value = "data")
    @JsonProperty("data")
    private T data;

    public ApiResponseDto(AppClientResponse message) {
        this.status = message.getStatus();
        this.statusName = message.getStatusName();
        this.statusDesc = message.getStatusDesc();
        this.timestamp = ZonedDateTime.now();
    }

    public ApiResponseDto(AppClientResponse message, T data) {
        this.status = message.getStatus();
        this.statusName = message.getStatusName();
        this.statusDesc = message.getStatusDesc();
        this.timestamp = ZonedDateTime.now();
        this.data = data;
    }

    public ApiResponseDto(String status, String statusName, String statusDesc, ZonedDateTime timestamp) {
        this.status = status;
        this.statusName = statusName;
        this.statusDesc = statusDesc;
        this.timestamp = timestamp;
    }

    public ApiResponseDto(String status, String statusName) {
        this.status = status;
        this.statusName = statusName;
    }

    public static ApiResponseDto success() {
        return new ApiResponseDto(AppClientResponse.GENERAL_SUCC);
    }

    public static <T> ApiResponseDto success(T data) {
        return new ApiResponseDto(AppClientResponse.GENERAL_SUCC, data);
    }

    public static ApiResponseDto success(String message) {
        return new ApiResponseDto(AppClientResponse.GENERAL_SUCC.getStatus(), message);
    }

    public static <T> ApiResponseDto fail(T data) {
        return new ApiResponseDto(AppClientResponse.GENERAL_ERROR, data);
    }

    public static ApiResponseDto fail(String message) {
        return new ApiResponseDto(AppClientResponse.GENERAL_ERROR.getStatus(), message);
    }

    public static ApiResponseDto fail(String status, String statusName) {
        return new ApiResponseDto(status, statusName);
    }

    private void addSubError(ApiSubErrorDto subError) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }
}