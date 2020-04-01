package com.halo.thread.basic;

/**
 * 当前类校验的是sleep方法与wait方法最明显的区别:
 * 执行wait方法时候, 会让出cpu并且释放锁
 * 执行sleep方法, 仅仅会让出cpu, 不会释放锁
 */
public class WaitSleepTest {
    static String value = "hello";

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread A start...");
                synchronized (value) {
                    try {
                        System.out.println("Thread A get WaitSleepTest class...start");
                        /**
                         * 情况1: 线程A执行wait:
                         *  线程A会释放锁, 所以线程B会执行,但是线程A中(Thread A done没有打印),因为不会自动唤醒,会被阻塞。
                         */
                        //value.wait();
                        /**
                         * 情况2: 线程A执行wait(long)方法:
                         *  线程A会释放锁, 线程B会执行, 隔一秒以后线程A打印(Thread A done),wait(long)会自动唤醒。
                         */
                        value.wait(1000);
                        /**
                         * 情况3: 线程A执行sleep方法:
                         * 线程A线程内容全部打印完以后, 才会打印线程B相关的信息。
                         */
                        //Thread.sleep(5000);
                        System.out.println("Thread A done");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread B start...");
                synchronized (value) {
                    System.out.println("Thread B get WaitSleepTest class...start");
                }
            }
        }).start();
    }
}