package com.halooing.singleton;

/**
 * 双重加锁机制
 */
public class DoubleLockLazySingleton {
    /**
     * volatile关键字的含义是：被其所修饰的变量的值不会被本地线程缓存，所有对该变量的读写都是直接操作共享内存来实现，从而确保多个线程能正确的处理该变量。
     * 该关键字可能会屏蔽掉虚拟机中的一些代码优化，所以其运行效率可能不是很高，所以，一般情况下，并不建议使用双重加锁机制，酌情使用才是正理！
     *
     * 更进一步说，其实使用volatile的目的是为了防止暴露一个未初始化的不完整单例实例，导致系统崩溃。
     * 因为创建单例实例其实需要经过以下几步：
     *      首先分配内存空间、然后将内存空间的首地址指向引用（指针），最后调用构造器创建实例，由于在第二步的时候这个引用（指针）就会变的非null，
     *      那么在第三步未执行，真正的单例实例还未创建完成的时候，一个线程过来在第一个校验中为false，将会直接将不完整的实例返回，从而造成系统崩溃。
     */
    private static volatile DoubleLockLazySingleton instance = null;
    private DoubleLockLazySingleton() {}

    public static DoubleLockLazySingleton getInstance() {
        if(instance == null) {
            //区域1
            //不同线程去抢夺锁资源
            synchronized (DoubleLockLazySingleton.class) {
                //区域2
                if(instance == null) {
                    //区域3
                    /**
                     * 这里还要进行判断的原因是: 如果两个线程都进入到区域1
                     * A线程抢到了锁, 实例话对象,如果不加判断, B线程进入同步块也会初始化对象 违反单例原则。
                     */
                    instance = new DoubleLockLazySingleton();
                }
            }
        }
        //单例存在, 不需要同步操作. 直接返回该引用
        return instance;
    }
}
