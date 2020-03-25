package com.xzh.customer.technical.log.loggingAspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.google.common.base.Stopwatch.createStarted;
//import static net.logstash.logback.argument.StructuredArguments.keyValue;

/**
 * @Author: Zhonghui XU
 * @Date: 2019-03-23
 */

/**
 * Utility logging aspect based on Spring AOP, used to log actions by watching class or method with @{@link PerformanceLog}
 * annotation.
 *
 * Example: Given a method `public void doSomething(UUID id, GeographicEntity geographicEntity) {...} `
 *
 * On call with `doSomething(UUID.randomUUID(), GeographicEntity.CN);`
 *
 * This aspect will log `doSomething|UUID:<UUID>,GeographicEntity:CN|<elapsedTimeInMillis>`
 */
@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Pointcut("@within(com.xzh.customer.technical.log.loggingAspect.PerformanceLog) || @annotation(com.xzh.customer.technical.log.loggingAspect.PerformanceLog)")
    public void performanceLog(){

    }

    @Around("performanceLog()")
    public Object aroundPerformance(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Class declaringType = proceedingJoinPoint.getSignature().getDeclaringType();
        Stopwatch stopwatch = createStarted();
        Object result = proceedingJoinPoint.proceed();
//        log.info("Performance Result [{}] [{}] [{}]",
//                keyValue("ClassMethodName", declaringType.getSimpleName() + "." + getMethod(proceedingJoinPoint).getName()),
//                keyValue("ParametersOfMethod", formatParameters(proceedingJoinPoint, getMethod(proceedingJoinPoint))),
//                keyValue("ExecutionTime", stopwatch.elapsed(TimeUnit.MILLISECONDS)));
        return result;
    }

    @Before("performanceLog()")
    public void doBefore(JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();
//        log.info("Log Aspect doBefore Result [{}]",
//                keyValue("ClassMethodName", signature.getDeclaringTypeName() + "." + joinPoint.getSignature().getName()));
        if (signature.getDeclaringTypeName().contains("Controller")) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest req = (null != attributes ? attributes.getRequest() : null);
//            log.info("Log Aspect doBefore Result [{}] [{}]", keyValue("RequestURL", Objects.requireNonNull(req).getRequestURI()), keyValue("RequestParameter", Arrays.toString(joinPoint.getArgs())));
        }
    }

    @AfterReturning(returning = "ret", pointcut = "performanceLog()")
    public void doAfterReturning(Object ret) {

        ObjectMapper objectMapper = new ObjectMapper();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = (null != attributes ? attributes.getRequest() : null);
        try {
            log.info("Log Aspect doAfter Result: The response of API {}/{} is [{}]", Objects.requireNonNull(req).getMethod(), req.getRequestURL().toString(), objectMapper.writeValueAsString(ret));
        } catch (JsonProcessingException e) {
            log.error("Log Aspect exception Result: [{}]", e.getMessage());
        }
    }

    /**
     * Return the method annotated with @{@link PerformanceLog}.
     */
    private Method getMethod(ProceedingJoinPoint proceedingJoinPoint) {
        return ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
    }

    /**
     * Format parameters of method into a string. If method has no argument, return an empty optional
     *
     * Example: UUID:<UUID>,GeographicEntity:CN
     */
    public Optional<String> formatParameters(ProceedingJoinPoint proceedingJoinPoint, Method method) {
        return Optional.of(proceedingJoinPoint.getArgs())
                .map(args -> IntStream.range(0, args.length)
                        .mapToObj(argumentIndex -> formatParameter(method, proceedingJoinPoint, argumentIndex))
                        .collect(Collectors.joining(","))
                )
                .filter(string -> !string.isEmpty());
    }

    /**
     * Format a method name and his value
     *
     * Example: UUID:<UUID>
     */
    private String formatParameter(Method method, ProceedingJoinPoint proceedingJoinPoint, int argumentIndex) {
        String name = method.getParameters()[argumentIndex].getType().getSimpleName();

        if (null == proceedingJoinPoint.getArgs()[argumentIndex]) {
            return name + ": null";
        } else {
            String value = proceedingJoinPoint.getArgs()[argumentIndex].toString();
            return name + ":" + value;
        }
    }
}