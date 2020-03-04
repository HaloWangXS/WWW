package com.halo.thread.basic;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 就是查看java中有哪些线程...
 */
public class ViewThreads {

    public static void main(String[] args) {
        ViewThreadTest viewThreadTest = new ViewThreadTest();
        viewThreadTest.start();
        // 获取java线程管理的MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的monitor和synchronized消息, 仅获取线程和堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        /**
         * 遍历线程信息，仅打印线程 ID 和线程名称信息
         */
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
    }
}
class ViewThreadTest extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println("休眠完成了...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}