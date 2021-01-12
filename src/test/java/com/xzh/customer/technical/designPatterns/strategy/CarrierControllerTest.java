package com.xzh.customer.technical.designPatterns.strategy;

import com.xzh.customer.technical.designPatterns.strategy.constants.CarrierEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CarrierControllerTest {

    @Autowired
    private CarrierServiceContext<ICarrierService> carrierServiceContext;

    @Test
    public void carrierTest(){
        ICarrierService carrierSevice = carrierServiceContext.getCarrierSevice(CarrierEnum.SF_QIAO.getName());
        System.out.println(carrierSevice.getClass().getSimpleName());
    }

}
