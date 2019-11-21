package com.halo.model.entity;


import java.io.Serializable;
import lombok.Data;
import java.util.Date;

/**
 *  t_order_info
 * @author halo 2019-11-14
 */
@Data
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * user_id
     */
    private Long userId;

    /**
     * order_id
     */
    private Long orderId;

    /**
     * order_no
     */
    private String orderNo;

    /**
     * isactive
     */
    private Boolean isactive;

    /**
     * inserttime
     */
    private Date inserttime;

    /**
     * updatetime
     */
    private Date updatetime;

    public TOrder() {
    }

}
