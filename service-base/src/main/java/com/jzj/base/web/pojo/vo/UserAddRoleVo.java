package com.jzj.base.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author Jzj
 * @Date 2024/5/6 下午5:55
 * @Version 1.0
 * @Message: 用户分配角色Vo
 */
@Data
@EqualsAndHashCode
@ApiModel("用户分配角色Vo")
public class UserAddRoleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String [] roleIds;
}
