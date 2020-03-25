package com.xzh.customer.technical.operation;

/**
 * @author XZHH
 * @Description:
 * @create 2019/5/8 0008 10:18
 * @modify By:
 **/
public class BitOperation {

    public static void main(String[] args) {
        int a = 10;
        printInfo(a);

        //左移一位(不分正负数，低位补0；)
        printInfo(a<<1);

        //右移2位(如果该数为正，则高位补0，若为负数，则高位补1；)
        printInfo(a>>2);

        printInfo(-a);

        //右移2位(负数最高位用1补齐)
        printInfo(-a>>2);

        //无符号右移2位(忽略符号位，空位都以0补齐)
        printInfo(-a>>>2);
        /*
          -20的源码：10000000  00000000   00000000   00010100

        　　　　反码：11111111  11111111   11111111   11101011

        　　　　补码：11111111  11111111   11111111   11101100

       无符号右移2位：00111111  11111111   11111111   11111011
        * */

    }

    /**
     * 输出一个int的二进制数
     * @param num
     */
    private static void printInfo(int num) {
        System.out.println(Integer.toBinaryString(num));
    }
}
