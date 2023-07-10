<template>
  <div class="border">
    <div >
        <h3>아이디 찾기</h3>
    </div>
    <div v-show="box1">
      <div class="member">

        <div class="text-center">
              <div><label>이름</label><input type="text" id="name" name="name"   v-model="name"></div>
              <div><label>이메일</label><input type="text" id="email" name="email" v-model="email"></div>
          </div>
          <div class="text-center">
              <button type="button" class="button" @:click="idFound">아이디 찾기</button>
          </div>
      </div>
    </div>
    <div v-show="box2">
      <span>회원님의 아이디는 {{ id }} 입니다</span>
      <br>
      <span @:click="back">돌아가기</span>

    </div>
    <div v-show="box3">조회된 회원 정보가 없습니다
     <br><br><br><br>
      <div class="text-right"><span @:click="back">돌아가기</span></div>
     </div>
  </div>

</template>

<script>


export default{
  data(){
    return{
      name:'',
      email:'',
      id:'',
      box1:true,
      box2:false,
      box3:false
    }
  },
  methods:{
    idFound(){
      let obj={name: this.name, email: this.email}
      console.log(obj);
      //아래 내용을 ajax 호출하기
      this.id='';
      this.box1=false
      if(this.id!=''){
        this.box2=true;
      }else{
        this.box3=true;
      }
    },
    back(){
      this.name='';
      this.email='';
      this.id='';
      this.box1=true,
      this.box2=false,
      this.box3=false
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