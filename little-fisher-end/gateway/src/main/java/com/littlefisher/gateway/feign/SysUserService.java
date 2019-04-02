package com.littlefisher.gateway.feign;

import com.littlefisher.base.util.ApiResult;
import com.littlefisher.base.vo.SysUserVo;
import com.littlefisher.gateway.feign.fallback.SysUserServiceFallback;
import feign.Logger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * feign 调用服务
 * </p>
 */
@FeignClient(name = "little-fisher-manage", fallback = SysUserServiceFallback.class, configuration = SysUserService.UserFeignConfig.class)

public interface SysUserService {

    /**
     * 通过用户名查找用户
     *
     * @param username
     * @return
     */
    @GetMapping("/user/loadUserByUsername/{username}")
    SysUserVo loadUserByUsername(@PathVariable(value = "username") String username);



    /**
     * 通过mobile查找用户
     *
     * @param mobile
     * @return
     */
    @GetMapping("/user/loadUserByMobile/{mobile}")
    SysUserVo loadUserByMobile(@PathVariable(value = "mobile") String mobile);




    class UserFeignConfig {
        @Bean
        public Logger.Level logger() {
            return Logger.Level.FULL;
        }
    }


}
