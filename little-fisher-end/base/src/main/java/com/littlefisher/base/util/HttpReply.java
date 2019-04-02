package com.littlefisher.base.util;

/**
 * 返回结果
 */
public class HttpReply {
    private int code;//状态码
    private String msg;//提示语
    private String token;//jwt
    private Object data;//返回数据

    public HttpReply() {
    }
    public HttpReply(int code, String msg, String token) {
        this.code = code;
        this.msg = msg;
        this.token = token;
    }
    public HttpReply(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public HttpReply data(Object data) {
        this.data = data;
        return this;
    }

    public HttpReply(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
