package com.halo.thread.basic;

/**
 * 双重校验锁 实现单例
 */
public class Singleton {
    /**
     * uniqueSingleton = new Singleton(); 可以拆分为三步去执行:
     * 1. 为 uniqueSingleton分配内存空间
     * 2. 初始化 uniqueSingleton
     * 3. 将 uniqueSingleton 指向分配的内存空间
     * 正常情况下, 是上述三步, 但是jvm存在指令重排的特性,执行顺序变成了1 -> 3 -> 2. 所以在多线程情况下,
     * 会获得没有初始化的实例对象。但是volatile可以禁止jvm指令重排,保证多线程情况也可以使用。
     */
    private volatile static Singleton uniqueSingleton;

    public static Singleton getSingleton() {
        // 先判断对象是否已经实例化, 没有实例化才可以进加锁代码
        if (uniqueSingleton == null) {
            // class类 上锁
            synchronized (Singleton.class) {
                if (uniqueSingleton == null) {
                    uniqueSingleton = new Singleton();
                }
            }
        }
        return uniqueSingleton;
    }
}