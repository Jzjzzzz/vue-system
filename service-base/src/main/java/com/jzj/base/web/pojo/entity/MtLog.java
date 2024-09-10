package com.jzj.base.web.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jzj.base.web.pojo.entity.base.CommonEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * i茅台执行日志对象 mt_log
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
@Data
@TableName("mt_log")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "i茅台执行日志对象", description = "i茅台执行日志")
public class MtLog extends CommonEntity {

    @ApiModelProperty(value = "操作人员 ")
    private Long mobile;

    @ApiModelProperty(value = "日志记录内容 ")
    private String logContent;
}
