<template>
    <v-container>
        <v-row justify="center">
            <v-col>
                <v-card>
                    <v-card-title class="text-center text-h5">
                        회원정보
                    </v-card-title>
                    <v-card-text>
                        <v-table>
                            <tbody>
                            <tr>
                                <td>ID : </td><td>{{ myinfo.id }}</td>
                            </tr>
                            <tr>
                                <td>이름 : </td><td>{{ myinfo.name }}</td>
                            </tr>
                            <tr>
                                <td>EMAIL : </td><td>{{ myinfo.email }}</td>
                            </tr>
                            <tr>
                                <td>주문건수 : </td><td>{{ myinfo.orderCount }}</td>
                            </tr>
                        </tbody>
                        </v-table>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
    <OrderListComponent
    :isMyPage="true"
    />
    
</template>

<script>
import OrderListComponent from '@/components/practice/OrderListComponent.vue';
import axios from 'axios';

export default{
    data(){
        return{
            myinfo:'',
        }
    },

    async created(){
         const response = await axios.get(`${process.env.VUE_APP_API_BASE_URL}/member/myinfo`);
         console.log(response);
         this.myinfo =response.data;
        },

    components:{
        OrderListComponent
    }
}


</script>