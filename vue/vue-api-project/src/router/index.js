import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import YoutubeView from "@/views/YoutubeView.vue";
import TmapView from "@/views/TmapView.vue";
import UserView from "@/views/UserView.vue";
import GymView from "@/views/GymView.vue";

import UserLogout from "@/components/user/UserLogout.vue";
import UserUploadImg from "@/components/user/UserUploadImg.vue";

import GymDetail from "@/components/gym/GymDetail.vue";
import GymModify from "@/components/gym/GymModify.vue";
import GymRegist from "@/components/gym/GymRegist.vue";
import GymList from "@/components/gym/GymList.vue";
import GymReview from "@/components/gym/GymReview.vue";

import ReviewList from "@/components/gym/review/ReviewList.vue";
import ReviewCreate from "@/components/gym/review/ReviewCreate.vue";
import ReviewUpdate from "@/components/gym/review/ReviewList.vue";
import ReviewDetail from "@/components/gym/review/ReviewDetail.vue";

import UserButtons from "@/components/user/userButton/UserButtons.vue";
import MypageView from "@/views/MypageView.vue";

import { useUserStore } from "@/stores/user";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/youtube",
      name: "youtube",
      component: YoutubeView,
    },
    {
      path: "/tmap",
      name: "tmap",
      component: TmapView,
    },
    {
      path: "/mypage",
      name: "mypage",
      component: MypageView,
    },
    {
      path: "/gym",
      name: "gym",
      component: GymView,
      children: [
        {
          path: "list",
          name: "gymList",
          component: GymList,
        },
        {
          path: "regist",
          name: "gymRegist",
          component: GymRegist,
        },
        {
          path: "modify/:gymSeq",
          name: "gymModify",
          component: GymModify,
        },
        {
          path: ":gymSeq",
          name: "gymDetail",
          component: GymDetail,
          children: [
            {
              path: "",
              name: "gymReview",
              component: GymReview,
              children: [
                {
                  path: "",
                  name: "reviewList",
                  component: ReviewList,
                },
                {
                  path: "create",
                  name: "reviewCreate",
                  component: ReviewCreate,
                },
                {
                  path: "update",
                  name: "reviewUpdate",
                  component: ReviewUpdate,
                },
                {
                  path: ":reviewSeq",
                  name: "reviewDetail",
                  component: ReviewDetail,
                },
              ],
            },
          ],
        },
      ],
    },
    {
      path: "/user",
      name: "user",
      component: UserView,
      children: [
        {
          path: "login",
          name: "userLogin",
          component: UserButtons,
        },

        // 안전하게 로그아웃이 되었습니다. 띄우고 다시 돌아가기
        {
          path: "logout",
          name: "userLogout",
          component: UserLogout,
        },
        {
          path: "uploadimg/:userId",
          name: "userUploadImg",
          component: UserUploadImg,
        },
      ],
    },
  ],
});

// 네비게이션 가드 추가
router.beforeEach((to, from, next) => {
  const store = useUserStore();
  const publicPages = ["home", "userLogin", "userSignup"]; // 로그인 체크 제외 페이지
  const authRequired = !publicPages.includes(to.name);

  if (authRequired && !store.isLogined) {
    // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
    alert("로그인 후 이용 가능합니다!");
    next({ name: "userLogin" });
  } else {
    next();
  }
});

export default router;
