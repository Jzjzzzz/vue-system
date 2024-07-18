package com.jzj.base.web.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.jzj.base.utils.constant.GenConstants;
import com.jzj.base.utils.sign.StringUtils;
import com.jzj.base.web.pojo.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;


/**
 * @Author Jzj
 * @Date 2024/7/16 下午3:02
 * @Version 1.0
 * @Message: 代码生成业务表
 */
@Data
@ApiModel(value = "GenTable对象", description = "代码生成业务表")
public class GenTable extends BaseEntity {

    private static final long serialVersionUID = 1L;

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

    public Long getTableId()
    {
        return tableId;
    }

    public void setTableId(Long tableId)
    {
        this.tableId = tableId;
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public String getTableComment()
    {
        return tableComment;
    }

    public void setTableComment(String tableComment)
    {
        this.tableComment = tableComment;
    }

    public String getSubTableName()
    {
        return subTableName;
    }

    public void setSubTableName(String subTableName)
    {
        this.subTableName = subTableName;
    }

    public String getSubTableFkName()
    {
        return subTableFkName;
    }

    public void setSubTableFkName(String subTableFkName)
    {
        this.subTableFkName = subTableFkName;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public String getTplCategory()
    {
        return tplCategory;
    }

    public void setTplCategory(String tplCategory)
    {
        this.tplCategory = tplCategory;
    }

    public String getTplWebType()
    {
        return tplWebType;
    }

    public void setTplWebType(String tplWebType)
    {
        this.tplWebType = tplWebType;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public String getModuleName()
    {
        return moduleName;
    }

    public void setModuleName(String moduleName)
    {
        this.moduleName = moduleName;
    }

    public String getBusinessName()
    {
        return businessName;
    }

    public void setBusinessName(String businessName)
    {
        this.businessName = businessName;
    }

    public String getFunctionName()
    {
        return functionName;
    }

    public void setFunctionName(String functionName)
    {
        this.functionName = functionName;
    }

    public String getFunctionAuthor()
    {
        return functionAuthor;
    }

    public void setFunctionAuthor(String functionAuthor)
    {
        this.functionAuthor = functionAuthor;
    }

    public String getGenType()
    {
        return genType;
    }

    public void setGenType(String genType)
    {
        this.genType = genType;
    }

    public String getGenPath()
    {
        return genPath;
    }

    public void setGenPath(String genPath)
    {
        this.genPath = genPath;
    }

    public GenTableColumn getPkColumn()
    {
        return pkColumn;
    }

    public void setPkColumn(GenTableColumn pkColumn)
    {
        this.pkColumn = pkColumn;
    }

    public GenTable getSubTable()
    {
        return subTable;
    }

    public void setSubTable(GenTable subTable)
    {
        this.subTable = subTable;
    }

    public List<GenTableColumn> getColumns()
    {
        return columns;
    }

    public void setColumns(List<GenTableColumn> columns)
    {
        this.columns = columns;
    }

    public String getOptions()
    {
        return options;
    }

    public void setOptions(String options)
    {
        this.options = options;
    }

    public String getTreeCode()
    {
        return treeCode;
    }

    public void setTreeCode(String treeCode)
    {
        this.treeCode = treeCode;
    }

    public String getTreeParentCode()
    {
        return treeParentCode;
    }

    public void setTreeParentCode(String treeParentCode)
    {
        this.treeParentCode = treeParentCode;
    }

    public String getTreeName()
    {
        return treeName;
    }

    public void setTreeName(String treeName)
    {
        this.treeName = treeName;
    }

    public String getParentMenuId()
    {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId)
    {
        this.parentMenuId = parentMenuId;
    }

    public String getParentMenuName()
    {
        return parentMenuName;
    }

    public void setParentMenuName(String parentMenuName)
    {
        this.parentMenuName = parentMenuName;
    }

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
