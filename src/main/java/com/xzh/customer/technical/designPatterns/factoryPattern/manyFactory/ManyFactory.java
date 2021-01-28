package com.xzh.customer.technical.designPatterns.factoryPattern.manyFactory;

import com.xzh.customer.technical.designPatterns.factoryPattern.normalFactory.MailNormalSender;
import com.xzh.customer.technical.designPatterns.factoryPattern.normalFactory.MessageNormalSender;
import com.xzh.customer.technical.designPatterns.factoryPattern.normalFactory.NormalSender;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-27 16:54
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class ManyFactory {

    public NormalSender produceMessage() {
        return new MessageNormalSender();
    }

    public NormalSender produceMail() {
        return new MailNormalSender();
    }
}
