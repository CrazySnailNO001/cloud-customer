package com.xzh.customer.common;

import lombok.Getter;

/**
 * @author Yuanqin DENG on 2018/8/8
 */

@Getter
public enum AppClientResponse {

  //Create success
  CREATED_SUCC("200000", "succ", "Order created successfully in shiphub"),
  //Cancel success
  CANCELLED_SUCC("200001", "succ", "Order is cancelled"),
  //Check success
  CHECK_SUCCESS("200003", "succ", "Request check successfully"),
  //Update success
  UPDATE_STATUS_SUCCESS("200004", "succ", "Order [%s] update successfully with new status  [%s]"),

  //do Ship success
  DO_SHIP_SUCCESS("200005", "succ", "Order is ready to send to carrier"),

  //reduce stock success
  STOCK_REDUCE_SUCC("200006","succ","Stock for the shipGroupId reduced successfully"),
  //cancel reserve stock success
  STOCK_CANCEL_RESERVE_SUCC("200007", "succ", "Stock cancel reservce successfully"),

  CHECK_AVAILABILITY_SUCC("200008","succ","Delivery method check successfully"),

  //SF qiao printer success
  SFQIAO_PRINT_SHOPPING_LIST_SUCC("80001", "succ", "Print order shopping list successfully"),
  SFQIAO_PRINT_LABEL_SUCC("80002", "succ", "SFexpress Print label successfully"),

  STOCK_REDUCE_ERROR("1501","exception","Can't reduce Stock for the shipGroupId "),
  STOCK_RESERVE_ERROR("1502","exception","Can't reserve Stock for the shipGroupId "),
  STOCK_CANCEL_RESERVE_ERROR("1503", "exception", "Can't cancel reserve Stock for the shipGroupId"),
  //Create exception
  MISSING_PARAMETERS("1101", "exception", "Request miss parameter, please check"),
  ACTION_DENIED("1102", "exception", "Action didn't exist"),
  CARRIER_DENIED("1103", "exception", "Carrier name didn't exist in shiphub"),
  CLIENT_DENIED("1104", "exception", "App client name is not allowed to call this api"),
  VALUE_WRONG("1105", "exception", "\"inventory_location_postcode\" value is wrong"),
  DELIVERY_TIME_DENIED("1106", "exception", "Delivery time exceed appointed duration"),
  SIGN_WRONG("1107", "exception", "Sign is not validated"),
  REPEATE_ORDER_IN_CARRIER("1108", "exception", "Order already sent to carrier"),
  REPEATE_ORDER_IN_SHIPHUB("1109", "exception", "Order already sent to shiphub, waiting for sending to carrier"),
  ORDER_IS_CANCELLED("1110", "exception", "Order is already cancelled"),
  DATE_FORMAT_WRONG("1111", "exception", "Date format is wrong. "),
  PICK_COMPLETED_ERROR("1112", "exception", "Order can't inform to app client with status pick_completed"),
  DO_SHIP_ERROR("1113", "exception", "Shipping module has error to trigger this action"),
  INVENTORY_NOT_FOUND("1114", "exception", "Inventory name didn't exist in shiphub"),
  GENERAL_CREATE_ERROR("1115", "exception", "Shiphub can't create order, check with Shiphub"),
  COUNTRY_DENIED("1116", "exception", "Country name is wrong"),
  OPERATOR_ID_DENIED("1117", "exception", "Operation id is not validated"),
  ORDER_SOURCE_NOT_VALIDATED("1118", "exception", "Order source is not validated"),
  ORDER_NOT_FOUND("1119", "exception", "Ship group id doesn't exist"),
  ORDER_NOT_VALIDATED_PICK_COMPLETED("1120", "exception", "Order can't be validated to pick_completed"),
  ORDER_NOT_VALIDATED_PICK_REJECTED("1121", "exception", "Order can't be changed to pick_rejected"),
  UPDATE_ERROR("1122", "exception", "Can't update order"),
  REQUEST_BODY_NOT_VALIDATED("1123", "exception", "Request body is not validated"),
  REPEAT_REDUCE("1124", "exception", "Shipping can only call Picking once to reduce stock for the same ShipGroupID"),
  CARRIER_NOT_AVAILABLE("1125", "exception", "Carrier is not validated"),
  ORDER_TYPE_NOT_VALIDATED("1126", "exception", "Order type is not validated"),
  //Cancel order exception
  ORDER_CANCEL_ERROR("1127", "exception", "Shiphub can't cancel order"),

