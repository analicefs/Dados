<template>
  <v-container>
    <div id="container-for-gastos-chart">
    </div>
    <span style="font-size:10px"><a href="http://www.portaltransparencia.gov.br/"> Fonte: Portal da Transparência - Governo Federal </a></span>
  </v-container>

</template>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/stock/modules/exporting.js"></script>



<script>
import { mapState, Store } from 'vuex'
import Highcharts from 'highcharts/highstock'
import BaseChart from './BaseChart'
import store from '../../store/index'
import axios from 'axios'
import drilldown from 'highcharts/modules/drilldown.src.js'
import Vue from 'vue';
import VueSweetalert2 from 'vue-sweetalert2';

Vue.use(VueSweetalert2);

drilldown(Highcharts)
import HighchartsExporting from 'highcharts/modules/exporting.js'
import HighchartsOfflineExporting from 'highcharts/modules/offline-exporting.js'

HighchartsExporting(Highcharts)
HighchartsOfflineExporting(Highcharts)

var Historico_categories = [];
var Historico_values = [];
var Historico_codigos = [];
var Detalhamento_categories = [];
var Detalhamento_values = [];
var Detalhamento_codigos = [];
var BackSubTitleDrilldown = '';
var categorieSelected = '';
var BackcategorieSelected = '';
var BackcategorieSelectedtitulo = '';
var categorieSelectedtitulo = '';
var MapaSelecionado = {'ORGAO_SUPERIOR':"Órgão Superior",'ORGAO_SUBORDINADO':"Órgão Subordinado",'PROGRAMA':"Programa",'ACAO':"Ação", 'ELEMENTO_DESPESA': 'Elemento Despesa'}
var Bool_Detalhamento = false;

const toast = Vue.swal.mixin({
    toast: true,
    position: 'bottom-end',
    showConfirmButton: false,
    timer: 3000
});

function DefinirSubtituloDetalhamento(){
    var str = {'ORGAO_SUPERIOR':"orgaos subordinados",
    'ORGAO_SUBORDINADO':"programas",'PROGRAMA':"ações",'ACAO':"elementos despesas"}

    var str2 = {'ORGAO_SUPERIOR':"Órgão Subordinado",
    'ORGAO_SUBORDINADO':"Programa",'PROGRAMA':"Ação",'ACAO':"Elemento Despesa"}

    var str3 = {'ORGAO_SUPERIOR':"orgao superior",
    'ORGAO_SUBORDINADO':"orgao subordinado",'PROGRAMA':"programa",'ACAO':"acao"}

    categorieSelected = str2[store.state.items_nivel_agregacao_selected];
    BackcategorieSelected = MapaSelecionado[store.state.items_nivel_agregacao_selected];

    categorieSelectedtitulo = str[store.state.items_nivel_agregacao_selected];
    BackcategorieSelectedtitulo = str3[store.state.items_nivel_agregacao_selected];

    return store.state.subtitle.toUpperCase();
}

function drilldownValues(categories, values){
    var drill = true;
    if (store.state.uri == '/api/v1/pagamento/elemento_despesa' ||
        store.state.uri == '/api/v1/servidor/orgao_subordinado/cargo' ||
        store.state.uri == '/api/v1/servidor/orgao_subordinado/funcao'){
        drill = false;
    }

    var colection = [];
	for(var i = 0; i < values.length; i++){
   		colection[i] = {
       	 "name": categories[i],
         "y": values[i],
         "drilldown": drill,
       }
    }
    return colection
}

async function getDetalhamentoValues(codigo){
   await axios.get(store.state.uri +'/detalhamento', {
    params: {
        top: store.state.top,
        mes_inicio: store.state.mes_inicio,
        ano_inicio: store.state.ano_inicio,
        mes_final: store.state.mes_final,
        ano_final: store.state.ano_final,
        codigo: codigo
    }
    }).then(response => {
        console.log(response.data)
        Detalhamento_categories = response.data.map(item => reduzNomeChips(item.nome));
        Detalhamento_values = response.data.map(item => item.total);
        Detalhamento_codigos = response.data.map(item => item.cod)
        store.commit('SET_LIST_DETALHAMENTO', response.data)
        })
        .catch(e => {
        console.log(e);
        });
}


async function drilldownDetalhamentoValues(codigo,categorie){
    var colectionDrilldown = [];
    var serie = [];

    await getDetalhamentoValues(codigo);

    for(var j = 0; j < Detalhamento_values.length; j++){
        colectionDrilldown[j] = {
            name: Detalhamento_categories[j],
            y: Detalhamento_values[j],
            drilldown: false}
    }
    serie = {
        name: categorie,
        id: categorie,
        data: colectionDrilldown,

    };

    return serie
}

