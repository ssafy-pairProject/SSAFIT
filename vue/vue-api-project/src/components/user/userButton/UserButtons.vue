<template>
  <div class="container" :class="{ active: isSignUpActive }">
    <div class="form-container sign-up">
      <form @submit.prevent="handleSignup">
        <h1>회원가입</h1>
        <span>짐싸의 회원이 되어 보세요!</span>
        <input
          type="text"
          id="userId"
          placeholder="아이디"
          v-model="signupUser.userId"
        />
        <input
          type="password"
          id="upassword"
          placeholder="비밀번호"
          v-model="signupUser.password"
        />
        <input
          type="email"
          id="email"
          placeholder="이메일"
          v-model="signupUser.email"
        />
        <input
          type="text"
          id="phoneNumber"
          placeholder="핸드폰 번호"
          v-model="signupUser.phoneNumber"
        />
        <input
          type="text"
          id="name"
          placeholder="이름"
          v-model="signupUser.name"
        />
        <input
          type="text" id="nickname"  placeholder="닉네임"  v-model="signupUser.nickname"
        />
        <input multiple @change="imageUpload($event)" ref="images" type="file" />
     
        <button type="submit">Sign Up</button>
      </form>
    </div>

    <div class="form-container sign-in">
      <form @submit.prevent="handleLogin">
        <h1>로그인</h1>
        <span>후회하지 않는 선택 짐싸</span>
        <input
          type="text"
          placeholder="아이디를 입력하세요"
          v-model="id"
          required
        />
        <input
          type="password"
          placeholder="비밀번호를 입력하세요"
          v-model="password"
          required
        />
        <!-- <a href="#">Forgot Your Password?</a> -->
        <button type="submit">Sign In</button>
      </form>
    </div>

    <div class="toggle-container">
      <div class="toggle">
        <div class="toggle-panel toggle-left">
          <h1>어서오세요!</h1>
          <p>내 주변 체육관 종합 플랫폼, 짐싸의 회원이 되신것을 환영합니다!</p>
          <button class="hidden" @click="setActive(false)">Sign In</button>
        </div>
        <div class="toggle-panel toggle-right">
          <h1>환영합니다!</h1>
          <p>지금 가입하고 짐싸의 다양한 기능을 이용해 보세요!</p>
          <button class="hidden" @click="setActive(true)">Sign Up</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user";

const signupUser = ref({
  userId: "",
  password: "",
  email: "",
  phoneNumber: "",
  name: "",
  nickname: "",
  img:"",
});

const id = ref("");
const password = ref("");

const store = useUserStore();
const router = useRouter();
const emit = defineEmits(["hide-buttons"]);
const isSignUpActive = ref(false);

const setActive = (isActive) => {
  isSignUpActive.value = isActive;
};

const imageUpload = (event) =>{
  console.log(event.target.files[0]);
  //pinia에 있는 변수를 바꾸기 
  // 달러 현재발생한 도큐멘트에서 발생한 change 이벤트를가져옴
  store.imgFile=event.target.files[0];
}

const handleSignup = () => {
  store.createUser(signupUser.value);
  isSignUpActive.value = false; // 회원가입 완료 후 로그인 폼으로 전환
  emit("hide-buttons");
};

const handleLogin = async () => {
  const loginSuccess = await store.userLogin(id.value, password.value);
  if (!loginSuccess) {
    alert("아이디나 비밀번호를 다시한번 확인하세요");
  } else {
    emit("hide-buttons");
    alert(`${store.currentUser.nickname} 님 환영합니다!`); // 로그인 성공 시 환영 메시지
  }
};
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
  background-color: #c9d6ff;
  background: linear-gradient(to right, #e2e2e2, #c9d6ff);
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
  width: 768px;
  max-width: 100%;
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

.container input {
  background-color: #eee;
  border: none;
  margin: 8px 0;
  padding: 10px 15px;
  font-size: 13px;
  border-radius: 8px;
  width: 100%;
  outline: none;
}

.form-container {
  position: absolute;
  top: 0;
  height: 100%;
  transition: all 0.6s ease-in-out;
}

.sign-in {
  left: 0;
  width: 50%;
  z-index: 2;
}

.container.active .sign-in {
  transform: translateX(100%);
}

.sign-up {
  left: 0;
  width: 50%;
}

.container.active .sign-up {
  transform: translateX(100%);
  opacity: 1;
  z-index: 5;
  animation: move 0.6s;
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

.social-icons {
  margin: 20px 0;
}

.social-icons a {
  border: 1px solid #ccc;
  border-radius: 20%;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  margin: 0 3px;
  width: 40px;
  height: 40px;
}

.toggle-container {
  position: absolute;
  top: 0;
  left: 50%;
  width: 50%;
  height: 100%;
  overflow: hidden;
  transition: all 0.6s ease-in-out;
  border-radius: 150px 0 0 100px;
  z-index: 1000;
}

.container.active .toggle-container {
  transform: translateX(-100%);
  border-radius: 0 150px 100px 0;
}

.toggle {
  background-color: #512da8;
  height: 100%;
  background: linear-gradient(to right, #5c6bc0, #512da8);
  color: #fff;
  position: relative;
  left: -100%;
  height: 100%;
  width: 200%;
  transform: translateX(0);
  transition: all 0.6s ease-in-out;
}

.container.active .toggle {
  transform: translateX(50%);
}

.toggle-panel {
  position: absolute;
  width: 50%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 30px;
  text-align: center;
  top: 0;
  transform: translateX(0);
  transition: all 0.6s ease-in-out;
}

.toggle-left {
  transform: translateX(-200%);
}

.container.active .toggle-left {
  transform: translateX(0);
}

.toggle-right {
  right: 0;
  transform: translateX(0);
}

.container.active .toggle-right {
  transform: translateX(200%);
}
</style>
