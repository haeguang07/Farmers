<template>
  <!-- 장바구니 내용 시작 -->
  <div class="" style="margin : 0 auto;">
    <!-- Contact Section Begin -->
    <section class="contact spad">
      <div class="container" style="border: 2px solid grey; padding-top: 50px;">
        <div class="row">
          <div class="col-lg-12 col-md-12">
            <div class="contact__text">
              <div class="section-title" style="text-align: center;">
                <h4>비밀번호 확인</h4>
                <p>본인 인증이 필요한 페이지입니다</p>
                <br>
                <label style="margin-right: 30px;">비밀번호확인</label><input id="pwCheck" type="text" class="form-control" style="display: inline; width: 300px; margin-right: 30px;">
                <button type="button" class="form-control" style="width: 100px; display: inline;" id="checkBtn">확인</button>
              </div>
            </div>
          </div>
    </section>
    <!-- Contact Section End -->
  </div>
  <!-- 장바구니 내용 끝 -->
</template>


<script th:inline="javascript">
  export default {
    data() {
      return {
        mem: this.$store.state.mem
      }
    },
    methods: {},
    mounted() {
      let thisVue = this;
      $('#checkBtn').on('click',function(){
        if(this.pw == $('#pwCheck').val()){
          console.log('match')
        }else{
          console.log('long')
        }
      })
    }
  }
</script>