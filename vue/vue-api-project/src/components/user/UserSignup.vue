<template>
  <form @submit.prevent="createUser">
    <div>
      <label for="userId">id: </label>
      <input type="text" id="userId" v-model="user.userId" />
    </div>
    <div>
      <label for="password">password: </label>
      <input type="password" id="password" v-model="user.password" />
    </div>
    <div>
      <label for="email">email: </label>
      <input type="email" id="email" v-model="user.email" />
    </div>
    <div>
      <label for="phoneNumber">phoneNumber: </label>
      <input type="text" id="phoneNumber" v-model="user.phoneNumber" />
    </div>
    <div>
      <label for="name">name: </label>
      <input type="text" id="name" v-model="user.name" />
    </div>
    <div>
      <label for="nickname">nickname: </label>
      <input type="text" id="nickname" v-model="user.nickname" />
    </div>
    <button type="submit" class="w3-button w3-green w3-round">등록</button>
  </form>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";

const user = ref({
  userId: "",
  password: "",
  email: "",
  phoneNumber: "",
  name: "",
  nickname: "",
});

const store = useUserStore();

const createUser = function () {
  store.createUser(user.value);
};
</script>

<style scoped></style>
