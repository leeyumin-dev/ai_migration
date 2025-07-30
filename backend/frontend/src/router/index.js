import { createRouter, createWebHashHistory } from 'vue-router';

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      component: () => import('../components/pages/Index.vue'),
    },
    {
      path: '/users',
      component: () => import('../components/ui/UserGrid.vue'),
    },
    {
      path: '/posts',
      component: () => import('../components/ui/PostGrid.vue'),
    },
    {
      path: '/postQueries',
      component: () => import('../components/ui/PostQueryGrid.vue'),
    },
    {
      path: '/agents',
      component: () => import('../components/ui/AgentGrid.vue'),
    },
    {
      path: '/tokens',
      component: () => import('../components/ui/TokenGrid.vue'),
    },
    {
      path: '/conversions',
      component: () => import('../components/ui/ConversionGrid.vue'),
    },
    {
      path: '/testLogs',
      component: () => import('../components/ui/TestLogGrid.vue'),
    },
    {
      path: '/conversionLogs',
      component: () => import('../components/ui/ConversionLogGrid.vue'),
    },
    {
      path: '/securities',
      component: () => import('../components/ui/SecurityGrid.vue'),
    },
  ],
})

export default router;
