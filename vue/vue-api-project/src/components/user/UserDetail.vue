<template>
  <div class="user-info-form">
    <form @submit.prevent="updateUserInfo">
      <div class="form-group">
        <label for="username">Username:</label>
        <input
          v-model="user.username"
          id="username"
          type="text"
          class="form-control"
        />
      </div>
      <div class="form-group">
        <label for="email">Email:</label>
        <input
          v-model="user.email"
          id="email"
          type="email"
          class="form-control"
        />
      </div>
      <!-- Add other fields as needed -->
      <button type="submit" class="btn btn-primary">Update Info</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";

const userStore = useUserStore();
const user = ref({ ...userStore.currentUser });

const updateUserInfo = async () => {
  await userStore.userUpdate(user.value);
};
</script>

<style scoped>
.user-info-form {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #f9f9f9;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
}

.form-group input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.btn {
  display: inline-block;
  padding: 10px 20px;
  font-size: 16px;
  color: #fff;
  background-color: #007bff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  text-align: center;
}

.btn-primary {
  background-color: #007bff;
}
</style>
