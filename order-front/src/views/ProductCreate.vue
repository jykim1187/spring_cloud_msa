<template>
    <v-container>
        <v-row justify="center">
            <v-col cols="12" sm="4" md="6">
                <v-card>
                    <v-card-title class="text-h5 text-center">
                        상품등록
                    </v-card-title>
                    <v-card-text>
                        <v-form>
                            <v-text-field
                            label="상품명"
                            v-model="name"
                            required
                            />
                            <v-text-field
                            label="카테고리"
                            v-model="category" 
                            required
                            />
                            <v-text-field
                            label="가격"
                            v-model="price"
                            required
                            />
                            <v-text-field
                            label="재고수량"
                            v-model="stockQunatity"
                            required
                            />
                            <v-file-input
                            label="상품이미지"
                            accpet="image/**"
                            @change="fileUpload"                   
                            />
                            <!-- fileUpload뒤에 ()붙이면 안됨. 여기서는 event매개변수가 주입되기 때문에 -->
                            <!-- 파일여러개 원하면 multiple옵션 붙이면 됨 -->
                            <v-row>
                                <v-col cols="12">
                                    <v-btn color="yellow" block @click="register()">제출</v-btn>
                                </v-col>
                            </v-row>
                        </v-form>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import axios from 'axios';

export default{
    data(){
        return{
            namme:"",
            category:"",
            price:null,
            stockQunatity:null,
            productImage:null,
        }
    },

    methods:{
        fileUpload(event){
            // 나중에 여러파일 첨부받으려면 변수도 배열로 받아야한다.
            this.productImage =event.target.files[0];

            },
        async register(){
            try{
                let registerData = new FormData();
            registerData.append("name",this.name);
            registerData.append("category",this.category);
            registerData.append("price",this.price);
            registerData.append("stockQuantity",this.stockQunatity);
            registerData.append("productImage",this.productImage);
// 폼데이터니까 {}로 감쌀 필요 없이 보내면 된다
            await axios.post(`${process.env.VUE_APP_API_BASE_URL}/product/create`,registerData);
            this.$router.push("/product/manage");
                
            }catch(e){
                alert("상품등록에 실패했습니다.");
            }
        }

    }
}


</script>