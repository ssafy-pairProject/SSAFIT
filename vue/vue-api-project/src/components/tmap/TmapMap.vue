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
        <div>{{ endLon }} {{ endLat }}</div>
      </div>
    </div>

    <input type="hidden" id="startlon" v-model="startLon" />
    <input type="hidden" id="startlat" v-model="startLat" />
    <input type="hidden" id="endlon" v-model="endLon" />
    <input type="hidden" id="endlat" v-model="endLat" />
    <button class="btn btn-primary" @click="displayRoute">경로 표시</button>
    <div>
      {{ totalDistance }}km {{ totalDistance * 1000 }} m의 경로가
      표시되었습니다.
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";

let map;
const startPoint = ref("");
const endPoint = ref("");
const startLon = ref("");
const startLat = ref("");
const endLon = ref("");
const endLat = ref("");
let startMarker = null;
let endMarker = null;
let routeLayer = null;

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
// 2. 키워드 검색 함수
const searchLocation = (keyword, type) => {
  const API_KEY = import.meta.env.VITE_TMAP_API_KEY;
  const URL = `https://apis.openapi.sk.com/tmap/pois?version=1&format=json&callback=result&searchKeyword=${encodeURIComponent(
    keyword
  )}&appKey=${API_KEY}`;

  fetch(URL)
    .then((response) => response.json())
    .then((data) => {
      if (data.searchPoiInfo.pois.poi.length > 0) {
        const firstResult = data.searchPoiInfo.pois.poi[0];
        const coordinates = {
          lat: firstResult.frontLat,
          lon: firstResult.frontLon,
        };

        if (type === "start") {
          startLat.value = coordinates.lat;
          startLon.value = coordinates.lon;
          addMarker(coordinates, "start");
        } else {
          endLat.value = coordinates.lat;
          endLon.value = coordinates.lon;
          addMarker(coordinates, "end");
        }
      } else {
        alert("검색 결과가 없습니다.");
      }
    })
    .catch((error) => console.error(error));
};

// 3. 마커 추가 함수

const addMarker = (coordinates, type) => {
  const position = new Tmapv2.LatLng(coordinates.lat, coordinates.lon);

  if (type === "start") {
    if (startMarker) {
      startMarker.setMap(null);
    }
    startMarker = new Tmapv2.Marker({
      position: position,
      map: map,
      icon: "https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png", // 새로운 출발지 마커 아이콘
    });
    map.setCenter(position);
  } else {
    if (endMarker) {
      endMarker.setMap(null);
    }
    endMarker = new Tmapv2.Marker({
      position: position,
      map: map,
      icon: "https://developers.google.com/maps/documentation/javascript/examples/full/images/info-i_maps.png", // 새로운 도착지 마커 아이콘
    });
    map.setCenter(position);
  }
};

// 출발지 검색 함수
const searchStartPoint = () => {
  searchLocation(startPoint.value, "start");
};

// 도착지 검색 함수
const searchEndPoint = () => {
  searchLocation(endPoint.value, "end");
};
const totalDistance = ref(0);
// 4. 경로지 선정
const displayRoute = () => {
  if (!startLat.value || !startLon.value || !endLat.value || !endLon.value) {
    alert("출발지와 도착지를 모두 설정해주세요.");
    return;
  }

  const API_KEY = import.meta.env.VITE_TMAP_API_KEY;
  const URL = `https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&appKey=${API_KEY}`;

  const headers = new Headers({
    "Content-Type": "application/json",
  });

  const body = JSON.stringify({
    startX: startLon.value,
    startY: startLat.value,
    endX: endLon.value,
    endY: endLat.value,
    reqCoordType: "WGS84GEO",
    resCoordType: "WGS84GEO",
    startName: "출발지",
    endName: "도착지",
  });
  console.log("fetch이전");
  console.log(body);
  fetch(URL, { method: "POST", headers: headers, body: body })
    .then((response) => response.json())
    .then((data) => {
      if (routeLayer) {
        routeLayer.setMap(null);
      }
      const routePoints = data.features
        .map((feature) => {
          const { geometry } = feature;
          if (geometry.type === "LineString") {
            return geometry.coordinates.map(
              (coord) => new Tmapv2.LatLng(coord[1], coord[0])
            );
          }
        })
        .flat();
      console.log("routePoints");
      console.log(routePoints);

      // 일부 구간 undefined 이슈로 빼고 그리기
      const filteredRoutePoints = routePoints.filter(
        (point) => point !== undefined
      );
      console.log("filteredRoutePoints");
      console.log(filteredRoutePoints);
      routeLayer = new Tmapv2.Polyline({
        path: filteredRoutePoints,
        strokeColor: "#FF0000",
        strokeWeight: 6,
        map: map,
      });

      totalDistance.value =
        calculateTotalDistanceOfSegments(filteredRoutePoints).toFixed(3);
      console.log("Total distance:", totalDistance, "km");

      const bounds = new Tmapv2.LatLngBounds();
      filteredRoutePoints.forEach((point) => bounds.extend(point));
      map.fitBounds(bounds);
    })
    .catch((error) => console.error(error));
};

// 경로의 km수를 확인해주는 함수
// 모든 세그먼트의 길이를 계산하는 함수
function calculateTotalDistanceOfSegments(points) {
  let totalDistance = 0;

  for (let i = 0; i < points.length - 1; i++) {
    const start = points[i];
    const end = points[i + 1];

    const segmentDistance = calculateDistance(start, end);
    console.log(i + "번째 " + segmentDistance);
    totalDistance += segmentDistance;
  }

  return totalDistance;
}

// 두 지점 간의 거리를 계산하는 함수 (Haversine 공식 활용)
function calculateDistance(start, end) {
  const earthRadius = 6371; // 지구 반지름 (단위: km)

  const lat1 = start._lat;
  const lon1 = start._lng;
  const lat2 = end._lat;
  const lon2 = end._lng;

  const dLat = toRadians(lat2 - lat1);
  const dLon = toRadians(lon2 - lon1);

  const a =
    Math.sin(dLat / 2) * Math.sin(dLat / 2) +
    Math.cos(toRadians(lat1)) *
      Math.cos(toRadians(lat2)) *
      Math.sin(dLon / 2) *
      Math.sin(dLon / 2);

  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  const distance = earthRadius * c;

  return distance;
}

// 각도를 라디안으로 변환하는 함수
function toRadians(degrees) {
  return (degrees * Math.PI) / 180;
}
</script>

<style>
#map_div {
  width: 100%;
  height: 500px;
}
#routeCSS {
  margin: 10px 0;
}
#startCSS,
#endCSS {
  margin-bottom: 10px;
}
.text {
  width: 80%;
  padding: 5px;
  margin-right: 10px;
}
.btn-primary {
  padding: 5px 10px;
  background-color: #007bff;
  border: none;
  color: white;
  cursor: pointer;
}
.btn-primary:hover {
  background-color: #0056b3;
}
</style>
