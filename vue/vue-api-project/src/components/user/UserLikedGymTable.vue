<template>
  <div>
    <table>
      <thead>
        <tr>
          <th>Gym ID</th>
          <th>Gym Name</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="gym in likedGyms" :key="gym.gymSeq">
          <td>{{ gym.gymSeq }}</td>
          <td>{{ gym.name }}</td>
          <td>
            <button @click="unlikeGym(gym.gymSeq)">Unlike</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useLikeStore } from "@/stores/like";
import { useUserStore } from "@/stores/user";
import axios from "axios";

const likeStore = useLikeStore();
const userStore = useUserStore();

const likedGyms = ref([]);

const fetchLikedGymsDetails = (gymSeqs) => {
  const gyms = [];
  const promises = gymSeqs.map((seq) =>
    axios
      .get(`/gym/details/${seq}`)
      .then((response) => {
        gyms.push(response.data);
      })
      .catch((error) => {
        console.error(`Error fetching details for gymSeq ${seq}:`, error);
      })
  );
  return Promise.all(promises).then(() => gyms);
};

const unlikeGym = (gymSeq) => {
  likeStore.unlikeGym(userStore.currentUser.userSeq, gymSeq).then(() => {
    likedGyms.value = likedGyms.value.filter((gym) => gym.gymSeq !== gymSeq);
  });
};

onMounted(() => {
  likeStore.fetchLikes(userStore.currentUser.userSeq).then(() => {
    fetchLikedGymsDetails(likeStore.likes.value).then((gyms) => {
      likedGyms.value = gyms;
    });
  });
});
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
}

th,
td {
  border: 1px solid #ccc;
  padding: 8px;
  text-align: left;
}

th {
  background-color: #f4f4f4;
}
</style>
