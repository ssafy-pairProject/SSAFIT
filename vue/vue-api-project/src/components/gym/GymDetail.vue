<template>
  <div class="container">
    <h3 class="card-title">{{ store.gym.gymName }}</h3>
    <br />

    <p class="card-text"><strong>주소:</strong> {{ store.gym.gymAddress }}</p>
    <p class="card-text">
      <strong>전화번호:</strong> {{ store.gym.gymPhoneNumber }}
    </p>
    <p class="card-text"><strong>가격(원):</strong> {{ store.gym.gymPrice }}</p>
    <p class="card-text">
      <strong>헬스장 소개:</strong>
      <span class="gym-introduce">{{ store.gym.gymIntroduce }}</span>
    </p>
    <p class="card-text">
      <strong>영업시간:</strong> {{ store.gym.gymOperatingHours }}
    </p>
    <p class="card-text">
      <strong>부가 시설:</strong> {{ store.gym.gymAmenities }}
    </p>
    <p class="card-text">
      <strong>추가 서비스:</strong> {{ store.gym.gymAdditionalService }}
    </p>
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

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap");

.container {
  font-family: "Montserrat", sans-serif;
  padding: 20px;
  background-color: #f8f9fa;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  max-width: 800px;
  margin: 0 auto;
}

.card-title {
  font-size: 2rem;
  font-weight: bold;
  color: #512da8;
  margin-bottom: 20px;
}

.card-text {
  font-size: 1rem;
  color: #333;
  margin-bottom: 10px;
}

.gym-introduce {
  display: block;
  white-space: pre-line;
  margin-top: 10px;
}

button {
  font-weight: bold;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  transition: background-color 0.3s ease, color 0.3s ease;
}

.btn-outline-success {
  background-color: #4caf50;
  color: white;
  box-shadow: 0 4px 16px rgba(0, 79, 255, 0.3);
}

.btn-outline-success:hover {
  background-color: rgba(76, 175, 80, 0.8);
  color: white;
  box-shadow: 0 2px 4px rgba(0, 79, 255, 0.6);
}

.btn-outline-danger {
  background-color: #f44336;
  color: white;
  box-shadow: 0 4px 16px rgba(0, 79, 255, 0.3);
}

.btn-outline-danger:hover {
  background-color: rgba(244, 67, 54, 0.8);
  color: white;
  box-shadow: 0 2px 4px rgba(0, 79, 255, 0.6);
}

.d-flex {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.mx-3 {
  margin-left: 10px;
  margin-right: 10px;
}
</style>
