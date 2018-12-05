<template>
  <v-app>
<!--      <v-navigation-drawer :clipped="clipped" v-model="drawer" enable-resize-watcher fixed app> -->
         <form-pagamentos></form-pagamentos>
     <!-- </v-navigation-drawer> -->

    <v-content v-if="this.$store.state.statusError.state == false">
      <alert></alert>
      <loading></loading>
      <gastos-chart></gastos-chart>
      <add-novo-orgao></add-novo-orgao>
    </v-content>
    <v-content v-else>
      <error></error>
    </v-content>

  </v-app>
</template>

<script>
import FormPagamentos from '@/components/FormPagamentos'
import GastosChart from  '@/components/charts/GastosChart'
import PieChart from  '@/components/charts/PieChart'
import Loading from '@/components/Loading'
import Alert from '@/components/Alert'
import Error from '@/components/Errors/500'
import AddNovoOrgao from '@/components/AddNovoOrgao'

import axios from 'axios'

const http = axios.create()

export default {
  name: 'App',
  components: {
    FormPagamentos, GastosChart, PieChart, Loading, AddNovoOrgao, Alert, Error
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
