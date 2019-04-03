package com.littlefisher.base.constants;

public interface JwtConstants {


    String AUTH_HEADER = "Authorization";

    String AUTH_HEADER_START = "Bearer ";


    /**
     * jwt中 用户id的key
     */
    String USER_ID = "userId";


    /**
     * jwt中 用户userName的key
     */
    String USER_NAME = "username";

    /**
     * jwt中 角色集合的key
     */
    String AUTHORITIES = "authorities";
}
