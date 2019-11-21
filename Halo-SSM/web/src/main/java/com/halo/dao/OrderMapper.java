package com.halo.dao;

import com.halo.model.entity.TOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author halo
 * @date 2019/11/14
 */
public interface OrderMapper {

    /**
     * [新增]
     **/
    int insert(@Param("info") TOrder info);

    List<TOrder> queryOrderByPage(@Param("start") int start,@Param("end") int end);

    List<TOrder> queryOrderByOrderNo(@Param("orderNo") String orderNo);

    int countOrderByOrderNo(@Param("orderNo") String orderNo);

    List<TOrder> queryOrderByOrderId(@Param("orderId") Long orderId);

    List<TOrder> queryOrderByUserId(@Param("userId") Long userId);

    List<TOrder> getOrderByOrderIdAndUserId(@Param("orderId") Long orderId,@Param("userId") Long userId);
}
