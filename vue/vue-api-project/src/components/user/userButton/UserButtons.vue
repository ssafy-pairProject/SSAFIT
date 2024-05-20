<template>
  <div>
    <button @click="goToLogin" class="btn btn-primary">로그인</button>
    <button @click="goToSignup" class="btn btn-secondary">회원가입</button>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router"; // 라우터 사용

const router = useRouter(); // 라우터 인스턴스 생성
const emit = defineEmits(["hide-buttons"]);

const goToLogin = () => {
  router.push({ name: "userLogin" });
  emit("hide-buttons"); // 부모 컴포넌트에 이벤트 전달
};
const goToSignup = () => {
  router.push({ name: "userSignup" });
  emit("hide-buttons"); // 부모 컴포넌트에 이벤트 전달
};
</script>

<style scoped>
/* 버튼 스타일 */
button {
  margin: 10px;
}
</style>
