<template>
  <div class="container">
    <h4>헬스장 상세</h4>
    <h4>{{ store.gym.gymName }}</h4>
    <p><strong>주소:</strong> {{ store.gym.gymAddress }}</p>
    <!-- 나머지 헬스장 상세 정보를 표시 -->
    <div class="d-flex justify-content-center">
      <button class="mx-3 btn btn-outline-success" @click="moveModify">
        수정
      </button>
      <button class="mx-3 btn btn-outline-danger" @click="deleteGym">
        삭제
      </button>
    </div>
    <GymReview />
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useGymStore } from "@/stores/gym";
import axios from "axios";
import GymReview from "@/components/gym/GymReview.vue";

const store = useGymStore();
const route = useRoute();
const router = useRouter();

const REST_GYM_API = `http://localhost:8080/gym`;

const gym = store.getGym;

onMounted(() => {
  store.getGym(route.params.gymSeq);
});

const moveModify = function () {
  router.push({
    name: "gymModify",
    params: { gymSeq: gym.gymSeq },
  });
};

const deleteGym = function () {
  axios
    .delete(`${REST_GYM_API}/delete/${route.params.gymSeq}`)
    .then(() => {
      router.push({ name: "gymList" });
    })
    .catch((err) => {
      console.error(err);
    });
};
</script>
