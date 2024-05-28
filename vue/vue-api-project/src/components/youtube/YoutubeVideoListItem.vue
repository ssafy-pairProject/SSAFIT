<template>
  <div class="video-item">
    <div class="card">
      <iframe
        class="card-img-top"
        :src="videoURL"
        title="YouTube video player"
        frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
        referrerpolicy="strict-origin-when-cross-origin"
        allowfullscreen
      ></iframe>
      <div class="card-body">
        <h5 class="card-title">{{ videoTitle }}</h5>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed } from "vue";
import _ from "lodash";

const props = defineProps({
  video: {
    type: Object,
    required: true,
  },
});

const videoURL = computed(() => {
  const videoId = props.video.id.videoId;
  return `https://www.youtube.com/embed/${videoId}`;
});

const videoTitle = computed(() => {
  return _.unescape(props.video.snippet.title);
});
</script>

<style scoped>
.video-item {
  margin-bottom: 20px;
}

.card {
  border: none;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  width: 100%;
  max-width: 1200px; /* 비디오 카드의 최대 너비를 더 크게 설정 */
  margin: 0 auto; /* 비디오 카드를 중앙에 정렬 */
}

.card-img-top {
  width: 100%;
  height: 500px; /* 비디오 높이를 더 크게 설정 */
}

.card-body {
  padding: 20px;
}

.card-title {
  font-size: 1.5rem; /* 제목 글꼴 크기를 더 크게 설정 */
  font-weight: bold;
  color: #333;
  margin: 0;
}
</style>
