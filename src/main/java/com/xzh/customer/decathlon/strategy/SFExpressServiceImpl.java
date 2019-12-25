package com.xzh.customer.decathlon.strategy;

import com.xzh.customer.dto.constants.CarrierEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ：xzh
 * @date ：Created in 2019-12-17 10:46
 * @description：
 * @modified By：
 * @version:
 */
@Service
@Slf4j
@CarrierTypeHandler(CarrierEnum.SF_QIAO)
public class SFExpressServiceImpl implements ICarrierService {
    @Override
    public String createOrder() {
        return CarrierEnum.SF_QIAO.getName() + "is doing create order .............";
    }

    @Override
    public String deliveryQuery() {
        return CarrierEnum.SF_QIAO.getName() + "is doing delivery query .............";

    }
}
