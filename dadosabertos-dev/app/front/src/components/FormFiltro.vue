<template>
  <v-container fluid>
    <v-layout row wrap>
      <v-flex xs12 md12>
        <h4>Filtros</h4>
        <v-radio-group v-model="fonte" column>
          <v-radio v-for="fonte in items_fontes" :key="fonte.Code" primary :label="fonte.Name" :value="fonte.Code"></v-radio>
        </v-radio-group>
        <v-select v-model="nome_orgao" label="Nome do Orgão" :items="nomes_orgaos" @input.native="loadNames" autocomplete></v-select>
        <v-select v-model="top" :items="items_top" item-value="items_top" box label="Top"></v-select>
        <v-select v-model="nivel_orgao" :items="items_nivel_orgao" item-text="Name" item-value="Code" box label="Nível Orgão"></v-select>
        <v-select v-model="gastos_por" :items="items_gastos_por" item-text="Name" item-value="Code" box label="Gastos Por"></v-select>
      </v-flex>
      <v-flex xs6 md6 class="pa-1">
        <v-select v-model="mes_inicio" :items="items_mes" item-text="Name" item-value="Code" box label="Mês Inicial"></v-select>
      </v-flex>
      <v-flex xs6 md6 class="pa-1">
        <v-select v-model="ano_inicio" :items="items_ano" box label="Ano Inicial"></v-select>
      </v-flex>
      <v-flex xs6 md6 class="pa-1">
        <v-select v-model="mes_fim" :items="items_mes" item-text="Name" item-value="Code" box label="Mês Final"></v-select>
      </v-flex>
      <v-flex xs6 md6 class="pa-1">
        <v-select v-model="ano_fim" :items="items_ano" box label="Ano Final"></v-select>
      </v-flex>
      <v-btn @click="analisar" block color="success" dark>Analisar <v-icon right dark>show_chart</v-icon></v-btn>
    </v-layout>
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  name: 'FormFiltro',
  methods: {
    loadNames() {
      if(this.nivel_orgao == "ORGAO_SUPERIOR"){
        this.nomes_orgaos = [];
        axios.get('/api/v1/orgao_superior/get_all').then(
          response => {
            response.data.forEach(element => {
              this.nomes_orgaos.push(element.nome_orgao_superior);
            });
          }
        )
      }else if(this.nivel_orgao == "ORGAO_SUBORDINADO"){
        this.nomes_orgaos = [];
        axios.get('/api/v1/orgao_subordinado/get_all').then(
          response => {
            response.data.forEach(element => {
              this.nomes_orgaos.push(element.nome_orgao_subordinado)
            })
          }
        )
      }
    },
    atualizarURI(){
      let novaURI = '';
      let baseURI = '/api/v1';
      let fonte = '/pagamento';
      let nivel_orgao = '/orgao_superior';
      let gastos_por = '';

      if (this.fonte != '') {
        fonte = '/' + this.fonte.toLowerCase();
      }

      if (this.nivel_orgao != '') {
        nivel_orgao = '/' + this.nivel_orgao.toLowerCase();
      }

      if (this.gastos_por != '') {
        gastos_por= '/' + this.gastos_por.toLowerCase();
      }

      this.uri = novaURI.concat(baseURI, fonte, nivel_orgao, gastos_por);
    },
    analisar() {
      this.atualizarURI();

      axios.get(this.uri, {
        params: {
          top: this.top,
          mes_inicio: this.mes_inicio,
          ano_inicio: this.ano_inicio,
          mes_final: this.mes_fim,
          ano_final: this.ano_fim
        }
      }).then(response => {
            console.log(response.data);
            this.$store.commit('SET_LIST', response.data)
          })
          .catch(e => {
            console.log(e);
          });
    },
  },
  data () {
    return {
      items_fontes: [
        { Code: 'PAGAMENTO', Name: 'Pagamentos'},
        { Code: 'SERVIDOR', Name: 'Servidores'}
      ],
      items_top: [5, 10, 15, 20],
      items_nivel_orgao: [
        { Code: 'ORGAO_SUPERIOR', Name: 'Orgão Superior'},
        { Code: 'ORGAO_SUBORDINADO', Name: 'Orgão Subordinado'}
      ],
      items_gastos_por: [
        { Code: 'PROGRAMA', Name: 'Programa'},
        { Code: 'ACAO', Name: 'Ação'},
        { Code: 'CARGO', Name: 'Cargo'},
        { Code: 'FUNCAO', Name: 'Função'}
      ],
      items_mes: [
        { Code: 1, Name: 'Janeiro'},
        { Code: 2, Name: 'Fevereiro'},
        { Code: 3, Name: 'Março'},
        { Code: 4, Name: 'Abril'},
        { Code: 5, Name: 'Maio'},
        { Code: 6, Name: 'Junho'},
        { Code: 7, Name: 'Julho'},
        { Code: 8, Name: 'Agosto'},
        { Code: 9, Name: 'Setembro'},
        { Code: 10, Name: 'Outubro'},
        { Code: 11, Name: 'Novembro'},
        { Code: 12, Name: 'Dezembro'}
      ],
      items_ano: [2016, 2017, 2018],
      fonte: 'PAGAMENTO',
      top: 10,
      nivel_orgao: 'ORGAO_SUPERIOR',
      gastos_por: '',
      mes_inicio: 1,
      ano_inicio: 2016,
      nome_orgao: null,
      nomes_orgaos: [],
      mes_fim: 12,
      ano_fim: 2016,
      uri: '/api/v1/pagamento/orgao_superior'
    }
  },
}
</script>
