package com.xzh.customer.technical.log.aopLog;

import com.xzh.customer.utils.SafeUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author XZHH
 * @Description: 日志记录，数据库添加、删除、修改方法，服务调用AOP
 * @create 2019/4/9 0009 16:58
 * @modify By:
 **/
@Aspect
@Configuration
@Component
public class LogAspect {
    //private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private Logger logger;

    /*@Autowired
    private TerminalStatisticsRepository terminalStatisticsRepository;
    @Autowired
    private ProductDao productDao;*/
    public LogAspect() {
        logger = LoggerFactory.getLogger(getClass());
        logger.debug("LOGASPECT");
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    public static int getDevice(String device) {
        int terminal = 0;
        if ("Android".equalsIgnoreCase(device)) {
            terminal = 3;
        } else if ("IOS".equalsIgnoreCase(device)) {
            terminal = 4;
        } else {
            terminal = 0;
        }
        return terminal;
    }

    /**
     * 业务逻辑方法切入点
     */
    //@Pointcut("execution(* com.xzh.customer.service.impl..*(..))")
    //public void serviceImplPointcut() {}
    @Pointcut("execution(* com.xzh.customer.technical.log.*.*(..))")
    public void daoImplPointcut() {
    }

    /**
     * 调用新浪业务逻辑方法切入点
     */
    @Pointcut("execution(* com.xzh.customer.*.services.impl.*ServiceImpl.*(..))")
    public void sinaHelperImplPointcut() {
    }

    /**
     *
     */
    @Pointcut("execution(* com.xzh.customer.*.*controllers.*Controller.*(..))")
    public void controllerPointcut() {
    }

    @Around(value = "controllerPointcut()")
    public Object controllerWatch(ProceedingJoinPoint pjp) throws Throwable {
        logger.debug("controller 切面执行了");
//        String name = pjp.getSignature().getName();
//        String className = pjp.getTarget().getClass().getName();
        StopWatch sw = new StopWatch();
        try {
            sw.start();
            return pjp.proceed();
        } finally {
            sw.stop();
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();
            String device = SafeUtils.getString(request.getHeader("Device"), "Web");
            String desc = getControllerMethodDescription(pjp);
            Object userId = request.getSession().getAttribute("userId");
            logger.info("LogAspect Device: {}, api: {}, app: {}, manufacturer: {}, system: {}",
                    device,
                    request.getHeader("apiVersion"),
                    request.getHeader("appVersion"),
                    request.getHeader("manufacturer"),
                    request.getHeader("systemVersion"));
            /**
             logger.debug("t_terminal_statistics Terminal:{}, Service : {} ,Time :{},CustomerID :{},Desc : {}, spend ; {}",
             device,
             className+"."+name,
             SafeUtils.getCurrentUnixTime(),
             userId,
             desc,
             sw.getTotalTimeMillis());
             **/
            //验证登陆信息session是否正确
            if (userId == null) {
                logger.info("session not found or expired : " + request.getRequestedSessionId());
            } else {
                logger.debug("LogAspect userId: {}", (Long) userId);
                int serviceType = 100;
                logger.debug("LogAspect desc : " + desc);
                if (!StringUtils.isEmpty(desc)) {
                    if ("productOrder".equals(desc)) {
                        Object[] method_param = null;
                        Object object;
                        try {
                            method_param = pjp.getArgs(); //获取方法参数
//                            object = pjp.proceed();
                        } catch (Exception e) {
                            // 异常处理记录日志..log.error(e);
                            throw e;
                        }
                        /*ProductOrderRequest productOrderRequest = (ProductOrderRequest)method_param[0];
                        String type = productOrderRequest.getType();
                        logger.debug("productOrder -- type: "+type);
                        logger.debug("productOrder -- productId : "+productOrderRequest.getProductId());
                        Long productId = null;
                        if("current".equalsIgnoreCase(type)){
                            productId = SafeUtils.getLong(SystemProperties.get("current_product_id"));
                        }else{
                            productId = SafeUtils.getLong(productOrderRequest.getProductId(), -1L);
                        }
                        if (productId == -1){//加新产品
                            productId = SafeUtils.getLong(DES.decrypt(new String(Base64Coder.decode(productOrderRequest.getProductId())), Long.toString(10000000L + (Long) userId)), -1L);
                        }*/


                        //0:实际产品;1:虚拟产品;2:个人定制;3:加薪计划;4:VIP专属;5:VVIP专属;6:餐桌计划;7:活期;
                        //3:普通标采购;4:企业标采购;5:订制个人标采购;6:VIP标采购;
//                        Product product = productDao.getProductById(productId);
                        /*int targetType = productDao.getProductTargetTypeById(productId);
                        if(2==targetType){
                            serviceType = 5;
                        }else if(4==targetType||5==targetType){
                            serviceType = 6;
                        }else{
                            serviceType = 3;
                        }*/
                    }/*else{
                        serviceType = StatisticsEnum.Service.valueOf(desc).getCode();
                    }*/
                }
                logger.debug("serviceType  : " + serviceType);
                /*if(100!=serviceType && null !=userId){
                    TerminalStatistics terminalStatistics = new TerminalStatistics();
                    terminalStatistics.setCustomerID(SafeUtils.getLong(userId));
                    terminalStatistics.setService(serviceType);
                    terminalStatistics.setTerminal(getDevice(device));
                    terminalStatistics.setTime(SafeUtils.getCurrentUnixTime());
                    this.terminalStatisticsRepository.save(terminalStatistics);
                }*/
            }
        }

    }

    @Around(value = "daoImplPointcut()")
    public Object daoImplWatch(ProceedingJoinPoint pjp) throws Throwable {
        logger.debug("dao 切面执行了");
        StopWatch sw = new StopWatch();
        String name = pjp.getSignature().getName();
        try {
            sw.start();
            return pjp.proceed();
        } finally {
            sw.stop();
            logger.debug("LogAspect STOPWATCH: " + sw.getTotalTimeMillis() + " - " + name);
        }
    }

    @Around(value = "sinaHelperImplPointcut()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        //logger.debug("切面执行了");
        StopWatch sw = new StopWatch();
        String name = pjp.getSignature().getName();
        try {
            sw.start();
            return pjp.proceed();
        } finally {
            sw.stop();
            logger.debug("LogAspect STOPWATCH: " + sw.getTotalTimeMillis() + " - " + name);
        }
    }

    @AfterReturning(value = "sinaHelperImplPointcut()", argNames = "joinPoint,rtv", returning = "rtv")
    public void insertServiceCallCalls(JoinPoint joinPoint, Object rtv) throws Throwable {
        //判断参数
        if (joinPoint.getArgs() == null) {//没有参数
            return;
        }
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取操作内容
        String opContent = getOptionContent(joinPoint.getArgs(), methodName);
        logger.debug("LogAspect MethodName:  " + methodName + "  opContent:  " + opContent);
    }

    /**
     * 使用Java反射来获取被拦截方法的参数值，
     * 将参数值拼接为操作内容
     */
    public String getOptionContent(Object[] args, String mName) throws Exception {
        if (args == null) {
            return null;
        }
        try {
            StringBuffer rs = new StringBuffer();
            rs.append(mName);
            String className = null;
            int index = 1;
            // 遍历参数对象
            for (Object info : args) {
                //获取对象类型
                className = info.getClass().getName();
                className = className.substring(className.lastIndexOf(".") + 1);
                rs.append("[参数" + index + "，类型：" + className + "，值：");
                // 获取对象的所有方法
                Method[] methods = info.getClass().getDeclaredMethods();
                // 遍历方法，判断get方法
                for (Method method : methods) {
                    String methodName = method.getName();
                    // 判断是不是get方法
                    if (methodName.indexOf("get") == -1) {// 不是get方法
                        continue;// 不处理
                    }
                    Object rsValue = null;
                    try {
                        // 调用get方法，获取返回值
                        rsValue = method.invoke(info);
                        if (rsValue == null) {//没有返回值
                            continue;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                    //将值加入内容中
                    rs.append("(" + methodName + " : " + rsValue + ")");
                }
                rs.append("]");
                index++;
            }
            return rs.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
