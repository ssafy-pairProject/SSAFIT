<template>
  <body>
  <div class="container" :class="{ active: isSignUpActive }">
    <div class="form-container gym-registration">
      <form @submit.prevent="createGym">
        <h1>Gym Resgist</h1>
        <span>사장님 환영합니다! 짐싸에 헬스장을 등록하세요!</span>
        <input
          type="text"
          id="gymName"
          placeholder="상호명"
          v-model="gym.gymName"
        />
        <input
          type="text"
          id="gymAddress"
          placeholder="주소"
          v-model="gym.gymAddress"
        />
        <input
          type="text"
          id="gymPhoneNumber"
          placeholder="전화번호"
          v-model="gym.gymPhoneNumber"
        />
        <input
          type="text"
          id="gymPrice"
          placeholder="가격"
          v-model="gym.gymPrice"
        />
        <textarea
          id="gymIntroduce"
          placeholder="소개"
          v-model="gym.gymIntroduce"
          style="height: 100px"
        ></textarea>
        <input
          type="text"
          id="gymOperatingHours"
          placeholder="영업 시간"
          v-model="gym.gymOperatingHours"
        />
        <input
          type="text"
          id="gymAmenities"
          placeholder="편의시설"
          v-model="gym.gymAmenities"
        />
        <input
          type="text"
          id="gymAdditionalService"
          placeholder="추가 서비스"
          v-model="gym.gymAdditionalService"
        />
        <input multiple @change="imageUpload($event)" ref="images" type="file"/>
        <button type="submit">등록</button>
      </form>
    </div>
  </div>
  </body>
</template>

<script setup>

import { ref } from "vue";
import { useRouter } from "vue-router";
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

const imageUpload = (event) =>{
console.log(event.target.files[0])

store.gymImg=event.target.files[0];

}


</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap");

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Montserrat", sans-serif;
}

body {

  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  height: 100vh;
}

.container {
  background-color: #fff;
  border-radius: 30px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.35);
  position: relative;
  overflow: hidden;
  width: 1000px;
  height: 1000px;
  min-height: 480px;
}

.container p {
  font-size: 14px;
  line-height: 20px;
  letter-spacing: 0.3px;
  margin: 20px 0;
}

.container span {
  font-size: 12px;
}

.container a {
  color: #333;
  font-size: 13px;
  text-decoration: none;
  margin: 15px 0 10px;
}

.container button {
  background-color: #512da8;
  color: #fff;
  font-size: 12px;
  padding: 10px 45px;
  border: 1px solid transparent;
  border-radius: 8px;
  font-weight: 700;
  letter-spacing: 0.5px;
  text-transform: uppercase;
  margin-top: 10px;
  cursor: pointer;
}

.container button.hidden {
  background-color: transparent;
  border-color: #fff;
}

.container form {
  background-color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 40px;
  height: 100%;
}

.container input,
.container textarea {
 
  background-color: #eee;

  border: none;
  margin: 8px 0;
  padding: 10px 15px;
  font-size: 13px;
  border-radius: 8px;
  width: 100%;
  outline: none;
  resize: none; /* For textarea */
}

.form-container {
  
  position: absolute;
  top: 0;
  height: 100%;
  transition: all 0.6s ease-in-out;
}

.gym-registration {
  
  left: 0;
  width: 100%;
  z-index: 2;
}

.container.active .gym-registration {
  transform: translateX(100%);
}

@keyframes move {
  0%,
  49.99% {
    opacity: 0;
    z-index: 1;
  }
  50%,
  100% {
    opacity: 1;
    z-index: 5;
  }
}



</style>
