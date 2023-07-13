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
        style="width: 100px; height: 40px; padding: 0px; background-color: lightgrey; border: 0;" v-on:click="seletedDelete">선택삭제</button>
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
        <a href="#" class="primary-btn">구매하기</a>
      </div>
    </div>
    <!-- 총액 끝 -->
    <!-- clone용 태그 -->
    <tr class="row product hideItem" id="cartItem">
      <td class="col-lg-1"><input type="checkbox" class="form-check-input myZoom" style="margin-left: 5px;"></td>
      <td class="product__cart__item col-lg-6">
        <div class="product__cart__item__pic">
          <img src="" alt="">
        </div>
        <div class="product__cart__item__text">
          <h6 id="title">상품명</h6>
          <h5 id="price">상품가격</h5>
        </div>
      </td>
      <td class="quantity__item col-lg-2">
        <div class="quantity">
          <div class="pro-qty-2" style="padding-top: 20px;">
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
          console.log(item)
          let clone = $('#cartItem').clone();
          $(clone).removeClass('hideItem')
          $(clone).find('#title').text(item.crpTitle)
          $(clone).find('#price').text(vuethis.priceToString(item.price) + '원')
          $(clone).find('#qty').val(item.qty)
          $(clone).find('#sumPrice').text(vuethis.priceToString((item.qty) * (item.price)) + '원')
          $(clone).find('#sumPrice').attr('val',(item.qty) * (item.price))
          //수량 버튼 클릭 시 수량 변경
          $(clone).find('.fa-angle-left').on('click', function () {
            if ($(clone).find('#qty').val() > 1) {
              $(clone).find('#qty').val(Number($(clone).find('#qty').val()) - 1)
              $(clone).find('#sumPrice').text(vuethis.priceToString(($(clone).find('#qty').val()) * (item
                .price)) + '원')
              $(clone).find('#sumPrice').attr('val',(item.qty) * (item.price))
            }
          })
          $(clone).find('.fa-angle-right').on('click', function () {
            $(clone).find('#qty').val(Number($(clone).find('#qty').val()) + 1)
            $(clone).find('#sumPrice').text(vuethis.priceToString(($(clone).find('#qty').val()) * (item
              .price)) + '원')
            $(clone).find('#sumPrice').attr('val',(item.qty) * (item.price))
          })
          //개별 체크박스 이벤트
          $(clone).find('input[type="checkbox"]').change(function (e) {
            if (!$(e.target).is(':checked')) {
              $('#allCheck').prop('checked', false)
            }
          })

          $('#tbody').append(clone)
        })
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
      },
      //선택항목 삭제
      seletedDelete : function(){
        $("input[type='checkbox']:checked").closest('tr').each(function(idx,item){
          console.log(item)
            if($(item).hasClass('product')){
              $(item).remove();
              $('#allCheck').prop('checked', false)
            }
        });
      }
    },
    mounted() {
      let vuethis = this;
      // 페이지 로드 시 ajax로 카드리스트 가져옴
      $.ajax({
          url: "cart",
          method: "POST",
          data: {
            memNo: vuethis.mem.memNo
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
























  //테스트
  $(function () {
    $( /*[[${cartList}]]*/ ).each(function (index, item) {
      console.log(item)
    })

    //로드 시 총액 계산
    $('.product').each(function (index, item) {
      sumCheck1(item)
    })

    //수량 변경 시 value 변경
    $('.fa-angle-right').on('click', function (e) {
      sumCheck2($(e.target).closest('tr'))
    })
    $('.fa-angle-left').on('click', function (e) {
      sumCheck3($(e.target).closest('tr'))
    })
  })

  //전체 체크 선택 박스 기능
  $('#allCheck').on('click', function () {
    if (!$('#allCheck').is(':checked')) {
      $('input[type="checkbox"]').prop('checked', false)
    } else {
      $('input[type="checkbox"]').prop('checked', true)
    }
  })

  // 수량 * 가격 구하기 함수
  //함수 분리 이유 (value값이 변하기 전에 해당 함수가 실행되고 value가 변경 되어 변경 되는 value값이 적용이 안됨 -> 임의로 숫자를 조정 )
  function sumCheck1(product) {
    let qty = $(product).find('#qty').val();
    if (qty > 0) {

      let price = $(product).find('#price').attr('data')

      $(product).find('#sumPrice').text(
        priceToString(qty * price) + '원');
      $(product).find('#sumPrice').attr("data-value",
        (qty * price))
    }
  }

  function sumCheck2(product) {
    let qty = $(product).find('#qty').val();
    if (qty > 0) {
      let price = $(product).find('#price').attr('data')

      $(product).find('#sumPrice').text(
        priceToString((Number(qty) + 1) * price) + '원');
      $(product).find('#sumPrice').attr("data-value",
        (qty * price))
    }
  }

  function sumCheck3(product) {
    let qty = $(product).find('#qty').val();
    if (qty > 0) {
      let price = $(product).find('#price').attr('data')

      $(product).find('#sumPrice').text(
        priceToString((Number(qty) - 1) * price) + '원');
      $(product).find('#sumPrice').attr("data-value",
        (qty * price))
    }
  }
</script>