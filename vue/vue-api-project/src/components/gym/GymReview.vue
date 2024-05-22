<template>
  <div>
    <h2>리뷰</h2>
    <ReviewCreate @review-added="fetchReviews" :gym-seq="gymSeq" />
    <ReviewList
      :reviews="reviews"
      @review-deleted="fetchReviews"
      :gym-seq="gymSeq"
    />
    <div v-if="errorMessage">{{ errorMessage }}</div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { useReviewStore } from "@/stores/review";
import ReviewCreate from "@/components/gym/review/ReviewCreate.vue";
import ReviewList from "@/components/gym/review/ReviewList.vue";

const route = useRoute();
const gymSeq = route.params.gymSeq;
const store = useReviewStore();
const reviews = ref([]);
const errorMessage = ref("");

const fetchReviews = async () => {
  await store.fetchReviews(gymSeq);
  reviews.value = store.reviews;
  errorMessage.value = store.errorMessage;
};

onMounted(fetchReviews);
</script>
