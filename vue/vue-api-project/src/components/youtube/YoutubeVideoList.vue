<template>
  <div class="video-list">
    <div v-if="store.videos && store.videos.length > 0">
      <YoutubeVideoListItem
        v-for="video in store.videos"
        :key="video.id.videoId"
        :video="video"
      />
    </div>
    <h4 class="text-center" v-else>검색결과가 없습니다. 😂</h4>
  </div>
</template>

<script setup>
import { useYoutubeStore } from "@/stores/youtube";
import YoutubeVideoListItem from "./YoutubeVideoListItem.vue";

const store = useYoutubeStore();
</script>

<style scoped>
.video-list {
  margin-top: 20px;
}

.text-center {
  font-size: 1.2rem;
  color: #666;
}
</style>
