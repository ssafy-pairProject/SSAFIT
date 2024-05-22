<template>
  <div class="container">
    <h2>User View</h2>
    <UserButtons v-if="showButtons" @hide-buttons="hideButtons" />
    <router-view v-else></router-view>
  </div>
 
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router"; // 라우터 사용
import UserButtons from "@/components/user/userButton/UserButtons.vue";

const showButtons = ref(true); // 버튼 표시 여부를 제어하는 상태

const hideButtons = () => {
  showButtons.value = false;
};
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
