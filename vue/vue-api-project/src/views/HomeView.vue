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
      <!-- Insights -->
      <ul class="insights">
        <li>
          <img src="@/assets/icons/userCount.png" style="width: 70px" />
          <span class="info">
            <h3>"{{ storeStats.userCnt }}" User</h3>
            <p>With GymSSA</p>
          </span>
        </li>
        <li>
          <img src="@/assets/icons/gym.png" style="width: 70px" />
          <span class="info">
            <h3>{{ storeStats.gymCnt }}</h3>
            <p>Gyms</p>
          </span>
        </li>
        <li>
          <img src="@/assets/icons/clock.png" style="width: 70px" />
          <span class="info">
            <h3>{{ currentTime }}</h3>
          </span>
        </li>
        <li>
          <img src="@/assets/icons/cloudy.png" style="width: 70px" />
          <span class="info">
            <WeatherForecast />
          </span>
        </li>
      </ul>
      <pre>
국내 1등 피트니스 O2O 서비스 짐싸는 거주지와 활동지가 다른
현대인들에게 편하고 즐거운 운동경험을 제공하기 위해 탄생했습니다.<br>
헬스장을 발품팔아 알아보기에는 시간도 없고 불편하다는 작은 불평은 모든
실내체육시설 최저가를 100% 공개하는 다짐 정책의 근본이 되었고, 장소의
제약없이 헬스장을 다니고 싶다는 누군가의 작은 소망은 최대 3개의
운동시설을 장소와 종목에 관계없이 하나 가격에 이용할 수 있는
통합회원권의 씨앗이 되었습니다. <br>


      </pre>
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
  background: var(--primary);
  padding: 0 24px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  color: white;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.content nav a {
  color: white;
}

.content nav .profile img {
  width: 60px;
  height: 60px;
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

/* Pre Tag Style */
pre {
  background: var(--light);
  border-radius: 8px;
  padding: 20px;
  font-size: 1rem;
  line-height: 1.6;
  color: var(--dark);
  white-space: pre-wrap;
  word-wrap: break-word;
  margin-top: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
</style>
