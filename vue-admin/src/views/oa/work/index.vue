<template>
  <div class="app-container">
    <el-row>
      <el-col :span="24">
        <el-collapse v-model="activeNames">
          <el-collapse-item v-for="(items,key) in processType" :key="key" :name="key">
            <template v-slot:title>
              <h2>{{ key }}</h2>
            </template>
            <el-row>
              <el-col v-for="item in items"
                      v-if="item.id!=null"
                      :key="item.id"
                      :span="3">
                <div class="container" @click="handleClick(item.id)">
                  <el-image  class="img" fit="scale-down" :src="item.iconUrl"></el-image>
                  <div class="title">{{ item.name }}</div>
                </div>
              </el-col>
            </el-row>
          </el-collapse-item>
        </el-collapse>
      </el-col>
    </el-row>
    <!--  开启审批  -->
    <el-dialog :title="title" :visible.sync="open" append-to-body>
      <form-create
        :rule="rule"
        :option="option"
        @submit="onSubmit"
      ></form-create>
    </el-dialog>
  </div>
</template>
<script>
import {findProcessType} from '@/api/oa/process'
import {getTemplate} from '@/api/oa/template'
export default {
  data() {
    return {
      activeNames: [],
      processType: {},
      template: {},
      open: false,
      title: '',
      option:{},
      rule:{}
    }
  },
  created() {
    this.init();
  },
  methods: {
    onSubmit(formData) {
      //封装数据
      let formShowData = {}
      this.rule.forEach(item =>{
        for(let key in formData){
          if(key === item.field){
            formShowData[item.title] = formData[key];
          }
        }
      })
      let data = {
        formData: formData,
        formShowData: formShowData
      }
      let processFormVo = {
        "processTemplateId": this.template.id,
        "processType": this.template.processType,
        "formValues": JSON.stringify(data)
      }
    },
    init() {
      findProcessType().then(res => {
        this.processType = res.data
        this.activeNames = Object.keys(this.processType)
      })
    },
    //根据id获取审批模板详细数据
    handleClick(id) {
      getTemplate(id).then(res=>{
        this.template = res.data
        this.rule = JSON.parse(this.template.formProps);
        this.option = JSON.parse(this.template.formOptions);
        this.title = res.data.name
        this.open = true
      })
    }
  }
}
</script>
<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.img {
  width: 50px;
  border-radius: 10px;
  margin-top: 10px
}

.title {
  font-size: 14px;
  padding: 4px
}
</style>
