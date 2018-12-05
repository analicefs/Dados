import Vue from 'vue'
import Router from 'vue-router'
import ComponentePagamento from '@/components/ComponentePagamento'
import ComponenteServidor from '@/components/ComponenteServidor'
import ComponenteSugestao from '@/components/ComponenteSugestao'
import store from '../store/index'


Vue.use(Router)

export const router = new Router({
  routes: [
    {
      path: '/',
      name: 'ComponentePagamento',
      component: ComponentePagamento
    },
    {
      path: '/servidores',
      name: 'ComponenteServidor',
      component: ComponenteServidor
    },
    {
      path: '/sugestao',
      name: 'ComponenteSugestao',
      component: ComponenteSugestao
    },
  ]
})

router.beforeResolve((to, from, next) => {
  if (to.name) {
      store.commit('SET_LOADING', true)
      if(to.name === 'ComponenteSugestao'){
        NProgress.done();
        //store.commit('SET_LOADING', false)
      }
  }
  next()
})

export default router