  //admin order management exception
  ORDER_RESET_STATUS_ERROR("1128","exception","Order status not 10002 and 10003 , cannot be reset"),
  NO_MATCH_ORDER("1129","exception","No matching order"),
  WITHOUT_PERMISSION("1130","exception","No permission operation"),

  //shipping manualSignFor exception
  ORDER_SIGN_FOR_STATUS_ERROR("1131","exception","Order status not 10009 , 10010 and 10011, cannot manual sign for"),

  // Store frontend msg
  STORE_FRONTEND_ORDER_COUNT_QUERY_ERROR("1501", "exception", "Order count query failed."),
  STORE_FRONTEND_ORDER_LIST_QUERY_ERROR("1502", "exception", "Order list query failed."),
  STORE_FRONTEND_ORDER_DETAIL_QUERY_ERROR("1503", "exception", "Order detail query failed."),

  // Amap API msg
  AMAP_GEO_REQUEST_ERROR("1601", "exception", "Amap Geo request failed."),

  //Printer msg
  SFQIAO_PRINTER_ERROR("40006", "exception", "Order can't print"),

  // Stock API msg
  STCOM_STORE_ITEM_STOCKS_REQUEST_PARAM_ERROR("1651", "exception", "Stcom stock query request param invalid."),
  STCOM_STORE_ITEM_STOCKS_REQUEST_ERROR("1652", "exception", "Stcom stock query request failed."),

  // Stockhub msg
  STOCK_LACK_ERROR("3101","exception","Stock is not enough"),

  //stock transaction
  STOCK_TRANSACTION_ERROR_NOT_ENOUGH("3201", "exception", "not enough stock for the operation"),
  STOCK_TRANSACTION_ERROR_INVALID_BUSINESS_TYPE("3202", "exception", "business type is invalided"),
  STOCK_TRANSACTION_ERROR_ALREADY_EXIST("3203", "exception", "business_bn already exist"),
  STOCK_TRANSACTION_ERROR_NOT_EXIST("3204", "exception", "business_bn not exist"),

  //timeout
  TIMEOUT_ERROR("5001", "exception", "timeout"),

  // Spid API msg
  SPID_MODELS_MEDIAS_FIRSTPACKSHOTWEB_REQUEST_PARAM_ERROR("1681", "exception", "Spid pixlId query request param invalid."),
  SPID_MODELS_MEDIAS_FIRSTPACKSHOTWEB_REQUEST_ERROR("1682", "exception", "Spid pixlId query request failed."),

  // Token msg
  FEDERATION_ACCESS_TOKEN_REQUEST_PARAM_ERROR("2001", "exception", "Federation access token request param error."),
  FEDERATION_ACCESS_TOKEN_REQUEST_ERROR("2002", "exception", "Federation access token request failed."),
  FEDERATION_USER_INFO_REQUEST_ERROR("2002", "exception", "Federation user info request failed."),

  OMS_RESP_SUCC("1", "", "succ"),
  OMS_RESP_ERROR("2", "", "error"),

  GENERAL_ERROR("1401", "error", "System error, please contact IT."),
  GENERAL_PARAM_ERROR("1405", "error", "Request param check invalid."),
  GENERAL_NOT_FOUND_ERROR("1406", "error", "Resource not found."),
  GENERAL_REQUEST_PAGE_SIZE_ERROR("1407", "error", "Request page size is too large."),
  GENERAL_SUCC("210000", "succ", "");


  private String status;
  private String statusName;
  private String statusDesc;

  AppClientResponse(String status, String statusName, String statusDesc) {
    this.status = status;
    this.statusName = statusName;
    this.statusDesc = statusDesc;
  }

  public static String getResponseStatusName(String status) {
    String statusName = null;
    for (AppClientResponse response: AppClientResponse.values()) {
      if (response.getStatus().equals(status)) {
        statusName = response.getStatusName();
      }
    }
    return statusName;
  }

  public static String getResponseStatusDesc(String status) {
    String statusDesc = null;
    for (AppClientResponse response: AppClientResponse.values()) {
      if (status.equals(response.getStatus())) {
        statusDesc = response.getStatusDesc();
      }
    }
    return statusDesc;
  }

  public static Integer getResponseStatus(String statusName) {
    Integer status = null;
    for (AppClientResponse response: AppClientResponse.values()) {
      if (statusName.equals(response.getStatusName())) {
        status = Integer.valueOf(response.getStatus());
      }
    }
    return status;
  }
}
