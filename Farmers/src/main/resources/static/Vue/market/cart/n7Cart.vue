<template>
 <!-- 장바구니 내용 시작 -->
        <div class="row col-lg-9">
          <div class="col-lg-12" style="margin-left: 0;">
            <div class="shopping__cart__table" style="margin-bottom: 0px;">
              <hr>
              <table>
                <thead>
                  <tr class="row">
                    <th class="col-lg-1"><input type="checkbox" class="form-check-input myZoom"
                        style="margin-left: 5px;" id="allCheck"></th>
                    <th class="col-lg-6">상품정보</th>
                    <th class="col-lg-2">수량</th>
                    <th class="col-lg-2">가격</th>
                    <th class="col-lg-1">삭제</th>
                  </tr>
                </thead>
                <tbody>

                </tbody>
              </table>
              <hr>
            </div>
          </div>
          <!-- 선택 삭제 버튼 -->
          <div class="col-lg-12" style="margin-bottom: 10px;">
            <button class="primary-btn myLeft" id="add"
              style="width: 100px; height: 40px; padding: 0px; background-color: lightgrey; border: 0;">선택삭제</button>
          </div>
          <!-- 총액 시작 -->
          <div class="col-lg-12" style="padding: 0px;">
            <div class="cart__total">
              <ul>
                <li>
                  <h5>
                    총액 <span>$ 169.50</span>
                  </h5>
                </li>
              </ul>
              <a href="#" class="primary-btn">구매하기</a>
            </div>
          </div>
          <!-- 총액 끝 -->
        </div>
        <!-- 장바구니 내용 끝 -->
</template>

<script th:inline="javascript">
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

  // 돈 콤마
  function priceToString(price) {
    return price.toLocaleString('ko-KR')
  }
</script>