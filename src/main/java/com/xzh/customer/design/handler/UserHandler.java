package com.xzh.customer.design.handler;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.xzh.customer.design.UserRequest;
import com.xzh.customer.design.UserRequestBo;
import com.xzh.customer.design.UserResponse;
import com.xzh.customer.design.UserResponseBo;
import com.xzh.customer.design.exception.BusinessException;
import com.xzh.customer.design.exception.VerifyException;

/**
 * @Author ：xuzhonghui
 * @Date ： 2024/07/11
 * @Description ：
 */
@Component
public class UserHandler extends BaseHandler<UserRequest, UserResponse, UserRequestBo, UserResponseBo> {

    @Override
    protected boolean verify(UserRequest request) throws VerifyException {
        CollectionUtils.isNotEmpty();
        return false;
    }

    @Override
    protected UserRequestBo convertRequest(UserRequest request) {
        return null;
    }

    @Override
    protected void preprocessingRequest(UserRequestBo requestBo) {
        // TODO document why this method is empty
    }

    @Override
    protected UserResponseBo doProcess(UserRequestBo requestBo) throws BusinessException {
        return null;
    }

    @Override
    protected UserResponse convertResponse(UserResponseBo responseBo) {
        return null;
    }

    @Override
    protected void decorateResponse(UserResponse response) {
        // TODO document why this method is empty
    }

    @Override
    protected UserResponse buildErrorResponse(Exception e) {
        return null;
    }

//    public static void main(String[] args) {
//        try {
//            Integer a;
//            assert a != null;
//            System.out.println(a.getClass().getName());
//        } catch (AssertionError e) {
//            System.out.println("323234233223322323");
//            throw new RuntimeException(e);
//        }
//    }
}
