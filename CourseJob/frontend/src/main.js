import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap/dist/css/bootstrap.css'
import router from './router' 
import DatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'


const app = createApp(App); // Создание экземпляра приложения

app.component('DatePicker', DatePicker);

import {globalVariables} from './global.variables'
app.config.globalProperties.globalVariables = globalVariables;

app.use(router); // Подключение маршрутизации
app.mount('#app');
