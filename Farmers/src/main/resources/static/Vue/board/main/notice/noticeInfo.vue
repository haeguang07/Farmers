<script>
export default {
    name: 'noticeInfo',
    data() {
        return {
            boardInfo: {},
            recoms : []
        }
    },
    mounted() {
        let vue = this;
        let postCtg = 'i2';
        console.log(this.boardNo)
        let boardNo = this.boardNo;

        $.ajax({
            url: "boardInfo",
            method: "GET",
            data: { postCtg,
                    boardNo },
            success: function(data){
                vue.boardInfo = data.boardInfo[0];
                vue.recoms = data.boardInfo[0].recoms;
                console.log(vue.boardInfo);
            },
            error: function(err){
                console.log(err);
            }
        })

    },
    props: {
        boardNo: {
            type: String,
            default: "1",
        }
    },
    computed: {
        numRecom : function() {
            return this.recoms.length;
        }
    }
}
</script>

<template class="col-lg-9">
<div>
    <!-- Blog Details Hero Begin -->
    <section class="blog-hero spad">
        <div class="container">
            <div class="row d-flex justify-content-center">
                <div class="col-lg-9 text-center">
                    <div class="blog__hero__text">
                        <h2 >{{ boardInfo.title }}</h2>
                        <ul>
                            <li>{{ boardInfo.nick }}</li>
                            <li>{{ boardInfo.wrtDate }}</li>
                            <li>조회 {{ boardInfo.hitCount }}</li>
                            <li>추천 {{ numRecom }}</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Blog Details Hero End -->

    <section class="blog-details spad">
        <div class="container">
            <div class="row d-flex justify-content-center">
                <div class="col-lg-9">
                    <div class="blog__details__pic">
                        <img src="img/blog/details/blog-details.jpg" alt="">
                    </div>
                </div>
                <div class="col-lg-8">
                    <div class="blog__details__content">
                        <div class="blog__details__text">
                            <p>{{ boardInfo.desct }}</p>
                            <p>Hydroderm is the highly desired anti-aging cream on the block. This serum restricts the
                                occurrence of early aging sings on the skin and keeps the skin younger, tighter and
                                healthier. It reduces the wrinkles and loosening of skin. This cream nourishes the skin
                                and brings back the glow that had lost in the run of hectic years.</p>
                        </div>
                        <div class="blog__details__btns">
                            <div class="row">
                                <div class="col-lg-6 col-md-6 col-sm-6">
                                    <a href="" class="blog__details__btns__item">
                                        <p><span class="arrow_left"></span> Previous Pod</p>
                                        <h5>It S Classified How To Utilize Free Classified Ad Sites</h5>
                                    </a>
                                </div>
                                <div class="col-lg-6 col-md-6 col-sm-6">
                                    <a href="" class="blog__details__btns__item blog__details__btns__item--next">
                                        <p>Next Pod <span class="arrow_right"></span></p>
                                        <h5>Tips For Choosing The Perfect Gloss For Your Lips</h5>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div>
                            <button type="button"><router-link tag="boardList" to="/">목록</router-link></button>
                        </div>
                        <div class="blog__details__comment">
                            <h4>Leave A Comment</h4>
                            <form action="#">
                                <div class="row">
                                    <div class="col-lg-4 col-md-4">
                                        <input type="text" placeholder="Name">
                                    </div>
                                    <div class="col-lg-4 col-md-4">
                                        <input type="text" placeholder="Email">
                                    </div>
                                    <div class="col-lg-4 col-md-4">
                                        <input type="text" placeholder="Phone">
                                    </div>
                                    <div class="col-lg-12 text-center">
                                        <textarea placeholder="Comment"></textarea>
                                        <button type="submit" class="site-btn">Post Comment</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
</template>