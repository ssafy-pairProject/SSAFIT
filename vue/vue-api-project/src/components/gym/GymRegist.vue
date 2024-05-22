<template>
  <div class="container mt-5">
    <h4 class="mb-4 text-center">헬스장 등록</h4>
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="gymName"
        placeholder="상호명"
        v-model="gym.gymName"
      />
      <label for="gymName">상호명</label>
    </div>
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="gymAddress"
        placeholder="주소"
        v-model="gym.gymAddress"
      />
      <label for="gymAddress">주소</label>
    </div>
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="gymPhoneNumber"
        placeholder="전화번호"
        v-model="gym.gymPhoneNumber"
      />
      <label for="gymPhoneNumber">전화번호</label>
    </div>
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="gymPrice"
        placeholder="가격"
        v-model="gym.gymPrice"
      />
      <label for="gymPrice">가격</label>
    </div>
    <div class="form-floating mb-3">
      <textarea
        class="form-control"
        id="gymIntroduce"
        placeholder="소개"
        v-model="gym.gymIntroduce"
        style="height: 100px"
      ></textarea>
      <label for="gymIntroduce">소개</label>
    </div>
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="gymOperatingHours"
        placeholder="영업 시간"
        v-model="gym.gymOperatingHours"
      />
      <label for="gymOperatingHours">영업 시간</label>
    </div>
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="gymAmenities"
        placeholder="편의시설"
        v-model="gym.gymAmenities"
      />
      <label for="gymAmenities">편의시설</label>
    </div>
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="gymAdditionalService"
        placeholder="추가 서비스"
        v-model="gym.gymAdditionalService"
      />
      <label for="gymAdditionalService">추가 서비스</label>
    </div>
    <div class="d-flex justify-content-end">
      <button class="btn btn-primary" @click="createGym">등록</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useGymStore } from "@/stores/gym";

const gym = ref({
  gymName: "",
  gymAddress: "",
  gymPhoneNumber: "",
  gymPrice: "",
  gymIntroduce: "",
  gymOperatingHours: "",
  gymAmenities: "",
  gymAdditionalService: "",
});

const store = useGymStore();

const createGym = function () {
  store.createGym(gym.value);
};
</script>

<style scoped>
.container {
  max-width: 600px;
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.form-floating {
  margin-bottom: 15px;
}

textarea.form-control {
  resize: none;
}

.btn {
  padding: 10px 20px;
}
</style>