function reduzNomeChips(nome){
    if(nome.length > 50){
      return nome.substring(0,25)+"..."+nome.substring(nome.length-25,nome.length);
    }
    return nome;
}
function numberToReal(numero) {
    var numero = numero.toFixed(2).split('.');
    numero[0] = "R$ " + numero[0].split(/(?=(?:...)*$)/).join('.');
    return numero.join(',');
}

export default {
  name: 'GastosChart',
  extends: BaseChart,
  methods: {
    dataSource() {
      const categories = this.list.map(item => reduzNomeChips(item.nome))
      const values = this.list.map(item => item.total)
      const codigos = this.list.map(item => item.cod)
      const pesquisa = this.$store.state.pesquisa

      this.setup({categories, values, codigos, pesquisa})
    },
    setup(obj) {
      var { categories, values, codigos, pesquisa } = obj

      Highcharts.setOptions({
        lang: {
            numericSymbols: ['mil', ' M', ' B', ' T'],
            contextButtonTitle: 'Exportar gráfico',
            downloadJPEG: 'Baixar imagem JPEG',
            downloadPDF: 'Baixar arquivo PDF',
            downloadPNG: 'Baixar imagem PNG',
            downloadSVG: 'Baixar vetor SVG',
            printChart: 'Imprimir gráfico',
            decimalPoint: ',',
            thousandsSep: '.',
            drillUpText: 'Voltar',
        }
      });

     return Highcharts.chart('container-for-gastos-chart', {
          exporting: {
            filename: "Grafico",
            chartOptions: {
              plotOptions: {
                series: {
                  dataLabels: {
                    enabled: false
                  },
                }
              },
              chart: {
                height: 600,
                width: 900,
                marginBottom: 300,
                events: {
                load: function() {
                    var renderer = this.renderer;

                    renderer.path(['M', 30, 385, 'L', 570, 385, 'Z'])
                    .css({
                        'p.thick': {
                        'font-weight': 'bold'
                        }
                    }).attr({
                    //stroke: 'black',
                    //'stroke-width': 1

                    }).add();
                    renderer.text('<strong>Total de gastos por orgão:</strong>', 30, 400).add()
                    for(var i = 0; i < categories.length; i++){
                        renderer.text((i+1)+". "+categories[i]+(" <strong>R$:"+values[i])+"</strong>", 30, 420+(14*i)).add()
                    }
                    //categories.map(item => renderer.text(item, 30, 400).add())
                }
                }
              },
              legend: {
                y: -220
              },
              credits: {
                position: {
                  y: -220
                }
              }
            },
            fallbackToExportServer: false
          },
          chart: {
              type: 'column',
              marginLeft: 150,
              events: {
                    drilldown: async function (e) {

                            await Vue.swal({
                                html: "<b><span style='font-size:18px;'>"+  categories[e.point.x] +"</span></b> </br>Valor: " +  numberToReal(values[e.point.x]),
                                showCancelButton: true,
                                confirmButtonColor: '#3085d6',
                                cancelButtonColor: '#d33',
                                confirmButtonText: 'Detalhar Orgão',
                                cancelButtonText: 'Remover Orgão',
                                confirmButtonClass: 'btn btn-success w-25 mr-05',
                                cancelButtonClass: 'btn btn-secondary w-25 ml-05',
                                showCloseButton: true
                                }).then((result) => {
                                if (result.value) {
                                    Bool_Detalhamento = true;
                                    toast({
                                        title: 'Iniciando Detalhamento'
                                    })
                                }
                                else if (result.dismiss === Vue.swal.DismissReason.cancel) {
                                    store.commit('REMOVE_ITEM_CHART', codigos[e.point.x]);
                                    toast({
                                        type: 'success',
                                        title: 'Orgão removido com sucesso'
                                    })
                                }
                            })

                        if (!e.seriesOptions && Bool_Detalhamento) {

                            store.commit('SET_LOADING', true)
                            var chart = this;

                            DefinirSubtituloDetalhamento()

                            chart.showLoading('Carregando ...');

                            BackSubTitleDrilldown = store.state.subtitle;
                            store.state.subtitle = categories[e.point.x];
                            var series =  await drilldownDetalhamentoValues(codigos[e.point.x], categories[e.point.x]);



                            setTimeout(function () {
                                chart.hideLoading();
                                chart.addSeriesAsDrilldown(e.point, series);

                                chart.update({
                                    subtitle: {
                                        useHTML: true,
                                        text: '<b><center>' + store.state.subtitle + '</center></b>'
                                    },
                                    title: {
                                        text: store.state.field.replace(BackcategorieSelectedtitulo, categorieSelectedtitulo),
                                    },
                                    tooltip:{
                                        valueDecimals: 2,
                                        headerFormat: '<span style="color:{series.color};padding:0"><b>'+ categorieSelected +':</b></span> <b>{point.key}</b><table>',
                                        pointFormat:  '<tr><td style="color:{series.color};padding:0"> <b> Valor:</b> </td>' +
                                                    '<td style="padding:0"><b> R$ {point.y}</b></td>',

                                        footerFormat: '</table> ',
                                        shared: true,
                                        useHTML: true
                                    }
                                });
                                store.commit('SET_LOADING', false)
                            }, 1000);

                            Bool_Detalhamento = false;
                            Historico_categories[Historico_categories.length] = categories
                            Historico_values[Historico_values.length] = values
                            Historico_codigos[Historico_codigos.length] = codigos
                            categories = Detalhamento_categories;
                            values = Detalhamento_values;
                            codigos = Detalhamento_codigos;

                        }

                    },

                    drillup: async function(e){
                        var chart = this;
                        store.state.subtitle = BackSubTitleDrilldown;
                        //store.state.items_nivel_agregacao_selected = BackcategorieSelected;
                        store.commit('SET_DET', false);
                         chart.update({
                                title: {
                                    text: store.state.field.replace(categorieSelectedtitulo,BackcategorieSelectedtitulo),
                                },
                                subtitle: {
                                    useHTML: true,
                                    text: '<b><center>' + store.state.subtitle + '</center></b>'
                                },
                                    tooltip:{
                                        valueDecimals: 2,
                                        headerFormat: '<span style="color:{series.color};padding:0"><b>'+ BackcategorieSelected +':</b></span> <b>{point.key}</b><table>',
                                        pointFormat:  '<tr><td style="color:{series.color};padding:0"> <b> Valor:</b> </td>' +
                                                    '<td style="padding:0"><b> R$ {point.y}</b></td></tr>',
                                        footerFormat: '</table>',
                                        shared: true,
                                        useHTML: true
                                    }
                            })
                        categories = Historico_categories.pop()
                        values = Historico_values.pop()
                        codigos = Historico_codigos.pop()
                    }
                }

          },
          title: {
              text: store.state.field,
          },
          subtitle: {
              useHTML: true,
              text: '<b><center>' + store.state.subtitle + '</center></b>'
          },
          xAxis: {
              crosshair: true,
              type: 'category',
              min: 0,
              max: 5,
              scrollbar: {
                enabled: true,
                height: 8,
                barBackgroundColor: '#D3D3D3',
                sbarBorderRadiu: 1,
                barBorderWidth: 0,
                buttonBackgroundColor: '#D3D3D3',
                buttonBorderWidth: 0,
                buttonBorderRadius: 7,
                trackBackgroundColor: 'none',
                trackBorderWidth: 1,
                trackBorderRadius: 8,
                trackBorderColor: '#CCC'
              },
              tickLength: 0
          },
          yAxis: {
              type: 'logarithmic',
              title: {
                  text: 'Total de Gastos(R$)'
              },
          },
          tooltip: {
              valueDecimals: 2,
              headerFormat: '<span style="color:{series.color};padding:0"><b>'+ MapaSelecionado[store.state.items_nivel_agregacao_selected] +':</b></span> <b>{point.key}</b><table>',
              pointFormat:  '<tr><td style="color:{series.color};padding:0"> <b> Valor:</b> </td>' +
                            '<td style="padding:0"><b> R$ {point.y}</b></td></tr>',
              footerFormat: '</table>',
              shared: true,
              useHTML: true
          },

          plotOptions: {
              column: {
                  pointPadding: 0.2,
                  borderWidth: 0
              },series: {
                cursor: 'pointer',
                point: {
                    events: {
                        click: async function (e) {
                            if(!e.point.drilldown){
                                await Vue.swal({
                                html: "<b><span style='font-size:18px;'>"+  categories[e.point.x] +"</span></b> </br>Valor: " +  numberToReal(values[e.point.x]),
                                showCancelButton: false,
                                confirmButtonColor: '#d33',
                                confirmButtonText: 'Remover Orgão',
                                confirmButtonClass: 'btn btn-success w-25 mr-05',
                                showCloseButton: true
                                }).then((result) => {
                                if (result.value) {
                                   this.remove()
                                    toast({
                                        type: 'success',
                                        title: 'Orgão removido com sucesso'
                                    })
                                }
                            })
                            }
                        }
                    }
                }
              },
           },
           legend: {
                enabled: false
            },

          series: [{
              name : ' - ',
              data: drilldownValues(categories,values)
          }],
          drilldown: {
            series: []

          },
      });
    }
  }

}
</script>

