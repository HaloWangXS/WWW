package com.halooing.singleton;

/**
 * 饿汉式: 在加载类时候创建类的实例,并保存在类中
 *  线程安全 但浪费资源
 */
public class HungrySingleton {
    private HungrySingleton() {}
    private static final  HungrySingleton instance = new HungrySingleton();
    public static HungrySingleton getInstance() {
        return instance;
    }
}
