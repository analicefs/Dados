<template>
  <v-form>
    <v-container>
      <v-layout row wrap>
        <v-flex xs3 md3>
          <v-autocomplete
            v-model="nome_orgao_superior_atual"
            :items="nomes_orgaos_superiores"
            :loading="isLoading1"
            :search-input.sync="searchField1"
            hide-no-data
            item-text="Description"
            item-value="API"
            label="Órgão superior"
            return-object>
          </v-autocomplete>
        </v-flex>
        <v-flex xs3 md3>
          <v-autocomplete
            v-model="nome_orgao_subordinado_atual"
            :items="nomes_orgaos_subordinados"
            :loading="isLoading2"
            :search-input.sync="searchField2"
            hide-no-data
            item-text="Description"
            item-value="API"
            label="Órgão subordinado"
            return-object>
          </v-autocomplete>
        </v-flex>
      </v-layout>
    </v-container>
  </v-form>
</template>

<script>
  import axios from 'axios'
  import Store from '../store/index'

  export default {
    computed: {
      fields () {
        if (!this.model) return []

        return Object.keys(this.model).map(key => {
          return {
            key: key,
            value: this.model[key] || 'n/a'
          }
        })
      },
      items () {
        return this.entries.map(entry => {
          const Description = entry.Description.length > this.descriptionLimit
            ? entry.Description.slice(0, this.descriptionLimit) + '...'
            : entry.Description

          return Object.assign({}, entry, { Description })
        })
      }
    },
    watch: {
      async searchField1 () {
        this.isLoading1 = true
        this.nomes_orgaos_superiores = []
        //consultageralautocompletenomes?orgao_superior=25000
        await axios
          .get(`${this.uri_autocomplete}consultageralautocompletenomes`)
          .then(response => {
            response.data.forEach(item => {
              this.nomes_orgaos_superiores.push(item.nome);
              this.cod_orgaos_superiores.push(item.cod);
            });
          })
          .catch(err => {
            console.log(err)
          })
          .finally(() => (this.isLoading1 = false))
      },
      async searchField2 () {
        this.isLoading2 = true
        this.nomes_orgaos_subordinados = []
        await axios
          .get(`${this.uri_autocomplete}consultageralautocompletenomes`, {
            params: {
              orgao_superior: this.buscarCodigoPorNome(this.nome_orgao_superior_atual, this.cod_orgaos_superiores, this.nomes_orgaos_superiores)
            }
          })
          .then(response => {
            response.data.forEach(item => {
              this.nomes_orgaos_subordinados.push(item.nome);
              this.cod_orgaos_subordinados.push(item.cod);
            });
          })
          .catch(err => {
            console.log(err)
          })
          .finally(() => (this.isLoading2 = false))
      }
    },
    methods: {
      buscaAvançada(cod) {
        /* let url = `${this.uri_autocomplete}consultageraldetalhamento?`;
        {cod_orgao_superior != null ? url += `orgao_superior=${cod_orgao_superior}&` : url }
        url += `mes_inicio=3&ano_inicio=2016&mes_final=3&ano_final=2016`
        console.log(url) */
        console.log(cod)
      },
      buscarCodigoPorNome(nome, arrayCod, arrayNomes) {
        console.log(`codigo ${nome}: ` + arrayCod[arrayNomes.indexOf(nome)])
        return arrayCod[arrayNomes.indexOf(nome)]
      },
      tratamentoErro(error){
        Store.commit('SET_STATUS_ERROR', {
          "page": this.fonte.toLowerCase(),
          "state": true,
          "cod": error.response.status,
          "msg": (error.response.status in  Store.state.msg_errors ?  Store.state.msg_errors[error.response.status] :  Store.state.msg_errors["default_error"])
        })
        Store.commit('SET_LOADING', false)
      },
      //A consulta avançada considera que os vetores nome_orgaos e cod_orgaos já estão preenchidos com valores
    //Não há tratamento para o caso de elementos que não estejam em nome_orgaos e cod_orgaos
    async consultaAvancada(orgao_sup, orgao_sub, programa, acao, despesa) {
        const { mes_inicio, ano_inicio, mes_final, ano_final} = this.$store.state
        let newObj = null
        this.$store.commit('SET_LOADING', true);
        await axios
          .get(`${this.uri_autocomplete}consultageraldetalhamento`, {
            params: {
              orgao_superior: orgao_sup,
              orgao_subordinado: orgao_sub,
              programa: programa,
              acao: acao,
              elemento_despesa: despesa,
              mes_inicio: mes_inicio,
              ano_inicio: ano_inicio,
              mes_final: mes_final,
              ano_final: ano_final
            }
          })
          .then(response => {
            console.log(response.data);
            response.data.forEach(element => {
              let desc = this.nome_orgaos[this.cod_orgaos.indexOf(element.cod_programa)];
              if(desc != undefined){
                newObj = {
                'total': element.valor,
                'cod': element.cod_programa,
                'desc':  desc
                };
                this.$store.commit("ADD_LIST", newObj);
              }
            })

            Store.state.list.sort((a, b) => {
              if (a.total > b.total) {
                return -1;
              }
              if (a.total < b.total) {
                return 1;
              }
              return 0;
            });
          })
          .catch(error => {
            this.tratamentoErro(error);
          });
          this.$store.commit('SET_LOADING', false);
      },
      async adicionarObjeto(nome_orgao_superior_atual) {
        const { mes_inicio, ano_inicio, mes_final, ano_final } = this.$store.state

        let newObj = null
        await axios
          .get(`${this.uri_autocomplete}orgao_superior_autocomplete`, {
            params: {
              mes_inicio: mes_inicio,
              ano_inicio: ano_inicio,
              mes_final: mes_final,
              ano_final: ano_final,
              codigo: this.cod_orgaos_superiores[this.nomes_orgaos_superiores.indexOf(nome_orgao_superior_atual)]
            }
          })
          .then(response => {
            const {cod, total} = response.data[0]
            newObj = {
              'total': total,
              'cod': cod,
              'desc': nome_orgao_superior_atual
            };
          })
          .catch(error => {
            this.tratamentoErro(error);
          });
          this.$store.commit("ADD_LIST", newObj)
          this.$store.commit('SET_LOADING', false);
        Store.state.list.sort((a, b) => {
          if (a.total > b.total) {
            return -1;
          }
          if (a.total < b.total) {
            return 1;
          }
          return 0;
        });
      },
    },
    data () {
      return {
        descriptionLimit: 60,
        isLoading1  : false,
        isLoading2: false,
        searchField1: null,
        searchField2: null,
        nome_orgao_superior_atual: null,
        nomes_orgaos_superiores: [],
        cod_orgaos_superiores: [],
        nome_orgao_subordinado_atual: null,
        nomes_orgaos_subordinados: [],
        cod_orgaos_subordinados: [],
        uri_autocomplete: '/api/v1/servidor/',
      }
    }
  }
</script>

