<template>
  <v-app>
    <form-sugestoes class="card"></form-sugestoes>
  </v-app>
</template>

<script>
import FormSugestoes from '@/components/FormSugestoes'

import axios from 'axios'

const http = axios.create()

export default {
  name: 'App',
  components: {
    FormSugestoes
  },
  data () {
    return {
      title: 'Dados Abertos',
      text: this.$store.state.errorMessage,
      alert: true
    }
  }
}
</script>

<style>
  .card {
    align-self: center;
    padding-top: 80px;
  }
</style>