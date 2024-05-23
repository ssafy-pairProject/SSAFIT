<template>
  <div class="container">
    <h2>로그아웃</h2>
    <p>정말로 로그아웃 하시겠습니까?</p>
    <button @click="doLogOut" class="btn btn-primary mt-3">예</button>
    <button @click="prev" class="btn btn-primary mt-3">아니요</button>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user";

const router = useRouter();
const store = useUserStore();

// store.userLogout();
const doLogOut = () => {
  store.userLogout();
  router.push({ name: "home" });
};

const prev = () => {
  history.back();
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
</style>
