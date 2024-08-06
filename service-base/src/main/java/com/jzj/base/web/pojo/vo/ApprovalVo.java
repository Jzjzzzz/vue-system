package com.jzj.base.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author Jzj
 * @Date 2024/8/6 上午9:29
 * @Version 1.0
 * @Message: 审核vo
 */
@Data
@EqualsAndHashCode
@ApiModel("审核vo")
public class ApprovalVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String processId;

    private String taskId;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "审批描述")
    private String description;
}
