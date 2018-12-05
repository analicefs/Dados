<template>
  <div class="card">
      <h1>Erro {{this.$store.state.statusError.cod}}</h1>
      <p>{{this.$store.state.statusError.msg}}</p>
      <a href="/" v-if="this.$store.state.statusError.page == 'pagamento'">Atualize a página</a>
      <a href="#/servidores" v-else> Atualize a página</a>
      <!-- <router-link to="/servidores" exact v-else>
          Atualize a página
      </router-link> -->
  </div>
</template>

<style>
  .card {
    flex: 1;
    align-items: center;
    background-color: #ccc;
    align-self: auto;
    width: 50%;
    margin: 20% 0 0 25%;
    border-radius: 8px;
    padding: 30px;
  }
</style>
