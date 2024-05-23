<template>
  <div class="container">
    <h2>User View</h2>
    {{ isLoggedIn }}
    <UserButtons v-if="!isLoggedIn" />
    <!-- <router-view v-else></router-view> -->
    <UserLogout v-if="isLoggedIn" />
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { useUserStore } from "@/stores/user";
import UserButtons from "@/components/user/userButton/UserButtons.vue";
import UserLogout from "@/components/user/UserLogout.vue";

const store = useUserStore();

const isLoggedIn = computed(() => store.isLogined);
</script>

<style scoped>
/* 컨테이너 스타일 */
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
}

/* 버튼 스타일 */
button {
  margin: 10px;
}
</style>
