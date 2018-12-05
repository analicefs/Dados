import Vue from 'vue'

import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    loading: false,
    field: 'Total de Despesas do Governo Federal',
    subtitle: '',
    list: [],
    listDetalhamento: [],
    items_nivel_agregacao_pagamentos: [
      { Code: 'ORGAO_SUPERIOR', Name: 'Orgão Superior'},
      { Code: 'ORGAO_SUBORDINADO', Name: 'Orgão Subordinado'},
      { Code: 'PROGRAMA', Name: 'Programa'},
      { Code: 'ACAO', Name: 'Ação'},
      { Code: 'ELEMENTO_DESPESA', Name: 'Elemento Despesa'},
    ],
    items_nivel_agregacao_servidores: [
      { Code: 'ORGAO_SUPERIOR', Name: 'Orgão Superior'},
      { Code: 'ORGAO_SUBORDINADO', Name: 'Orgão Subordinado'}
    ],
    items_nivel_agregacao_selected: 'ORGAO_SUPERIOR',
    filter: '',
    statusError: { state: false, cod: null, msg: '' },
    top: 10,
    mes_inicio: 1,
    ano_inicio: 2016,
    mes_final: 12,
    ano_final: 2016,
    uri: '/api/v1/pagamento/orgao_superior',
    det: false,
    msg_errors: {
      301: `O seu navegador deve acessar outro endereço.`,
      400: `Ops, há algo errado nesta página ou configurações do servidor`,
      401: `Acesso não autorizado!`,
      403: `Você não tem autorização para visualizar este arquivo`,
      404: `Página não encontrada. Desculpa, a pagina não existe.`,
      500: `O servidor não conseguiu responder a sua requisição, por favor corrija os parâmetros e tente novamente.`,
      default_error: `Erro inesperado! Tente atualizar a página.`,
    }
  },
  mutations: {
    SET_LIST(state, obj) {
      state.list = obj
      if(state.listDetalhamento == []){
        state.listDetalhamento = state.list
      }
    },
    SET_LOADING(state, obj) {
      state.loading = obj
    },
    SET_DET(state, obj) {
      state.det = obj
    },
    SET_LIST_DETALHAMENTO(state, obj) {
      state.det = true;
      state.listDetalhamento = obj
    },
    ADD_LIST(state, obj) {

      let estaPresente = false;
      state.list.forEach(element => {
        if(element.cod == obj.cod){
          estaPresente = true;
        }
      })

      if(!estaPresente){
        state.list.push(obj)
      }
    },
    REMOVE_ITEM(state, obj) {
      function remover(item, index) {
        if(obj.cod == item.cod){
          state.list.splice(index, 1);
        }
      }
      state.list.forEach(remover)
    },
    REMOVE_ITEM_CHART(state, obj) {
      function remover(item, index) {
        if(obj == item.cod){
          state.list.splice(index, 1);
        }
      }
      state.list.forEach(remover)
    },
    TITULO_FIELD(state, obj){
      state.field = obj
    },
    SET_STATUS_ERROR(state, obj){
      state.statusError = obj
    },
    SET_URI(state, obj) {
      state.uri = obj
    },
    SET_TOP(state, obj) {
      state.top = obj
    },
    SET_MES_INICIO(state, obj) {
      state.mes_inicio = obj
    },
    SET_MES_FINAL(state, obj) {
      state.mes_final = obj
    },
    SET_ANO_INICIO(state, obj) {
      state.ano_inicio = obj
    },
    SET_ANO_FINAL(state, obj) {
      state.ano_final = obj
    },
    SET_YTEXT(state, obj) {
      state.yText = obj
    },
    SET_ITEMS_NIVEL_AGREGACAO_SELECTED(state, obj) {
      state.items_nivel_agregacao_selected = obj
    },
  }
})
