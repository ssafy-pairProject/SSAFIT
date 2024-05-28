<template>
  <div class="mypage-container">
    <!-- <div class="user-info-section">
      <h2>User Information</h2>
      <UserDetail />
    </div> -->
    <div class="liked-gyms-section">
      <h1>찜한 헬스장</h1>
      <UserLikedGymTable />
    </div>
  </div>
</template>

<script setup>
import UserDetail from "@/components/user/UserDetail.vue";
import UserLikedGymTable from "@/components/user/UserLikedGymTable.vue";
</script>

<style scoped>
.mypage-container {
  padding: 20px;
}

.user-info-section,
.liked-gyms-section {
  margin-bottom: 20px;
}
</style>
