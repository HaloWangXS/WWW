package com.halo.service.impl;

import com.halo.dao.SecKillDao;
import com.halo.dao.SuccessKilledDao;
import com.halo.model.entity.SecKill;
import com.halo.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @program: Halo-SSM
 * @author: wangyanyang
 * @create: 2019-06-16 22:24
 **/
@Service
public class SecKillServiceImpl implements SecKillService {
    @Autowired
    private SecKillDao secKillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;

    @Override
    public Boolean executeSecKill(long secKillId, String userPhone, int buys) {
        // 1. 查询库存,以及版本号
        SecKill secKill = secKillDao.queryById(secKillId);
        if(null == secKill) {
            return false;
        }
        // 2 获得版本号
        long version = secKill.getVersion();

        // 3 获得商品库存数量
        int number = secKill.getNumber();
        if(number < buys) {
            return false;
        }
        // 更新库存
        if(secKillDao.updateStock(secKillId, buys, version) > 0) {
            // 记录购买记录
            int insertCount = successKilledDao.insertSuccessKilled(secKillId,userPhone,buys);
            return true;
        }
        // 秒杀失败 继续秒杀(递归调用) 但是要随机sleep一会 实现错峰
        waitForLock();
        return executeSecKill(secKillId,userPhone,buys);
    }

    private void waitForLock() {
        try {
            Thread.sleep(new Random().nextInt(10) + 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
