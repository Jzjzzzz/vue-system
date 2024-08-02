package com.jzj.base.web.pojo.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;

import com.jzj.base.web.pojo.entity.base.CommonEntity;

/**
 * 审批类型对象 oa_process
 * 
 * @author Jzj
 * @date 2024-08-01
 */
@Data
@TableName("oa_process")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "审批类型对象", description = "审批类型")
public class Process extends CommonEntity{

    @ApiModelProperty(value = "审批编号 ")
    private String processCode;

    @ApiModelProperty(value = "标题 ")
    private String title;

    @ApiModelProperty(value = "用户 ")
    private String userName;

    @ApiModelProperty(value = "用户id ")
    private String userId;

    @ApiModelProperty(value = "审批类型 ")
    private String processType;

    @ApiModelProperty(value = "审批模板id ")
    private String processTemplateId;

    @ApiModelProperty(value = "描述 ")
    private String description;

    @ApiModelProperty(value = "表单值 ")
    private String formValues;

    @ApiModelProperty(value = "流程实例id ")
    private String processInstanceId;

    @ApiModelProperty(value = "当前审批人 ")
    private String currentAuditor;

    @ApiModelProperty(value = "删除标记（0:不可用 1:可用） ")
    private Integer isDeleted;


}
