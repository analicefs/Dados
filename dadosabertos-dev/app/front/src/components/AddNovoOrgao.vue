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
            label="Órgãos superiores"
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
            label="Órgãos subordinados"
            return-object>
          </v-autocomplete>
        </v-flex>
        <v-flex xs2 md2>
          <v-autocomplete
            v-model="nome_programa_atual"
            :items="nomes_programas"
            :loading="isLoading3"
            :search-input.sync="searchField3"
            hide-no-data
            item-text="Description"
            item-value="API"
            label="Programas"
            return-object>
          </v-autocomplete>
        </v-flex>
        <v-flex xs2 md2>
          <v-autocomplete
            v-model="nome_acao_atual"
            :items="nomes_acoes"
            :loading="isLoading4"
            :search-input.sync="searchField4"
            hide-no-data
            item-text="Description"
            item-value="API"
            label="Ações"
            return-object>
          </v-autocomplete>
        </v-flex>
        <v-flex xs2 md2>
          <v-autocomplete
            v-model="nome_elem_despesa_atual"
            :items="nomes_elem_despesa"
            :loading="isLoading5"
            :search-input.sync="searchField5"
            hide-no-data
            item-text="Description"
            item-value="API"
            label="Elemento Despesa"
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
        this.cod_orgaos_superiores = []

        let url = `${this.uri_autocomplete}consultageralautocompletenomes?retorno=ORGAO_SUPERIOR`

        await axios
          .get(url)
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
        this.cod_orgaos_subordinados = []

        let url = this.uri_autocomplete + 'consultageralautocompletenomes?';
        if(this.nome_orgao_superior_atual != null){
          url += `orgao_superior=${this.buscarCodigoPorNome(this.nome_orgao_superior_atual, this.cod_orgaos_superiores, this.nomes_orgaos_superiores)}&`
        }
        url += 'retorno=ORGAO_SUBORDINADO'

        await axios
          .get(url)
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
      },
      async searchField3 () {
        this.isLoading3 = true
        this.nomes_programas = []
        this.cod_programas = []

        let url = this.uri_autocomplete + 'consultageralautocompletenomes?';
        if(this.nome_orgao_superior_atual != null){
          url += `orgao_superior=${this.buscarCodigoPorNome(this.nome_orgao_superior_atual, this.cod_orgaos_superiores, this.nomes_orgaos_superiores)}&`
        }
        if(this.nome_orgao_subordinado_atual != null){
          url += `orgao_subordinado=${this.buscarCodigoPorNome(this.nome_orgao_subordinado_atual, this.cod_orgaos_subordinados, this.nomes_orgaos_subordinados)}&`
        }
        url += 'retorno=PROGRAMA'

        await axios
          .get(url)
          .then(response => {
            response.data.forEach(item => {
              this.nomes_programas.push(item.nome);
              this.cod_programas.push(item.cod);
            });
          })
          .catch(err => {
            console.log(err)
          })
          .finally(() => (this.isLoading3 = false))
      },
      async searchField4 () {
        this.isLoading4 = true
        this.nomes_acoes = []
        this.cod_acoes = []

        let url = this.uri_autocomplete + 'consultageralautocompletenomes?';
        if(this.nome_orgao_superior_atual != null){
          url += `orgao_superior=${this.buscarCodigoPorNome(this.nome_orgao_superior_atual, this.cod_orgaos_superiores, this.nomes_orgaos_superiores)}&`
        }
        if(this.nome_orgao_subordinado_atual != null){
          url += `orgao_subordinado=${this.buscarCodigoPorNome(this.nome_orgao_subordinado_atual, this.cod_orgaos_subordinados, this.nomes_orgaos_subordinados)}&`
        }
        if(this.nome_programa_atual != null){
          url += `programa=${this.buscarCodigoPorNome(this.nome_programa_atual, this.cod_programas, this.nomes_programas)}&`
        }
        url += 'retorno=ACAO'

        await axios
          .get(url)
          .then(response => {
            response.data.forEach(item => {
              this.nomes_acoes.push(item.nome);
              this.cod_acoes.push(item.cod);
            });
          })
          .catch(err => {
            console.log(err)
          })
          .finally(() => (this.isLoading4 = false))
      },
      async searchField5 () {
        this.isLoading5 = true
        this.nomes_elem_despesa = []
        this.cod_elem_despesa = []

        let url = this.uri_autocomplete + 'consultageralautocompletenomes?';
        if(this.nome_orgao_superior_atual != null){
          url += `orgao_superior=${this.buscarCodigoPorNome(this.nome_orgao_superior_atual, this.cod_orgaos_superiores, this.nomes_orgaos_superiores)}&`
        }
        if(this.nome_orgao_subordinado_atual != null){
          url += `orgao_subordinado=${this.buscarCodigoPorNome(this.nome_orgao_subordinado_atual, this.cod_orgaos_subordinados, this.nomes_orgaos_subordinados)}&`
        }
        if(this.nome_programa_atual != null){
          url += `programa=${this.buscarCodigoPorNome(this.nome_programa_atual, this.cod_programas, this.nomes_programas)}&`
        }
        if(this.nome_acao_atual != null){
          url += `acao=${this.buscarCodigoPorNome(this.nome_acao_atual, this.cod_acoes, this.nomes_acoes)}&`
        }
        url += 'retorno=ELEMENTO_DESPESA'

        await axios
          .get(url)
          .then(response => {
            response.data.forEach(item => {
              this.nomes_elem_despesa.push(item.nome);
              this.cod_elem_despesa.push(item.cod);
            });
          })
          .catch(err => {
            console.log(err)
          })
          .finally(() => (this.isLoading5 = false))
      },
    },
    methods: {
      buscaAvançada(cod) {
        /* let url = `${this.uri_autocomplete}consultageralautodetalhamento?`;
        {cod_orgao_superior != null ? url += `orgao_superior=${cod_orgao_superior}&` : url }
        url += `mes_inicio=3&ano_inicio=2016&mes_final=3&ano_final=2016`
        console.log(url) */
        console.log(cod)
      },
      buscarCodigoPorNome(nome, arrayCod, arrayNomes) {
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
        isLoading1: false,
        isLoading2: false,
        isLoading3: false,
        isLoading4: false,
        isLoading5: false,
        searchField1: null,
        searchField2: null,
        searchField3: null,
        searchField4: null,
        searchField5: null,
        nome_orgao_superior_atual: null,
        nomes_orgaos_superiores: [],
        cod_orgaos_superiores: [],
        nome_orgao_subordinado_atual: null,
        nomes_orgaos_subordinados: [],
        cod_orgaos_subordinados: [],
        nome_programa_atual: null,
        nomes_programas: [],
        cod_programas: [],
        nome_acao_atual: null,
        nomes_acoes: [],
        cod_acoes: [],
        nome_elem_despesa_atual: null,
        nomes_elem_despesa: [],
        cod_elem_despesa: [],
        uri_autocomplete: '/api/v1/pagamento/',
      }
    }
  }
</script>

