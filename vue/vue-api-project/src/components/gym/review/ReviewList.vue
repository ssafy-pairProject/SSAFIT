<template>
  <div class="container">
    <h4>리뷰 목록</h4>
    <table class="table table-hover text-center">
      <thead>
        <tr>
          <th>번호</th>
          <th>내용</th>
          <th>평점</th>
          <th>작성자</th>
          <th>등록일</th>
          <th>삭제</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(review, index) in currentPageReviewList"
          :key="review.reviewSeq"
        >
          <td>{{ index + 1 + (currentPage - 1) * perPage }}</td>
          <td>{{ review.comment }}</td>
          <td>{{ review.rating }}점</td>
          <td>{{ review.userSeq }}</td>
          <td>{{ review.reviewDate }}</td>
          <td><button @click="handleDelete(review.reviewSeq)">삭제</button></td>
        </tr>
      </tbody>
    </table>
    <nav aria-label="Page navigation">
      <ul class="pagination d-flex justify-content-center">
        <li class="page-item">
          <a
            class="page-link"
            @click.prevent="currentPage--"
            :class="{ disabled: currentPage <= 1 }"
            href="#"
            >&lt;</a
          >
        </li>
        <li
          class="page-item"
          :class="{ active: currentPage === page }"
          v-for="page in pageCount"
          :key="page"
        >
          <a class="page-link" href="#" @click.prevent="clickPage(page)">{{
            page
          }}</a>
        </li>
        <li class="page-item">
          <a
            class="page-link"
            @click.prevent="currentPage++"
            :class="{ disabled: currentPage >= pageCount }"
            href="#"
            >&gt;</a
          >
        </li>
      </ul>
    </nav>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import { useReviewStore } from "@/stores/review";

const props = defineProps(["gymSeq"]);
const emit = defineEmits(["review-deleted"]);
const store = useReviewStore();

const perPage = 5;
const currentPage = ref(1);

const reviews = ref([]);

const fetchReviews = async () => {
  await store.fetchReviews(props.gymSeq);
  reviews.value = store.reviews;
};

onMounted(fetchReviews);

const pageCount = computed(() => {
  return Math.ceil(reviews.value.length / perPage);
});

const clickPage = (page) => {
  currentPage.value = page;
};

const currentPageReviewList = computed(() => {
  return reviews.value.slice(
    (currentPage.value - 1) * perPage,
    currentPage.value * perPage
  );
});

const handleDelete = async (reviewSeq) => {
  await store.deleteReview(reviewSeq, props.gymSeq);
  emit("review-deleted");
  fetchReviews(); // 리뷰 삭제 후 목록 갱신
};
</script>

<style scoped>
.container {
  max-width: 800px;
  margin: 0 auto;
}

.table {
  margin-top: 20px;
}

.page-link {
  cursor: pointer;
}

.page-item.disabled .page-link {
  pointer-events: none;
  color: #6c757d;
  background-color: #fff;
  border-color: #dee2e6;
}

.page-item.active .page-link {
  z-index: 3;
  color: #fff;
  background-color: #007bff;
  border-color: #007bff;
}
</style>
