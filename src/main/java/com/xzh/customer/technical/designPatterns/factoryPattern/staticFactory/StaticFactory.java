package com.xzh.customer.technical.designPatterns.factoryPattern.staticFactory;

import com.xzh.customer.technical.designPatterns.factoryPattern.normalFactory.MailNormalSender;
import com.xzh.customer.technical.designPatterns.factoryPattern.normalFactory.MessageNormalSender;
import com.xzh.customer.technical.designPatterns.factoryPattern.normalFactory.NormalSender;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-27 16:58
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class StaticFactory {
    public static NormalSender produceMessage() {
        return new MessageNormalSender();
    }

    public static NormalSender produceMail() {
        return new MailNormalSender();
    }
}

//public class MatchFactory {
//    static Map<String, MathOperation> operationMap = new HashMap<>();
//    static {
//        operationMap.put("add", new AddOperation());
//        operationMap.put("sub", new SubOperation());
//        operationMap.put("multiply", new MultiplyOperation());
//        operationMap.put("divide", new DivideOperation());
//    }
//
//    public static Optional<Operation> getOperation(String operator) {
//        return Optional.ofNullable(operationMap.get(operator));
//    }
//}
