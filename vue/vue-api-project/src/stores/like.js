import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

export const useLikeStore = defineStore("like", () => {
  const likes = ref([]);
  const likeCount = ref(0);

  const fetchLikes = async (userSeq) => {
    try {
      const response = await axios.get(`/gymlike/reviewlist/${userSeq}`);
      // 서버 응답 데이터를 로그로 출력
      console.log("Server response data:", response.data);

      // response.data가 배열인지 확인하고 배열일 경우 처리
      if (Array.isArray(response.data)) {
        likes.value = response.data.map((gym) => gym.gymSeq);
      } else {
        console.log;
        console.error("Error fetching likes: response.data is not an array");
        likes.value = [];
      }
    } catch (error) {
      console.error("Error fetching likes:", error);
    }
  };

  const checkIfLiked = (gymSeq) => {
    return likes.value.includes(gymSeq);
  };

  const likeGym = async (userSeq, gymSeq) => {
    try {
      const response = await axios.post(`/gymlike/like/${userSeq}/${gymSeq}`);
      if (response.status === 200) {
        likes.value.push(gymSeq);
        likeCount.value++;
      }
    } catch (error) {
      console.error("Error liking gym:", error);
    }
  };

  const unlikeGym = async (userSeq, gymSeq) => {
    try {
      const response = await axios.delete(
        `/gymlike/unlike/${userSeq}/${gymSeq}`
      );
      if (response.status === 200) {
        likes.value = likes.value.filter((seq) => seq !== gymSeq);
        likeCount.value--;
      }
    } catch (error) {
      console.error("Error unliking gym:", error);
    }
  };

  const fetchLikeCount = async (gymSeq) => {
    try {
      const response = await axios.get(`/gymlike/count/${gymSeq}`);
      likeCount.value = response.data;
    } catch (error) {
      console.error("Error fetching like count:", error);
    }
  };

  return {
    likes,
    likeCount,
    fetchLikes,
    checkIfLiked,
    likeGym,
    unlikeGym,
    fetchLikeCount,
  };
});
