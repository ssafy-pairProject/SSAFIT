import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";
export const useLikeStore = defineStore("like", () => {
  const likes = ref([]);
  const likeCount = ref(0);

  const fetchLikes = function () {
    axios
      .get(`/gymlike/reviewlist/6`, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then((response) => {
        likes.value = response.data;
        // return likes.value; // 배열 반환
      })
      .catch((err) => {
        console.log(err);
        // return []; // 오류 발생 시 빈 배열 반환
      });
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
