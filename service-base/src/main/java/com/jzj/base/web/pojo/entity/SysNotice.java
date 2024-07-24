package com.jzj.base.web.pojo.entity;

import com.jzj.base.web.pojo.entity.base.CommonEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 通知公告表
 * </p>
 *
 * @author Jzj
 * @since 2022-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysNotice对象", description = "通知公告表")
public class SysNotice extends CommonEntity {

    @ApiModelProperty(value = "公告标题")
    private String noticeTitle;

    @ApiModelProperty(value = "公告类型（1通知 2公告）")
    private String noticeType;

    @ApiModelProperty(value = "公告内容")
    private String noticeContent;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "备注")
    private String remark;


}
