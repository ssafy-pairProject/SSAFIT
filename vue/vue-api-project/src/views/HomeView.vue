<template>
  <div>
    <h2>HomeView</h2>
    <hr />
    <WeatherForecast />
    <hr />
    <DustForecast />
  </div>
</template>

<script setup>
import DustForecast from "@/components/weather/DustForecast.vue";
import WeatherForecast from "@/components/weather/WeatherForecast.vue";
</script>

<style scoped></style>
