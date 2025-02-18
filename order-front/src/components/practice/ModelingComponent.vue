<template>
<h1>hello world {{language}}</h1>
<!-- v-model문법을 사용하면 양방향 데이터 바인딩이 가능하다 -->
<input type="text" v-model="value">
<button @click="showValue()">변수변경사항 확인</button><br>
<button @click="changeValue()">변수 변경</button>
</template>

<script>
export default{
     data(){
          return{
               language:"java",
               value:"python"
          }
     },
     // /화면이 생성되자마자(vue인스턴스가 생성되기 전)실행되는 함수
     created(){
          alert("created함수 호출");
     },
     methods : {
          showValue(){
               alert(this.value);
          },
           changeValue(){
               this.value ="c++";
           },
     },
}
</script>
