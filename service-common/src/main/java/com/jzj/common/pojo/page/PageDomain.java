package com.jzj.common.pojo.page;


import com.jzj.common.utils.StringUtils;
import lombok.Data;
import lombok.Setter;

/**
 * <p>
 * 分页数据
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
@Data
public class PageDomain {

    private final String ASCENDING = "ascending";

    private final String DESCENDING = "descending";

    /**
     * 当前记录起始索引
     */
    @Setter
    private Integer pageNum;

    /**
     * 每页显示记录数
     */
    private Integer pageSize;

    /**
     * 排序列
     */
    private String orderByColumn;

    /**
     * 排序的方向desc或者asc
     */
    private String isAsc = "asc";

    /**
     * 分页参数合理化
     */
    private Boolean reasonable = true;

    public String getOrderBy() {
        if (StringUtils.isEmpty(orderByColumn)) {
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }

    public void setIsAsc(String isAsc) {
        if (StringUtils.isNotEmpty(isAsc)) {
            // 兼容前端排序类型
            if (ASCENDING.equals(isAsc)) {
                isAsc = "asc";
            } else if (DESCENDING.equals(isAsc)) {
                isAsc = "desc";
            }
            this.isAsc = isAsc;
        }
    }

    public Boolean getReasonable() {
        if (StringUtils.isNull(reasonable)) {
            return Boolean.TRUE;
        }
        return reasonable;
    }
}
