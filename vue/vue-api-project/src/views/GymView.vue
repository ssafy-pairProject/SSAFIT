<template>
  <div class="container">
    <RouterView />
  </div>
</template>

<script setup></script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap");
* {
  font-family: "Montserrat", sans-serif;
}
.my-h2 {
  display: inline-block;
}

.my-underline {
  background: linear-gradient(to top, yellow 20%, transparent 30%);
}
</style>
