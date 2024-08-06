package com.jzj.base.web.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Jzj
 * @Date 2024/8/5 下午5:39
 * @Version 1.0
 * @Message: 审批列表vo
 */
@Data
@ApiModel(description = "审批列表vo")
@EqualsAndHashCode
public class ProcessVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private Date createTime;

    @ApiModelProperty(value = "审批code")
    private String processCode;

    @ApiModelProperty(value = "用户id")
    private String userId;

    private String name;

    @TableField("process_template_id")
    private String processTemplateId;

    private String processTemplateName;

    @ApiModelProperty(value = "审批类型")
    private String processType;

    private String processTypeName;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "表单属性")
    private String formProps;

    @ApiModelProperty(value = "表单选项")
    private String formOptions;

    @ApiModelProperty(value = "表单属性值")
    private String formValues;

    @ApiModelProperty(value = "流程实例id")
    private String processInstanceId;

    @ApiModelProperty(value = "当前审批人")
    private String currentAuditor;

    @ApiModelProperty(value = "状态（0：默认 1：审批中 2：审批通过 -1：驳回）")
    private String status;

    private String taskId;
}
