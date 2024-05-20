<template>
  <div class="container">
    <div class="text-center">
      <h2 class="my-h2 my-underline">GymView</h2>
    </div>

    <ul class="nav nav-tabs">
      <li class="nav-item">
        <RouterLink :class="list" class="nav-link" :to="{ name: 'gymList' }"
          >헬스장리스트</RouterLink
        >
      </li>
      <li class="nav-item">
        <RouterLink :class="regist" class="nav-link" :to="{ name: 'gymRegist' }"
          >헬스장등록</RouterLink
        >
      </li>
      <!-- <li class="nav-item">
        <RouterLink :class="`${gymSeq}`" class="nav-link" :to="{ name: 'gymDetail' }">헬스장상세</RouterLink>
      </li> -->
    </ul>
    <RouterView />
  </div>
</template>

<script setup>
import { computed } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();

const list = computed(() => {
  if (route.name === "gymList") return { active: true };
});
const regist = computed(() => {
  if (route.name === "gymRegist") return { active: true };
});
</script>

<style scoped>
.my-h2 {
  display: inline-block;
}

.my-underline {
  background: linear-gradient(to top, yellow 20%, transparent 30%);
}
</style>
