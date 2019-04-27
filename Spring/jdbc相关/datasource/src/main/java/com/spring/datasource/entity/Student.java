package com.spring.datasource.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
@Builder
public class Student implements Serializable {
    private String sno;
    private String sname;
    private String ssex;
    private Date sbirthday;
    private String sclass;

    public Student() {
    }

    public Student(String sno, String sname, String ssex, Date sbirthday, String sclass) {
        this.sno = sno;
        this.sname = sname;
        this.ssex = ssex;
        this.sbirthday = sbirthday;
        this.sclass = sclass;
    }
}
