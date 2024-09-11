<template>
  <div class="dashboard-editor-container">
    <github-corner class="github-corner" />
    <panel-group />
  </div>
</template>

<script>
import GithubCorner from '@/components/GithubCorner'
import PanelGroup from './components/PanelGroup'
import { getSevenCount } from '@/api/system/count'
export default {
  name: 'Dashboard',
  components: {
    GithubCorner,
    PanelGroup
  },
  data() {
    return {
      lineChartData1: {
        dataList: [],
        dateList: [],
        title: '访问量'
      },
      lineChartData2: {
        dataList: [],
        dateList: [],
        title: '点赞量'
      }
    }
  },
  created() {
    this.getCount()
  },
  methods: {
    getCount() {
      getSevenCount().then(res => {
        this.lineChartData1.dataList = res.data.clickDayCounts
        this.lineChartData1.dateList = res.data.dateList
        this.lineChartData2.dataList = res.data.likeDayCounts
        this.lineChartData2.dateList = res.data.dateList
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .github-corner {
    position: absolute;
    top: 0px;
    border: 0;
    right: 0;
  }

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
