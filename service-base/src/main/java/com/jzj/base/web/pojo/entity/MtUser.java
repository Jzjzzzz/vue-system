package com.jzj.base.web.pojo.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jzj.base.web.pojo.entity.base.CommonEntity;
import com.jzj.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * i茅台用户对象 mt_user
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
@Data
@TableName("mt_user")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "i茅台用户对象", description = "i茅台用户")
@NoArgsConstructor
public class MtUser extends CommonEntity {

    @ApiModelProperty(value = "i茅台手机号")
    private Long mobile;

    @ApiModelProperty(value = "i茅台用户id")
    private Long userId;

    @ApiModelProperty(value = "i茅台toekn")
    private String token;

    @ApiModelProperty(value = "i茅台cookie")
    private String cookie;

    @ApiModelProperty(value = "设备id")
    private String deviceId;

    @ApiModelProperty(value = "商品预约code，用@间隔")
    private String itemCode;

    @ApiModelProperty(value = "门店商品id")
    private String ishopId;

    @ApiModelProperty(value = "省份")
    private String provinceName;

    @ApiModelProperty(value = "城市")
    private String cityName;

    @ApiModelProperty(value = "完整地址")
    private String address;

    @ApiModelProperty(value = "纬度")
    private String lat;

    @ApiModelProperty(value = "经度")
    private String lng;

    @ApiModelProperty(value = "预约的分钟（0-59")
    private Long minute;

    @ApiModelProperty(value = "预约类型")
    private Long shopType;

    @ApiModelProperty(value = "随机时间预约")
    private String randomMinute;

    @ApiModelProperty(value = "推送token")
    private String pushPlusToken;

    @ApiModelProperty(value = "返回参数")
    private String jsonResult;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "到期时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expireTime;

    public MtUser(Long mobile, JSONObject jsonObject) {
        JSONObject data = jsonObject.getJSONObject("data");
        this.userId = data.getLong("userId");
        this.mobile = mobile;
        this.token = data.getString("token");
        this.cookie = data.getString("cookie");
        this.jsonResult = StringUtils.substring(jsonObject.toJSONString(), 0, 2000);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        this.expireTime = calendar.getTime();
    }

    public MtUser(Long mobile, String deviceId, JSONObject jsonObject) {
        JSONObject data = jsonObject.getJSONObject("data");
        this.userId = data.getLong("userId");
        this.mobile = mobile;
        this.token = data.getString("token");
        this.cookie = data.getString("cookie");
        this.deviceId = deviceId.toLowerCase();
        this.jsonResult = StringUtils.substring(jsonObject.toJSONString(), 0, 2000);

        if (StringUtils.isEmpty(this.remark)) {
            this.remark = data.getString("userName");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        this.expireTime = calendar.getTime();
    }


}
