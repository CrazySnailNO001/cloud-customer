package com.xzh.customer.technical.designPatterns.decoratorpattern;

/**
 * @author ：xzh
 * @date ：Created in 2021-03-12 15:02
 * @description：装饰者模式
 * 继承:
 *   被增强的对象固定的
 *   增强的内容也是固定的
 * 装饰者模式:
 *   被增强的对象是可以切换的
 *   增强的内容是固定的
 * @modified By：xzh
 * @version: V1.0.0
 */
public class DecoratorDemo {
    public static void main(String[] args) {
        //首先创建一个黑咖啡
        Coffee cof = new CoffeeImpl();
        //加糖咖啡
        Coffee sugar = new Sugar(cof);
        sugar.coffee();
        System.out.println();

        //加糖又加奶咖啡
        Coffee milk = new Milk(sugar);
        milk.coffee();
        System.out.println();

        //加糖加奶又加蜂蜜咖啡
        Coffee honey = new Honey(milk);
        honey.coffee();
    }

    /**
     * 首先定义一个接口来规范咖啡.
     */
    interface Coffee {
        void coffee();
    }

    /*
     * 实现这个上面这个接口中的方法.
     */
    static class CoffeeImpl implements Coffee {
        public void coffee() {
            System.out.println("黑咖啡");
        }
    }

    /*
     * 在给黑咖啡添加调味品之前,我们先定义一个类,这个类是所有添加调味品咖啡的父类,进行包装
     */
    static class CoffeeWrapper implements Coffee {
        private Coffee cof;

        CoffeeWrapper(Coffee cof) {
            this.cof = cof;
        }

        public void coffee() {
            cof.coffee();
        }
    }

    /**
     * 加糖咖啡
     */
    static class Sugar extends CoffeeWrapper {
        Sugar(Coffee cof) {
            super(cof);
        }

        public void coffee() {
            super.coffee();
            System.out.println("加糖");
        }
    }

    /**
     * 加奶咖啡
     */
    static class Milk extends CoffeeWrapper {
        Milk(Coffee cof) {
            super(cof);
        }

        public void coffee() {
            super.coffee();
            System.out.println("加奶");
        }
    }

    /**
     * 蜂蜜咖啡
     */
    static class Honey extends CoffeeWrapper {
        Honey(Coffee cof) {
            super(cof);
        }

        public void coffee() {
            super.coffee();
            System.out.println("加蜂蜜");
        }
    }
}
