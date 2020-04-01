package com.halo.thread.basic.bankTransfer;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

import static java.lang.System.exit;

/**
 * 模拟解决银行转账死锁问题
 *
 * @author halo
 */
public class TransFerTest {
    /**
     * 账户详细信息
     */
    static private List<Account> testAccount = new ArrayList<>();
    /**
     * 账户数目
     */
    static final int testAccountNumber = 100;
    /**
     * 线程数
     */
    static final int testThreadNumber = 1000;
    /**
     * 线程池
     */
    static final ThreadPoolExecutor threadPool =
            new ThreadPoolExecutor(10, testThreadNumber,
                    1L, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(),
                    new ThreadPoolExecutor.AbortPolicy());
    /**
     * aqs工具
     */
    static final CountDownLatch countDownLatch = new CountDownLatch(testThreadNumber);

    static public void test() {
        /**
         * 随机获取两个账户A和B A给B转账。转账余额也是随机的
         */
        testAccount.get(ThreadLocalRandom.current().nextInt(testAccountNumber))
                .transactionToTarget(ThreadLocalRandom.current().nextInt(100),
                        testAccount.get(ThreadLocalRandom.current().nextInt(testAccountNumber)));
        // 表示一个请求已经被完成
        countDownLatch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < testAccountNumber; i++) {
            testAccount.add(new Account(i));
        }

        for (int i = 0; i < testThreadNumber; i++) {
            threadPool.execute(() -> {
                test();
            });
        }
        // 方法具有阻塞作用，也就是说主线程在这里暂停
        countDownLatch.await();
        long totalNumber = 0;
        for (Account i : testAccount) {
            if (i.getBalance() < 0) {
                System.out.println("余额小于0： " + i.toString());
                exit(-1);
            }
            totalNumber += i.getBalance();
        }
        System.out.println("总余额： " + totalNumber);
        /**
         * 线程池使用完毕要关闭
         */
        threadPool.shutdown();
    }
}

class Allocator {
    /**
     * 单例的账号管理对象
     */
    private volatile static Allocator singleAllocator;

    /**
     * 有进行转账的账号都记录到这里。
     */
    private Set<Account> accountSet = new HashSet<>();


    /**
     * 双层锁校验, 确保获得到的锁对象是单例的。
     *
     * @return
     */
    public static Allocator getAllocator() {
        if (null == singleAllocator) {
            synchronized (Allocator.class) {
                if (null == singleAllocator) {
                    singleAllocator = new Allocator();
                }
            }
        }
        return singleAllocator;
    }

    /**
     * @param source 转出账户
     * @param target 转入账户
     */
    public synchronized void addLock(Account source, Account target) {
        while (accountSet.contains(source) || accountSet.contains(target)) {
            //如果转出账户 和 转入账户有一方已经得到锁资源, 那么另外一方不获取锁资源。
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        accountSet.add(source);
        accountSet.add(target);
    }

    public synchronized void releaseLock(Account source, Account target) {
        accountSet.remove(source);
        accountSet.remove(target);
        notifyAll();
    }
}

class Account {
    /**
     * 余额
     */
    private Integer balance;
    /**
     * 账户id
     */
    private Integer id;

    Account(Integer id) {
        this.id = id;
        balance = 100;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "账号id " + id.toString() + " 余额 ￥" + balance.toString() + " ";
    }

    //转账
    public void transactionToTarget(Integer money, Account target) {
        //转账过程先上锁
        Allocator.getAllocator().addLock(this, target);
        System.out.println("转账开始--转出账号：" + this.toString() + "|转入账号：" + target.toString());
        if (this.balance - money >= 0) {
            this.balance -= money;
            target.setBalance(target.getBalance() + money);
            System.out.println("转账成功--转出账号：" + this.toString() + "|转入账号：" + target.toString());
        } else {
            System.out.println("转账失败--转出账号：" + this.toString() + "|想转金额：￥" + money.toString());
        }
        //释放锁
        Allocator.getAllocator().releaseLock(this, target);
    }
}
