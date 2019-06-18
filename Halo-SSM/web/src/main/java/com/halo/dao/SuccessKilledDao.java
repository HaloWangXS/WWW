package com.halo.dao;

import org.apache.ibatis.annotations.Param;

public interface SuccessKilledDao {
    /**
     * 插入购买明细，可过滤重复
     * @param secKillId
     * @param userPhone
     * @return插入的行数
     */
    int insertSuccessKilled(@Param("secKillId") long secKillId, @Param("userPhone") String userPhone, @Param("buys") int buys);
}
