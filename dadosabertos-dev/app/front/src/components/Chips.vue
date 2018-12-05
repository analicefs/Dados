<template>
  <div v-if="this.$store.state.det">
      <v-chip
      v-for="chip in chips"
      :key="chip.cod"
      v-model="chip.chip">
      {{ chip.novoNome}}
    </v-chip>
    </div>
    <div v-else>
    <v-chip
      v-for="chip in chips"
      :key="chip.cod"
      v-model="chip.chip"
      @input="remove(chip)"
      atribulo="1"
      close>
      {{chip.novoNome}}
    </v-chip>
    </div>
</template>
<script>
  import { mapState } from 'vuex'
  function reduzNomeChips(nome){
    if(nome.length > 50){
      return nome.substring(0,25)+"..."+nome.substring(nome.length-25,nome.length);
    }
    return nome;
  }

  function addNovoNome(state){
    if(state.length > 1){
      for(var i=0; i<=state.length-1; i++){
        state[i].novoNome = reduzNomeChips(state[i].nome);
        state[i].chip = true;
      }
    }
    return state;
  }

  export default {
    computed: mapState({
      chips: state => (state.det == false ? addNovoNome(state.list) : addNovoNome(state.listDetalhamento))
    }),
    watch: {
      chips() {
      }
    },
    data () {
      return {
      }
    },

    methods: {
      remove (item) {
        this.$store.commit('REMOVE_ITEM', item)
      }
    }
  }
</script>
