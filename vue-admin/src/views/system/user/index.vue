<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="68px" size="small">
      <el-form-item label="用户名" prop="username">
        <el-input
          v-model="queryParams.username"
          clearable
          placeholder="请输入用户名"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="状态"
          clearable
        >
          <el-option
            v-for="dict in dict.type.currency_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="dateRange"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        />
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" size="mini" type="primary" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          icon="el-icon-plus"
          plain
          size="mini"
          type="primary"
          @click="handleAdd"
          :disabled="$hasBP('btn.user.add')  === false"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          :disabled="single || $hasBP('btn.user.edit')  === false"
          icon="el-icon-edit"
          plain
          size="mini"
          type="success"
          @click="handleUpdate"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          icon="el-icon-download"
          plain
          size="mini"
          type="primary"
          @click="dialogVisible = true"
        >导入
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          :disabled="multiple || $hasBP('btn.user.del')  === false"
          icon="el-icon-delete"
          plain
          size="mini"
          type="danger"
          @click="handleDelete"
        >删除
        </el-button>
      </el-col>
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList"/>
    </el-row>

    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column align="center" label="序号" type="index"/>
      <el-table-column
        :show-overflow-tooltip="true"
        align="center"
        label="用户名"
        prop="username"
        width="200px"
      />
      <el-table-column
        :show-overflow-tooltip="true"
        align="center"
        label="姓名"
        prop="name"
      />
      <el-table-column
        :show-overflow-tooltip="true"
        align="center"
        label="手机号"
        prop="phone"
      />
      <el-table-column align="center" label="性别" prop="sex">
        <template v-slot="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.sex"/>
        </template>
      </el-table-column>
      <el-table-column
        :show-overflow-tooltip="true"
        align="center"
        label="登录状态"
        prop="onLine"
        width="150px"
      >
        <template v-slot="scope">
          <el-button :disabled="$hasBP('btn.user.off')  === false" type="primary" @click="handleOffLine(scope.row)" v-if="scope.row.onLine" size="mini" round>在线</el-button>
          <el-button type="info" v-else size="mini" disabled round>离线</el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" label="状态" prop="status">
        <template v-slot="scope">
          <dict-tag :options="dict.type.currency_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="创建时间" prop="createTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作" width="250px">
        <template v-slot="scope">
          <el-button
            icon="el-icon-edit"
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
            :disabled="$hasBP('btn.user.edit')  === false"
          >修改
          </el-button>
          <el-button
            icon="el-icon-s-custom"
            size="mini"
            type="text"
            @click="handleRole(scope.row)"
            :disabled="$hasBP('btn.user.edit')  === false"
          >角色分配
          </el-button>
          <el-button
            icon="el-icon-refresh-left"
            size="mini"
            type="text"
            @click="handleResetPassword(scope.row)"
            :disabled="$hasBP('btn.user.edit')  === false"
          >重置密码
          </el-button>
          <el-button
            icon="el-icon-delete"
            size="mini"
            type="text"
            @click="handleDelete(scope.row)"
            :disabled="$hasBP('btn.user.del')  === false"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :limit.sync="queryParams.pageSize"
      :page.sync="queryParams.pageNum"
      :total="total"
      @pagination="getList"
    />

    <el-dialog title="导入" :visible.sync="dialogVisible" width="30%">
      <el-form>
        <el-form-item label="请选择Excel文件">
          <el-upload
            :auto-upload="true"
            :multiple="false"
            :limit="1"
            :on-exceed="fileUploadExceed"
            :on-success="fileUploadSuccess"
            :on-error="fileUploadError"
            :action="BASE_API + 'api/excel/import'"
            name="file"
            accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            :data="{code:'user'}"
            :headers="header"
          >
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">
          取消
        </el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" >
        <el-row>
          <el-col :span="24">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="form.username" :disabled="form.id!=null" placeholder="请输入用户名"/>
            </el-form-item>
          </el-col>
          <el-col v-if="form.id==null" :span="24">
            <el-form-item label="密码" prop="password">
              <el-input v-model="form.password" placeholder="请输入密码" show-password/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="form.name" placeholder="请输入姓名"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="性别" prop="sex">
              <el-select v-model="form.sex" placeholder="请选择性别" clearable :style="{width: '100%'}">
                <el-option
                  v-for="dict in dict.type.sys_user_sex"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态" clearable :style="{width: '100%'}">
                <el-option
                  v-for="dict in dict.type.currency_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 重置密码对话框 -->
    <el-dialog :title="title" :visible.sync="openPassword" append-to-body>
      <el-form ref="form" :model="form" :rules="rules">
        <el-row>
          <el-col :span="24">
            <el-form-item label="密码" prop="password">
              <el-input v-model="form.password" placeholder="请输入密码" show-password/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitPassword">确 定</el-button>
        <el-button @click="openPassword=false">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 角色分配对话框 -->
    <el-dialog :title="title" :visible.sync="openRole" append-to-body>
      <el-form ref="form" :model="roleVo" :rules="rules" >
        <el-row>
          <el-col :span="24">
            <el-form-item label="角色" prop="role">
              <el-select
                v-model="roleVo.roleIds"
                placeholder="请选择角色"
                filterable
                multiple
                clearable
                :style="{width: '100%'}"
                @change="$forceUpdate()"
              >
                <el-option
                  v-for="item in roleList"
                  :key="item.id"
                  :label="item.roleName"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitRole">确 定</el-button>
        <el-button @click="openRole=false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {list, get, del, add, update, restPassword, allocationRole, offLine} from '@/api/system/user'
