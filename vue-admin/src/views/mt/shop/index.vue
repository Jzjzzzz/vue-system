<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="68px" size="small">
      <el-form-item label="店铺ID" prop="iShopId">
        <el-input
          v-model="queryParams.iShopId"
          clearable
          placeholder="请输入店铺ID"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="省市区" prop="region">
        <el-cascader
          v-model="queryParams.region"
          :options="pcaTextArr"
          size="large"
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
      <el-form-item label="地区" prop="districtName">
        <el-input
          v-model="queryParams.districtName"
          clearable
          placeholder="请输入地区"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          clearable
          placeholder="请输入名称"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公司名称" prop="tenantName">
        <el-input
          v-model="queryParams.tenantName"
          clearable
          placeholder="请输入公司名称"
          @keyup.enter.native="handleQuery"
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
          :disabled="$hasBP('mt.shop.add') === false"
          icon="el-icon-plus"
          plain
          size="mini"
          type="primary"
          @click="handleAdd"
        >获取商店列表
        </el-button>
      </el-col>
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
    </el-row>

    <el-table v-loading="loading" :data="shopList" @selection-change="handleSelectionChange">
      <el-table-column align="center" fixed label="序号" type="index" />
      <el-table-column align="center" fixed label="店铺ID" prop="ishopId" width="150px" />
      <el-table-column align="center" label="省份" prop="provinceName" />
      <el-table-column align="center" label="城市" prop="cityName" />
      <el-table-column align="center" label="地区" prop="districtName" />

      <el-table-column align="center" label="纬度" prop="lat" width="100px" />
      <el-table-column align="center" label="经度" prop="lng" width="100px" />
      <el-table-column align="center" label="名称" prop="name" width="300px" />
      <el-table-column align="center" label="地址" prop="fullAddress" width="300px" />
      <el-table-column align="center" label="公司名称" prop="tenantName" width="200px" />
      <el-table-column align="center" label="创建时间" prop="createTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
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
  </div>
</template>

<script>
import { addShop, listShop } from '@/api/mt/shop'
import { pcaTextArr } from 'element-china-area-data'

export default {
  name: 'Shop',
  data() {
    return {
      pcaTextArr,
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
      // i茅台商店表格数据
      shopList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        iShopId: null,
        provinceName: null,
        cityName: null,
        districtName: null,
        fullAddress: null,
        lat: null,
        lng: null,
        name: null,
        tenantName: null,
        region: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    }
  },
  created() {
    this.isPreList('mt.shop.list')
    this.getList()
  },
  methods: {
    /** 查询i茅台商店列表 */
    getList() {
      this.loading = true
      listShop(this.queryParams).then(response => {
        this.shopList = response.rows
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
        iShopId: null,
        provinceName: null,
        cityName: null,
        districtName: null,
        fullAddress: null,
        lat: null,
        lng: null,
        name: null,
        tenantName: null,
        createTime: null,
        updateTime: null,
        createBy: null,
        updateBy: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      const region = this.queryParams.region
      if (region != null) {
        this.queryParams.provinceName = region[0]
        this.queryParams.cityName = region[1]
        this.queryParams.districtName = region[2]
        console.log(this.queryParams)
      }
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
      this.loading = true
      addShop().then(res => {
        this.$message.success('获取成功')
        this.getList()
      })
    }
  }
}
</script>
