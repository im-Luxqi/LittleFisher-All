package com.littlefisher.gateway.configspringsecurity;

import com.alibaba.fastjson.JSON;
import com.littlefisher.base.util.HttpReply;
import com.littlefisher.base.util.JwtTokenUtil;
import com.littlefisher.gateway.enums.JwtRedisEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LittleFisherLogoutSuccessHandler implements LogoutSuccessHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            String authToken = authHeader.substring("Bearer ".length());
            String randomKey = jwtTokenUtil.getMd5KeyFromToken(authToken);
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            redisTemplate.delete(JwtRedisEnum.getTokenKey(username, randomKey));
            redisTemplate.delete(JwtRedisEnum.getAuthenticationKey(username, randomKey));

            logger.info("删除【{}】成功", JwtRedisEnum.getTokenKey(username, randomKey));
            logger.info("退出成功");

            response.setHeader("Authorization", "");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(new HttpReply(HttpStatus.OK.value(), "退出成功！").data(null)));

        } else {
            throw new RuntimeException("暂无权限");
        }
    }
}
