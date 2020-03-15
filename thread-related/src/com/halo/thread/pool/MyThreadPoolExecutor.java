package com.halo.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolExecutor {
    /**
     * 核心线程数
     */
    private static final int CORE_POOL_SIZE = 2;
    /**
     * 最大线程数
     */
    private static final int MAX_POOL_SIZE = 4;
    /**
     * 线程池中的任务队列数量
     */
    private static final int QUEUE_CAPACITY = 10;
    /**
     * 非核心线程存活时间
     */
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {
        /**
         * 通过ThreadPoolExecutor构造函数自定义参数创建
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10; i++) {
            MyRunnable run = new MyRunnable("" + i);
            //执行Runnable
            executor.execute(run);
        }
        executor.shutdown();
        //while (!executor.isTerminated()) {
        //}
        System.out.println("Finished all threads");
    }
}
