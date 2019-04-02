package com.fisher.gateway.feign;

import com.littlefisher.base.vo.SysResourceVO;
import com.littlefisher.gateway.feign.SysResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysResourceServiceTest {

    @Autowired
    private SysResourceService sysResourceService;

    @Test
    public void testListResourceByRole() {
        Set<SysResourceVO> set = sysResourceService.listResourceByRole("ROLE_ADMIN");
        System.out.println("==============" + set.size());
    }

}