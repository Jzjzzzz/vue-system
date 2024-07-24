package com.jzj.base.web.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author Jzj
 * @Date 2022/8/19 14:30
 * @Version 1.0
 * @Message: 个人信息修改vo
 */
@Data
@EqualsAndHashCode
public class UserUpdateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String avatar;

    private String oldpassword;

    private String newpassword1;

    private String newpassword2;
}
