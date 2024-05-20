<template>
  <h2>Tmap</h2>
  <div id="map_div"></div>
  <div>
    <label for="route" class="label">경로선택</label><br />
    <div id="routeCSS">
      <div id="startCSS">
        <label for="startPoint">출발지</label><br />
        <input
          type="text"
          class="text"
          id="startPoint"
          placeholder="검색어를 입력해주세요"
          v-model="startPoint"
        />
        <button class="btn btn-primary" @click="searchStartPoint">검색</button>
      </div>
      <div>{{ startLon }} {{ startLat }}</div>
      <div id="endCSS">
        <label for="endPoint">도착지</label><br />
        <input
          type="text"
          class="text"
          id="endPoint"
          placeholder="검색어를 입력해주세요"
          v-model="endPoint"
        />
        <button class="btn btn-primary" @click="searchEndPoint">검색</button>
      </div>
    </div>

    <input type="hidden" id="startlon" v-model="startLon" />
    <input type="hidden" id="startlat" v-model="startLat" />
    <input type="hidden" id="endlon" v-model="endLon" />
    <input type="hidden" id="endlat" v-model="endLat" />
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";

let map;

const initMap = () => {
  if (window.Tmapv2) {
    console.log("Tmapv2가 이미 로드되었습니다.");
    initializeMap();
  } else {
    console.log("Tmapv2를 로드합니다.");
    const script = document.createElement("script");
    script.src = `https://apis.openapi.sk.com/tmap/jsv2?version=1&appKey=${
      import.meta.env.VITE_TMAP_API_KEY
    }`;
    script.onload = initializeMap;
    document.head.appendChild(script);
  }
};

const initializeMap = () => {
  const center = new Tmapv2.LatLng(37.566481622437934, 126.98502302169841);
  const mapOptions = {
    center: center,
    width: "50%",
    height: "300px",
    httpsMode: true,
    zoom: 15,
  };
  map = new Tmapv2.Map("map_div", mapOptions);
};
onMounted(initMap);

// 좌표 마커 띄우는 작업 시작

const startPoint = ref("");
const endPoint = ref("");
const startLon = ref("");
const startLat = ref("");
const endLon = ref("");
const endLat = ref("");

const searchPOI = (keyword, isStartPoint) => {
  if (keyword === "") {
    alert("검색하고자 하는 곳을 입력해주세요.");
    return;
  }
  console.log("here");

  const onComplete = (data) => {
    if (data.features.length > 0) {
      const { lon, lat } = data.features[0].geometry.coordinates;
      if (isStartPoint) {
        startLon.value = lon;
        startLat.value = lat;
      } else {
        endLon.value = lon;
        endLat.value = lat;
      }
    }
  };
  console.log("here2");
  const onProgress = (progress) => {
    console.log("Loading:", progress);
  };

  const onError = (error) => {
    console.error("Error:", error);
  };
  console.log("here2");
  const tData = new Tmapv2.extension.TData();
  const optionObj = {}; // 빈 객체로 기본 옵션 설정
  const params = {
    onComplete: onComplete,
    onProgress: onProgress,
    onError: onError,
  };
  tData.getPOIDataFromSearchJson(keyword, optionObj, params);
};

const searchStartPoint = () => {
  searchPOI(startPoint.value, true);
};

const searchEndPoint = () => {
  searchPOI(endPoint.value, false);
};
</script>
