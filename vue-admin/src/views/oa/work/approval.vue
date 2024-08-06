<template>
  <div class="app-container">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane name="pending">
        <span slot="label"><i class="el-icon-chat-dot-round"></i> 待处理</span>
      </el-tab-pane>
      <el-tab-pane name="process">
        <span slot="label"><i class="el-icon-check"></i> 已处理</span>
      </el-tab-pane>
      <el-tab-pane name="init">
        <span slot="label"><i class="el-icon-paperclip"></i> 已发起</span>
      </el-tab-pane>
    </el-tabs>

    <el-table v-loading="loading" :data="processList">
      <el-table-column type="index" label="序号" align="center"/>
      <el-table-column label="标题" align="center" prop="title"  />
      <el-table-column label="审批模板" align="center" prop="processTemplateName" />
      <el-table-column label="描述" align="center" prop="description" />
      <el-table-column label="状态" align="center" prop="status">
        <template v-slot="scope">
          <dict-tag :options="dict.type.oa_approval_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="btnShow(scope.row)"
          >
            {{activeName === 'process' || activeName === 'init' ? '查看' : '审批'}}
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-if="activeName === 'init'"
          >取消</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 审核 -->
    <el-dialog title="审核" :visible.sync="open" append-to-body>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>{{process.title}}</span>
          <span style="float: right; padding: 3px 0">{{parseTime(process.createTime)}}</span>
        </div>
        <div class="text item">
          <div class="detail-wrap">
            <div class="item" v-for="(value,key) in formValues.formShowData">
              <h5>{{ key }} : {{value}}</h5>
            </div>
          </div>
          <el-divider ></el-divider>
          <h4>流程</h4>
          <div style="float: left">
            <el-timeline :reverse="reverse">
              <el-timeline-item
                v-for="(activity) in processRecordList"
                :key="activity.id"
                :timestamp="parseTime(activity.createTime)"
                icon='el-icon-check' color='#67C23A'>
                {{activity.operateUser}} - {{activity.description}}
              </el-timeline-item>
            </el-timeline>
          </div>
          <div style="float: right">
            <img class="sh_img_tg" v-if="this.process.status ==='2'" src="@/assets/image/shtg.png">
            <img class="sh_img_bh" v-if="this.process.status ==='-1'" src="@/assets/image/bh.png">
            <div style="padding: 20px" v-if="activeName === 'pending'" >
              <el-button size="small" type="primary" @click="btnApprove(1)" >通 过</el-button>
              <el-button type="danger" size="small" @click="btnApprove(-1)">驳 回</el-button>
            </div>
          </div>
        </div>
      </el-card>
    </el-dialog>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import {show, approve, find, delProcess} from "@/api/oa/process";

export default {
  name: "Process",
  dicts: ['oa_approval_status', 'sys_oa_type'],
  data() {
    return {
      reverse: true,
      // 遮罩层
      loading: true,
      activeName: 'pending',
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      open: false,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      processList:[],
      // 总条数
      total: 0,
      // 修改人时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        status: null,
        createTime: null,
        type: 'pending'
      },
      taskId: 0,
      process: { },
      formValues: {},
      processRecordList: [],
      isApprove: false
    };
  },
  created() {
    this.isPreList('oa.process.list')
    this.getList();
  },
  methods: {
    handleClick() {
      this.queryParams.type = this.activeName;
      this.getList()
    },
    btnApprove(status){
      let approvalVo = {
        processId: this.process.id,
        taskId: this.taskId,
        status: status
      }
      approve(approvalVo).then(response => {
        this.$message.success('审批成功');
        this.open = false;
        this.getList();
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id;
      this.$modal.confirm('是否确认取消编号为"' + ids + '"的审批？').then(function() {
        return delProcess(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("取消成功");
      });
    },
    btnShow(row){
      this.open = true;
      show(row.id).then(res=>{
        this.process = res.data.process
        this.formValues = JSON.parse(this.process.formValues)
        this.processRecordList = res.data.processRecordList
        this.isApprove = res.data.isApprove
        this.taskId = row.taskId
      })
    },
    /** 查询审批类型列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' !== this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      find(this.queryParams).then(response => {
        this.processList = response.data.records;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        processCode: null,
        title: null,
        userName: null,
        userId: null,
        processType: null,
        processTemplateId: null,
        description: null,
        formValues: null,
        processInstanceId: null,
        currentAuditor: null,
        status: null,
        createTime: null,
        updateTime: null,
        isDeleted: null,
        createBy: null,
        updateBy: null
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
      this.daterangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },

  }
};
</script>
<style scoped>
.text {
  font-size: 16px;
}
.sh_img_tg{
  width: 120px;
}
.sh_img_bh{
  width: 120px;
  padding-right: 20px;
}
</style>
