package com.spring.orm.model;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    private String sno;

    private String sname;

    private String ssex;

    private Date sbirthday;

    private String sclass;

    private static final long serialVersionUID = 1L;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex == null ? null : ssex.trim();
    }

    public Date getSbirthday() {
        return sbirthday;
    }

    public void setSbirthday(Date sbirthday) {
        this.sbirthday = sbirthday;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass == null ? null : sclass.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sno=").append(sno);
        sb.append(", sname=").append(sname);
        sb.append(", ssex=").append(ssex);
        sb.append(", sbirthday=").append(sbirthday);
        sb.append(", sclass=").append(sclass);
        sb.append("]");
        return sb.toString();
    }
}