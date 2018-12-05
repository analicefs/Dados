
<script>
  import axios from 'axios'
  import Store from '../store/index'

  function buscar(propriedade, valor, lista){
    return lista.find(function(item){
        return item[propriedade] === valor;
    });
  }
  export default {
    name: 'FormPagamentos',
    computed: {
      form () {
        return {
          top: this.top,
          nivel_agregacao: this.nivel_agregacao,
          mes_inicio: this.mes_inicio,
          ano_inicio: this.ano_inicio,
          mes_fim: this.mes_fim,
          ano_fim: this.ano_fim
        }
      }
    },
    methods: {
      carregarItemsNivelAgregacao() {
        return Store.state.items_nivel_agregacao_pagamentos
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
      atualizarItemNivelAgregacaoAtual(novo) {
        this.$store.commit('SET_ITEMS_NIVEL_AGREGACAO_SELECTED', novo)
      },
      atualizarURI(){
        let novaURI = '';
        let baseURI = '/api/v1';
        let fonte = '/pagamento';
        let nivel_agregacao = '';
        let text_field = 'Despesas do Governo Federal';

        if (this.fonte != '') {
          text_field = text_field.concat(` da base de ` + this.fonte.toLowerCase());
          text_field = text_field.replace('_', ' ');
          fonte = '/' + this.fonte.toLowerCase();
        }

        if (this.nivel_agregacao != '') {
          text_field = text_field.concat(' com gastos em ' + this.nivel_agregacao.toLowerCase());
          text_field = text_field.replace('_', ' ');
          nivel_agregacao= '/' + this.nivel_agregacao.toLowerCase();
        }
        let mes = ' de ';
        mes = mes.concat(this.mes_inicio, "/", this.ano_inicio, " até ", this.mes_fim, "/", this.ano_fim)
        text_field = text_field.concat(mes);
        this.$store.commit('TITULO_FIELD', text_field);

        this.uri = novaURI.concat(baseURI, fonte, nivel_agregacao);
        this.$store.commit('SET_URI', this.uri);
      },
/*       async analisar() {
        this.erroNoForm = false
        Object.keys(this.form).forEach(f => {
          if (!this.form[f]){
            this.erroNoForm = true
          }else if(!((this.mes_fim >= this.mes_inicio && this.ano_fim >= this.ano_inicio)
              || (this.mes_fim <= this.mes_inicio && this.ano_fim > this.ano_inicio))){
            this.erroNoForm = true
          }
          this.$refs[f].validate(true)
        })

        if(this.erroNoForm == false){
          this.atualizarURI();
          this.$store.commit('SET_LOADING', true)
          this.$store.commit('SET_TOP', this.top);
          this.$store.commit('SET_MES_INICIO', this.mes_inicio);
          this.$store.commit('SET_MES_FINAL',this.mes_fim);
          this.$store.commit('SET_ANO_INICIO', this.ano_inicio);
          this.$store.commit('SET_ANO_FINAL',this.ano_fim);
          await axios.get(`${this.uri}`, {
            params: {
              top: this.top,
              mes_inicio: this.mes_inicio,
              ano_inicio: this.ano_inicio,
              mes_final: this.mes_fim,
              ano_final: this.ano_fim
            }
          }).then(response => {
            this.$store.commit('SET_STATUS_ERROR', {state: false})
            this.$store.commit('SET_LIST', response.data)
            this.$store.commit('SET_LOADING', false)
          }).catch(error => {
            this.tratamentoErro(error);
          });
        }
      }, */
    },
    async mounted() {
      this.nivel_agregacao = Store.state.items_nivel_agregacao_selected
      this.atualizarURI();
      await axios.get('/api/v1/pagamento/orgao_superior', {
        params: {
          top: 27,
          mes_inicio: 1,
          ano_inicio: 2016,
          mes_final: 12,
          ano_final: 2016
        }
      }).then(response => {
        this.$store.commit('SET_STATUS_ERROR', {state: false})
        this.$store.commit('SET_LIST', response.data)
        this.$store.commit('SET_LOADING', false)
      }).catch(error => {
        this.tratamentoErro(error)
      });
      this.atualizarURI();
    },
    data () {
      return {
        item_title: "Filtro Pagamentos",
        items_top: [5, 10, 15, 20],
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
        nivel_agregacao: '',
        mes_inicio: 1,
        ano_inicio: 2016,
        mes_fim: 12,
        ano_fim: 2016,
        uri: '/api/v1/pagamento/orgao_superior',
        uri_autocomplete: '/api/v1/pagamento/',
        erroNoForm: false
      }
    },
  }
</script>
