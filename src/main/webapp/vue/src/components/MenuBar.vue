<template>
  <Menubar :model="items" />
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from 'vue-router';

const router = useRouter();

const items = ref([
  {
    label: 'Home',
    icon: 'pi pi-home'
  },
  {
    label: 'Zona',
    icon: 'pi pi-plus',
    //command: () => router.push('/cadastrar-funcionario'),
    items: [
      {
        label: 'Número',
        icon: 'pi pi-plus',
      },
      {
        label: 'Município Sede',
        icon: 'pi pi-plus',
      },
      {
        label: 'Municípios',
        icon: 'pi pi-plus',
      },
      {
        label: 'Seções',
        icon: 'pi pi-plus',
      }
    ],
  },
  {
    label: 'Município',
    icon: 'pi pi-cog',
    items: [
      {
        label: 'Codigo TSE',
        icon: 'pi pi-user-edit',
      },
      {
        label: 'Zonas',
        icon: 'pi pi-trash',
      },
      {
        label: 'Seções',
        icon: 'pi pi-search'
      }
    ]
  },
  {
    label: 'Polo',
    icon: 'pi pi-envelope',
    items: [
      {
        label: 'Municípios',
        icon: 'pi pi-plus',
      },
      {
        label: 'Zonas',
        icon: 'pi pi-plus',
      },
      {
        label: 'N° Polo Município',
        icon: 'pi pi-plus',
      },
      {
        label: 'N° Polo Zona',
        icon: 'pi pi-plus',
      }
    ]
  },
  {
    label: 'Seção',
    icon: 'pi pi-envelope',
    items: [
      {
        label: 'Todas as Seções',
        icon: 'pi pi-plus',
      },
      {
        label: 'Seções da Zona',
        icon: 'pi pi-plus',
      },
    ]
  }
]);
</script>
