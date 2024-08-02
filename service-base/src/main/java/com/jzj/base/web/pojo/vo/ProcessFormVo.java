package com.jzj.base.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author Jzj
 * @Date 2024/8/2 下午2:26
 * @Version 1.0
 * @Message: 流程表单vo
 */
@Data
@ApiModel(description = "流程表单vo")
@EqualsAndHashCode
public class ProcessFormVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "审批模板id")
    private Long processTemplateId;

    @ApiModelProperty(value = "审批类型id")
    private Long processTypeId;

    @ApiModelProperty(value = "表单值")
    private String formValues;
}
