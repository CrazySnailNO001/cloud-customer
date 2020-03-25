package com.xzh.customer.technical.decathlon.pdf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: shiphub_micro
 * @Description: com.decathlon.shiphub.order.dto
 * @Author: Aaron Cui
 * @Date: 2019/6/3 9:31 AM
 * @Version: 1.0
 * @Copyright: www.decathlon.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class ShoppingListDto implements Serializable {
  @JsonIgnore
  private static final long serialVersionUID = 6367979330820207992L;

  private String deliveryFee;
  private String shippingName;
  private String shippingCountry;
  private String shippingMobile;
  private String totalPrice;
  private String payMethod;
  private String totalDiscount;
  private String otherDiscount;
  private String orderPlatform;
  private List<SkuDataFormDto> skuDataFormDtoList;
  private String orderProductNum;
  private String orderTotalPrice;
  private String b2cOrderId;
  private String shipGroupId;
  private String inventoryLocationId;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
  public static class SkuDataFormDto {
    private String skuName;
    private String skuDiscout;
    private String skuSalePrice;
    private String skuNum;
    private String skuId;
    private String skuPrice;
  }
}