package com.halo.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @program: Halo-SSM
 * @author: wangyanyang
 * @create: 2019-06-16 22:13
 **/
@Data
public class SecKill {
    private Long secKillId;

    private String name;

    private Integer number;

    private Date createTime;

    private Long version;
}
