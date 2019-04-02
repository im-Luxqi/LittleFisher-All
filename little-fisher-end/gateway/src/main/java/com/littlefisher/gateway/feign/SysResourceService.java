package com.littlefisher.gateway.feign;

import com.littlefisher.base.vo.SysResourceVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "little-fisher-manage", configuration = FeignConfig.class)
public interface SysResourceService {

    /**
     * 根据角色查询资源信息
     *
     * @param roleCode
     * @return
     */
    @GetMapping("/resource/role/{roleCode}")
    Set<SysResourceVO> listResourceByRole(@PathVariable("roleCode") String roleCode);



    @GetMapping("/test")
    String test();


}
