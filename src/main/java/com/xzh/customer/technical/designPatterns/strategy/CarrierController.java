package com.xzh.customer.technical.designPatterns.strategy;

import com.xzh.customer.technical.designPatterns.strategy.constants.CarrierEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：xzh
 * @date ：Created in 2019-12-17 10:40
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/strategy")
public class CarrierController {
    @Autowired
    private CarrierServiceContext<ICarrierService> carrierServiceContext;

    @GetMapping("test001")
    public String test001() {
        ICarrierService carrierSevice = carrierServiceContext.getCarrierSevice(CarrierEnum.SAMESTORE.getName());
        return carrierSevice.createOrder();
    }

    @GetMapping("test002")
    public String test002() {
        ICarrierService carrierSevice = carrierServiceContext.getCarrierSevice(CarrierEnum.SAMESTORE.getName());
        return carrierSevice.deliveryQuery();
    }

    @GetMapping("test003")
    public String test003() {
        ICarrierService carrierSevice = carrierServiceContext.getCarrierSevice(CarrierEnum.SF_QIAO.getName());
        return carrierSevice.deliveryQuery();
    }
}
