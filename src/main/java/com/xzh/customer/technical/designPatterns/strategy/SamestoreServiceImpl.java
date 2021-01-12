package com.xzh.customer.technical.designPatterns.strategy;

import com.xzh.customer.technical.designPatterns.strategy.constants.CarrierEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ：xzh
 * @date ：Created in 2019-12-17 10:45
 * @description：
 * @modified By：
 * @version:
 */
@Service
@Slf4j
@CarrierTypeHandler(CarrierEnum.SAMESTORE)
public class SamestoreServiceImpl implements ICarrierService {
    @Override
    public String createOrder() {
        return CarrierEnum.SAMESTORE.getName() + "is doing create order .............";
    }
}
