<template>
  <div class="container">
    <h4>리뷰 상세</h4>
    <hr />
    <div class="d-flex justify-content-center">
      <div class="card" style="width: 30rem">
        <div class="card-body">
          <div class="mb-3 d-flex justify-content-between align-items-center">
            <h4 class="card-title">
              {{ review.comment }}
              <span class="badge bg-primary">{{ review.rating }}점</span>
            </h4>
            <div class="d-flex justify-content-end">
              <h6 class="card-subtitle mx-3 text-body-secondary">
                {{ review.userSeq }}
              </h6>
              <h6 class="card-subtitle text-body-secondary">
                {{ review.reviewDate }}
              </h6>
            </div>
          </div>
          <p class="card-text">
            {{ review.comment }}
          </p>
          <div class="d-flex justify-content-center">
            <button class="mx-3 btn btn-outline-danger" @click="deleteReview">
              삭제
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { ref, onMounted } from "vue";
import { useReviewStore } from "@/stores/review";
import axios from "axios";

const route = useRoute();
const router = useRouter();
const store = useReviewStore();
const review = ref({});

const fetchReview = async () => {
  await store.fetchReviews(route.params.gymSeq);
  review.value = store.reviews.find(
    (r) => r.reviewSeq === parseInt(route.params.reviewSeq)
  );
};

onMounted(fetchReview);

const deleteReview = async () => {
  await store.deleteReview(route.params.reviewSeq, route.params.gymSeq);
  router.push({ name: "gymDetail", params: { gymSeq: route.params.gymSeq } });
};
</script>

<style scoped></style>
