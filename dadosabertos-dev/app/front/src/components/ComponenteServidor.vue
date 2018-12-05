<template>
  <v-app>
     <!-- <v-navigation-drawer :clipped="clipped" v-model="drawer" enable-resize-watcher fixed app> -->
         <form-servidores></form-servidores>
     <!-- </v-navigation-drawer> -->

    <v-content v-if="this.$store.state.statusError.state == false">
      <alert></alert>
      <loading></loading>
      <gastos-chart></gastos-chart>
      <add-novo-orgao-servidor></add-novo-orgao-servidor>
    </v-content>

    <v-content v-else>
      <error></error>
    </v-content>

  </v-app>
</template>

<script>
import FormServidores from '@/components/FormServidores'
import GastosChart from  '@/components/charts/GastosChart'
import PieChart from  '@/components/charts/PieChart'
import Loading from '@/components/Loading'
import Alert from '@/components/Alert'
import Error from '@/components/Errors/500'
import AddNovoOrgaoServidor from '@/components/AddNovoOrgaoServidor'

import axios from 'axios'

const http = axios.create()

export default {
  name: 'App',
  components: {
    FormServidores, GastosChart, PieChart, Loading, AddNovoOrgaoServidor, Alert, Error
  },
  data () {
    return {
      clipped: true,
      drawer: true,
      fixed: false,
      title: 'Dados Abertos',
      text: this.$store.state.errorMessage,
      alert: true
    }
  }
}
</script>
