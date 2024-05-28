<template>
  <div class="container">
    <h2 class="text-center">
      <i class="bi bi-youtube" style="color: red"></i> Youtube
    </h2>
    <div class="button-group">
      <button
        v-for="(keyword, index) in randomKeywords"
        :key="index"
        class="btn btn-outline-primary"
        @click="search(keyword)"
      >
        {{ keyword }}
      </button>
    </div>
    <YoutubeVideoSearch :keyword="keyword" @search="executeSearch" />
    <hr />
    <YoutubeVideoList />
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import YoutubeVideoSearch from "@/components/youtube/YoutubeVideoSearch.vue";
import YoutubeVideoList from "@/components/youtube/YoutubeVideoList.vue";
import { useYoutubeStore } from "@/stores/youtube";

const store = useYoutubeStore();
const keyword = ref("");

// 키워드 리스트 정의
const keywords = [
  "로이더 부작용",
  "눈물 흘리면 근손실 오나요",
  "다이어트 식단",
  "치팅 데이",
  "운동자극 영상",
  "제로 치킨",
  "제로 피자",
  "가슴 운동 루틴",
  "등 운동 루틴",
  "필라테스",
  "헬스장 음악추천",
  "아무생각없이 보기좋은 영상",
];

// 랜덤 키워드 생성
const getRandomKeywords = (count) => {
  const shuffled = keywords.sort(() => 0.5 - Math.random());
  return shuffled.slice(0, count);
};

const randomKeywords = computed(() => getRandomKeywords(5));

const search = (term) => {
  keyword.value = term;
  store.youtubeSearch(term);
};

const executeSearch = (term) => {
  keyword.value = term;
  store.youtubeSearch(term);
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap");

.container {
  font-family: "Montserrat", sans-serif;
  max-width: 800px;
  margin: 20px auto;
  padding: 20px;
  background-color: #f8f9fa;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.text-center {
  font-size: 1.8rem;
  font-weight: bold;
  color: #333;
  margin-bottom: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.text-center i {
  margin-right: 10px;
}

.button-group {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 10px;
  margin-bottom: 20px;
}

.btn-outline-primary {
  background-color: #512da8;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.btn-outline-primary:hover {
  background-color: #3e1e88;
  color: white;
}

hr {
  border: none;
  height: 1px;
  background-color: #ccc;
  margin: 20px 0;
}

@media (max-width: 768px) {
  .container {
    max-width: 90%;
    padding: 10px;
  }

  .text-center {
    font-size: 1.5rem;
    margin-bottom: 10px;
  }

  .button-group {
    flex-direction: column;
    align-items: center;
  }

  hr {
    margin: 10px 0;
  }
}
</style>
