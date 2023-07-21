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
            <div class="row">
          		<div class="col-3"> </div>
            	<div class="text-center col-6">
              		<button type="button" class="button form-control" @:click="pwFound">임시 비밀번호 발급</button>
            	</div>
            </div>
        </div>
    </div>
    <div v-show="box2">
      <div><span>해당 이메일로 임시 비밀번호를 전송 하였습니다.</span><a href="login">로그인</a></div>
      <br>
      <p>이메일이 도착하지 않았나요? <span @:click="pwFound" class="back">다시 보내기</span></p><br><br>
      
      <div class="text-right"><span @:click="back" class="back">돌아가기</span></div>
    </div>
    <div v-show="box3">조회된 회원 정보가 없습니다
      <br><br><br>
      <div class="text-right"><span @:click="back" class="back">돌아가기</span></div>
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
      fetch('pwFind',{
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email: this.email,
          id: this.id
        })
      })
      .then(resolve => resolve.json())
      .then(result=>{
        this.box1=false;
        if(result.retCode=='Success'){
          alert('이메일을 성공적으로 발송했습니다')
          this.box2=true;
          //성공시 이메일 발송
          fetch('sendEmailPw',{
            method:'post',
            headers: {
            "Content-Type": "application/json",
            },
            body: JSON.stringify({
            email: this.email,
            id: this.id,
            memNo: result.memNO
          })
          .then(resolve=> resolve.json())
          .then(data=> console.log(data))
          .catch(err=>console.log(err))
          })
        }else if(result.retCode=='Fail'){
          this.box3=true;
        }
      })
      .catch(err=> console.log(err))

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
  .back{
    font-size: small;
  }
</style>