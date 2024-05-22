import { ref, computed } from "vue";
import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

const REST_USER_API = `http://localhost:8080/user`;

export const useUserStore = defineStore("user", () => {
  const createUser = function (user) {
    console.log(user);
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
        // router.push({ name: "home" });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const loginUserId = ref(null);

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
      });
  };

  const userLogout = function () {
    axios
      .get(`${REST_USER_API}/logout`, {})
      .then((res) => {
        sessionStorage.removeItem("access-token");
        loginUserId.value = null;
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
    userUpdate,
  };
});
