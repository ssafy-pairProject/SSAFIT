import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import YoutubeView from "@/views/YoutubeView.vue";
import BoardView from "@/views/BoardView.vue";
import TmapView from "@/views/TmapView.vue";
import UserView from "@/views/UserView.vue";
import GymView from "@/views/GymView.vue";

import BoardCreate from "@/components/board/BoardCreate.vue";
import BoardList from "@/components/board/BoardList.vue";
import BoardUpdate from "@/components/board/BoardUpdate.vue";
import BoardDetail from "@/components/board/BoardDetail.vue";

import UserLogin from "@/components/user/UserLogin.vue";
import UserSignup from "@/components/user/UserSignup.vue";
import UserDetail from "@/components/user/UserDetail.vue";
import UserLogout from "@/components/user/UserLogout.vue";
import UserUploadImg from "@/components/user/UserUploadImg.vue";

import GymDetail from "@/components/gym/GymDetail.vue";
import GymModify from "@/components/gym/GymModify.vue";
import GymRegist from "@/components/gym/GymRegist.vue";
import GymList from "@/components/gym/GymList.vue";
import GymReview from "@/components/gym/GymReview.vue";

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
      path: "/board",
      name: "board",
      component: BoardView,
      children: [
        {
          path: "",
          name: "boardList",
          component: BoardList,
        },
        {
          path: "create",
          name: "boardCreate",
          component: BoardCreate,
        },
        {
          path: "update",
          name: "boardUpdate",
          component: BoardUpdate,
        },
        {
          path: ":id",
          name: "boardDetail",
          component: BoardDetail,
        },
      ],
    },
    {
      path: "/tmap",
      name: "tmap",
      component: TmapView,
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
          path: "signup",
          name: "userSignup",
          component: UserSignup,
        },
        {
          path: "detail",
          name: "userDetail",
          component: UserDetail,
        },
        {
          path: "login",
          name: "userLogin",
          component: UserLogin,
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

export default router;
