package com.jzj.base.web.pojo.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;

import com.jzj.base.web.pojo.entity.base.CommonEntity;

/**
 * 审批模板对象 oa_process_template
 * 
 * @author Jzj
 * @date 2024-07-30
 */
@Data
@TableName("oa_process_template")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "审批模板对象", description = "审批模板")
public class ProcessTemplate extends CommonEntity{

    @ApiModelProperty(value = "模板名称 ")
    private String name;

    @ApiModelProperty(value = "图标路径 ")
    private String iconUrl;

    @ApiModelProperty(value = "审批类别 ")
    private String processType;

    @ApiModelProperty(value = "表单属性 ")
    private String formProps;

    @ApiModelProperty(value = "表单选项 ")
    private String formOptions;

    @ApiModelProperty(value = "流程定义key ")
    private String processDefinitionKey;

    @ApiModelProperty(value = "流程定义上传路径 ")
    private String processDefinitionPath;

    @ApiModelProperty(value = "流程定义模型id ")
    private String processModelId;

    @ApiModelProperty(value = "描述 ")
    private String description;

    @ApiModelProperty(value = "删除标记（0:不可用 1:可用） ")
    private Integer isDeleted;


}
