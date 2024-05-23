<template>
  <div class="container">
   
    <p>정말로 로그아웃 하시겠습니까?</p>
    <button @click="doLogOut" style="background-color: #512da8;" class="btn btn-primary mt-3">예</button>
    <button @click="prev" style="background-color: #512da8;" class="btn btn-primary mt-3">아니요</button>
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

  width: 300px; 
  height: 100px; 
  margin: auto; 
  padding: 20px; 
  text-align: center; 
}
</style>
