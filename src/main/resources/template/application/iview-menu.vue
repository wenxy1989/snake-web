<template>
  <Menu mode="horizontal" theme="primary" active-name="${app.code}-${modelList?first.code}" @on-select="selectMenu">
    <#list modelList as model>
    <MenuItem name="${app.code}-${model.code}">${model.name}</MenuItem>
    </#list>
  </Menu>
</template>
<script>
export default {
  methods: {
    selectMenu (name) {
      this.$router.push({ path: '/' + name })
    }
  }
}
</script>
