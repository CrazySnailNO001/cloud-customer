package com.xzh.customer.technical.safeAndConcurrent.interview;

/**
 * @author ：xzh
 * @date ：Created in 2020-07-13 22:51
 * @description：
 * @modified By：
 * @version:
 */
public class T_02_cas {
    private static volatile ReadyToRun readyToRun = ReadyToRun.T1;

    public static void main(String[] args) {
        char[] aI = "123456".toCharArray();
        char[] aC = "ABCDEF".toCharArray();

        new Thread(() -> {
            for (char c : aI) {
                while (readyToRun != ReadyToRun.T1) {
                }
                System.out.print(c);
                readyToRun = ReadyToRun.T2;
            }
        }, "Thread-01").start();


        new Thread(() -> {
            for (char c : aC) {
                while (readyToRun != ReadyToRun.T2) {
                }
                System.out.print(c);
                readyToRun = ReadyToRun.T1;
            }
        }, "Thread-02").start();

    }

    enum ReadyToRun {T1, T2}
}
