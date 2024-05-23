import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";

const REST_GYM_API = `http://localhost:8080/gym`;

export const useGymStore = defineStore("gym", () => {
  const gymImg = ref(null);
  const createGym = function (gym) {
    const gymToBlob = new Blob([JSON.stringify(gym)], {
      type: "application/json",
    });

    var formData = new FormData();

    formData.append("gym", gymToBlob);
    formData.append("gymImg", gymImg.value);

    axios
      .post(`${REST_GYM_API}/regist`, formData, {
        headers: {
          "Content-Type": `multipart/form-data`,
        },
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

  return {
    createGym,
    gymList,
    getGymList,
    gym,
    getGym,
    modifyGym,
    gymImg,
  };
});
