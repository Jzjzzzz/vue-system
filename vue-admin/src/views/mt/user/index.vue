<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="68px" size="small">
      <el-form-item label="手机号" prop="mobile">
        <el-input
          v-model="queryParams.mobile"
          clearable
          placeholder="请输入手机号"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          clearable
          placeholder="请输入用户id"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="省份" prop="provinceName">
        <el-input
          v-model="queryParams.provinceName"
          clearable
          placeholder="请输入省份"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="城市" prop="cityName">
        <el-input
          v-model="queryParams.cityName"
          clearable
          placeholder="请输入城市"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="到期时间" prop="expireTime">
        <el-date-picker
          v-model="queryParams.expireTime"
          clearable
          placeholder="请选择到期时间"
          type="date"
          value-format="yyyy-MM-dd"
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
          :disabled="$hasBP('mt.user.add') === false"
          icon="el-icon-plus"
          plain
          size="mini"
          type="primary"
          @click="handleAdd"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          :disabled="single || $hasBP('mt.user.edit') === false"
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
          :disabled="multiple || $hasBP('mt.user.remove') === false"
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

    <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column align="center" label="序号" type="index"/>

      <el-table-column align="center" label="手机号" prop="mobile"/>
      <el-table-column align="center" label="省份" prop="provinceName"/>
      <el-table-column align="center" label="城市" prop="cityName"/>
      <el-table-column align="center" label="地址" prop="address"/>
      <el-table-column align="center" label="预约的分钟" prop="minute"/>
      <el-table-column align="center" label="预约类型" prop="shopType">
        <template v-slot="scope">
          <dict-tag :options="dict.type.mt_pre_type" :value="scope.row.shopType"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="是否随机时间预约" prop="randomMinute" width="150px">
        <template v-slot="scope">
          <dict-tag :options="dict.type.mt_pre_random" :value="scope.row.randomMinute"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="到期时间" prop="expireTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.expireTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template v-slot="scope">
          <el-button
            icon="el-icon-thumb"
            size="mini"
            type="text"
            @click="reservation(scope.row)"
          >预约
          </el-button>
          <el-button
            :disabled="$hasBP('mt.user.edit') === false"
            icon="el-icon-edit"
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
          >修改
          </el-button>
          <el-button
            :disabled="$hasBP('mt.user.remove') === false"
            icon="el-icon-delete"
            size="mini"
            type="text"
            @click="handleDelete(scope.row)"
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

    <!-- 新增i茅台账号 -->
    <el-dialog :visible.sync="openAdd" append-to-body title="新增i茅台账号">
      <el-form ref="form" :model="form">
        <el-form-item label="手机号" prop="mobile">
          <el-input v-model="form.mobile" placeholder="请输入i茅台用户手机号"/>
          <div style="margin-top: 10px">
            <el-button
              :disabled="state"
              type="primary"
              @click="sendCode(form.mobile)"
            >发送验证码<span v-if="state">({{ stateNum }})</span>
            </el-button>
          </div>
        </el-form-item>

        <el-form-item label="验证码" prop="userId">
          <el-input v-model="form.code" placeholder="请输入验证码"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button
          type="primary"
          @click="login(form.mobile, form.code)"
        >登 录
        </el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 修改 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="i茅台手机号" prop="mobile">
          <el-input v-model="form.mobile" placeholder="请输入i茅台手机号"/>
        </el-form-item>
        <el-form-item label="i茅台用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入i茅台用户id"/>
        </el-form-item>
        <el-form-item label="i茅台toekn" prop="token">
          <el-input v-model="form.token" placeholder="请输入i茅台toekn"/>
        </el-form-item>
        <el-form-item label="i茅台cookie" prop="cookie">
          <el-input v-model="form.cookie" placeholder="请输入i茅台cookie"/>
        </el-form-item>
        <el-form-item label="设备id" prop="deviceId">
          <el-input v-model="form.deviceId" placeholder="请输入设备id"/>
        </el-form-item>
        <el-form-item label="预约code" prop="itemCode">
          <el-select
            v-model="itemSelect"
            multiple
            placeholder="请选择"
            @change="changeItem"
          >
            <el-option
              v-for="item in itemList"
              :key="item.itemCode"
              :label="item.title"
              :value="item.itemCode"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="店铺id" prop="ishopId">
          <el-input v-model="form.ishopId" placeholder="请输入门店商品id"/>
        </el-form-item>
        <el-form-item label="预约的分钟" prop="minute">
          <el-input v-model="form.minute" placeholder="请输入预约的分钟"/>
        </el-form-item>
        <el-form-item label="预约类型" prop="shopType">
          <el-select v-model="form.shopType" placeholder="请选择预约类型">
            <el-option
              v-for="dict in dict.type.mt_pre_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="随机时间预约" prop="randomMinute">
          <el-select v-model="form.randomMinute" placeholder="请选择随机时间预约">
            <el-option
              v-for="dict in dict.type.mt_pre_random"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="推送token" prop="pushPlusToken">
          <el-input v-model="form.pushPlusToken" placeholder="请输入推送token"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注"/>
        </el-form-item>
        <el-form-item label="到期时间" prop="expireTime">
          <el-date-picker
            v-model="form.expireTime"
            clearable
            placeholder="请选择到期时间"
            type="date"
            value-format="yyyy-MM-dd"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {addUser, delUser, getUser, listUser, login, reservation, sendCode, updateUser} from '@/api/mt/user'
