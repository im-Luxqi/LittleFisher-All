package com.littlefisher.gateway.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 权限模块配置
 *
 * @author chentongwei@bshf360.com 2018-05-31 13:27
 */
@Component
@ConfigurationProperties(prefix = "bigworld.security.authorize")
public class AuthorizeProperties {
    /** 无需权限即可访问的url */
    private String permitUrls;


    public String getPermitUrls() {
        return permitUrls;
    }

    public void setPermitUrls(String permitUrls) {
        this.permitUrls = permitUrls;
    }
}
