<template>
  <div class="content">
    <!-- Navbar -->
    <nav>
      <!-- 프로필 사진 유저 이미지 동적으로 가져오기 -->
      <a href="#" class="profile">
        <img :src="profileImage" alt="Profile Image" />
      </a>
      <h2>{{ currentUserName }}님! GymSSA 방문을 환영합니다.</h2>
    </nav>

    <!-- Main Content -->
    <main>
      <div class="header">
        <div class="left">
          <h1>Today's News</h1>
        </div>
      </div>
      <!-- C:\SSAFIT\vue\vue-api-project\src\assets\userimg\default.png -->
      <!-- Insights -->
      <ul class="insights">
        <li>
          <img src="@/assets/icons/userCount.png" style width="70px" />
          <span class="info">
            <h3>{{ storeStats.userCnt }}</h3>
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
          <img src="@/assets/icons/visitCount.png" style width="70px" />
          <span class="info">
            <h3>3,944</h3>
            <p>Site Visit</p>
          </span>
        </li>
      </ul>
      <!-- End of Insights -->

      <div class="bottom-data">
        <div class="orders">
          <div class="header">
            <i class="bx bx-receipt"></i>
            <h3>Recent Orders</h3>
            <i class="bx bx-filter"></i>
            <i class="bx bx-search"></i>
          </div>
          <table>
            <thead>
              <tr>
                <th>User</th>
                <th>Order Date</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  <img src="@/assets/person-running.png" />
                  <p>John Doe</p>
                </td>
                <td>14-08-2023</td>
                <td><span class="status completed">Completed</span></td>
              </tr>
              <tr>
                <td>
                  <img src="@/assets/person-running.png" />
                  <p>John Doe</p>
                </td>
                <td>14-08-2023</td>
                <td><span class="status pending">Pending</span></td>
              </tr>
              <tr>
                <td>
                  <img src="@/assets/person-running.png" />
                  <p>John Doe</p>
                </td>
                <td>14-08-2023</td>
                <td><span class="status process">Processing</span></td>
              </tr>
            </tbody>
          </table>
        </div>

        <!-- Reminders -->
        <div class="reminders">
          <div class="header">
            <i class="bx bx-note"></i>
            <h3>Reminders</h3>
            <i class="bx bx-filter"></i>
            <i class="bx bx-plus"></i>
          </div>
          <ul class="task-list">
            <li class="completed">
              <div class="task-title">
                <i class="bx bx-check-circle"></i>
                <p>Start Our Meeting</p>
              </div>
              <i class="bx bx-dots-vertical-rounded"></i>
            </li>
            <li class="completed">
              <div class="task-title">
                <i class="bx bx-check-circle"></i>
                <p>Analyse Our Site</p>
              </div>
              <i class="bx bx-dots-vertical-rounded"></i>
            </li>
            <li class="not-completed">
              <div class="task-title">
                <i class="bx bx-x-circle"></i>
                <p>Play Football</p>
              </div>
              <i class="bx bx-dots-vertical-rounded"></i>
            </li>
          </ul>
        </div>
        <!-- End of Reminders-->
      </div>
    </main>
  </div>
</template>

<script setup>
import { computed } from "vue";
import { useUserStore } from "@/stores/user";
import { useStatsStore } from "@/stores/stats";

const store = useUserStore();
const storeStats = useStatsStore();

storeStats.getGymCnt();
storeStats.getUserCnt();

const currentUserName = computed(() => store.currentUser?.name || "사용자");

const URL = "C:/SSAFIT/vue/vue-api-project/src/assets/userimg";
// C:/SSAFIT/vue/vue-api-project/src/assets/userimg/
// default.png를 쓰거나 사용자의 파일을 가져다가 놓거나
// 동적으로 이미지를 가져오는 함수
const getProfileImage = (img) => {
  try {
    return `${URL}/${img}`;
  } catch (e) {
    console.err(e);
    return `${URL}/default.png`;
  }
};

const profileImage = computed(() => {
  const img = store.currentUser?.img || "default.png";
  console.log(img);
  return getProfileImage(img);
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
}

.content main .insights li .info h3 {
  font-size: 1.5rem;
  color: var(--dark);
}

.content main .insights li .info p {
  font-size: 1.5rem;
  color: var(--dark-grey);
}

.content main .bottom-data {
  display: grid;
  grid-template-columns: 3fr 1.6fr;
  grid-gap: 24px;
  margin-top: 24px;
  flex-wrap: wrap;
}

.content main .bottom-data .orders,
.content main .bottom-data .reminders {
  padding: 24px;
  background: var(--light);
  border-radius: 12px;
  overflow-x: auto;
}

.content main .bottom-data .orders .header,
.content main .bottom-data .reminders .header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  grid-gap: 16px;
  margin-bottom: 16px;
}

.content main .bottom-data .orders table {
  width: 100%;
  border-collapse: collapse;
}

.content main .bottom-data .orders table thead {
  font-size: 0.9rem;
  color: var(--dark-grey);
}

.content main .bottom-data .orders table thead th {
  padding-bottom: 12px;
  text-align: left;
}

.content main .bottom-data .orders table tbody td {
  padding-top: 16px;
  text-align: left;
  color: var(--dark);
}

.content main .bottom-data .orders table tbody td img {
  width: 36px;
  height: 36px;
  border-radius: 50%;
  margin-right: 8px;
}

.content main .bottom-data .orders table tbody td .status {
  display: inline-block;
  padding: 6px 12px;
  font-size: 0.8rem;
  border-radius: 36px;
}

.content main .bottom-data .orders table tbody td .status.completed {
  background: var(--light-success);
  color: var(--success);
}

.content main .bottom-data .orders table tbody td .status.pending {
  background: var(--light-warning);
  color: var(--warning);
}

.content main .bottom-data .orders table tbody td .status.process {
  background: var(--light-danger);
  color: var(--danger);
}

.content main .bottom-data .reminders .task-list li {
  padding: 10px;
  background: var(--grey);
  border-radius: 6px;
  margin-bottom: 16px;
}

.content main .bottom-data .reminders .task-list li.completed .task-title p {
  text-decoration: line-through;
}

.content main .bottom-data .reminders .task-list li .task-title {
  display: flex;
  align-items: center;
  grid-gap: 10px;
  font-size: 0.9rem;
  color: var(--dark);
}

.content main .bottom-data .reminders .task-list li .bx-dots-vertical-rounded {
  cursor: pointer;
}
</style>
