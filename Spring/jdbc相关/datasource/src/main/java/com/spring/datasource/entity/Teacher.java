package com.spring.datasource.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
@Builder
public class Teacher implements Serializable {
    private String tno;
    private String tname;
    private String tsex;
    private Date tbirthday;
    private String prof;
    private String depart;

    public Teacher() {
    }

    public Teacher(String tno, String tname, String tsex, Date tbirthday, String prof, String depart) {
        this.tno = tno;
        this.tname = tname;
        this.tsex = tsex;
        this.tbirthday = tbirthday;
        this.prof = prof;
        this.depart = depart;
    }
}
