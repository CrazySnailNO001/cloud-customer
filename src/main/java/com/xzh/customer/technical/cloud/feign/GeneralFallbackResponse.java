package com.xzh.customer.technical.cloud.feign;

/**
 * @author ：xzh
 * @date ：Created in 2020-07-21 17:33
 * @description：
 * @modified By：
 * @version:
 */
class GeneralFallbackResponse {
    public String generalSuccess(){
        System.out.println(this.getClass().getSimpleName() + " hello请求失败,进入FeignFallbackImpl.fallback");

        return "请求失败,进入fallback,success";
    }

    String generalFail(){
        System.out.println(this.getClass().getSimpleName() + " hello请求失败,进入FeignFallbackImpl.fallback");
        return "请求失败,进入fallback,fail";
    }
}
