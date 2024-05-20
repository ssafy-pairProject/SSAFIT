import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";

const REST_GYM_API = `http://localhost:8080/gym`;

export const useGymStore = defineStore("gym", () => {
  const createGym = function (gym) {
    axios({
      url: `${REST_GYM_API}/regist`,
      method: "POST",
      // 아래 작업하지 않아도 그냥 JSON 형태로 Content-type을 결정해서 보내버림
      // headers: {
      //   "Content-Type": "applcation/json"
      // },
      data: gym,
    })
      .then(() => {
        router.push({ name: "gymList" });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const gymList = ref([]);
  const getGymList = function () {
    axios
      .get(`${REST_GYM_API}/list`, {
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then((response) => {
        gymList.value = response.data;
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const gym = ref({});

  const getGym = function (gymSeq) {
    axios.get(`${REST_GYM_API}/${gymSeq}`).then((response) => {
      gym.value = response.data;
    });
  };

  const modifyGym = function () {
    console.log("modify");
    console.log(gym.value);
    axios
      .put(`${REST_GYM_API}/modify/${gym.value.gymSeq}`, gym.value)
      .then(() => {
        console.log("수정된 후 데이터");
        console.log(gym.value);
        router.push({ name: "gymList" });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // const searchBoardList = function (searchCondition) {
  //   axios
  //     .get(REST_BOARD_API, {
  //       params: searchCondition,
  //     })
  //     .then((res) => {
  //       boardList.value = res.data;
  //     });
  // };

  return {
    createGym,
    gymList,
    getGymList,
    gym,
    getGym,
    modifyGym,
  };
});
