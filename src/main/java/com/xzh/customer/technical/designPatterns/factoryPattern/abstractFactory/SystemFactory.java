package com.xzh.customer.technical.designPatterns.factoryPattern.abstractFactory;

public interface SystemFactory {
    OperationController createOperationController();
    UIController createInterfaceController();
}