import {itemAll} from '@/api/mt/item'

export default {
  name: 'User',
  dicts: ['mt_pre_random', 'mt_pre_type'],
  data() {
    return {
      // I茅台预约商品列表格数据
      itemList: [],
      // 选择的数据
      itemSelect: [],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 发送短信按钮倒计时
      state: false,
      stateNum: 60,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // i茅台用户表格数据
      userList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      openAdd: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        mobile: null,
        userId: null,
        provinceName: null,
        cityName: null,
        expireTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        mobile: [
          {required: true, message: 'i茅台手机号不能为空', trigger: 'blur'}
        ]
      }
    }
  },
  created() {
    this.isPreList('mt.user.list')
    this.getList()
    itemAll().then((response) => {
      this.itemList = response.data
    })
  },
  methods: {
    // 预约
    reservation(row) {
      const id = row.id || this.ids
      reservation(id).then((response) => {
        this.$modal.msgSuccess('请求成功，结果看日志')
      })
    },
    // item下拉框选择
    changeItem(e) {
      this.form.itemCode = ''
      this.itemSelect.forEach((e) => {
        this.form.itemCode += e + '@'
      })
    },
    // 发送验证码
    sendCode(mobile, deviceId) {
      if (deviceId === undefined || deviceId === '') {
        this.form.deviceId = this.guid()
      } else {
        this.form.deviceId = deviceId
      }
      sendCode(mobile, this.form.deviceId).then((response) => {
        this.$modal.msgSuccess('发送成功')
        this.state = true
        const timer = setInterval(() => {
          this.stateNum--
          if (this.stateNum === 0) {
            clearInterval(timer)
            this.state = false
            this.stateNum = 60
          }
        }, 1000)
      })
    },
    // 登录
    login(mobile, code) {
      this.refresh(mobile, code, this.form.deviceId, 0)
    },
    refresh(mobile, code, deviceId, status) {
      const msg = status ? '刷新成功' : '登录成功'
      login(mobile, code, deviceId).then((response) => {
        this.$modal.msgSuccess(msg)
        this.openAdd = false
        this.getList()
      })
    },
    guid() {
      return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(
        /[xy]/g,
        function (c) {
          var r = (Math.random() * 16) | 0
          var v = c === 'x' ? r : (r & 0x3) | 0x8
          return v.toString(16)
        }
      )
    },
    /** 查询i茅台用户列表 */
    getList() {
      this.loading = true
      listUser(this.queryParams).then(response => {
        this.userList = response.rows
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
        id: null,
        mobile: null,
        userId: null,
        token: null,
        cookie: null,
        deviceId: null,
        itemCode: null,
        ishopId: null,
        provinceName: null,
        cityName: null,
        address: null,
        lat: null,
        lng: null,
        minute: null,
        shopType: null,
        randomMinute: null,
        pushPlusToken: null,
        jsonResult: null,
        remark: null,
        expireTime: null,
        createTime: null,
        createBy: null,
        updateTime: null,
        updateBy: null
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
      this.openAdd = true
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getUser(id).then((response) => {
        this.form = response.data
        this.open = true
        this.title = '修改I茅台用户'
        this.itemSelect = []
        if (
          this.form.itemCode.indexOf('@') === -1 &&
          this.form.itemCode !== ''
        ) {
          this.itemSelect.push(this.form.itemCode)
        } else {
          const arr = this.form.itemCode.split('@')
          arr.forEach((e) => {
            if (e !== '') {
              this.itemSelect.push(e)
            }
          })
        }
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateUser(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addUser(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除i茅台用户编号为"' + ids + '"的数据项？').then(function () {
        return delUser(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    }
  }
}
</script>
