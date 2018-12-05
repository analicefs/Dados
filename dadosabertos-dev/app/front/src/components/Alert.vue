<template>
  <div>
    <v-alert
      v-if="this.$store.state.errorMessage !=''"
      v-model="this.$store.state.errorMessage"
      type="error"
      title="erro"
      outline
    ><div v-text="this.$store.state.errorMessage">c</div></v-alert>
  </div>
</template>

<script>
  export default {
    data () {
      return {
      }
    }
  }
</script>
