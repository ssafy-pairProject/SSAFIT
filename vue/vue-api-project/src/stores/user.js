import { ref, computed } from "vue";
import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

const REST_USER_API = `http://localhost:8080/user`;

export const useUserStore = defineStore("user", () => {
  const imgFile = ref(null);


  const createUser = function (user) {

    console.log(user)
    console.log(imgFile.value)

    //user를 json형태의 blob으로 바꿈 
    const userToBlob = new Blob([JSON.stringify(user)], {
      type: 'application/json'
    })

    //formData 만듬 
    var formData = new FormData()

    //blob형태가 된 user에 이미지를 넣어주기위해
    formData.append("user", userToBlob);
    // 파일의 다양한 데이터 자체를 전달 
    // 파일 그자체
    formData.append("imgFile", imgFile.value);


    console.log(formData);
    // URL 목적지 -> 데이터 -> 헤더영역 전달
    axios.post(`${REST_USER_API}/signup`, formData,  {
      headers: {
        "Content-Type": `multipart/form-data`
      }
    })
      .then(() => {
        router.push({ name: "home" });
      })
      .catch((err) => {
        console.log(err);
      });
  
    
    // axios({
    //   url: `${REST_USER_API}/signup`,
    //   method: "POST",
    //   data: formData,
    //   headers: {
    //     "Content-Type": `multipart/form-data`
    //   }
    // })
    //   .then(() => {
    //     router.push({ name: "home" });
    //   })
    //   .catch((err) => {
    //     console.log(err);
    //   });

  };

  const loginUserId = ref(null);
  const currentUser = ref(null); // 현재 로그인한 사용자 정보

  const userLogin = async function (id, password) {
    try {
      const res = await axios.post(`${REST_USER_API}/login`, {
        userId: id,
        password: password,
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
    imgFile,
  };
});
