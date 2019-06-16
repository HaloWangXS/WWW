package com.halo.model;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = -3959972750371333941L;
    private boolean success = false;
    private T data = null;
    private String msg = "";
    private String code = "500";

    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<T>();
        r.setData(data);
        r.setSuccess(true);
        r.setCode("200");
        r.setMsg("success");
        return r;
    }

    public static <T> Result<T> fail(String code, String msg) {
        Result<T> r = new Result<T>();
        r.setSuccess(false);
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public boolean isSuccess() {
        return success;
    }

    public Result<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }
    public String getCode() {
        return code;
    }
    public Result<T> setCode(String code) {
        this.code = code;
        return this;
    }
}