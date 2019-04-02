package com.littlefisher.fishermanage.model.dto;


import com.littlefisher.fishermanage.model.entity.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class SysUserInfoDTO {

    private SysUser sysUser;

    private List<String> roles;

    private List<String> permissions;
}
