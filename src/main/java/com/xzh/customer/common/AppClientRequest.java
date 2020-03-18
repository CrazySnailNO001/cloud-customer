package com.xzh.customer.common;

import lombok.Getter;

/**
 * @author Yuanqin DENG on 2018/8/7
 */

@Getter
public enum AppClientRequest {

    APP_CLIENT_OMS(0,"oms", ""),
    APP_CLIENT_PICKING(0,"picking", ""),
    APP_CLIENT_SHIPPING(0,"shipping", ""),
    APP_CLIENT_FRONTEND(0,"frontend", ""),

    OMS_DEV_ID(0,"dev_id", ""),
    OMS_DEV_KEY(0,"dev_key", ""),
    OMS_BASE_URL(0,"base_url", ""),
    OMS_TOKEN(0,"token", ""),
    OMS_SHOP_ID_HUAMU(0,"shop_id_huamu", ""),
    OMS_ORDER_SOURCE(0,"order_source", ""),
    OMS_VERSION(0,"version", ""),

    OMS_CREATE_STATUS_SUCC(0,"succ", ""),
    OMS_CREATE_STATUS_ERROR(0,"error", ""),
    OMS_CREATE_STATUS_PICK_COMPLETED(0,"pick_completed", ""),
    OMS_CREATE_MSG_SUCC(0,"created successfully", ""),
    OMS_CREATE_SERVICE_TYPE_CREATE(0,"create_order", ""),
    OMS_CALLBACK_STATUS(0,"order_status", ""),
    OMS_CREATE_REQ_FLAG(0,"shiphub_callback", ""),
    OMS_CREATE_REQ_METHOD_CALLBACK(0,"shiphub.callback", ""),
    OMS_CREATE_REQ_TYPE_JSON(0,"json", ""),
    OMS_CREATE_REQ_CHARSET_UTF8(0,"utf-8", ""),
    OMS_CREATE_REQ_CALLBACK_URL(0,"create_order_callback_url", ""),
    OMS_CALLBACK_STATUS_SHIPHUB_CANCEL(0,"shiphub_cancel", ""),

    OMS_SHIP_GROUP_ID(0,"shop_group_id", ""),
    OMS_EXPECT_DELIVERY_TIME(0,"expect_delivery_time", ""),

    OMS_CREATE_CALLBACK_DELIVERY_BN(0,"delivery_bn", ""),
    OMS_CREATE_CALLBACK_DATA_TYPE(0,"data_type", ""),
    OMS_CREATE_CALLBACK_DATA(0,"data", ""),
    OMS_CREATE_CALLBACK_FLAG(0,"flag", ""),
    OMS_CREATE_CALLBACK_METHOD(0,"method", ""),
    OMS_CREATE_CALLBACK_TYPE(0,"type", ""),
    OMS_CREATE_CALLBACK_CHARSET(0,"charset", ""),
    OMS_CREATE_CALLBACK_TIMESTAMP(0,"timestamp", ""),
    OMS_CREATE_CALLBACK_TOKEN(0,"token", ""),
    OMS_CREATE_CALLBACK_SIGN(0,"sign", ""),


    SFS_LABEL_ORDER_SEQUENCE(0,"order_sequence", ""),
    SFS_LABEL_ORDER_SEQUENCE_VALUE(0,"迪卡侬单号", ""),
    SFS_LABEL_USER_NAME(0,"user_name", ""),
    SFS_LABEL_USER_ADDR(0,"user_addr", ""),
    SFS_LABEL_USER_MOBILE(0,"user_mobile", ""),
    SFS_LABEL_INVENTORY_NAME(0,"inventory_name", ""),
    SFS_LABEL_INVENTORY_ADDR(0,"inventory_addr", ""),
    SFS_LABEL_INVENTORY_MOBILE(0,"inventory_mobile", ""),
    SFS_LABEL_SERIAL(0,"label_serial", ""),

    SF_ROUTE_PUSH(0,"RoutePushService", ""),

    ACTION_DOCHECK(0,"doCheck", ""),
    ACTION_DOCREATE(0,"doCreate", ""),
    ACTION_DOSHIP(0,"doShip", ""),
    ACTION_DOUPDATE(0,"doUpdate", ""),
    ACTION_DORESERVE(0,"doReserve", ""),
    ACTION_DOREDUCE(0,"doReduce", ""),
    ACTION_DOCANCEL(0,"doCancel", ""),
    ACTION_DOVALIDATION(0,"doValidate", ""),
    ACTION_DOREJECT(0,"doReject", ""),


    BACKORDER_ORDER_STATUS_URL(0,"backorder_order_status_url", ""),
    BACKORDER_DO_CREATE_URL(0,"backorder_do_create_url", ""),

    STORE_API_REQUEST_ORDER_STATUS_SHIPPED(0,"shipped", ""),

    DELIVERY_TYPE_HOME_DELIVERY(1,"Home delivery", ""),
    DELIVERY_TYPE_HOME_DELIVERY_2H(2,"Home delivery 2h", ""),
    DELIVERY_TYPE_CLICK_AND_COLLECT(3,"C&C", ""),
    DELIVERY_TYPE_CLICK_AND_COLLET_2H(4,"C&C 2h", ""),
    DELIVERY_TYPE_TMALL_DSS(5,"Appointment delivery", "");

    private Integer id;
    private String value;
    private String country;

    AppClientRequest(Integer id, String value, String country){
        this.id = id;
        this.value = value;
        this.country = country;
    }

    public static String getRequestValue(String id) {
        String value = null;
        for (AppClientRequest request: AppClientRequest.values()) {
            if (request.getId() == Integer.valueOf(id)) {
                value = request.getValue();
            }
        }
        return value;
    }
}
