import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_PER_STATS_API = `http://localhost:8080/gymstatistics`;
const REST_HOME_STATS_API = `http://localhost:8080/home`;

export const useStatsStore = defineStore("stats", () => {
  // homePagerestcontroller

  const gymCnt = ref(0);
  const userCnt = ref(0);

  const getGymCnt = async function () {
    try {
      const response = await axios.get(`${REST_HOME_STATS_API}/gymCount`);
      gymCnt.value = response.data;
    } catch (error) {
      console.error("Error fetching gym count:", error);
    }
  };
  const getUserCnt = async function () {
    try {
      const response = await axios.get(`${REST_HOME_STATS_API}/userCount`);
      userCnt.value = response.data;
    } catch (error) {
      console.error("Error fetching user count:", error);
    }
  };

  //gymstaticsrestcontroller

  const gymStats = ref({});

  const selectGymInfo = async function (gymSeq) {
    try {
      const response = await axios.get(`${REST_PER_STATS_API}/${gymSeq}`);
      gymStats.value = response.data;
    } catch (error) {
      console.error("Error fetching gym stats:", error);
    }
  };

  return {
    gymCnt,
    userCnt,
    getGymCnt,
    getUserCnt,
    gymStats,
    selectGymInfo,
  };
});
