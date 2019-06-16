package com.halo.service;

/**
 * @program: Halo-SSM
 * @author: wangyanyang
 * @create: 2019-06-16 22:23
 **/
public interface SecKillService {
    /**
     * 执行秒杀操减库存操作
     * @param secKillId
     * @param userPhone
     * @param buys
     * @return
     */
    public Boolean executeSecKill(long secKillId,long userPhone,int buys);
}
