tml 코드 복사
<template>
  <div class="container">
    <div class="row my-2">
      <div class="col-12 d-flex justify-content-end">
        <RouterLink to="/gym/regist" class="btn btn-primary"
          >헬스장 등록</RouterLink
        >
      </div>
    </div>
    <div class="row">
      <div
        v-for="(gym, index) in gymList"
        :key="gym.gymSeq"
        class="card my-2 col-12"
      >
        <RouterLink
          :to="{ name: 'gymDetail', params: { gymSeq: gym.gymSeq } }"
          class="card-body"
        >
          <h5 class="card-title">{{ gym.gymName }}</h5>
          <p class="card-text"><strong>주소:</strong> {{ gym.gymAddress }}</p>
          <p class="card-text">
            <strong>전화번호:</strong> {{ gym.gymPhoneNumber }}
          </p>
          <p class="card-text"><strong>가격(원):</strong> {{ gym.gymPrice }}</p>
          <p class="card-text">
            <strong>헬스장 소개:</strong> {{ gym.gymIntroduce }}
          </p>
          <p class="card-text">
            <strong>영업시간:</strong> {{ gym.gymOperatingHours }}
          </p>
          <p class="card-text">
            <strong>부가 시설:</strong> {{ gym.gymAmenities }}
          </p>
          <p class="card-text">
            <strong>추가 서비스:</strong> {{ gym.gymAdditionalService }}
          </p>
          <p class="card-text">
            <strong>좋아요:</strong> {{ gym.userLikedCnt }}
          </p>
          <div>
            <!-- 운동 사진 유저 이미지 동적으로 가져오기 -->
            <!-- 사진배치랑 크기 수정 부탁드려요 -->
            <a href="#" class="profile">
              <img :src="getGymImage(gym.gymImg)" alt="Gym Image" />
            </a>
          </div>
        </RouterLink>
        <!-- npm install --save @fortawesome/fontawesome-free -->
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
    console.err(e);
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
  // 서버에 업데이트를 보내는 코드 추가 가능
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

.container {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  padding: 2rem 0;
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
  /* background: linear-gradient(to right, #fff, #512da8); */
  overflow: hidden;
}

.card:hover {
  box-shadow: rgba(240, 46, 170, 0.4) 5px 5px, rgba(240, 46, 170, 0.3) 10px 10px,
    rgba(240, 46, 170, 0.2) 15px 15px, rgba(240, 46, 170, 0.1) 20px 20px,
    rgba(240, 46, 170, 0.05) 25px 25px;
}

.card-body {
  width: calc(100% - 26px);
  display: grid;
  cursor: pointer;
  font-family: "Montserrat", sans-serif;
  padding-top: 8px;
  padding-bottom: 8px;
  margin: 8px 13px;
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
