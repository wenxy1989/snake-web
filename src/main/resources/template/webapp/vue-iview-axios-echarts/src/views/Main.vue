<template>
  <div>
    <menu-view></menu-view>
    <router-view path="/" />
  </div>
</template>
<script>
import MenuView from '_c/Menu.vue'
export default {
  name: 'app',
  components: {
    MenuView
  },
  data () {
    return {
    }
  },
  methods: {
  }
}
</script>
