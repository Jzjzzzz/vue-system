package com.jzj.base.web.pojo.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jzj.base.web.pojo.entity.base.CommonEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * i茅台预约商品对象 mt_item
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
@Data
@TableName("mt_item")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "i茅台预约商品对象", description = "i茅台预约商品")
@NoArgsConstructor
public class MtItem extends CommonEntity {

    @ApiModelProperty(value = "预约商品编码")
    private String itemCode;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "图片url")
    private String picture;

    public MtItem(JSONObject item) {
        this.itemCode = item.getString("itemCode");
        this.title = item.getString("title");
        this.content = item.getString("content");
        this.picture = item.getString("picture");
    }

}
