package halo.service;

import com.halo.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.print.DocFlavor;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @program: Halo-SSM
 * @author: wangyanyang
 * @create: 2019-06-18 23:15
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
@Slf4j
public class SeckKillTest {

    // 参与秒杀活动的用户人数
    private static final int USER_NUM = 1000;
    // 货物id
    private static final int GOODS_ID = 1000;
    //  库存
    private static final int GOOD_LIFT = 100;
    // 计数器
    private static int successed = 0;
    // 记录卖出去的商品
    private static int saleNum = 0;
    // 发令枪 模拟高并发
    private static CountDownLatch countDownLatch = new CountDownLatch(USER_NUM);

    @Autowired
    private SecKillService secKillService;

    // 发令枪
    private CyclicBarrier cyc = new CyclicBarrier(USER_NUM + 1);

    @Before
    public void init() {

    }

    // 乐观锁实现秒杀
    @Test
    public void SeckKillDemo() throws InterruptedException, BrokenBarrierException {
        long start = System.currentTimeMillis();
        for(int i = 0; i < USER_NUM; i++) {
            //模拟用户请求 假设每个用户买三个
            new Thread(new UserRequest(GOODS_ID,3)).start();
            if(i == USER_NUM) {
                Thread.currentThread().sleep(1000);
            }
            // 计时器减1
            countDownLatch.countDown();
        }
        // 主线程睡两秒
        long end = System.currentTimeMillis();
        cyc.await();
        log.info("消耗时间:{};",end - start);
        log.info("成功购买订单商品的人数:{},",successed);
        log.info("已经售出商品数字:{};",saleNum);
    }

    /**
     * 内部类 用于模拟用户请求
     */
    public class UserRequest implements Runnable {
        private int code;
        private int buys;

        public UserRequest(int code, int buys) {
            this.code = code;
            this.buys = buys;
        }

        @Override
        public void run() {
            try {
                // 所有的线程运行到这里都睡眠 等待发令枪的指示
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 库存更新 同步修改购买成功的人数以及商品个数
            Boolean aBoolean = secKillService.executeSecKill(code, "15735104513", buys);
            if(aBoolean) {
                synchronized (countDownLatch) {
                    successed++;
                    saleNum = saleNum + buys;
                }
            }

            try {
                cyc.await();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
