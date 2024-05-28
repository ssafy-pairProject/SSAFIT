<template>
  <div class="container">
    <h4>헬스장 수정</h4>
    <form @submit.prevent="updateGym">
      <div class="mb-3">
        <label for="gymName" class="form-label">헬스장 이름</label>
        <input
          type="text"
          class="form-control"
          id="gymName"
          v-model="store.gym.gymName"
          required
        />
      </div>
      <div class="mb-3">
        <label for="gymAddress" class="form-label">주소</label>
        <input
          type="text"
          class="form-control"
          id="gymAddress"
          v-model="store.gym.gymAddress"
          required
        />
      </div>
      <div class="mb-3">
        <label for="gymPhoneNumber" class="form-label">전화번호</label>
        <input
          type="text"
          class="form-control"
          id="gymPhoneNumber"
          v-model="store.gym.gymPhoneNumber"
          required
        />
      </div>
      <div class="mb-3">
        <label for="gymPrice" class="form-label">가격</label>
        <input
          type="text"
          class="form-control"
          id="gymPrice"
          v-model="store.gym.gymPrice"
          required
        />
      </div>
      <div class="mb-3">
        <label for="gymIntroduce" class="form-label">소개</label>
        <textarea
          type="text"
          class="form-control"
          id="gymIntroduce"
          v-model="store.gym.gymIntroduce"
          required
        />
      </div>
      <div class="mb-3">
        <label for="gymOperatingHours" class="form-label">운영 시간</label>
        <input
          type="text"
          class="form-control"
          id="gymOperatingHours"
          v-model="store.gym.gymOperatingHours"
          required
        />
      </div>
      <div class="mb-3">
        <label for="gymAmenities" class="form-label">어메니티</label>
        <input
          type="text"
          class="form-control"
          id="gymAmenities"
          v-model="store.gym.gymAmenities"
          required
        />
      </div>
      <div class="mb-3">
        <label for="gymAdditionalService" class="form-label">추가 서비스</label>
        <input
          type="text"
          class="form-control"
          id="gymAdditionalService"
          v-model="store.gym.gymAdditionalService"
          required
        />
      </div>
      <div class="d-flex justify-content-center">
        <button type="submit" class="mx-3 btn btn-outline-success">
          수정 완료
        </button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useGymStore } from "@/stores/gym";

const store = useGymStore();
const route = useRoute();
const router = useRouter();

onMounted(() => {
  const gymSeq = route.params.gymSeq;
  store.getGym(gymSeq);
});

const updateGym = () => {
  store.modifyGym();
  router.push({ name: "gymList" });
};
</script>

<style scoped>
/* Add any additional styling if needed */
</style>
