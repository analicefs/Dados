<template>
  <div id="container-for-pie-chart">

  </div>
</template>

<script>
import BaseChart from './BaseChart'
import Highcharts from 'highcharts'

export default {
  name: 'PieChart',
  extends: BaseChart,
  methods: {
    dataSource() {

      //this.setup(categories)
    },
    setup(obj) {

      const { finalData } = obj

      Highcharts.chart('container-for-pie-chart', {
          chart: {
              plotBackgroundColor: null,
              plotBorderWidth: null,
              plotShadow: false,
              type: 'pie'
          },
          title: {
              text: 'Browser market shares in January, 2018'
          },
          tooltip: {
              pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
          },
          plotOptions: {
              pie: {
                  allowPointSelect: true,
                  cursor: 'pointer',
                  dataLabels: {
                      enabled: true,
                      format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                      style: {
                          color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                      }
                  }
              }
          },
          series: [{
              name: 'Brands',
              colorByPoint: true,
              data: finalData
          }]
      });
    }
  }

}
</script>

<style lang="css">
</style>
