<template>
  <div class="border">
    <!-- 비밀번호 -->
    <div class="">
        <h3>비밀번호 찾기</h3>
    </div>
    <div v-show="box1">

        <div class="">
            <div class="text-center">
                <div><label>아이디</label><input type="text" name="id" id="id" v-model="id" /></div>
                <div class="">
                   <label>이메일</label><input type="text" id="email" name="email" v-model="email">
                </div>
            </div>
            <div class="text-center">
              <button type="button" class="button" @:click="pwFound">임시 비밀번호 발급</button>
            </div>
        </div>
    </div>
    <div v-show="box2">
      <span>해당 이메일로 임시 비밀번호를 전송 하였습니다.</span>
      <br>
      <p>이메일이 도착하지 않았나요? <span>다시 보내기</span></p><br><br>
      <div class="text-right"><span @:click="back">돌아가기</span></div>
    </div>
    <div v-show="box3">조회된 회원 정보가 없습니다
      <br><br><br>
      <div class="text-right"><span @:click="back">돌아가기</span></div>
    </div>

  </div>

</template>

<script>
export default{
  data(){
    return{
      id:'',
      email:'',
      box1:true,
      box2:false,
      box3:false,
      memNo:''
    }
  },
  methods:{
    pwFound(){

      this.box1=false;

      //성공시
      this.memNo='mem000001';
      if(this.memNo!=''){
        this.box2=true;
      }else{
        this.box3=true;
      }
    },
    back(){
      this.id='',
      this.email='',
      this.box1=true,
      this.box2=false,
      this.box3=false,
      this.memNo=''
    }
  }
}
</script>

<style>
  label{
    display: inline-block;
    width: 100px;
  }
  .border{
  	width: 500px;
    height: 300px;
   	margin: 50px auto;
   	padding : 50px;
   }
   button{
   	margin-top: 20px;
   }
   .text-right{
	  color : blue;
	  cursor: pointer;
	}
</style>