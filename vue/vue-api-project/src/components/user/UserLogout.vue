<template>
  <div class="container">
    <h2>로그아웃</h2>
    <p>안전하게 로그아웃 되었습니다.</p>
    <button @click="goHome" class="btn btn-primary mt-3">홈으로가기</button>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user";

const router = useRouter();
const store = useUserStore();

store.userLogout();

const goHome = () => {
  router.push({ name: "home" });
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
