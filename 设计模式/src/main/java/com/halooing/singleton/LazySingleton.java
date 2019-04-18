package com.halooing.singleton;

/**
 * 懒汉式: 不在系统加载时创建类的单例, 而是第一次使用实例时候创建。
 */
public class LazySingleton {
    /**
     * 保证 instance 在所有线程中同步
     */
    private static LazySingleton instance = null;

    /**
     * 避免在外部被实例化
     */
    private LazySingleton(){}
    public static synchronized LazySingleton getInstance() {
        if(instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
