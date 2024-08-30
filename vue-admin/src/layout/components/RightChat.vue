<template>
  <div style="position: fixed;right: 10px; bottom: 50px;z-index: 1000;">
    <button @click="handleBtn">
      <div class="svg-wrapper-1">
        <div class="svg-wrapper">
          <svg t="1725009156870" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="10952" width="200" height="200"><path d="M818.564313 1023.999795H512.355665a511.975279 511.975279 0 1 1 368.34894-156.756015v94.369542a62.283898 62.283898 0 0 1-62.242867 62.304412zM512.355665 73.677994a438.572188 438.572188 0 1 0 0 877.144376h295.27409v-98.718747a36.393819 36.393819 0 0 1 11.016619-26.115745A438.469612 438.469612 0 0 0 512.355665 73.677994z" p-id="10953" /><path d="M512.355665 701.502143a234.775008 234.775008 0 0 1-234.508311-234.508311 36.537425 36.537425 0 0 1 73.074849 0 161.433462 161.433462 0 1 0 322.866924 0 36.537425 36.537425 0 0 1 73.07485 0A234.775008 234.775008 0 0 1 512.355665 701.502143z" p-id="10954" /></svg>
        </div>
      </div>
      <span>聊天</span>
    </button>
    <el-dialog title="站内通讯" :visible.sync="open" width="700px" append-to-body>
      <div>1111</div>
    </el-dialog>
  </div>
</template>
<script>

export default {
  name: 'RightChat',
  data() {
    return {
      open: false
    }
  },
  methods: {
    handleBtn() {
      this.open = true
    }
  }
}
</script>
<style scoped>
button {
  font-family: inherit;
  background: linear-gradient(to bottom, #4dc7d9 0%,#66a6ff 100%);
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

button:hover {
  transform: translateY(-3px);
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.3);
}

button:active {
  transform: scale(0.95);
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.2);
}

button span {
  display: block;
  margin-left: 0.4em;
  transition: all 0.3s;
}

button svg {
  width: 20px;
  height: 20px;
  fill: white;
  transition: all 0.3s;
}

button .svg-wrapper {
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

button:hover .svg-wrapper {
  background-color: rgba(255, 255, 255, 0.5);
}

button:hover svg {
  transform: rotate(45deg);
}
</style>
