package com.littlefisher.gateway.enums;

/**
 * URL 枚举
 *
 * @author TongWei.Chen 2018-06-10 12:26:57
 */
public enum JwtUrlEnum {

    /**
     * 退出登录
     */
    LOGOUT("logout")
    ;

    private String url;


    JwtUrlEnum(String url) {
        this.url = url;
    }

    public String url() {
        return url;
    }
}
