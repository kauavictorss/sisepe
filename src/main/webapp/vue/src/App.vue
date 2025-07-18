<template>
  <div class="card">
    <MenuBar @abrir-cadastro="showDialog = true" />
    <Dialog v-model="showDialog" modal header="Cadastrar Funcionário" :style="{ width: '40vw' }">
      <NumeroZona />
    </Dialog>
    <router-view />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import MenuBar from './components/MenuBar.vue';
import Dialog from 'primevue/dialog';
import NumeroZona from "./components/datatables/NumeroZona.vue";

const showDialog = ref(false);
</script>
