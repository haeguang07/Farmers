<template>
  <!-- 장바구니 내용 시작 -->
  <div class="row col-lg-9">
    <div class="col-lg-12" style="margin-left: 0;">
      <div class="shopping__cart__table" style="margin-bottom: 0px;">
        <hr>
        <table>
          <thead>
            <tr class="row">
              <th class="col-lg-1"><input type="checkbox" v-on:change="allCheck" class="form-check-input myZoom"
                  style="margin-left: 5px;" id="allCheck"></th>
              <th class="col-lg-6">상품정보</th>
              <th class="col-lg-2">수량</th>
              <th class="col-lg-2">가격</th>
              <th class="col-lg-1">삭제</th>
            </tr>
          </thead>
          <tbody id="tbody">

          </tbody>
        </table>
        <hr>
      </div>
    </div>
    <!-- 선택 삭제 버튼 -->
    <div class="col-lg-12" style="margin-bottom: 10px;">
      <button class="primary-btn myLeft" id="add"
        style="width: 100px; height: 40px; padding: 0px; background-color: lightgrey; border: 0;"
        v-on:click="seletedDelete">선택삭제</button>
    </div>
    <!-- 총액 시작 -->
    <div class="col-lg-12" style="padding: 0px;">
      <div class="cart__total">
        <ul>
          <li>
            <h5>
              총액 <span id="allSumPrice">0</span>
            </h5>
          </li>
        </ul>
        <a href="" class="primary-btn" v-on:click="getPay">구매하기</a>
      </div>
    </div>
    <!-- 총액 끝 -->
    <!-- clone용 태그 -->
    <tr class="row product hideItem" id="cartItem"  style="padding-bottom : 20px">
      <td class="col-lg-1"><input type="checkbox" class="form-check-input myZoom mySelect" style="margin-left: 5px;"></td>
      <td class="product__cart__item col-lg-6">
        <div class="product__cart__item__pic">
          <img id="cartImage" width="100" height="100">
        </div>
        <div class="product__cart__item__text">
          <h6 id="title">상품명</h6>
          <h5 id="price">상품가격</h5>
        </div>
      </td>
      <td class="quantity__item col-lg-2">
        <div class="quantity">
          <div class="pro-qty-my" style="padding-top: 20px;">
            <span class="fa fa-angle-left dec qtybtn"></span>
            <input type="text" value="1" id="qty" class="qty" readonly>
            <span class="fa fa-angle-right inc qtybtn"></span>
          </div>
        </div>
      </td>
      <td class="cart__price col-lg-2">
        <p style="padding-top: 20px;" id="sumPrice">총액</p>
      </td>
      <td class="cart__close col-lg-1" style="padding-top: 45px;"><span><i class="fa fa-close"></i></span></td>
    </tr>

  </div>
  <!-- 장바구니 내용 끝 -->
</template>


