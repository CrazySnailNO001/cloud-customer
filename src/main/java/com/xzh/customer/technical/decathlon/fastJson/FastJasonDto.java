package com.xzh.customer.technical.decathlon.fastJson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.xzh.customer.helper.LowerCaseClassNameResolver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：xzh
 * @date ：Created in 2020-01-20 17:06
 * @description：
 * @modified By：
 * @version:
 */

@NoArgsConstructor
@AllArgsConstructor
@ApiModel
@Data
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY) //任何级别的字段都可以自动识别
@JsonInclude(JsonInclude.Include.NON_NULL)//解决实体类与json互转的时候 属性值为null的不参与序列化
@JsonIgnoreProperties(ignoreUnknown = true) //过滤掉未知的属性
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)//json序列化策略 驼峰
public class FastJasonDto implements Serializable {

    private static final long serialVersionUID = -3684100780450249163L;

    //    @JsonProperty("user_name") 解析后格式为：{"user_name":"张三"}
    @ApiModelProperty("用户姓名")
    private String userName;

    @ApiModelProperty("用户年龄")
    private String userAge;

    /**
     * 格式化日期属性
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("用户生日")
    private Date birthday;

    /**
     * 不JSON 序列化此属性
     */
    @JsonIgnore
    @ApiModelProperty("别名")
    private String orgName;
}
