package com.halo.thread.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程环境下使用AtomicInter原子类
 */
public class MutiThreadUseAtomicDemo {
    private AtomicInteger count = new AtomicInteger();

    public void increamentCount() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        MutiThreadUseAtomicDemo counter = new MutiThreadUseAtomicDemo();
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
