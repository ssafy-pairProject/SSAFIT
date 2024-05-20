<template>
  <div class="container">
    <h4>헬스장을 등록합니다. GymRegist</h4>
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="title"
        placeholder="상호명"
        v-model="gym.gymName"
      />
      <label for="name">gymName</label>
    </div>
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="address"
        placeholder="주소"
        v-model="gym.gymAddress"
      />
      <label for="address">gymAddress</label>
    </div>
    <div class="form-floating mb-3">
      <!-- 전화번호 양식 확인 -->
      <input
        type="text"
        class="form-control"
        id="phoneNumber"
        placeholder="전화번호"
        v-model="gym.gymPhoneNumber"
      />
      <label for="phoneNumber">gymPhoneNumber</label>
    </div>
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="price"
        placeholder="가격"
        v-model="gym.gymPrice"
      />
      <label for="price">gymPrice</label>
    </div>
    <div class="form-floating mb-3">
      <textarea
        type="text"
        class="form-control"
        id="introduce"
        placeholder="소개"
        v-model="gym.gymIntroduce"
      />
      <label for="introduce">gymIntroduce</label>
    </div>
    <!-- <div class="form-floating mb-3">
          <input type="text" class="form-control" id="title" placeholder="제목" v-model=".title">
          <label for="title">gymImg</label>
      </div> -->
    <!-- 시간 -->
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="hours"
        placeholder="시간"
        v-model="gym.gymOperatingHours"
      />
      <label for="hours">gymOperatingHours</label>
    </div>
    <!-- checkbox로? -->
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="amenities"
        placeholder="어메니티"
        v-model="gym.gymAmenities"
      />
      <label for="amenities">gymAmenities</label>
    </div>
    <!--  -->
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="additionalService"
        placeholder="추가서비스"
        v-model="gym.gymAdditionalService"
      />
      <label for="additionalService">gymAdditionalService</label>
    </div>
    <div class="form-floating mb-3">
      <div class="d-flex justify-content-end">
        <button class="btn btn-outline-primary" @click="createGym">등록</button>
      </div>
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
  //gymImg: "",
  gymOperatingHours: "",
  gymAmenities: "",
  gymAdditionalService: "",
  //userLikeCnt: "", // int
  //gymviews: "", // int
  //gymSeq: ""// int
});

const store = useGymStore();

const createGym = function () {
  store.createGym(gym.value);
};
</script>

<style scoped></style>
