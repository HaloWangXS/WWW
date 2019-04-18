package com.halooing.singleton;

/**
 * 静态内部类
 *  内部类和其外部类之间没有从属关系, 加载外部类时候不会加载静态内部类, 只有在调用时候才会加载, 创建单例对象返回，实现懒加载。
 *  同步问题: 使用静态初始化器的方式，借助jvm实现线程安全
 */
public class ClassInnerClassSingleton {
    public static class Singleton {
        private static ClassInnerClassSingleton instance = new ClassInnerClassSingleton();
    }
    private ClassInnerClassSingleton() {}
    public static ClassInnerClassSingleton grtInstance() {
        return Singleton.instance;
    }
}
