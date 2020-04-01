package com.halo.thread.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 多线程环境下保证线程安全
 */
public class MutiThreadDemo {
    /**
     * volatile关键字表示 线程间共享
     */
    private volatile int count = 0;

    /**
     * 方法上锁
     */
    public synchronized void increamentCount() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        MutiThreadDemo counter = new MutiThreadDemo();
        int workCount = 50000;
        ExecutorService executor = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();
        for (int i = 0; i < workCount; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    counter.increamentCount();
                }
            };
            executor.execute(runnable);
        }
        // 关闭启动线程，执行未完成的任务
        executor.shutdown();
        // 等待所有线程完成任务，完成后才继续执行下一步
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");
        System.out.println("执行结果：count=" + counter.getCount());
    }
}
