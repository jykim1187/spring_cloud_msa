<template>
    <h1>store componentB</h1>
    <p>일반변수값 count : {{ count }}</p>
    <p>store변수값 count : {{ getStoreCount }}</p>
    <p>store변수값 message : {{ getStoreMessage }}</p>

   
</template>
    
<script>
export default{
    data(){
        return{
            count: 0,
            message:"",
        }
    },
    computed:{
        getStoreCount(){
            return this.$store.getters.getCount;
        },
        getStoreMessage(){
            return this.$store.getters.getMessage;
        }
        }
    }
    


</script>