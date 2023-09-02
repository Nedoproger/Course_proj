<template>

<div class="bg-white w-mine mx-auto mt-4 p-3 rounded">
    <div class="row g-custom">
        <div class="mx-auto d-flex flex-column flex-sm-column flex-md-column flex-lg-row flex-xl-row mb-2">
            <div class="align-self-center d-flex" style="font-size: 180%; text-align: center;">
                <p class="p-0 m-0">Список докладов</p>
            </div>
            <!-- v-if="currentUser" -->
            <div class="align-self-center d-flex p-2 ">
                <router-link class="btn btn-outline-success " style="text-align: center; font-size: 110%;" to="/addPresentation">Добавить доклад</router-link>
            </div>

            <div class="d-flex flex-grow-1"></div>

            <div class="d-flex align-self-center p-2 ">
                <DatePicker placeholder="Поиск по дате"
                    locale="ru"
                    @cleared="getPresentations"
                    @closed="searchPresentationsByDate"
                    v-model="date" :enable-time-picker="false">   
                </DatePicker>
            </div>

            <form @submit="searchPresentationsByName" class="d-flex float-right align-self-center">
                <div class="align-self-center">
                    <input style="font-size: 110%;" type="search" @input="debouncedSearch" aria-label="Search" class="form-control" name="name" id=""  placeholder="Поиск" v-model="name">
                </div>
                <div class="align-self-center rounded " id="btn_no_rounded">
                    &nbsp;<svg xmlns="http://www.w3.org/2000/svg" style="" class="" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
                    &nbsp;
                </div>         
            </form>
        </div>
     
        <div class="" style="font-size: 120%;">
            <ul class="list-group">
                <li class="list-group-item" v-for="(presentation, index) in presentations" :key="index">
                    <router-link class="link" :to="{
                            name: 'presentation-details',
                            params: { id: presentation.id }
                        }">
                        {{presentation.name}}
                    </router-link>
            </li>
            </ul>
        </div>
    </div>
</div>

</template>

<script>
    import http from "../../http-common";
    import debounce from 'lodash/debounce';
    export default {
        name: "ListPresentations", 
        data() { 
            return {
                name:"",
                date:"",
                presentations: [],
                value:"Поиск"
            };
        },
        // computed: {
        //     currentUser() {
        //         return this.$store.state.auth.user;
        //     }
        // },
        methods: { 
            searchPresentationsByDate() {
                if (this.date) { 
                    http
                        .get("/presentation/date/" + this.date)
                        .then(response => {
                            this.presentations = response.data;
                        })
                        .catch();
                }
                else {
                    this.getPresentations();
                }

            },
            debouncedSearch: debounce(function (e) {
                this.searchPresentationsByName(e);
            }, 300),
            searchPresentationsByName(e) {
                e.preventDefault();
                if (this.name){ 
                    http
                        .get("/presentation/name/" + this.name)
                        .then(response => {
                            this.presentations = response.data;
                        })
                        .catch(e => {
                            console.log(e);
                        });
                }
                else{
                    this.getPresentations();
                }
            },
            getPresentations() {
                http
                    .get("/listPresentations")
                    .then(response => {
                        this.presentations = response.data;
                    })
                    .catch(e => {
                        console.log(e);
                    });
            }
        },
        mounted() {
            this.getPresentations();
        }
    }
</script>

<style>
    .item {
        margin-left: 5px;
    }
    .link{
        position: relative;
        font-size: 1.25rem;
        text-decoration: none;
        /* color:rgb(1, 70, 123); */
        color: rgb(7, 50, 113)
    }
    .link::before {
        content: '';
        position: absolute;
        left: 0; bottom: -5px;
        width: 0;
        height: 1.5px;
        background-color: #0a58ca;
    }
    .link:hover:before {
        width: 100%;
    }
    .link::before {
        transition: width 0.35s;
    }
</style>