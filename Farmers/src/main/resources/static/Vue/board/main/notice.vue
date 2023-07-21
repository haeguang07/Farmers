<script>
  export default {
    data() {
      return {
        boards: [],
        postCtg: 'i2',
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
              {data : 'title'},
              {data : 'nick'},
              {data : 'wrtDate'},
              {data : 'hitCount'}
            ]
        }
      
    },
    mounted(){
      this.boardList();
      
    },
    methods: {
      boardList() {
        // 선택한 카테고리 값을 서버에 전달하여 해당 카테고리의 게시판 목록 조회
        fetch(`boards?postCtg=${this.postCtg}`)
        .then(resp => resp.json())
        .then(data => {
          this.boards = data.boardList;
          console.log(data);
          let vue = this;
          $('#myTable').DataTable({
            destroy: true,
            language : vue.lang_kor,  
            ordering : false,
            searching: false,    //검색란 표시 설정
            data: vue.boards,
            columns: vue.columns
          })
        })
        .catch(err => console.log(err))
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
</style>

