package com.xzh.customer.decathlon.fastJson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author ：xzh
 * @date ：Created in 2020-02-21 16:13
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/json")
@Slf4j
public class FastJsonController {

    @GetMapping(value = "/test001",produces = "application/json")
    public FastJasonDto test001() {
        FastJasonDto fastJasonDto = new FastJasonDto();
        fastJasonDto.setUserName("张三");
        fastJasonDto.setUserAge("12");
        fastJasonDto.setBirthday(new Date());
        fastJasonDto.setOrgName("没我");

        return fastJasonDto;
    }

    @PostMapping(value = "/test002", produces = {"application/json"}, consumes = {"application/json"})
    public FastJasonDto test002(@RequestBody FastJasonDto fastJasonDto) {
        fastJasonDto.setBirthday(new Date());

        return fastJasonDto;
    }
}
