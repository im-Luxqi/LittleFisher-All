package com.littlefisher.gateway.properties;

import com.littlefisher.base.properties.JwtProperties;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * APP模块的统一配置
 */
@Component
public class PublicProperties {

    @Autowired
    private JwtProperties jwt;  //jwt的一些配置
    @Autowired
    private AuthorizeProperties auth;   //权限的一些配置


    public JwtProperties getJwt() {
        return jwt;
    }

    public void setJwt(JwtProperties jwt) {
        this.jwt = jwt;
    }

    public AuthorizeProperties getAuth() {
        return auth;
    }

    public void setAuth(AuthorizeProperties auth) {
        this.auth = auth;
    }

    /**
     * 将配置文件读出来的url去除空白
     *
     * @param permitUrls
     * @return
     */
    public static String[] readPropertiesUrl(String permitUrls) {
        if (StringUtils.isNotEmpty(permitUrls) && StringUtils.isNotBlank(permitUrls)) {
            permitUrls = permitUrls.replace(" ", "");
            String[] urlArray = StringUtils.splitByWholeSeparator(permitUrls, ",");
            return urlArray;
        }
        return new String[]{};
    }


    /**
     * 所有无需权限访问的url
     * @return
     */
    public String[] getPermitUrls() {
        new AuthorizeProperties();
        /** 核心模块相关的URL */
        String[] corePermitUrls = readPropertiesUrl(auth.getPermitUrls());
//        String[] corePermitUrls = coreAuthorizeConfigProvider.getPermitUrls();
        /** 验证模块相关的URL */
//        String[] validatePermitUrls = ValidateAuthorizeConfigProvider.getPermitUrls();
        String[] validatePermitUrls = {"/login","/user11/**"};
        /** 返回的数组 */
        return (String[]) ArrayUtils.addAll(corePermitUrls, validatePermitUrls);
    }
}
