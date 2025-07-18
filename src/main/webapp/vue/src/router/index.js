import { createRouter, createWebHistory } from 'vue-router';
import NumeroZona from '@/components/dataTables/NumeroZona.vue';

const routes = [
  {
    path: '/cadastrar-funcionario',
    name: 'CadastrarFuncionario',
    //component: CadFuncionario
  },
  {
    path: '/funcionarios-ativos',
    name: 'FuncionariosAtivos',
    // component: ConsFuncAtivos
  },
  {
    path: '/funcionarios-inativos',
    name: 'FuncionariosInativos',
    //component: ConsFuncInativos
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
