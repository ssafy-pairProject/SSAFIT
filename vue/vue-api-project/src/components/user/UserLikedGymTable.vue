<template>
  <div>
    <table>
      <thead>
        <tr>
          <th>헬스장</th>
          <th>주소</th>
          <th>전화 번호</th>
          <th>가격</th>
          <th>영업 시간</th>
          <th>편의시설</th>
          <th>찜</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="gym in gyms" :key="gym.gymSeq">
          <td>{{ gym.gymName }}</td>
          <td>{{ gym.gymAddress }}</td>
          <td>{{ gym.gymPhoneNumber }}</td>
          <td>{{ gym.gymPrice }}</td>
          <td>{{ gym.gymOperatingHours }}</td>
          <td>{{ gym.gymAmenities }}</td>
          <td>
            <button
              class="btn btn-outline-danger"
              @click="removeGym(gym.gymSeq)"
            >
              취소
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

const gyms = ref([]);

const fetchGyms = () => {
  axios
    .get("http://localhost:8080/gymlike/reviewlist/6", {
      headers: {
        "access-token": sessionStorage.getItem("access-token"),
      },
    })
    .then((response) => {
      gyms.value = response.data;
    })
    .catch((err) => {
      console.log(err);
    });
};

const removeGym = (gymSeq) => {
  gyms.value = gyms.value.filter((gym) => gym.gymSeq !== gymSeq);
};

onMounted(() => {
  fetchGyms();
});
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap");

table {
  width: 100%;
  border-collapse: collapse;
  font-family: "Montserrat", sans-serif;
  margin: 20px 0;
  background-color: #f8f9fa;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  overflow: hidden;
}

th,
td {
  padding: 16px;
  text-align: left;
}

th {
  background-color: #512da8;
  color: white;
  font-weight: bold;
}

td {
  border-bottom: 1px solid #ccc;
}

tr:nth-child(even) {
  background-color: #f4f4f4;
}

button {
  font-weight: bold;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  transition: background-color 0.3s ease, color 0.3s ease;
}

.btn-outline-danger {
  background-color: #f44336;
  color: white;
  box-shadow: 0 4px 16px rgba(0, 79, 255, 0.3);
}

.btn-outline-danger:hover {
  background-color: rgba(244, 67, 54, 0.8);
  color: white;
  box-shadow: 0 2px 4px rgba(0, 79, 255, 0.6);
}
</style>
