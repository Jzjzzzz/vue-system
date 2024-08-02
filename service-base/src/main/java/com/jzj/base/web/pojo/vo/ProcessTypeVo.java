package com.jzj.base.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author Jzj
 * @Date 2024/8/1 下午4:23
 * @Version 1.0
 * @Message: 审批模板类别vo
 */
@Data
@EqualsAndHashCode
@ApiModel("审批模板类别vo")
public class ProcessTypeVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模板类型")
    private String dictLabel;

    @ApiModelProperty(value = "审批模板id")
    private String id;

    @ApiModelProperty(value = "模板名称")
    private String name;

    @ApiModelProperty(value = "模板图标")
    private String iconUrl;
}
