package com.littlefisher.fishermanage.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.littlefisher.fishermanage.model.entity.SysResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 资源表(菜单与按钮) Mapper 接口
 * </p>
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

    /**
     * 根据角色code查询资源集合
     *
     * @param roleCode
     * @return
     */
    List<SysResource> findResourceByRoleCode(String roleCode);

}