import {listAll} from '@/api/system/role'
import {getToken} from '@/utils/auth'
export default {
  name: 'User',
  dicts: ['sys_user_sex', 'currency_status'],
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
      // 表格数据
      list: [],
      // 弹出层标题
      title: '',
      roleList: [],
      // 日期范围
      dateRange: [],
      // 是否显示增改弹出层
      open: false,
      //角色分配弹出层
      openRole: false,
      // 重置密码弹出层
      openPassword: false,
      dialogVisible: false, //文件上传对话框是否显示
      BASE_API: process.env.VUE_APP_BASE_API, //获取后端接口地址
      header:{
        token: getToken()
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        username: undefined,
        status: undefined
      },
      // 表单参数
      form: {},
      roleVo: {},
      // 表单校验
      rules: {
        username: [
          {required: true, message: '用户名不能为空', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '密码不能为空', trigger: 'blur'}
        ],
        status: [
          {required: true, message: '请选择状态', trigger: 'change'}
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    // 上传多于一个文件时
    fileUploadExceed() {
      this.$message.warning('只能选取一个文件')
    },
    //上传成功回调
    fileUploadSuccess(response) {
      if (response.code === 0) {
        this.$message.success('数据导入成功')
        this.dialogVisible = false
      } else {
        this.$message.error(response.message)
      }
    },
    //上传失败回调
    fileUploadError(error) {
      this.$message.error('数据导入失败')
    },
    /** 查询列表 */
    getList() {
      this.isPreList('btn.user.list')
      this.loading = true
      list(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.list = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = []
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加用户'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      get(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改用户'
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id !== undefined) {
            update(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            add(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    submitRole: function () {
      allocationRole(this.roleVo).then(response => {
        this.$modal.msgSuccess('分配成功')
        this.openRole = false
        this.getList()
      })
    },
    submitPassword: function () {
      this.$refs['form'].validate(valid => {
        if (valid) {
          restPassword(this.form).then(response => {
            this.$modal.msgSuccess('重置成功')
            this.openPassword = false
            this.getList()
          })
        }
      })
    },
    /** 重置密码按钮操作 */
    handleResetPassword(row) {
      this.reset()
      this.form.id = row.id
      this.openPassword = true
      this.title = '重置密码'
    },
    handleRole(row) {
      this.roleVo = {}
      this.roleVo.userId = row.id
      this.openRole = true
      this.title = '角色分配'
      listAll(row.id).then(response => {
        console.log(response)
        this.roleList = response.data.roles
        this.roleVo.roleIds = response.data.ids
      })
    },
    /** 下线按钮操作 */
    handleOffLine(row){
      this.$modal.confirm('是否强制下线"' + row.username + '"？').then(function () {
        return offLine(row.id)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('下线成功')
      }).catch(() => {
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const userIds = row.id || this.ids
      this.$modal.confirm('是否确认删除用户编号为"' + userIds + '"的数据项？').then(function () {
        return del(userIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    }
  }
}
</script>