<script th:inline="javascript">
  export default {
    data() {
      return {
        cartList: [],
        mem: this.$store.state.mem
      }
    },
    methods: {
      //장바구니 아이템 만들기
      makeCartItem: function (list) {
        let vuethis = this;
        $('#tbody').children().remove()

        //CartList for문
        $(list).each(function (idx, item) {

          let clone = $('#cartItem').clone();
          $(clone).removeClass('hideItem')
          $(clone).addClass('printItem')
          $(clone).attr('cartNo', item.cartNo)
          $(clone).attr('boardNo', item.boardNo)
          $(clone).attr('boardCtg', item.boardCtg)
          $(clone).attr('productQty', item.productQty)
          $(clone).find('#cartImage').attr('src', item.rep)
          ///////////////타이틀 바꾸기///////////////
          $(clone).find('#title').text(item.title)
          $(clone).find('#price').text(vuethis.priceToString(item.price) + '원')
          $(clone).find('#price').attr('dataPrice', item.price)
          $(clone).find('#qty').val(item.qty)
          $(clone).find('#qty').attr('dataQty', item.qty)
          $(clone).find('#sumPrice').text(vuethis.priceToString((item.qty) * (item.price)) + '원')
          $(clone).find('.mySelect').attr('id','myBox')
          //삭제버튼 이벤트
          $(clone).find('.fa-close').on('click', function (e) {
            vuethis.deleteData($(e.target).closest('tr'))
            $(e.target).closest('tr').remove()
            vuethis.allSumPriceCheck()
          })
          //수량 버튼 클릭 시 수량 변경
          $(clone).find('.fa-angle-left').on('click', function () {
            if ($(clone).find('#qty').val() > 1) {
              $(clone).find('#qty').val(Number($(clone).find('#qty').val()) - 1)
              $(clone).find('#qty').attr('dataQty', Number($(clone).find('#qty').val()))
              $(clone).find('#sumPrice').text(vuethis.priceToString(($(clone).find('#qty').val()) * (item
                .price)) + '원')
              // $(clone).find('#sumPrice').fadeOut(100 , function () {
              //   $(clone).find('#sumPrice').fadeIn('fast');
              // });
              $(clone).find('#sumPrice').toggle(0,function(){
                $(clone).find('#sumPrice').toggle('fast');
              });
              vuethis.allSumPriceCheck();
            }
          })
          $(clone).find('.fa-angle-right').on('click', function () {
            if (Number($(clone).find('#qty').val()) < Number($(clone).attr('productQty'))) {
            $(clone).find('#qty').val(Number($(clone).find('#qty').val()) + 1)
            $(clone).find('#qty').attr('dataQty', Number($(clone).find('#qty').val()))
            $(clone).find('#sumPrice').text(vuethis.priceToString(($(clone).find('#qty').val()) * (item
              .price)) + '원')
            $(clone).find('#sumPrice').toggle(0,function(){
              $(clone).find('#sumPrice').toggle('fast');
            });
            vuethis.allSumPriceCheck();
            } else {
              new swal({
		                		title: "초과된 수량입니다",
		                		icon : "warning",
		                		confirmButtonColor: '#95D083'		
		                })
           }
          })
          //개별 체크박스 이벤트
          $(clone).find('input[type="checkbox"]').change(function (e) {
            vuethis.allSumPriceCheck();
            vuethis.checkMySelect();
            if (!$(e.target).is(':checked')) {
              $('#allCheck').prop('checked', false)
            }
          })

          $('#tbody').append(clone)
        })
        vuethis.allSumPriceCheck();
      },
      //돈 콤마 찍기 함수
      priceToString: function (price) {
        return price.toLocaleString('ko-KR')
      },
      //전체 선택 박스
      allCheck: function (e) {
        if (!$(e.target).is(':checked')) {
          $('input[type="checkbox"]').prop('checked', false)
        } else {
          $('input[type="checkbox"]').prop('checked', true)
        }
        this.allSumPriceCheck()
      },
      //선택항목 삭제
      seletedDelete: function () {
        let vuethis = this;
        $("input[type='checkbox']:checked").closest('tr').each(function (idx, item) {
          if ($(item).hasClass('product')) {
            vuethis.deleteData(item)
            $(item).remove();
            $('#allCheck').prop('checked', false)
          }
          vuethis.allSumPriceCheck()
        });
      },
      //전체 금액 계산
      allSumPriceCheck: function () {
        let sum = 0;
        $('.printItem').each(function (idx, item) {
          if ($(item).find('.myZoom').is(':checked')) {
            sum = sum + ($(item).find('#price').attr('dataPrice') * $(item).find('#qty').attr('dataQty'))
          }
        })
        $('#allSumPrice').text(this.priceToString(sum) + '원')
      },
      //DB 데이터 삭제
      deleteData: function (tr) {
        let cNo = $(tr).attr('cartNo')
        $.ajax({
            url: "/deleteCart",
            data: {
              cartNo: cNo
            }
          })
      },
      //결제 넘기기
      getPay: function (e) {
        e.preventDefault()
        //선택한 상품이 있는지 체크
        if ($('.mySelect').is(':checked')) {
          let productList = [];
          $('.printItem').each(function (idx, item) {
            if ($(item).find('.myZoom').is(':checked')) {

              //배열에 담을 구매상품정보 객체 생성
              let obj = {
                boardNo: $(item).attr('boardNo'),
                qty: $(item).find('#qty').val(),
                boardCtg: $(item).attr('boardCtg')
              }

              //배열에 객체  담기
              productList.push(obj)
            }
          })

          //상품객체배열을 json 변환 후 결제피이지 파라미터로 보냄 (한글, 특수문자가 url 인코딩이 안되서 encodeURI 사용)
          //파라미터를 url에서 가리고 싶으면 form 태그 생성 해서 사용 (https://amongthestar.tistory.com/178)
          location.href = "/payment?productList=" + encodeURI(JSON.stringify(productList));
        } else {
          new swal({
		                		title: "구매할 상품을 선택해주세요",
		                		icon : "warning",
		                		confirmButtonColor: '#95D083'		
		                })
        }
      },
      //체크박스 전체 확인
      checkMySelect : function(){
        let result = true;
        $('.mySelect').each(function(idx, item){
          if(!$(item).is(':checked') && $(item).attr('id') == 'myBox'){
            result = false;
          }
        })
       
        if(result){
          $('#allCheck').prop('checked', true)
        }
      }
    },
    mounted() {
      let vuethis = this;
      // 페이지 로드 시 ajax로 카드리스트 가져옴
      $.ajax({
          url: "/cart",
          method: "POST",
          data: {
            memNo: vuethis.mem.memNo,
            boardCtg : 'n8'
          }
        })
        .done(function (data, status, xhr) {
          vuethis.cartList = data
          vuethis.makeCartItem(vuethis.cartList)
        })
        .fail(function () {
          console.log('fail')
        })
    },
    props: ["val"]
  }
</script>