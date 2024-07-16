<template>
  <el-table :data="list" style="width: 100%;padding-top: 15px;">
    <el-table-column label="文章" min-width="200">
      <template slot-scope="scope">
        999
      </template>
    </el-table-column>
    <el-table-column label="点击数" width="195" align="center">
      <template slot-scope="scope">
        999
      </template>
    </el-table-column>
    <el-table-column label="点赞数" width="100" align="center">
      <template slot-scope="{row}">
        <el-tag :type="row.status">
          999
        </el-tag>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        success: 'success',
        pending: 'danger'
      }
      return statusMap[status]
    },
    orderNoFilter(str) {
      return str.substring(0, 30)
    }
  },
  data() {
    return {
      list: []
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {

    }
  }
}
</script>
