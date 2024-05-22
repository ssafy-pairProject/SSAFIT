// stores/review.js
import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_REVIEW_API = `http://localhost:8080/gymreview`;

export const useReviewStore = defineStore("review", () => {
  const reviews = ref([]);
  const errorMessage = ref("");

  const fetchReviews = async (gymSeq) => {
    try {
      const response = await axios.get(`${REST_REVIEW_API}/${gymSeq}`);
      reviews.value = response.data;
    } catch (error) {
      reviews.value = [];
      errorMessage.value = "리뷰를 불러오는 데 실패했습니다.";
    }
  };

  const addReview = async (review) => {
    try {
      await axios.post(`${REST_REVIEW_API}/regist`, review);
      errorMessage.value = "";
      // 리뷰를 추가한 후 전체 리뷰를 다시 불러오기
      await fetchReviews(review.gymSeq);
    } catch (error) {
      errorMessage.value = "리뷰 등록에 실패했습니다.";
    }
  };

  const deleteReview = async (reviewSeq, gymSeq) => {
    try {
      await axios.delete(`${REST_REVIEW_API}/${reviewSeq}`);
      errorMessage.value = "";
      // 리뷰를 삭제한 후 전체 리뷰를 다시 불러오기
      await fetchReviews(gymSeq);
    } catch (error) {
      errorMessage.value = "리뷰 삭제에 실패했습니다.";
    }
  };

  return {
    reviews,
    errorMessage,
    fetchReviews,
    addReview,
    deleteReview,
  };
});
