<template>
<h1>store componentA</h1>
<p>일반변수값 count : {{ count }}</p>
<v-btn @click="incrementCount()">count값 증가</v-btn>
<p>store변수값 count : {{ getStoreCount }}</p>
<v-btn @click="incremenStoreCount()">store count값 증가</v-btn>
<br><br>
<input type="text" v-model="message"/>
<p>store변수값 message : {{ getStoreMessage }}</p>
<v-btn @click="updateMessage()">store message 변경</v-btn>

</template>

<script>
export default{
    data(){
        return{
            count: 0,
            message: "",
        }
    },
    computed:{
        getStoreCount(){
            return this.$store.getters.getCount;
        },
        getStoreMessage(){
            return this.$store.getters.getMessage;
        }
    },
    methods:{
        incrementCount(){
            this.count++;
        }
        ,incremenStoreCount(){
            //vuex에서 actions를 호출할 때는 dispatch를 사용
            this.$store.dispatch('increment');
        },
        updateMessage(){
            this.$store.dispatch('updateMessage',this.message);
        }

    }
}

</script>