<template>
  <div class="navbar">
    <hamburger :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />

    <breadcrumb class="breadcrumb-container" />

    <div class="right-menu">
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <img src="../../assets/image/avg.jpg" height="80" width="80" alt="用户头像" class="user-avatar">
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <router-link to="/">
            <el-dropdown-item>
              主页
            </el-dropdown-item>
          </router-link>
          <el-dropdown-item @click.native="dialogFormVisible = true">
            <span style="display:block;">个人资料</span>
          </el-dropdown-item>
          <el-dropdown-item divided @click.native="logout">
            <span style="display:block;">退出</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <!--  dialog  -->
    <el-dialog title="个人资料" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="修改密码" name="second">
            <el-form-item label="旧密码" :label-width="formLabelWidth">
              <el-input v-model="form.oldpassword" type="password" autocomplete="off" />
            </el-form-item>
            <el-form-item label="新密码" :label-width="formLabelWidth">
              <el-input v-model="form.newpassword1" type="password" autocomplete="off" />
            </el-form-item>
            <el-form-item label="新密码" :label-width="formLabelWidth">
              <el-input v-model="form.newpassword2" type="password" autocomplete="off" />
            </el-form-item>
          </el-tab-pane>
        </el-tabs>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
    <!--  dialog  -->
  </div>

</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import { deleteImg } from '@/api/upload'
import { updateUser } from '@/api/user'

export default {
  components: {
    Breadcrumb,
    Hamburger
  },
  data() {
    return {
      activeName: 'second',
      dialogFormVisible: false,
      form: {
        oldpassword: '',
        newpassword1: '',
        newpassword2: '',
        avatar: ''
      },
      // 图片url
      imgPath: '',
      formLabelWidth: '120px',
      BASE_API: process.env.VUE_APP_BASE_API, // 接口API地址
      uploadUrl: '/api/upload/uploadImg?name=adminAvatar',
      // 文件上传类型
      imgType: ['png', 'jpg', 'jpeg']
    }
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar'
    ])
  },
  methods: {
    /** 提交按钮 */
    submitForm: function() {
      updateUser(this.form).then(response => {
        this.$modal.msgSuccess('修改成功')
        this.logout()
      })
    },
    handleClick(tab, event) {
      console.log(tab, event)
    },
    // 图片回显
    handleResponse(response) {
      if (response.code === 20000) {
        this.$modal.msgSuccess('上传文件成功')
        this.imgPath = response.data.url
        return response.data.url
      }
      return this.$modal.msgError('上传文件失败')
    },
    // 删除图片
    beforeRemove() {
      deleteImg(this.imgPath).then(response => {
        this.$modal.msgSuccess('删除成功')
      })
    },
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    }
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color:transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
