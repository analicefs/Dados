<template>
    <div class="text-xs-center">
      <v-dialog
        v-model="dialog"
        hide-overlay
        persistent
        width="30%">
        <v-card
          color="blue"
          dark>
          <v-card-text>
            Aguarde...
            <v-progress-linear
              indeterminate
              color="white"
              class="mb-0"
            ></v-progress-linear>
          </v-card-text>
        </v-card>
      </v-dialog>
    </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  computed: mapState({
      dialog: state => state.loading
  }),

  watch: {
    dialog(){
    }
  },
  methods: {
    ativar() {
        this.$store.commit('SET_LOADING', !this.$store.state.loading)
    }
  },
  data () {
    return {
    }
  }
}

</script>
