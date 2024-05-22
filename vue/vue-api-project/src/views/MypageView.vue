<template>
  <div>
    여기에 구현할 내용은 usertype을 보고 회원인지 관장인지 먼저 확인하고 나서
    자동으로 관장, 회원에 따라서 마이페이지를 다르게 구성하기
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router"; // 라우터 사용
import UserButtons from "@/components/user/userButton/UserButtons.vue";
</script>

<style scoped></style>
