package com.xzh.customer.design.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xzh.customer.design.exception.BusinessException;
import com.xzh.customer.design.exception.VerifyException;

/**
 * @param <P> 入参
 * @param <R> 出参
 * @param <T> 入参BO
 * @param <U> 出参BO
 * @Author ：xuzhonghui
 * @Date ： 2024/07/11
 * @Description ：基础Handler，定义请求处理流程，和默认的异常处理方式。
 */

public abstract class BaseHandler<P, R, T, U> {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public R process(P request) {
        R response = null;
        try {
            if (verify(request)) {
                return null;
            }
            T requestBo = convertRequest(request);
            preprocessingRequest(requestBo);
            U responseBo = doProcess(requestBo);
            response = convertResponse(responseBo);
            decorateResponse(response);
            return response;
        } catch (VerifyException e) {
            logger.error("参数校验错误", e);
            return buildErrorResponse(e);
        } catch (BusinessException e) {
            logger.error("业务异常", e);
            return buildErrorResponse(e);
        } catch (Exception e) {
            logger.error("未知错误", e);
            return buildErrorResponse(e);
        } finally {
            logger.info("耗时  {}", 12);
            logger.info("请求参数  {} , 响应参数  {}", request, response);
        }
    }

    /**
     * 对 {@link P} 类型的请求进行验证。
     *
     * @param request 待验证的请求对象
     * @return 如果验证通过则返回 true，否则抛出 {@link VerifyException} 异常
     * @throws VerifyException 验证失败时抛出的异常
     */
    protected abstract boolean verify(P request) throws VerifyException;

    /**
     * 将 {@link P} 类型的请求转换为 {@link T} 类型的业务请求对象。
     *
     * @param request 待转换的请求对象
     * @return 转换后的业务请求对象
     */
    protected abstract T convertRequest(P request);

    /**
     * 前置处理 {@link T} 类型的业务请求对象。
     *
     * @param requestBo 待处理的业务请求对象
     */
    protected abstract void preprocessingRequest(T requestBo);

    /**
     * 对 {@link T} 类型的业务请求对象进行处理，并返回 {@link U} 类型的业务响应对象。
     *
     * @param requestBo 待处理的业务请求对象
     * @return 处理后的业务响应对象
     */
    protected abstract U doProcess(T requestBo) throws BusinessException;

    /**
     * 将 {@link U} 类型的业务响应对象转换为 {@link R} 类型的响应对象。
     *
     * @param responseBo 待转换的业务响应对象
     * @return 转换后的响应对象
     */
    protected abstract R convertResponse(U responseBo);

    /**
     * 对 {@link R} 类型的响应对象进行装饰或额外处理。
     *
     * @param response 待装饰的响应对象
     */
    protected abstract void decorateResponse(R response);

    /**
     * 构建并返回 {@link R} 类型的响应对象。
     *
     * @return 构建的响应对象
     */
    protected abstract R buildErrorResponse(Exception e);


}
