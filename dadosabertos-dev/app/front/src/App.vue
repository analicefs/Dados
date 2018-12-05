<template>
  <v-app>

    <v-toolbar app :clipped-left="clipped" dark color="light-blue darken-1">
      <v-toolbar-title v-text="title"></v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items class="hidden-sm-and-down">

        <v-btn to="/" flat>Filtro Pagamentos </v-btn>
        <v-btn to="/servidores" flat>Filtro Servidores </v-btn>
        <v-btn icon to="/sugestao" flat><v-icon>chat</v-icon> </v-btn>
      </v-toolbar-items>
    </v-toolbar>

    <router-view></router-view>      

    <v-footer class="pa-3" :inset="false" app>
      <v-spacer></v-spacer>
      <div>Copyright &copy; {{ new Date().getFullYear() }} <a href="http://www.npi.quixada.ufc.br/" target="_blank">NPI</a> todos os direitos reservados.</div>
    </v-footer>
  </v-app>
</template>

<script>
import GastosChart from  '@/components/charts/GastosChart'
import PieChart from  '@/components/charts/PieChart'
import Chips from '@/components/Chips'
import Alert from '@/components/Alert'
import Error from '@/components/Errors/500'
import AddNovoOrgao from '@/components/AddNovoOrgao'

import axios from 'axios'

const http = axios.create()
/*axios.interceptors.response.use(
function(response) { return response;}, 
function(error) {
  // handle error
  if (error.response) {
    alert(error.response.status);  
    this.$store.commit('SET_STATUS_ERROR', {state: true})
  }
});*/

export default {
  name: 'App',
  components: {
    GastosChart, PieChart, Chips, AddNovoOrgao, Alert, Error
  },
  data () {
    return {
      clipped: true,
      drawer: true,
      fixed: false,
      title: 'Dados Abertos do Governo Federal',
      text: this.$store.state.errorMessage,
      alert: true
    }
  }
}
</script>
