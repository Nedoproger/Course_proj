<template>
<!-- v-if="currentUser" -->
<div class="bg-white w-mine mx-auto rounded mt-4 p-3 ">
    <div class="align-self-center" style="font-size: 200%; text-align: center;">
        <p>Добавление семинара</p>
    </div>
    
    <hr>
    <div class="align-self-center g-2" v-if="!submitted">
        <form class="justify-content-center mx-auto d-flex flex-column flex-sm-column flex-md-row flex-lg-row flex-xl-row" @submit="addSeminar">
            <div class="d-flex flex-grow-1 p-2">
                <input type="text" name="name" class="form-control" style="font-size: 120%;" id="name" placeholder="Название семинара" required v-model="seminar.name">
            </div>
            <div class="d-flex p-2 align-self-center">
                <input type="submit" class="btn btn-outline-success" style="font-size: 120%; text-align: center;" value="Добавить">
            </div>
        </form>
    </div>
    <div class="row text-center" v-else>
        <div class="align-self-center" style="font-size: 140%; text-align: center;">
            <p>Вы успешно добавили запись!</p>
        </div>
        <div>
            <button v-on:click="newSeminar" style="font-size: 120%;" class="btn btn-success mb-2">Добавить новый семинар</button>
        </div>
        <div>
            <router-link to="/listSeminars" style="font-size: 120%;" class="btn btn-primary mb-2">Вернуться к списку семинаров</router-link>
        </div>
    </div>   
</div>
<!-- <div v-else>
    {{$router.push('/home')}}
</div> -->
</template>

<script>
    import http from "../../http-common";
    export default {
        name: "addSeminar",
        data() {
            return {
                seminar: {
                    name: ""
                },
                submitted: false
            };
        },
        // computed: { // вычисляемые свойства
        //     currentUser() {
        //         return this.$store.state.auth.user;
        //     }
        // },
        methods: {
            addSeminar(e) {
                e.preventDefault(); 
                var data = {
                    name: this.seminar.name
                };
                console.log(data);
                http
                    .post("/addSeminar", data)
                    .then({ 
                        // this.seminar.id = response.data.id;
                    })
                    .catch(e => { 
                        console.log(e);
                    });

                this.submitted = true;
            },
            newSeminar() {
                this.submitted = false;
                this.seminar = {
                    name: ""
                };
            }
        }
    }
</script>