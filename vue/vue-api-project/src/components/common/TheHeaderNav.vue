<template>
  <div id="container">
    <header>
      <nav>
        <RouterLink to="/"><i class="bi bi-house-fill"></i>Home</RouterLink>
        <img src="" alt="" />
        <RouterLink to="/youtube"
          ><i class="bi bi-play-btn-fill"></i>Youtube</RouterLink
        >
        <RouterLink :to="{ name: 'tmap' }"
          ><i class="bi bi-person-walking"></i>Tmap exercise</RouterLink
        >
        <RouterLink :to="{ name: 'gymList' }"
          ><i class="bi bi-list-ul"></i>GymList</RouterLink
        >
        <RouterLink :to="{ name: 'mypage' }"
          ><i class="bi bi-person-vcard"></i>MyPage</RouterLink
        >
        <!-- {{ isLoggedIn }}
        ------
        {{ store.isLogined }}<br /> -->
        <RouterLink v-if="!isLoggedIn" :to="{ name: 'userLogin' }"
          ><i class="bi bi-person"></i>Login</RouterLink
        >
        <RouterLink v-else :to="{ name: 'userLogout' }"
          ><i class="bi bi-person-fill-check"></i>Logout</RouterLink
        >
      </nav>
    </header>
  </div>
</template>

<script setup>
import { computed, onMounted } from "vue";
import { useUserStore } from "@/stores/user";

const store = useUserStore();

// 로그인 상태를 computed 속성으로 설정
const isLoggedIn = computed(() => store.isLogined);

onMounted(() => {
  store.updateLoginState();
  console.log("onmounted");
  console.log(isLoggedIn.value);
});
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap");
#container {
  font-family: "Montserrat", sans-serif;
  display: flex;
}

i {
  margin: 10px;
  font-size: 20px;
}

header {
  position: fixed;
  top: 0;
  left: 0;
  height: 100%;
  width: 200px;
  background-color: #f8f9fa;
  padding: 10px;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
  z-index: 1000;
}

nav {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  height: 100%;
}

nav a {
  font-weight: bold;
  text-decoration: none;
  color: black;
  margin: 19px 0;
}

nav a.router-link-exact-active {
  color: #512da8;
}
</style>
