package com.jzj.base.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author Jzj
 * @Date 2024/8/6 上午11:13
 * @Version 1.0
 * @Message: 审核分页查询实体
 */
@Data
@EqualsAndHashCode
@ApiModel(description = "审核分页查询实体")
public class ProcessQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer pageNum;

    private Integer pageSize;

    private String status;

    private String createTime;

    private String type;

    private String userName;
}
