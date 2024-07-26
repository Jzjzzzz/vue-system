package com.jzj.base.web.pojo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Jzj
 * @Date 2024/7/26 下午3:23
 * @Version 1.0
 * @Message:
 */
@Data
public class UserExcel {
    @ApiModelProperty(value = "用户名")
    @ExcelProperty("用户名")
    private String username;

    @ExcelProperty("性别")
    private String sex;

    @ExcelProperty("姓名")
    private String name;
}
