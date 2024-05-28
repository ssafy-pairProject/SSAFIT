<template>
  <div class="search-container">
    <div class="input-group mb-3">
      <span class="input-group-text"><i class="bi bi-search"></i></span>
      <input
        type="text"
        class="form-control"
        placeholder="검색어 입력"
        v-model="searchTerm"
        @keyup.enter="search"
      />
      <button class="btn btn-outline-primary" @click="search">검색</button>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from "vue";

const props = defineProps({
  keyword: String,
});

const emits = defineEmits(["search"]);

const searchTerm = ref(props.keyword || "");

watch(
  () => props.keyword,
  (newKeyword) => {
    searchTerm.value = newKeyword;
  }
);

const search = () => {
  emits("search", searchTerm.value);
};
</script>

<style scoped>
.search-container {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.input-group {
  max-width: 600px;
  width: 100%;
}

.input-group-text {
  background-color: #512da8;
  color: white;
  border: none;
  border-radius: 8px 0 0 8px;
  padding: 10px 20px;
}

.form-control {
  border: none;
  border-radius: 0;
  padding: 10px 20px;
  font-size: 1rem;
}

.btn-outline-primary {
  background-color: #512da8;
  color: white;
  border: none;
  border-radius: 0 8px 8px 0;
  padding: 10px 20px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.btn-outline-primary:hover {
  background-color: #3e1e88;
  color: white;
}

@media (max-width: 768px) {
  .input-group-text,
  .form-control,
  .btn-outline-primary {
    padding: 8px 10px;
    font-size: 0.9rem;
  }
}
</style>
