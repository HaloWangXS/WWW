package com.halo.json;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.rdb.sharding.api.HintManager;
import com.halo.annotation.RequestLog;
import com.halo.dao.OrderMapper;
import com.halo.model.entity.TOrder;
import com.halo.utils.SequenceIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SequenceIdGenerator sequenceIdGenerator;

    @RequestMapping("/initOrder")
    public void initOrder() {
        for (int i = 0; i < 100; i++) {
            TOrder orderInfo = new TOrder();
            orderInfo.setOrderId(sequenceIdGenerator.nextId()/10);
            orderInfo.setOrderNo("NO:"+UUID.randomUUID().toString());
            orderInfo.setUserId(sequenceIdGenerator.nextId()/10000);
            int insert = orderMapper.insert(orderInfo);
            log.info("批量插入数据:{};",insert);
        }
    }

    /**
     * 没有指定任何的分片规则, 无论是分库规则还是分表规则
     */
    @RequestMapping("/getOrderByOrderNo")
    @RequestLog
    public void getOrderByOrderNo() {
        List<TOrder> orderEntities = orderMapper.queryOrderByOrderNo("NO:c0ae4634-7ee8-4451-9937-3e8481fab1e0");
        log.info("没有指定任何的分片规则,全库全表查询...{};",JSON.toJSONString(orderEntities));

        int count = orderMapper.countOrderByOrderNo("NO:c0ae4634-7ee8-4451-9937-3e8481fab1e0");
        log.info("没有指定任何的分片规则,数据统计...{};",count);
    }

    /**
     * 如果sql里没有传分片字段，则可以使用HintManager手动加入，如果不加入，则走全表扫描
     */
    @RequestMapping("/getOrderUseHint")
    public void getOrderUseHint() {
        HintManager instance = HintManager.getInstance();
        instance.addTableShardingValue("t_order","order_id","46166677035732184");
        // 正确的userId: 46166677035732 / 错误的userId: 46166677049153
        // userId给错了会路由到其他库, 所以分片字段尽量不要修改, 会导致查询不到。
        instance.addDatabaseShardingValue("t_order","user_id","46166677035732");
        List<TOrder> orderEntities = orderMapper.queryOrderByOrderNo("NO:c0ae4634-7ee8-4451-9937-3e8481fab1e0");
        log.info("使用HintManager强制加入的分片规则,查询...{};",JSON.toJSONString(orderEntities));
    }

    /**
     * 没有指定是那个库的分片规则
     */
    @RequestMapping("/getOrderByOrderId")
    public void getOrderByOrderId() {
        List<TOrder> orderEntities = orderMapper.queryOrderByOrderId(46166677046637376L);
        log.info("没有指定是那个库的分片规则...{};",JSON.toJSONString(orderEntities));
    }

    /**
     * 没有指定是那个表的分片规则
     */
    @RequestMapping("/getOrderByUserId")
    public void getOrderByUserId() {
        List<TOrder> orderEntities = orderMapper.queryOrderByUserId(46166677061736L);
        log.info("没有指定是那个表的分片规则...{};",JSON.toJSONString(orderEntities));
    }

    /**
     * 指定了所有的分片规则
     */
    @RequestMapping("/accurateFindOrder")
    public void accurateFindOrder() {
        List<TOrder> orderEntities = orderMapper.getOrderByOrderIdAndUserId(46166716438282268L,46166716438701L);
        log.info("指定了所有的分片规则...{}",JSON.toJSONString(orderEntities));
    }

    /**
     * 全表扫描, 得到结果组合然后取相应的数据
     */
    @RequestLog
    @RequestMapping("/queryOrdersByPage")
    public void queryOrdersByPage() {
        List<TOrder> orderEntities = orderMapper.queryOrderByPage(20,10);
        log.info("分页查询...{};",orderEntities.size());
    }
}