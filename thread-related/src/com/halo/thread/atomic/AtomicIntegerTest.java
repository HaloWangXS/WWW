package com.halo.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 此方法是AtomicInteger原子类的测试类
 */
public class AtomicIntegerTest {
    public static void main(String[] args) {
        AtomicInteger num = new AtomicInteger(10);
        /**
         * 获取当前值
         */
        int n1 = num.get();
        System.out.println("n1 -->  " + n1 + " and now num --> " + num);
        /**
         * 获取当前值 并设置新的值
         */
        int n2 = num.getAndSet(20);
        System.out.println("n2 --> " + n2 + " and now num --> " + num);
        /**
         * 获取当前值 并自增
         * n3 --> 20 and now num --> 21
         */
        //int n3 = num.getAndIncrement();
        int n3 = num.incrementAndGet();
        System.out.println("n3 --> " + n3 + " and now num --> " + num);
        /**
         * 获取当前值 并自减
         */
        int n4 = num.getAndDecrement();
        System.out.println("n4 --> " + n4 + " and now num --> " + num);
        /**
         * 获取当前值 并加相应值
         */
        int n5 = num.getAndAdd(10);
        System.out.println("n5 --> " + n5 + " and now num --> " + num);
        /**
         * 如果输入的数值等于预期值，则以原子方式将该值设置为输入值（update）
         */
        boolean b = num.compareAndSet(30, 5);
        System.out.println("b --> " + b + " and now num --> " + num);
    }
}
