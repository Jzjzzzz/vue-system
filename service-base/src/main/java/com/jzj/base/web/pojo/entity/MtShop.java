package com.jzj.base.web.pojo.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jzj.base.web.pojo.entity.base.CommonEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * i茅台商店对象 mt_shop
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
@Data
@TableName("mt_shop")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "i茅台商店对象", description = "i茅台商店")
@NoArgsConstructor
public class MtShop extends CommonEntity {

    @ApiModelProperty(value = "店铺ID ")
    private String iShopId;

    @ApiModelProperty(value = "省份 ")
    private String provinceName;

    @ApiModelProperty(value = "城市 ")
    private String cityName;

    @ApiModelProperty(value = "地区 ")
    private String districtName;

    @ApiModelProperty(value = "完整地址 ")
    private String fullAddress;

    @ApiModelProperty(value = "纬度 ")
    private String lat;

    @ApiModelProperty(value = "经度 ")
    private String lng;

    @ApiModelProperty(value = "名称 ")
    private String name;

    @ApiModelProperty(value = "公司名称 ")
    private String tenantName;

    /**
     * 距离
     */
    @TableField(exist = false)
    private Double distance;

    public MtShop(String iShopId, JSONObject jsonObject) {
        this.iShopId = iShopId;
        this.provinceName = jsonObject.getString("provinceName");
        this.cityName = jsonObject.getString("cityName");
        this.districtName = jsonObject.getString("districtName");
        this.fullAddress = jsonObject.getString("fullAddress");
        this.lat = jsonObject.getString("lat");
        this.lng = jsonObject.getString("lng");
        this.name = jsonObject.getString("name");
        this.tenantName = jsonObject.getString("tenantName");
    }


}
