<template>  
<div>
  <h3>문의 관리</h3>
  <br>
  <div class="row">
    <div class="col-1 fw-bolder lh-lg text-center">답변여부</div>
    <div class="col-2" style="width: 13%; flex: 0 0 13%;max-width: 13%;">
				<select class="form-select" id="addon-wrapping" v-model="searchStts"  @change="search">
					<option value="">전체</option>
					<option value="d0">답변대기</option>
					<option value="d1">답변완료</option>
				</select>
			</div>
      <div class="col-1 fw-bolder lh-lg text-center">카테고리</div>
			<div class="col-2" style="width: 13%; flex: 0 0 13%;max-width: 13%;">
				<select class="form-select" id="addon-wrapping" v-model="searchCtg"  @change="search">
					<option value="">전체</option>
					<option v-for="ctg in ctgList" :value="ctg.cmmnDetaCode" v-text="ctg.codeDesct"></option>
				</select>
      </div>
		</div>
    <div class="row">
      <div class="col-1 fw-bolder lh-lg text-center">작성일</div>
      <div class="col-2" style="width: 13%; flex: 0 0 14%;max-width: 14%;">
        <input type="date" class="form-select" v-model="searchStr"  @change="search">
      </div>
    </div>
  <v-data-table
    v-model:page="page"
    v-model:items-per-page="itemsPerPage"
    :headers="headers"
    :items="inquiryList"
    item-value="inqNo"
    no-data-text="조회된 문의가 없습니다"
    hide-default-footer
    @click:row=info
    class="elevation-1"
    >
    <template v-slot:bottom>
      <div class="text-center pt-2">
        <v-pagination v-model="page" :length="pageCount"></v-pagination>
      </div>
    </template>
  </v-data-table>

  <!-- 모달창 -->
  <div id="myModal" class="modal">
      <!-- 모달창 내용 -->
      <div class="modal-content">
        <span class="close">&times;</span>
        <div v-if="Object.keys(inquiry).length>0">
          <div>
            <table class="table">
							<tbody>
								<tr>
									<th>문의번호</th><td v-text="inquiry.inqNo"></td>
									<th>문의일자</th><td v-text="inquiry.inqDate"></td>
								</tr>
								<tr>
									<th>회원번호</th><td v-text="inquiry.memNo"></td>
									<th>닉네임</th><td v-text="inquiry.name"></td>
								</tr>
								<tr>
									<th>제목</th><td colspan="3" v-text="inquiry.inqTitle"></td>
								</tr>
                <tr>
                  <th>문의내용</th>
                  <td colspan="3" v-html="inquiry.inqDesct"></td>
                </tr>
                <tr>
                  <th>답변</th>
                  <td colspan="3" v-if="inquiry.replStts=='답변 대기'"> 
                    <ckeditor :editor="editor" v-model="inquiry.replDesct" :config="editorReplyConfig"></ckeditor>
                  </td>
                  <td colspan="3" v-else v-html="inquiry.replDesct"></td>
                </tr>
							</tbody>
						</table>
          </div>
          <div class="text-end">
            <button class="btn btn-success mb-3 mx-3"  v-if="inquiry.replStts=='답변 대기'" @click="reply">답변보내기</button>
            <button class="btn btn-primary mb-3 mx-3" @click="back">돌아가기</button>
          </div>
        </div>
      </div>
    </div>
	
</div>


</template>


<script>
import { VDataTable } from 'vuetify/labs/VDataTable'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import axios from 'axios'
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'

export default {
  data() {
    return {

      searchStts:'',
			searchStr:'',searchEnd:'',
      ctgList:[],searchCtg:'',
      editor: ClassicEditor,
      editorData: '<p>Content of the editor.</p>',
      editorDisabled: true,
      editorReplyConfig: {
        width: '100%',
        height: '400px'
      },
      idx:0,page:1,itemsPerPage: 10,
      inquiry:{}, inquiryList:[],
      headers:[{title: '문의번호',key: 'inqNo',align: 'center'},
	        { title: '카테고리',key: 'inqCtg'},
	        {title: '제목', key: 'inqTitle'},
					{title: '작성일',key: 'inqDate',align: 'center'},
					{title: '작성자',key: 'name'},
	        {title: '답변여부', key: 'replStts',align: 'center'}],
        multiSearch: {},
    };
  },
  components:{
    VDataTable,VueDatePicker 
  },
  methods:{
    info(event,inq){
     
     this.idx=inq.item.index
     this.inquiry=this.inquiryList[this.idx];
     this.onpenModal()
    },
    onpenModal(){
		  document.getElementById("myModal").style.display = "block";
	  },
    reply(){
      fetch('/admin/inquiryReply',{
        method: 'PUT',  
        headers: {
          'Content-Type': 'application/json', 
        },
        body:JSON.stringify(this.inquiry)
      })
      .then(result=>result.json())
      .then(result=> {
        if(result.retCode=='Success'){
          this.$swal({
              title: "답변을 성공적으로 보냈습니다",
              icon: "success",
              showConfirmButton:false,
              timer:1500
            });
          this.inquiryList[this.idx].replStts="답변 완료"
        }else{
          this.$swal({
              title: "답변을 전송하지 못하였습니다",
              icon: "error",
              showConfirmButton:false,
              timer:1500
            });
        }
        this.search();
      })
      .catch(err=>console.log(err))
      .finally(()=>this.back())
     
    },
    back(){
      document.getElementById("myModal").style.display = "none";
    },
	  callList(vo){
      axios.get("/admin/inquiryAdmin",{params: vo})
      .then(response => {
        this.inquiryList = response.data;
      })
      .catch(err => console.log(err));
	},
  search(){
    let obj={};
    obj.stts=this.searchStts;
    obj.end=this.searchEnd;
    obj.str=this.searchStr;
    obj.ctg=this.searchCtg;
    this.callList(obj)
  }
    
  },
  
  mounted(){
    this.callList({stts:'d0'})
    this.searchStts='d0';
    // Vuex에서 데이터 가져오기
		//this.ctgList = 
    axios.get('/admin/getCodes')
        .then(response => {
          this.ctgList=response.data['0F']
        })
        .catch(err=> console.log(err))
		window.onclick = function(event) {
  		if (event.target == document.getElementById("myModal")) {
				document.getElementById("myModal").style.display = "none";
				this.inquiry={};
  		}
		}
		document.getElementsByClassName("close")[0].addEventListener('click',function(){
			document.getElementById("myModal").style.display = "none";
			this.inquiry={};
		})
  },
  computed: {
      pageCount () {
        return Math.ceil(this.inquiryList.length / this.itemsPerPage)
      },
  },
  created() {
    document.title = "문의관리";
  }
};
</script>


<style>
.ck-content{
  height: 300px;

}
.ck-sticky-panel__content {
  width: 600px;
	height: 200px;
}
.ck-toolbar_grouping{
  width: 600px;
}
.row{
  margin: 10px;
}

</style>