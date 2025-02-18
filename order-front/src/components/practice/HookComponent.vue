<template>

<h1>안녕하세요</h1>
<button @click="moveComponent()">화면이동</button>
<h1>{{ count1 }}</h1>
<button @click="incrementCount1()" >incrementCount1</button>
<h1>{{doubleCount1}}</h1>

</template>
<script>    

export default{

        data(){
            return{
                count1:0
            }

        },
        // 화면이 열리기 전(vue인스턴스가 생성되기 전)실행되는 함수
        created(){
            // alert("훅 함수 입니다");
        },
        // 컴포넌트가 화면에서 제거될 때 실행되는 함수(화면종료, 라우트 이동시 )
        beforeUnmount(){
            // alert("화면 종료"); 
        },
        // 특정 변수값의 변경사항을 인지하는 hook함수
        watch:{
            //인지하고자 하는 변수값과 일치되는 함수명을 사용
            count1(){
                // alert("count1값 변경")
            }
        },
        // 종속된 데이터가 변경될 때 함수를 다시 실행하여 값을 계산하는 함수
        computed:{
            doubleCount1(){
                return this.count1 *2;
            }
        },
        updated(){
            alert("화면에 요소가 변경되었습니다.")
        },
        // 화면에 어떠한 것이든지 변경사항에 대해 인지하는 함수
        methods :{
                moveComponent(){
                    this.$router.push("/practice/vuetify")
                },
                incrementCount1(){
                    this.count1++;
                }
        },




}



</script>