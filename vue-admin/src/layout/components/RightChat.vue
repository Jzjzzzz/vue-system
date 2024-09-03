<template>
  <div style="position: fixed;right: 10px; bottom: 50px;z-index: 1000;">
    <button class="btn-z" @click="handleBtn">
      <div class="svg-wrapper-1">
        <div class="svg-wrapper">
          <svg class="icon" height="200" p-id="10952" t="1725009156870" version="1.1"
               viewBox="0 0 1024 1024" width="200" xmlns="http://www.w3.org/2000/svg">
            <path
              d="M818.564313 1023.999795H512.355665a511.975279 511.975279 0 1 1 368.34894-156.756015v94.369542a62.283898 62.283898 0 0 1-62.242867 62.304412zM512.355665 73.677994a438.572188 438.572188 0 1 0 0 877.144376h295.27409v-98.718747a36.393819 36.393819 0 0 1 11.016619-26.115745A438.469612 438.469612 0 0 0 512.355665 73.677994z"
              p-id="10953"/>
            <path
              d="M512.355665 701.502143a234.775008 234.775008 0 0 1-234.508311-234.508311 36.537425 36.537425 0 0 1 73.074849 0 161.433462 161.433462 0 1 0 322.866924 0 36.537425 36.537425 0 0 1 73.07485 0A234.775008 234.775008 0 0 1 512.355665 701.502143z"
              p-id="10954"/>
          </svg>
        </div>
      </div>
      <span>聊天</span>
    </button>
    <el-dialog :visible.sync="open" append-to-body>
      <div class="chat-card">
        <div ref="body" class="chat-body"/>
        <el-form ref="form" :model="form">
          <div class="chat-footer">
            <el-select v-model="form.toUid" placeholder="选择私聊用户" style="padding-right: 5px">
              <el-option v-for="user in users" :key="user.id" :label="user.username" :value="user.id"/>
            </el-select>
            <el-input v-model="form.message" placeholder="输入消息"/>
            <button @click="onSubmit">发送</button>
          </div>
        </el-form>
      </div>
    </el-dialog>
  </div>
</template>
<script>

import {getLine} from '@/api/system/user'

export default {
  name: 'RightChat',
  data() {
    return {
      open: false,
      form: {
        message: '',
        toUid: ''
      },
      users: []
    }
  },
  created() {
    this.getUserList()
  },
  methods: {
    getUserList() {
      getLine().then(res => {
        this.users = res.data
      })
    },
    addMessage(data, type) {
      const body = this.$refs.body
      const messageDiv = document.createElement('div')
      messageDiv.className = type
      messageDiv.setAttribute('data-v-1a9b9037', '')
      messageDiv.innerHTML = `<p>${data}</p>`
      body.appendChild(messageDiv)
      this.scrollToBottom()
    },
    scrollToBottom() {
      const body = this.$refs.body
      body.scrollTop = body.scrollHeight
    },
    onSubmit() {
      if (this.form.toUid === '') {
        this.$modal.msgError('请选择私聊用户')
      } else {
        this.$socket.emit('chat', this.form)
        this.addMessage(this.form.message, 'message outgoing')
      }
    },
    handleBtn() {
      this.open = true
    }
  },
  sockets: {
    chat: function (data) {
      this.addMessage(data, 'message incoming')
    }
  }
}
</script>
<style scoped>
.chat-card {
  width: 600px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  overflow: hidden;
}

.chat-body {
  padding: 20px;
}

.message {
  margin-bottom: 10px;
  padding: 10px;
  border-radius: 5px;
}

.incoming {
  background-color: #e1e1e1;
}

.outgoing {
  background-color: #f2f2f2;
  text-align: right;
}

.message p {
  font-size: 14px;
  color: #333;
  margin: 0;
}

.chat-footer {
  padding: 10px;
  background-color: #f2f2f2;
  display: flex;
}

.chat-footer input[type="text"] {
  flex-grow: 1;
  padding: 5px;
  border: none;
  border-radius: 3px;
}

.chat-footer button {
  padding: 5px 10px;
  border: none;
  background-color: #4285f4;
  color: #fff;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s;
}

.chat-footer button:hover {
  background-color: #0f9d58;
}

@keyframes chatAnimation {
  0% {
    opacity: 0;
    transform: translateY(10px);
  }

  100% {
    opacity: 1;
    transform: translateY(0);
  }
}

.chat-card .message {
  animation: chatAnimation 0.3s ease-in-out;
  animation-fill-mode: both;
  animation-delay: 0.1s;
}

.chat-card .message:nth-child(even) {
  animation-delay: 0.2s;
}

.chat-card .message:nth-child(odd) {
  animation-delay: 0.3s;
}

.btn-z {
  font-family: inherit;
  background: linear-gradient(to bottom, #4dc7d9 0%, #66a6ff 100%);
  color: white;
  padding: 0.8em 1.2em;
  display: flex;
  align-items: center;
  justify-content: center;
  border: none;
  border-radius: 25px;
  box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.2);
  transition: all 0.3s;
}

.btn-z:hover {
  transform: translateY(-3px);
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.3);
}

.btn-z:active {
  transform: scale(0.95);
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.2);
}

.btn-z span {
  display: block;
  margin-left: 0.4em;
  transition: all 0.3s;
}

.btn-z svg {
  width: 20px;
  height: 20px;
  fill: white;
  transition: all 0.3s;
}

.btn-z .svg-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background-color: rgba(255, 255, 255, 0.2);
  margin-right: 0.5em;
  transition: all 0.3s;
}

.btn-z:hover .svg-wrapper {
  background-color: rgba(255, 255, 255, 0.5);
}

.btn-z:hover svg {
  transform: rotate(45deg);
}
</style>
