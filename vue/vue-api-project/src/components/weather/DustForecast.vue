<template>
  <h4>미세먼지 정보</h4>
  <div>미세먼지: {{}}</div>
  <div>초미세먼지: {{}}</div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import axios from "axios";

// onMounted(() => {
//   const today = new Date();
//   let year = today.getFullYear();
//   let month = today.getMonth() + 1;
//   let day = today.getDate();
//   month = month < 10 ? "0" + month : month;
//   day = day < 10 ? "0" + day : day;
//   const todayStr = `${year}${month}${day}`;
//   const times = ["", ""];
//   console.log(import.meta.env.VITE_DUST_API_KEY);
//   axios.get(API_URL, {
//     params: {
//       ServiceKey: import.meta.env.VITE_DUST_API_KEY,
//     },
//   });
// });
</script>

<style scoped></style>
