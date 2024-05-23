<template>
  <div class="content">
    <!-- Navbar -->
    <nav>
      <!-- 프로필 사진 유저 이미지 동적으로 가져오기 -->
      <a href="#" class="profile">
        <img :src="profileImage" alt="Profile Image" />
      </a>
      <h2>{{ currentUserName }} 님! GymSSA 방문을 환영합니다.</h2>
    </nav>

    <!-- Main Content -->
    <main>
      <div class="header">
        <div class="left">
          <h1>Info Graphic</h1>
        </div>
      </div>
      <!-- C:\SSAFIT\vue\vue-api-project\src\assets\userimg\default.png -->
      <!-- Insights -->
      <ul class="insights" >
        <li >
          <img src="@/assets/icons/userCount.png" style width="70px" />
          <span class="info">
            <h3>"{{ storeStats.userCnt }}" User</h3>
            <p>With GymSSA</p>
          </span>
        </li>
        <li>
          <img src="@/assets/icons/gym.png" style width="70px" />
          <span class="info">
            <h3>{{ storeStats.gymCnt }}</h3>
            <p>Gyms</p>
          </span>
        </li>
 
        <li>
          <img src="@/assets/icons/clock.png" style width="70px" />
          <span class="info">
            <h3>{{ currentTime }}</h3>
            
          </span>
        </li>
        <li> 
          
          <WeatherForecast />
        </li>
   
       
      </ul>
    </main>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, onUnmounted } from "vue";
import { useUserStore } from "@/stores/user";
import { useStatsStore } from "@/stores/stats";
import WeatherForecast from "@/components/weather/WeatherForecast.vue";

const store = useUserStore();
const storeStats = useStatsStore();

storeStats.getGymCnt();
storeStats.getUserCnt();

const currentUserName = computed(() => store.currentUser?.nickname || "사용자");

const URL = "./src/assets/userimg";

// default.png를 쓰거나 사용자의 파일을 가져다가 놓거나
// 동적으로 이미지를 가져오는 함수
const profileImage = computed(() => {
  const img = store.currentUser?.img || "default.png";
  return getProfileImage(img);
});

const getProfileImage = (img) => {
  try {
    return `${URL}/${img}`;
  } catch (e) {
    console.error(e);
    return `${URL}/default.png`;
  }
};

// Current time functionality
const currentTime = ref(new Date().toLocaleTimeString());

const updateCurrentTime = () => {
  currentTime.value = new Date().toLocaleTimeString();
};

onMounted(() => {
  const interval = setInterval(updateCurrentTime, 1000);
  onUnmounted(() => {
    clearInterval(interval);
  });
});
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap");

:root {
  --light: #f6f6f9;
  --primary: #1976d2;
  --light-primary: #cfe8ff;
  --grey: #eee;
  --dark-grey: #aaaaaa;
  --dark: #363949;
  --danger: #d32f2f;
  --light-danger: #fecdd3;
  --warning: #fbc02d;
  --light-warning: #fff2c6;
  --success: #388e3c;
  --light-success: #bbf7d0;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
}

.bx {
  font-size: 1.7rem;
}

a {
  text-decoration: none;
}

li {
  list-style: none;
}

html {
  overflow-x: hidden;
}

body.dark {
  --light: #181a1e;
  --grey: #25252c;
  --dark: #fbfbfb;
}

body {
  background: var(--grey);
  overflow-x: hidden;
}

.content {
  position: relative;
  width: calc(100% - 230px);
  left: 230px;
  transition: all 0.3s ease;
}

.content nav {
  height: 56px;
  background: var(--light);
  padding: 0 24px 0 0;
  display: flex;
  align-items: center;
  grid-gap: 24px;
  position: sticky;
  top: 0;
  left: 0;
  z-index: 1000;
}

.content nav::before {
  content: "";
  position: absolute;
  width: 40px;
  height: 40px;
  bottom: -40px;
  left: 0;
  border-radius: 50%;
  box-shadow: -20px -20px 0 var(--light);
}

.content nav a {
  color: var(--dark);
}

.content nav .bx.bx-menu {
  cursor: pointer;
  color: var(--dark);
}

.content nav .profile img {
  width: 90px;
  height: 90px;
  object-fit: cover;
  border-radius: 50%;
}

.content main {
  width: 100%;
  padding: 36px 24px;
  max-height: calc(100vh - 56px);
  overflow-y: auto;
}

.content main .header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  grid-gap: 16px;
  flex-wrap: wrap;
  margin-bottom: 24px;
}

.content main .header .left h1 {
  font-size: 2rem;
  font-weight: 700; 
  color: var(--dark);
}

.content main .header .left .breadcrumb {
  display: flex;
  align-items: center;
  grid-gap: 16px;
  margin-top: 8px;
}

.content main .header .left .breadcrumb a {
  font-size: 0.9rem;
  color: var(--dark-grey);
}

.content main .header .left .breadcrumb a.active {
  color: var(--primary);
}

.content main .header .report {
  display: flex;
  align-items: center;
  grid-gap: 6px;
  padding: 8px 20px;
  background: var(--primary);
  color: #fff;
  border-radius: 36px;
  font-weight: 500;
}

.content main .insights {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
  grid-gap: 24px;
}

.content main .insights li {
  padding: 24px;
  background: var(--light);
  border-radius: 12px;
  display: flex;
  align-items: center;
  grid-gap: 24px;
  border-style: solid;
  border-color: #6d23b1;
}

.content main .insights li .info h3 {
  font-size: 1.5rem;
}
.content main .insights li .info p {
  font-size: 1.5rem;
}

</style>
