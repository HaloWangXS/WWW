package com.halo.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @program: Halo-SSM
 * @author: wangyanyang
 * @create: 2019-06-16 22:14
 **/
@Data
public class SuccessKilled {
    private Long id;

    private Long secKillId;

    private String userPhone;

    private Short state;

    private Integer buys;

    private Date createTime;
}
