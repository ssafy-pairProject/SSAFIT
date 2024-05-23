import { ref } from "vue";
import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

const REST_USER_API = `http://localhost:8080/user`;

export const useUserStore = defineStore("user", () => {
  const imgFile = ref(null);
  const isLogined = ref(false);
  const currentUser = ref(null);

  // 로그인 상태를 sessionStorage와 동기화
  const updateLoginState = () => {
    const token = sessionStorage.getItem("access-token");
    if (token) {
      isLogined.value = true;
      currentUser.value = JSON.parse(sessionStorage.getItem("currentUser"));
    } else {
      isLogined.value = false;
      currentUser.value = null;
    }
  };

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
        console.error(err);
      }
    }
  };

  const userLogin = async function (id, password) {
    try {
      const res = await axios.post(`${REST_USER_API}/login`, {
        userId: id,
        password: password,
      });
      sessionStorage.setItem("access-token", res.data["access-token"]);
      sessionStorage.setItem("currentUser", JSON.stringify(res.data.loginUser));
      updateLoginState();
      router.push({ name: "home" });
      return true;
    } catch (err) {
      console.error(err);
      return false;
    }
  };

  const userLogout = function () {
    sessionStorage.removeItem("access-token");
    sessionStorage.removeItem("currentUser");
    updateLoginState();
    router.push({ name: "home" });
  };

  const userUpdate = async function (user) {
    try {
      await axios.put(`${REST_USER_API}/update`, user);
      sessionStorage.setItem("currentUser", JSON.stringify(user));
      currentUser.value = user;
      router.push({ name: "home" });
    } catch (err) {
      console.error(err);
    }
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
    updateLoginState,
    userLogin,
    userLogout,
    isLogined,
    createUser,
    currentUser,
    userUpdate,
    imgFile,
    checkUserId,
  };
});
