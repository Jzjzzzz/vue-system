package com.jzj.base.web.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.jzj.base.web.pojo.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Author Jzj
 * @Date 2024/7/24 下午4:18
 * @Version 1.0
 * @Message: 后台列表显示用户
 */
@ApiModel("后台列表显示用户")
@EqualsAndHashCode
@Data
public class User extends BaseEntity {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "性别（0：男 1：女 ）")
    private String sex;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "是否是超级管理员")
    private String isSuper;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "是否在线(0:离线,1:在线)")
    @TableField(exist = false)
    private boolean isOnLine;
}
