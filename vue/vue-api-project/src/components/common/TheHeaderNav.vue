<template>
  <div id="container">
    <header>
      <nav>
        <RouterLink to="/">Home</RouterLink> |
        <RouterLink to="/youtube">Youtube</RouterLink> |
        <RouterLink :to="{ name: 'tmap' }">Tmap</RouterLink> |
        <RouterLink :to="{ name: 'gym' }">Gym</RouterLink> |
        <RouterLink v-if="!isLoggedIn" :to="{ name: 'userLogin' }"
          >Login</RouterLink
        >
        <RouterLink v-else :to="{ name: 'userLogout' }">Logout</RouterLink>
      </nav>
    </header>
  </div>
</template>

<script setup>
import { computed } from "vue";
import { useUserStore } from "@/stores/user";

const store = useUserStore();

const isLoggedIn = computed(() => store.loginUserId !== null);
</script>

<style scoped>
#container {
  display: flex;
}

header {
  position: fixed;
  top: 0;
  left: 0;
  height: 100%;
  width: 200px;
  background-color: #f8f9fa;
  padding: 10px;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
  z-index: 1000;
}

nav {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  height: 100%;
}

nav a {
  font-weight: bold;
  text-decoration: none;
  color: black;
  margin: 10px 0;
}

nav a.router-link-exact-active {
  color: skyblue;
}
</style>
