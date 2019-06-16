package com.halo.dao;

import com.halo.model.entity.SecKill;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * @program: Halo-SSM
 * @author: wangyanyang
 * @create: 2019-06-16 22:49
 **/
@Repository
public interface SecKillDao {
    /**
     * 修改库存
     * @param secKillId
     * @param buys
     * @param version
     * @return
     */
    public int updateStock(@Param("secKillId") long secKillId,@Param("buys") int buys,@Param("version") long version);

    /**
     * 根据id查询秒杀对象
     * @param secKillId
     * @return
     */
    SecKill queryById(@Param("secKillId") long secKillId);

}
