import { createWebHistory, createRouter } from "vue-router";
// импорт компонентов
import ListSeminars from "./components/seminar/listSeminars";
import Seminar from "./components/seminar/seminar";
import Presentation from "./components/presentation/presentation";
import ListPresentations from "./components/presentation/listPresentations";
import AddSeminar from "./components/seminar/addSeminar";
import AddPresentation from "./components/presentation/addPresentation";


// определяем маршруты
const routes = [
    {
        path: "/", // указание маршрута, по которому будем переходить к списку пользователей
        name: "listSeminars", // имя маршрута
        alias: "/listSeminars", // указание дополнительного маршрута
        component: ListSeminars, // компонент, на основании которого будет отрисовываться страница
        meta: {
            title: "Семинары"
        }
    },
    {
        path: "/addPresentation", // указание маршрута, по которому будем переходить к списку пользователей
        name: "addPresentation", // имя маршрута
        component: AddPresentation, // компонент, на основании которого будет отрисовываться страница
        meta: {
            title: "Добавление доклада"
        }
    },
    {
        path: "/addSeminar", // указание маршрута, по которому будем переходить к списку пользователей
        name: "addSeminar", // имя маршрута
        component: AddSeminar, // компонент, на основании которого будет отрисовываться страница
        meta: {
            title: "Добавление семинара"
        }
    },
    {
        path: "/presentations", // указание маршрута, по которому будем переходить к списку пользователей
        name: "listPresentations", // имя маршрута
        alias: "/listPresentations", // указание дополнительного маршрута
        component: ListPresentations, // компонент, на основании которого будет отрисовываться страница
        meta: {
            title: "Доклады"
        }
    },
    {
        path: "/seminar/:id",
        name: "seminar-details",
        component: Seminar,
        props: true,
        meta: {
            title: "Семинар"
        }
    },
    {
        path: "/presentation/:id",
        name: "presentation-details",
        component: Presentation,
        props: true,
        meta: {
            title: "Доклад"
        }
    },

];

const router = createRouter({
    history: createWebHistory(), // указываем, что будет создаваться история посещений веб-страниц
    routes, // подключаем маршрутизацию
});

// указание заголовка компонентам (тега title), заголовки определены в meta
router.beforeEach((to, from, next) => {
    // для тех маршрутов, для которых не определены компоненты, подключается только App.vue
    // поэтому устанавливаем заголовком по умолчанию название "Главная страница"
    document.title = to.meta.title || 'Главная страница';
    next();
});

export default router;