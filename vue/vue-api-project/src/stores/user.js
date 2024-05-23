import { ref } from "vue";
import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

const REST_USER_API = `http://localhost:8080/user`;

export const useUserStore = defineStore("user", () => {
  const imgFile = ref(null);

  const createUser = async function (user) {
    const userToBlob = new Blob([JSON.stringify(user)], {
      type: "application/json",
    });

    var formData = new FormData();
    formData.append("user", userToBlob);
    formData.append("imgFile", imgFile.value);

    try {
      await axios.post(`${REST_USER_API}/signup`, formData, {
        headers: {
          "Content-Type": `multipart/form-data`,
        },
      });
      router.push({ name: "home" });
    } catch (err) {
      if (err.response && err.response.status === 400) {
        alert(err.response.data);
      } else {
        console.log(err);
      }
    }
  };

  const loginUserId = ref(null);
  const currentUser = ref(null);

  const userLogin = async function (id, password) {
    try {
      const res = await axios.post(`${REST_USER_API}/login`, {
        userId: id,
        password: password,
      });
      sessionStorage.setItem("access-token", res.data["access-token"]);
      loginUserId.value = id;
      currentUser.value = await axios
        .get(`${REST_USER_API}/${id}`)
        .then((response) => response.data);
      router.push({ name: "home" });
      return true;
    } catch (err) {
      console.log(err);
      return false;
    }
  };

  const userLogout = function () {
    axios
      .get(`${REST_USER_API}/logout`, {})
      .then(() => {
        sessionStorage.removeItem("access-token");
        loginUserId.value = null;
        currentUser.value = null;
        router.push({ name: "home" });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const userUpdate = function (user) {
    axios.put(`${REST_USER_API}/update`).then(() => {
      router.push({ name: "home" });
    });
  };

  const checkUserId = async function (userId) {
    try {
      const response = await axios.get(`${REST_USER_API}/${userId}`);
      if (response.status === 200 && typeof response.data === "string") {
        return response.data;
      } else {
        return "이미 존재하는 아이디입니다!";
      }
    } catch (error) {
      console.error("Error checking user ID:", error);
      return "오류 발생";
    }
  };

  return {
    userLogin,
    userLogout,
    loginUserId,
    createUser,
    currentUser,
    userUpdate,
    imgFile,
    checkUserId,
  };
});
