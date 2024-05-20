<template>
  <div>
    <div class="row">
      <RouterLink
        :to="{ name: 'gymDetail', params: { gymSeq: gym.gymSeq } }"
        v-for="(gym, index) in gymList"
        :key="gym.gymSeq"
        class="card my-2 col-12"
      >
        <div class="card-body">
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
            <strong>어메니티:</strong> {{ gym.gymAmenities }}
          </p>
          <p class="card-text">
            <strong>추가 서비스:</strong> {{ gym.gymAdditionalService }}
          </p>
          <p class="card-text"><strong>조회수:</strong> {{ gym.gymviews }}</p>
          <!-- npm install --save @fortawesome/fontawesome-free -->
          <span class="heart-icon" @click="toggleLike(gym)">
            <i v-if="gym.userLiked" class="fas fa-heart"></i>
            <i v-else class="far fa-heart"></i>
          </span>
        </div>
      </RouterLink>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { useGymStore } from "@/stores/gym";

const store = useGymStore();
const gymList = computed(() => {
  return store.gymList;
});

onMounted(() => {
  store.getGymList();
});

const toggleLike = (gym) => {
  gym.userLiked = !gym.userLiked;
  if (gym.userLiked) {
    gym.userLikeCnt += 1;
  } else {
    gym.userLikeCnt -= 1;
  }
  // 서버에 업데이트를 보내는 코드 추가 가능
};
</script>

<style scoped>
.card {
  width: 100%;
  margin-bottom: 1rem;
}
.card-title {
  font-size: 1.5rem;
}
.card-text {
  margin-bottom: 0.5rem;
}

.heart-icon {
  cursor: pointer;
  color: rgb(155, 0, 0);
}

.heart-icon i {
  font-size: 20px;
}
</style>
