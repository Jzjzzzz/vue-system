package com.jzj.base.web.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.jzj.base.web.pojo.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 审批记录对象 oa_process_record
 * 
 * @author Jzj
 * @date 2024-08-05
 */
@Data
@TableName("oa_process_record")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "审批记录对象", description = "审批记录")
public class ProcessRecord extends BaseEntity {

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "审批流程id ")
    private String processId;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "审批描述 ")
    private String description;

    @ApiModelProperty(value = "操作用户id ")
    private String operateUserId;

    @ApiModelProperty(value = "操作用户 ")
    private String operateUser;

    @ApiModelProperty(value = "删除标记（0:不可用 1:可用） ")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
