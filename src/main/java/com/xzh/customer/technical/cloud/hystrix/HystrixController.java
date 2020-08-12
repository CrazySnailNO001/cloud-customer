package com.xzh.customer.technical.cloud.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xzh.customer.common.ApiResponseDto;
import com.xzh.customer.technical.cloud.feign.HystrixServiceFeign;
import com.xzh.customer.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.stream.IntStream;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-25 11:53
 * @description：配置优先级:动态配置实例属性>内置实例默认值>动态全局默认属性>内置全局默认值
 * @modified By： 默认情况下,hystrix按照组别建分配线程池,线程池键可以做到,请求依旧在组别键呢统计,但在同一个组别键下,安排不通的线程运行Hystrix命令
 * HystrixCommand注解属性:
 * commandKey:默认注解的方法名称
 * groupKey:默认注释方法的运行时类名
 * threadPoolKey:默认定义为groupKey
 * commandProperties:配置Hystrix配置参数
 * threadPoolProperties:配置舱壁隔离线程池参数
 * ignoreExceptions:定义需要忽略的异常,未被忽略的异常都会被包装成HystrixRuntimeException
 * raiseHystrixExceptions:定义需要抛出的异常,未被抛出的异常都会被包装成HystrixRuntimeException
 * @version:
 */
@RestController
@RequestMapping("/hystrix")
@Slf4j
public class HystrixController {
    @Resource
    private HystrixServiceFeign hystrixServiceFeign;
    @Resource
    private TestHystrixCommand hystrixCommand;
    @Resource
    private FeignHystrixService feignHystrixService;

    /**
     * Hystrix 线程池 测试API
     */
    @GetMapping(value = "/thread_pool_test", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
//    @HystrixCommand(commandKey = "commandKey01",threadPoolKey = "threadPoolKeyTest",groupKey = "groupKey001") //不好使?
    public String threadPoolTest(String option) {
        IntStream.range(0, 5).asLongStream().parallel().forEach(value -> new Thread(() -> {
            log.info("ThreadPoolTest API ready to send request to provider value [ {} ]", value);
            String result = hystrixServiceFeign.hello(option + value);
            log.info("ThreadPoolTest API get response from provider [ {} ]", result);
        }).start());

        return "success";
    }

    /**
     * Hystrix 超时时间、commandKey 测试API
     */
    @GetMapping(value = "/command_execution_test", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    @HystrixCommand(commandKey = "command_key_test"
            ,commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "800")} //在没有对command_key_test进行配置的时候生效,否则被覆盖
            )
    public String commandExecutionTest() {
        int randomTime = RandomUtils.getInstance().generateValue(500, 900);

        log.info("ThreadPoolTest API ready to send request to provider time: {}", randomTime);
        String result = hystrixServiceFeign.hystrixTimeOut(randomTime);
        log.info("ThreadPoolTest API get response from provider [ {} ]", result);

        return result;
    }

    /**
     * Hystrix command 测试API
     */
    @GetMapping(value = "/command_test", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public String commandTest(){
        return hystrixCommand.execute();
    }

    /**
     * Hystrix command 测试API
     */
    @GetMapping(value = "/feign_hystrix_test", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity<ApiResponseDto> feignHystrixTest(@RequestParam(required = false)String name){
        String result = feignHystrixService.hello02(name);
        log.info(result);
        return ResponseEntity.ok(ApiResponseDto.success(result));
    }

    /**
     * Feign Hystrix timeOut 测试API
     */
    @GetMapping(value = "/feign_hystrix_time_out_test", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity<ApiResponseDto> feignHystrixTimeOutTest(@RequestParam(required = false)String name){
        int randomTime = RandomUtils.getInstance().generateValue(500, 900);

        log.info("ThreadPoolTest API ready to send request to provider time: {}", randomTime);
        String result = feignHystrixService.hystrixTimeOut02(randomTime);
        log.info("ThreadPoolTest API get response from provider [ {} ]", result);
        return ResponseEntity.ok(ApiResponseDto.success(randomTime));
    }
}
