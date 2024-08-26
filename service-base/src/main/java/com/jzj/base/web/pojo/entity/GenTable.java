package com.jzj.base.web.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.jzj.base.utils.constant.GenConstants;
import com.jzj.base.web.pojo.entity.base.BaseEntity;
import com.jzj.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.ArrayUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 代码生成业务表
 * </p>
 *
 * @author Jzj
 * @since 2023/1/16 15:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "GenTable对象", description = "代码生成业务表")
public class GenTable extends BaseEntity {

    @ApiModelProperty(value = "编号")
    private Long tableId;

    @ApiModelProperty(value = "表名称")
    @NotBlank(message = "表名称不能为空")
    private String tableName;

    @ApiModelProperty(value = "表描述")
    @NotBlank(message = "表描述不能为空")
    private String tableComment;

    @ApiModelProperty(value = "关联父表的表名")
    private String subTableName;

    @ApiModelProperty(value = "本表关联父表的外键名")
    private String subTableFkName;

    @NotBlank(message = "实体类名称不能为空")
    @ApiModelProperty(value = "实体类名称(首字母大写)")
    private String className;

    @ApiModelProperty(value = "使用的模板（crud单表操作 tree树表操作 sub主子表操作）")
    private String tplCategory;

    @ApiModelProperty(value = "前端类型（element-ui模版 element-plus模版）")
    private String tplWebType;

    @NotBlank(message = "生成包路径不能为空")
    @ApiModelProperty(value = "生成包路径")
    private String packageName;

    @NotBlank(message = "生成模块名不能为空")
    @ApiModelProperty(value = "生成模块名")
    private String moduleName;

    @NotBlank(message = "生成业务名不能为空")
    @ApiModelProperty(value = "生成业务名")
    private String businessName;

    @NotBlank(message = "生成功能名不能为空")
    @ApiModelProperty(value = "生成功能名")
    private String functionName;

    @NotBlank(message = "作者不能为空")
    @ApiModelProperty(value = "生成作者")
    private String functionAuthor;

    @ApiModelProperty(value = "生成代码方式（0zip压缩包 1自定义路径）")
    private String genType;

    @ApiModelProperty(value = "生成路径（不填默认项目路径）")
    private String genPath;

    @ApiModelProperty(value = "主键信息")
    private GenTableColumn pkColumn;

    @ApiModelProperty(value = "子表信息")
    private GenTable subTable;

    @Valid
    @ApiModelProperty(value = "表列信息")
    private List<GenTableColumn> columns;

    @ApiModelProperty(value = "其它生成选项")
    private String options;

    @ApiModelProperty(value = "树编码字段")
    private String treeCode;

    /** 树父编码字段 */
    @ApiModelProperty(value = "树父编码字段")
    private String treeParentCode;

    @ApiModelProperty(value = "树名称字段")
    private String treeName;

    @ApiModelProperty(value = "上级菜单ID字段")
    private String parentMenuId;

    @ApiModelProperty(value = "上级菜单名称字段")
    private String parentMenuName;

    @ApiModelProperty(value = "持久层框架(0-mybatis,1-mybatis-plus)")
    private String ormType;

    @ApiModelProperty(value = "是否生成导出(0-不生成,1-生成)")
    private String genExport;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "备注")
    private String remark;

    public boolean isSub()
    {
        return isSub(this.tplCategory);
    }

    public static boolean isSub(String tplCategory)
    {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_SUB, tplCategory);
    }

    public boolean isTree()
    {
        return isTree(this.tplCategory);
    }

    public static boolean isTree(String tplCategory)
    {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_TREE, tplCategory);
    }

    public boolean isCrud()
    {
        return isCrud(this.tplCategory);
    }

    public static boolean isCrud(String tplCategory)
    {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_CRUD, tplCategory);
    }

    public boolean isSuperColumn(String javaField)
    {
        return isSuperColumn(this.tplCategory, javaField);
    }

    public static boolean isSuperColumn(String tplCategory, String javaField)
    {
        if (isTree(tplCategory))
        {
            return StringUtils.equalsAnyIgnoreCase(javaField,
                    ArrayUtils.addAll(GenConstants.TREE_ENTITY, GenConstants.BASE_ENTITY));
        }
        return StringUtils.equalsAnyIgnoreCase(javaField, GenConstants.BASE_ENTITY);
    }
}
