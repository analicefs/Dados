<template>
  <v-container>
    <v-layout ref="form_sugestao" justify-center row>
      <v-flex xs12 sm6 md6 lg8 xl10>
        <v-form v-model="valid" ref="formulario_sugestao" >
              <h2>Sugestão</h2>
              <v-text-field v-model="nome" @blur="carregarReCaptcha" box label="Nome" ref="nome" :rules="[() => !!nome || 'Nome é requerido']" required></v-text-field>
              <v-text-field v-model="email" box label="E-mail" ref="email" :rules="emailRules" required></v-text-field>
              <v-textarea v-model="obs" box label="Observação" ref="obs" :rules="[() => !!obs || 'Observação é requerida']" required></v-textarea>
              <v-select v-model="tipo_relato" :items="items_tipo_relato" item-text="Name" item-value="Code" box label="Tipo de Relato" ref="tipo_relato" :rules="[(v) => !!v || 'Tipo de Relato é requerido']" required></v-select>
              <div id="div-captcha-erro">
                <div id="captcha-login" class=""></div>
              </div>
            <v-btn @click="salvar" :class="{ red: ( !valid || !this.checkRC), green: (valid && this.checkRC) }" block dark>Salvar <v-icon right dark>show_chart</v-icon></v-btn>
        </v-form>        
      </v-flex>
    </v-layout>
    <v-snackbar v-model="snackbar" :color="'success'"> 
      {{ text }}
      <v-btn color="pink" flat @click="snackbar = false"> Fechar </v-btn>
    </v-snackbar>
  </v-container>
</template>

<script>
  import axios from 'axios'

  var widget_login = null;

  export default {
  name: 'FormSugestoes',
  computed: {
    form_sugestao(){
    }
  },
  methods: {
    carregarReCaptcha(){
      if(widget_login == null){
       widget_login = grecaptcha.render('captcha-login', {
            callback: ()=> {this.checkRC = true;}, 
            'sitekey' : '6LePe3YUAAAAAKYY_rHblABhUDdGBPz44NR9Xxvz',
          })
        /*widget_login = grecaptcha.render('captcha-login', {
            'sitekey' : '6LePe3YUAAAAAKYY_rHblABhUDdGBPz44NR9Xxvz',
          })*/
      }else if(this.checkRC == true){
        grecaptcha.reset();
        this.checkRC = false;
      }
    },
    getTipoRelato(){
      let uri_sugestao = '/api/v1/sugestao/getTipoRelato';
      axios.get(uri_sugestao, {params:  {relato: this.tipo_relato}})
            .then(response => {
          //console.log(response.data);
      });
    },
    salvar(){
      if(widget_login == null){
			  if(document.getElementById('captcha-erro') == null){
          //$('#div-captcha-erro').after('<span id="captcha-erro" class="help-block">Selecione a opção "Não sou um robô"</span>');
          this.text = 'Preencha todos os campos!';
          this.snackbar = true;
        }
			  return false;
      }else if(grecaptcha.getResponse() == '') {
			  if(document.getElementById('captcha-erro') == null){
          //$('#div-captcha-erro').after('<span id="captcha-erro" class="help-block">Selecione a opção "Não sou um robô"</span>');
          this.text = 'Selecione a opção "Não sou um robô"';
          this.snackbar = true;
        }
			  return false;
		  }

      if(this.$refs.formulario_sugestao.validate()){
        let uri_sugestao = '/api/v1/sugestao/add';

        const params_sugestao = new URLSearchParams();
        params_sugestao.append('nome', this.nome);
        params_sugestao.append('email', this.email);
        params_sugestao.append('relato', this.tipo_relato);
        params_sugestao.append('obs', this.obs);

        axios.post(uri_sugestao, params_sugestao)
              .then(response => {
              this.$refs.formulario_sugestao.reset();
              this.text = "Sugestão submetida com sucesso!";
              this.snackbar = true;
              this.carregarReCaptcha();
        }).catch(error => {
          console.log("Passou aqui");
          console.log(error.response.status);
          if(error.response.status === 400) {
            this.$store.commit('SET_STATUS_ERROR', {
              "state": true,
              "cod": error.response.status,
              "msg": `O servidor não conseguiu responder a sua requisição, por favor corrija os parâmetros e tente novamente.`
            })
            this.$store.commit('SET_LOADING', false)
          }
        });
      }
    }
  },
  data () {

    return {
      checkRC: false,
      widget_login : "",
      valid: false,
      tipo_relato: '',
      obs: '',
      nome: '',
      email: '',
      items_tipo_relato: [
        { Code: 'sugestao', Name: 'Sugestão'},
        { Code: 'erro', Name: 'Erro'},
        { Code: 'elogio', Name: 'Elogio'},
        { Code: 'outros', Name: 'Outros'}
      ],
      snackbar: false,
      timeout: 6000,
      text: '',
      emailRules: [
          (v) => !!v || 'E-mail é requerido',
          (v) => /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail deve ser válido'
      ],
      tipo_relato: null
    }
  },
}
</script>

