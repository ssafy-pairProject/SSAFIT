<template>
  <div class="review-create">
    <h3 ><i class="bi bi-pencil"></i>  리뷰 작성</h3>
    <form @submit.prevent="handleSubmit">
      <textarea
        v-model="reviewContent"
        placeholder="리뷰 내용을 입력하세요"
        class="review-textarea"
      ></textarea>
      <div class="star-rating">
        <span
          v-for="star in 5"
          :key="star"
          :class="{ filled: star <= rating }"
          @click="setRating(star)"
          class="star"
          >★</span
        >
      </div>
      <button type="submit" class="submit-button">리뷰등록</button>
    </form>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";

import { useReviewStore } from "@/stores/review";
import { useUserStore } from "@/stores/user";

import router from "@/router";

const props = defineProps(["gymSeq"]);
const emit = defineEmits(["review-added"]);
const store = useReviewStore();
const storeUser = useUserStore();

const reviewContent = ref("");
const rating = ref(1);

const currentUserSeq = computed(() => storeUser.currentUser?.userSeq);

const handleSubmit = async () => {
  if (!currentUserSeq.value) {
    alert("로그인이 필요합니다.");
    router.push({ path: "/user/login" });
    return;
  }
  await store.addReview({
    gymSeq: props.gymSeq,
    comment: reviewContent.value,
    rating: rating.value,
    userSeq: currentUserSeq.value,
  });
  emit("review-added");
  reviewContent.value = "";
  rating.value = 1;
};

const setRating = (star) => {
  rating.value = star;
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@200..900&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&family=Noto+Serif+KR:wght@200..900&display=swap');
*{
  font-family: "Noto Sans KR", sans-serif;
}

h3{
  
   letter-spacing: 2px;
   text-indent: 0.2em;
}

.review-create {
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #f9f9f9;
}

.review-textarea {
  width: 100%;
  height: 100px;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: none;
}

.star-rating {
  display: flex;
  justify-content: center;
  margin-bottom: 10px;
}

.star {
  font-size: 2rem;
  cursor: pointer;
  color: #ccc;
}

.star.filled {
  color: #ffd700;
}

.submit-button {
  display: block;
  width: 100%;
  padding: 10px;
  background-color: #6a48b9;

  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
}

.submit-button:hover {
  background-color: #512da8;
}
</style>

<!-- <template>
  <div class="container">
    <h4>게시글 작성</h4>
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="title"
        placeholder="제목"
        v-model="board.title"
      />
      <label for="title">Title</label>
    </div>
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="writer"
        placeholder="작성자"
        v-model="board.writer"
      />
      <label for="writer">Writer</label>
    </div>
    <div class="form-floating mb-3">
      <textarea
        class="form-control"
        id="content"
        placeholder="내용"
        style="height: 200px"
        v-model="board.content"
      ></textarea>
      <label for="content">Content</label>
    </div>
    <div class="d-flex justify-content-end">
      <button class="btn btn-outline-primary" @click="createBoard">등록</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useBoardStore } from "@/stores/review";

const board = ref({
  title: "",
  writer: "",
  content: "",
});

const store = useBoardStore();

const createBoard = function () {
  store.createBoard(board.value);
};
</script>

<style scoped></style> -->

<!-- <template>
  <div>
    <h3>리뷰 작성</h3>
    <form @submit.prevent="handleSubmit">
      <textarea
        v-model="reviewContent"
        placeholder="리뷰 내용을 입력하세요"
      ></textarea>
      <input
        v-model.number="rating"
        type="number"
        min="1"
        max="5"
        placeholder="평점"
      />
      <button type="submit">리뷰 등록</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useReviewStore } from "@/stores/review";

const props = defineProps(["gymSeq"]);
const emit = defineEmits(["review-added"]);
const store = useReviewStore();

const reviewContent = ref("");
const rating = ref(1);

const handleSubmit = async () => {
  await store.addReview({
    gymSeq: props.gymSeq,
    comment: reviewContent.value,
    rating: rating.value,
  });
  emit("review-added");
  reviewContent.value = "";
  rating.value = 1;
};
</script> -->
