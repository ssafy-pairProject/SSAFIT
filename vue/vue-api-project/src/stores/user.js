import { ref, computed } from "vue";
import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

const REST_USER_API = `http://localhost:8080/user`;

export const useUserStore = defineStore("user", () => {
  const createUser = function (user) {
    axios({
      url: `${REST_USER_API}/signup`,
      method: "POST",
      // 아래 작업하지 않아도 그냥 JSON 형태로 Content-type을 결정해서 보내버림
      // headers: {
      //   "Content-Type": "applcation/json"
      // },
      data: user,
    })
      .then(() => {
        router.push({ name: "home" });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const loginUserId = ref(null);
  const currentUser = ref(null); // 현재 로그인한 사용자 정보

<<<<<<< HEAD
  const userLogin = async function (id, password) {
    try {
      const res = await axios.post(`${REST_USER_API}/login`, {
        userId: id,
        password: password,
=======
  const userLogin = function (user) {
      axios({
        url: `${REST_USER_API}/login`,
        method: "POST",
        data:user,
  })
      .then((res) => {
        sessionStorage.setItem("access-token", res.data["access-token"]);
        const token = res.data["access-token"].split(".");
        let id = JSON.parse(atob([token[1]]))["id"];
        loginUserId.value = id;

        router.push({ name: "home" });
      })
      .catch((err) => {
        console.log(userId)
        console.log(password)
        console.log(err);
>>>>>>> main
      });
      sessionStorage.setItem("access-token", res.data["access-token"]);
      const token = res.data["access-token"].split(".");
      let userId = JSON.parse(atob(token[1]))["id"];
      loginUserId.value = userId;

      // 로그인 성공 시 사용자 정보 설정
      currentUser.value = await axios
        .get(`${REST_USER_API}/${userId}`)
        .then((response) => response.data);

      router.push({ name: "home" });
      return true; // 로그인 성공 시 true 반환
    } catch (err) {
      console.log(err);
      return false; // 로그인 실패 시 false 반환
    }
  };

  const userLogout = function () {
    axios
      .get(`${REST_USER_API}/logout`, {})
      .then((res) => {
        sessionStorage.removeItem("access-token");
        loginUserId.value = null;
        currentUser.value = null; // 로그아웃 시 사용자 정보 초기화
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

  return {
    userLogin,
    userLogout,
    loginUserId,
    createUser,
    currentUser, // currentUser 반환
    userUpdate,
  };
});
