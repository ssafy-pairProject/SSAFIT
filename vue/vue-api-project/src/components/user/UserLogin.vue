<template>
  <div class="container">
    <h2>로그인</h2>
    <form @submit.prevent="login">
      <div class="form-group">
        <label for="id">아이디</label>
        <input type="text" id="id" v-model="id" class="form-control" required />
      </div>
      <div class="form-group">
        <label for="password">비밀번호</label>
        <input
          type="password"
          id="password"
          v-model="password"
          class="form-control"
          required
        />
      </div>
      <button type="submit" class="btn btn-primary mt-3">로그인</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";

const id = ref("");
const password = ref("");
const store = useUserStore();

const login = () => {
  store.userLogin(id.value, password.value);
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
}
.form-group {
  margin-bottom: 1rem;
}
</style>
