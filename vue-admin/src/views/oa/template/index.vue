<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="审批名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入审批名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审批类型" prop="processType">
        <el-select v-model="queryParams.processType" placeholder="请选择审批类型" clearable>
          <el-option
            v-for="dict in dict.type.sys_oa_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.oa_template_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <router-link :to="'templateSet'">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            :disabled="$hasBP('oa.template.add')  === false"
          >新增
          </el-button>
        </router-link>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          @click="handleDelete"
          :disabled="multiple || $hasBP('oa.template.remove')  === false"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="templateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="index" label="序号" align="center"/>

      <el-table-column label="审批名称" align="center" prop="name" />
      <el-table-column label="图标">
        <template slot-scope="scope">
          <img :src="scope.row.iconUrl" style="width: 30px;height: 30px;vertical-align: text-bottom;">
        </template>
      </el-table-column>
      <el-table-column label="审批类型" align="center" prop="processType">
        <template v-slot="scope">
          <dict-tag :options="dict.type.sys_oa_type" :value="scope.row.processType"/>
        </template>
      </el-table-column>
      <el-table-column label="描述" align="center" prop="description" />
      <el-table-column label="状态" align="center" prop="status">
        <template v-slot="scope">
          <dict-tag :options="dict.type.oa_template_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="180px" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="show(scope.row)"
            :disabled="$hasBP('oa.template.edit')  === false"
          >查看</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-promotion"
            @click="publish(scope.row.id)"
          >发布</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            :disabled="$hasBP('oa.template.remove')  === false"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!--  查看审批设置  -->
    <el-dialog title="查看审批设置" :visible.sync="formDialogVisible">
      <h3>基本信息</h3>
      <el-divider/>
      <el-form ref="flashPromotionForm" label-width="80px" size="small" style="padding-right: 40px;">
        <el-form-item label="审批类型" prop="sex">
          <el-select disabled v-model="processTemplate.processType" clearable :style="{width: '100%'}">
            <el-option
              v-for="dict in dict.type.sys_oa_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="审批名称" prop="phone">
          <el-input v-model="processTemplate.name" disabled/>
        </el-form-item>
      </el-form>
      <h3>表单信息</h3>
      <el-divider/>
      <div>
        <form-create
          :rule="rule"
          :option="option"
        ></form-create>
      </div>
      <span slot="footer" class="dialog-footer">
    <el-button @click="formDialogVisible = false" size="small">取 消</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
import {listTemplate, delTemplate, publish} from "@/api/oa/template";

export default {
  name: "Template",
  dicts: ['sys_oa_type', 'oa_template_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 审批模板表格数据
      templateList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        processType: null,
        status: null,
      },
      rule: [],
      option: {},
      processTemplate: {},
      formDialogVisible: false,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "审批名称不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "change" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ],
        isDeleted: [
          { required: true, message: "删除标记不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.isPreList('oa.template.list')
    this.getList();
  },
  methods: {
    publish(id) {
      publish(id).then(response => {
        this.$message.success('发布成功')
        this.fetchData(this.page)
      })
    },
    /** 查询审批模板列表 */
    getList() {
      this.loading = true;
      listTemplate(this.queryParams).then(response => {
        this.templateList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    show(row) {
      this.rule = JSON.parse(row.formProps)
      this.option = JSON.parse(row.formOptions)
      this.processTemplate = row
      this.formDialogVisible = true
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        iconUrl: null,
        processType: null,
        formProps: null,
        formOptions: null,
        processDefinitionKey: null,
        processDefinitionPath: null,
        processModelId: null,
        description: null,
        status: null,
        createTime: null,
        updateTime: null,
        isDeleted: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除审批模板编号为"' + ids + '"的数据项？').then(function() {
        return delTemplate(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
  }
};
</script>
