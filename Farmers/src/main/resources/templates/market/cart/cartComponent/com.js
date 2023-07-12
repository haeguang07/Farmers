Export Default Vue.component ('test-component' ,{
template:'
<div>
<!-- 제목 시작 -->
        <div class="justify-content-center">
            <br>
            <h3 class="current text-center">장바구니</h3>
        </div>
        <!-- 제목 끝 -->
        <!-- 본문 시작 -->
        <section class="shopping-cart spad" style="padding-top: 10px;">
            <div class="container">
                <hr>
                <br>
                <div class="row">
                    <!-- 사이드바 시작 -->
                    <div class="col-lg-3">
                        <div class="shop__sidebar">
                            <div class="shop__sidebar__accordion">
                                <div class="accordion" id="accordionExample">
                                    <div class="card">
                                        <div class="card-heading">
                                            <a data-toggle="collapse" data-target="#collapseOne"
                                                style="font-size: larger;">장바구니</a>
                                        </div>
                                        <div id="collapseOne" class="collapse show" data-parent="#accordionExample">
                                            <div class="card-body">
                                                <div class="shop__sidebar__categories">
                                                    <ul>
                                                        <li><a href="fundingList" class="mySelect"
                                                                style="color: black;">농작물판매</a></li>
                                                        <li><a href="#">농기구판매</a></li>
                                                        <li><a href="#">전원마켓</a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 사이드바 끝 -->
                    <!-- 장바구니 내용 시작 -->
                    <div class="row col-lg-9">
                        <div class="col-lg-12" style="margin-left: 0;">
                            <div class="shopping__cart__table" style="margin-bottom: 0px;">
                                <hr>
                                <table >
                                    <thead>
                                        <tr class="row">
                                            <th class="col-lg-1"><input type="checkbox" class="form-check-input myZoom"
                                                    style="margin-left: 5px;"></th>
                                            <th class="col-lg-6">상품정보</th>
                                            <th class="col-lg-2">수량</th>
                                            <th class="col-lg-2">가격</th>
                                            <th class="col-lg-1">삭제</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="row">
                                            <td class="col-lg-1"><input type="checkbox" class="form-check-input myZoom"
                                                    style="margin-left: 5px;"></td>
                                            <td class="product__cart__item col-lg-6">
                                                <div class="product__cart__item__pic">
                                                    <img src="img/shopping-cart/cart-1.jpg" alt="">
                                                </div>
                                                <div class="product__cart__item__text">
                                                    <h6>T-shirt Contrast Pocket</h6>
                                                    <h5>$98.49</h5>
                                                </div>
                                            </td>
                                            <td class="quantity__item col-lg-2">
                                                <div class="quantity">
                                                    <div class="pro-qty-2" style="padding-top: 20px;">
                                                        <input type="text" value="1">
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="cart__price col-lg-2"><p style="padding-top: 20px;">$ 30.00</p></td>
                                            <td class="cart__close col-lg-1" style="padding-top: 45px;"><span><i class="fa fa-close"></span></i></td>
                                        </tr>
                                    </tbody>
                                </table>
                                <hr>
                            </div>
                        </div>
                        <!-- 선택 삭제 버튼 -->
                        <div class="col-lg-12" style="margin-bottom: 10px;">
                            <button class="primary-btn myLeft" id="add" style="width: 100px; height: 40px; padding: 0px; background-color: lightgrey; border: 0;">선택삭제</button>
                        </div>
                        <!-- 총액 시작 -->
                        <div class="col-lg-12" style="padding: 0px;">
                            <div class="cart__total">
                                <ul>
                                    <li><h5>총액 <span>$ 169.50</span></h5></li>
                                </ul>
                                <a href="#" class="primary-btn">구매하기</a>
                            </div>
                        </div>
                        <!-- 총액 끝 -->
                    </div>
                    <!-- 장바구니 내용 끝 -->
                </div>
            </div>
        </section>
        <!-- 본문 끝 -->
</div>
'
}