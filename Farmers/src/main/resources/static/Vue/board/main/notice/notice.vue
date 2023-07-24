<script>
export default {
    data() {
      return {
        boards: [],
        lang_kor : {
	        "decimal" : "",
	        "emptyTable" : "데이터가 없습니다.",
	        "info" : "_START_ - _END_ (총 _TOTAL_ 개)",
	        "infoEmpty" : "0명",
	        "infoFiltered" : "(전체 _MAX_ 명 중 검색결과)",
	        "infoPostFix" : "",
	        "thousands" : ",",
	        "lengthMenu" : "_MENU_ 개씩 보기",
	        "loadingRecords" : "로딩중...",
	        "processing" : "처리중...",
	        "search" : "검색 : ",
	        "zeroRecords" : "검색된 데이터가 없습니다.",
	        "paginate" : {
	            "first" : "첫 페이지",
	            "last" : "마지막 페이지",
	            "next" : "다음",
	            "previous" : "이전"
	        },
	        "aria" : {
	            "sortAscending" : " :  오름차순 정렬",
	            "sortDescending" : " :  내림차순 정렬"
	        }
        },
          columns : [
              {data : 'num',
                render: function (data, type, row, meta) {
						            // meta.row는 0부터 시작하는 행의 인덱스입니다.
						            // 순번은 1부터 시작해야 하므로 meta.row + 1을 반환합니다.
						            return meta.row + 1;
						          }
						  },
              {data : 'title',
                render: function (data, type, row) {
                  return `<a href="noticeInfo" class="title-link" data-boardNo="${row.boardNo}">${data}</a>`;}
              },
              {data : 'nick'},
              {data : 'wrtDate'},
              {data : 'hitCount'}
            ]
        }
      
    },
    mounted(){
      let vue = this;
      let postCtg = 'i2';

      $.ajax({
        url: "boardList",
        method: "GET",
        data: { postCtg },
        success: function(data){
          vue.boards = data.boardList;
          console.log(vue.boards);

          // //let tr = $('#myTable tbody').append('<tr />');
          // $(vue.boards).each(function(idx, item){
          //   let tr = $('<tr>')
          //   tr.append($('<td>').text('aa'))
          //   tr.append($('<td>').append($('<button>').text('버튼').on('click', () => vue.gets(item.boardNo))))
          //     tr.append($('<td>').text('aa'))
          //       tr.append($('<td>').text('aa'))
          //         tr.append($('<td>').text('aa'))

          //   $('#tbody').append(tr)
          // })

          $('#myTable').DataTable({
            destroy: true,
            language : vue.lang_kor,  
            ordering : false,
            searching: false,   //검색란 표시 설정
            data: vue.boards,
            columns: vue.columns
          })
        },
        error: function(err){
          console.log(err);
        }
      })

      // DataTable에서 생성된 링크 클릭 이벤트에 함수 연결
      $('#myTable').on('click', '.title-link', vue.handleTitleLinkClick);
    },
    methods: {
      handleTitleLinkClick(event) {
      event.preventDefault();
      let boardNo = $(event.target).attr('data-boardNo');
      console.log(boardNo);
      this.$router.push({ name: 'noticeInfo', params: { boardNo: boardNo } });
    }
  }
}
</script>

<template>
  <!-- 공지사항 -->
  <div class="row col-lg-9">
    <div class="col-lg-12" style="margin-left: 0;">
      <h4 class="center">공지사항</h4>
      <div class="shopping__cart__table" style="margin-bottom: 0px;">
        <table id="myTable" class="center">
          <thead>
            <hr>
            <tr class="center">
              <th class="col-lg-1">순번</th>
              <th class="col-lg-5">제목</th>
              <th class="col-lg-2">작성자</th>
              <th class="col-lg-2">작성일</th>
              <th class="col-lg-2">조회수</th>
            </tr>
          </thead>
          <!-- <tbody id="tbody">
            <tr v-for="board in boards">
              <td>1</td>
              <td><router-link :to="{name:'boardInfo', params: {'boardNo' : board.boardNo}}">{{ board.title }}</router-link></td>
              <td>{{ board.nick }}</td>
              <td>{{ board.wrtDate }}</td>
              <td>{{ board.hitCount }}</td>
            </tr>
          </tbody> -->
        </table>
        <hr>
      </div>
    </div>
  </div>
</template>

<style>
  h4 {
    font-weight: bold;
  }
  .center {
    text-align: center;
  }
  #myTable {
    width: 100%
  }
</style>