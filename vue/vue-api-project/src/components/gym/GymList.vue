<template>
  <RouterLink to="/gym/regist" class="pathsearchbutton"> 등록</RouterLink>
  <div class="container">
    <div class="row">
      <div
        v-for="(gym, index) in gymList"
        :key="gym.gymSeq"
        class="card my-2 col-12"
      >
        <RouterLink
          :to="{ name: 'gymDetail', params: { gymSeq: gym.gymSeq } }"
          class="card-body d-flex"
        >
          <div class="card-content">
            <h5 class="card-title">{{ gym.gymName }}</h5>
            <p class="card-text"><strong>주소:</strong> {{ gym.gymAddress }}</p>
            <p class="card-text">
              <strong>전화번호:</strong> {{ gym.gymPhoneNumber }}
            </p>
            <p class="card-text">
              <strong>가격(원):</strong> {{ gym.gymPrice }}
            </p>
            <p class="card-text">
              <strong>영업시간:</strong> {{ gym.gymOperatingHours }}
            </p>
          </div>
          <div class="card-image">
            <img :src="getGymImage(gym.gymImg)" alt="Gym Image" />
          </div>
        </RouterLink>
        <span class="heart-icon" @click.prevent="toggleLike(gym)">
          <i v-if="gym.userLiked" class="fas fa-heart"></i>
          <i v-else class="far fa-heart"></i>
        </span>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { useGymStore } from "@/stores/gym";
import { useLikeStore } from "@/stores/like";
import { useUserStore } from "@/stores/user";

const gymStore = useGymStore();
const likeStore = useLikeStore();
const userStore = useUserStore();
const gymList = computed(() => gymStore.gymList);

const URL = "../src/assets/gymimg";

const getGymImage = (img) => {
  try {
    return `${URL}/${img}`;
  } catch (e) {
    console.error(e);
    return `${URL}/gym-default.png`;
  }
};

onMounted(async () => {
  await gymStore.getGymList();
  await likeStore.fetchLikes(userStore.currentUser.userSeq);
  syncLikesWithGyms();
});

const syncLikesWithGyms = () => {
  gymList.value.forEach((gym) => {
    gym.userLiked = likeStore.checkIfLiked(gym.gymSeq);
  });
};

const toggleLike = async (gym) => {
  if (gym.userLiked) {
    await likeStore.unlikeGym(userStore.currentUser.userSeq, gym.gymSeq);
  } else {
    await likeStore.likeGym(userStore.currentUser.userSeq, gym.gymSeq);
  }
  gym.userLiked = !gym.userLiked;
  gym.userLikeCnt += gym.userLiked ? 1 : -1;
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap");

body {
  margin: 0;
  padding: 0;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}
.pathsearchbutton {
  display: inline-block;
  width: 150px; /* 버튼의 너비를 조절합니다 */
  height: 50px; /* 버튼의 높이를 조절합니다 */
  margin-bottom: 20px;
  background-color: #b72929;
  border: none;
  color: white;
  box-shadow: 0 4px 16px rgba(0, 79, 255, 0.3);
  font-size: 16px;
  font-weight: bold;
  transition: 0.5s;
  text-decoration: none;
  text-align: center;
  line-height: 50px; /* 버튼의 높이에 맞춰 텍스트를 가운데 정렬합니다 */
}
.pathsearchbutton:focus {
  outline: 0;
}
.pathsearchbutton:hover {
  background-color: rgba(192, 175, 212, 0.895);
  cursor: pointer;
  box-shadow: 0 2px 4px rgba(0, 79, 255, 0.6);
}

.container {
  display: flex;
  flex-wrap: wrap;
  width: 100%;
}

.row {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.card {
  width: 100%;
  max-width: 600px;
  margin: 1rem;
  border: 1px solid #ccc;
  border-radius: 10px;
  transition: box-shadow 0.3s ease;
  text-decoration: none;
  overflow: hidden;
}

.card:hover {
  box-shadow: rgba(139, 22, 202, 0.4) 5px 5px, rgba(122, 74, 144, 0.3) 10px 10px,
    rgba(102, 21, 142, 0.28) 15px 15px, rgba(240, 46, 170, 0.1) 20px 20px,
    rgba(47, 158, 17, 0.05) 25px 25px;
}

.card-body {
  display: flex;
  justify-content: space-between;
  align-items: center;
  text-decoration: none;
  padding: 16px;
}

.card-content {
  flex: 1;
}

.card-image {
  width: 150px;
  height: 150px;
  margin-left: 20px;
}

.card-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 10px;
}

.card-title {
  font-size: 1.5rem;
  margin-bottom: 1rem;
  color: rgb(61, 65, 73);
}

.card-text {
  margin-bottom: 0.5rem;
  font-size: 1rem;
  color: rgb(61, 65, 73);
}

.heart-icon {
  cursor: pointer;
  color: rgb(155, 0, 0);
  float: right;
}

.heart-icon i {
  font-size: 20px;
}

*::-webkit-scrollbar {
  display: none;
  width: 0;
  background: transparent;
}
</style>
