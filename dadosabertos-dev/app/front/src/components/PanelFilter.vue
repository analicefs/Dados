<template>
  <v-app id="sandbox" :dark="dark">
    <v-content>
      <v-container fluid>
        <v-layout align-center justify-center>
          <v-flex md5>
            <v-card>
              <v-card-text>
                <v-layout row wrap>
                  <v-flex xs12 md12>
                    <span>Filtros</span>
                    <v-radio-group v-model="primaryDrawer.type" column>
                      <v-radio v-for="drawer in drawers" :key="drawer" primary :label="drawer" :value="drawer.toLowerCase()"></v-radio>
                    </v-radio-group>
                    <v-select :items="items" box label="Top"></v-select>
                    <v-select :items="items" box label="Nível Orgão"></v-select>
                    <v-select :items="items" box label="Gastos Por"></v-select>
                  </v-flex>
                  <v-flex xs6 md6>
                    <v-select :items="items" box label="Gastos Por"></v-select>
                  </v-flex>
                  <v-flex xs6 md6>
                    <v-select :items="items" box label="Gastos Por"></v-select>
                  </v-flex>
                  <v-flex xs6 md6>
                    <v-select :items="items" box label="Gastos Por"></v-select>
                  </v-flex>
                  <v-flex xs6 md6>
                    <v-select :items="items" box label="Gastos Por"></v-select>
                  </v-flex>
                </v-layout>
              </v-card-text>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
  export default {
    data: () => ({
      drawers: ['Pagamentos', 'Servidores'],
      primaryDrawer: {
        model: null,
        type: 'persistent',
        clipped: false,
        floating: false,
        mini: false
      },
      items: ['Foo', 'Bar', 'Fizz', 'Buzz'],
      footer: {
        fixed: false
      }
    })
  }
</script>
