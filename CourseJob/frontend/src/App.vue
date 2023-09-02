
<template>
  <div id="app">
    <navbar />
    <router-view />
  </div>
</template>

<script>
import Navbar from './components/Navbar.vue'
export default {
  name: 'App',
  components: {
    navbar: Navbar
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  margin-top: 20px;
  /* height: 100%; */
}
</style>
