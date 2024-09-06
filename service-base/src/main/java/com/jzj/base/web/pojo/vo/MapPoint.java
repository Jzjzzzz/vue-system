package com.jzj.base.web.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 坐标
 * </p>
 *
 * @author Jzj
 * @since 2024/9/6 17:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapPoint {

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 经度
     */
    private Double longitude;
}
