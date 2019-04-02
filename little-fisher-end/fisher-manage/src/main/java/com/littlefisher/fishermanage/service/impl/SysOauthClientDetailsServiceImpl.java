package com.littlefisher.fishermanage.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.littlefisher.fishermanage.mapper.SysOauthClientDetailsMapper;
import com.littlefisher.fishermanage.model.entity.SysOauthClientDetails;
import org.springframework.stereotype.Service;

/**
 * <p>
 * oauth2客户端资源认证表 服务实现类
 * </p>
 *
 * @author yukong
 * @since 2018-12-13
 */
@Service
public class SysOauthClientDetailsServiceImpl extends ServiceImpl<SysOauthClientDetailsMapper, SysOauthClientDetails> implements IService<SysOauthClientDetails> {

}
