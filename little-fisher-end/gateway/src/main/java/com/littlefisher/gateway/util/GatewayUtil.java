package com.littlefisher.gateway.util;

import com.littlefisher.base.constants.JwtConstants;
import com.littlefisher.gateway.configspringsecurity.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GatewayUtil {

    /**
     * 准备jwt payload信息
     * @param claims
     * @return
     */
    public Map<String, Object> userInfoClaim(Map<String, Object>  claims){
        Map<String, Object> map = new HashMap<>();
        map.put(JwtConstants.USER_ID, claims.get("username"));
        map.put(JwtConstants.USER_NAME, claims.get("username"));
        map.put(JwtConstants.AUTHORITIES, claims.get("authorities"));
        return map;
    }

    /**
     * 准备jwt payload信息
     * @param authentication
     * @return
     */
    public Map<String, Object> userInfoClaim(Authentication authentication){
        Map<String, Object> claims = new HashMap<>();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        claims.put(JwtConstants.USER_ID, userDetails.getUserId());
        claims.put(JwtConstants.USER_NAME, userDetails.getUsername());
        claims.put(JwtConstants.AUTHORITIES, userDetails.getRoleVos());
        return claims;
    }
}
