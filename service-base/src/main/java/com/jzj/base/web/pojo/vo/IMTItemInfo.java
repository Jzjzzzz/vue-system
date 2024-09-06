package com.jzj.base.web.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * i茅台预约商品信息
 * </p>
 *
 * @author Jzj
 * @since 2024/9/6 17:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IMTItemInfo {

    private String shopId;

    private int count;

    private String itemId;

    /**
     * 库存
     */
    private int inventory;

}
