package com.jzj.base.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Jzj
 * @Date 2022/12/8 9:11
 * @Version 1.0
 * @Message: 获取七日统计vo
 */
@Data
@EqualsAndHashCode
@ApiModel("获取七日统计vo")
public class BeforeDayCountVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日点击数列表")
    private List<Long> clickDayCounts;

    @ApiModelProperty(value = "日点赞数列表")
    private List<Long> likeDayCounts;

    @ApiModelProperty(value = "日期列表")
    private List<String> dateList;
}
