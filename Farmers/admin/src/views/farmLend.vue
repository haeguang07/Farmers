<template>

	<div class="body">
		<h3>농지대여</h3>
		<br>
		<div class="row">
			<div class="col-1 fw-bolder lh-lg text-center">상태</div>
			<div class="col-1" style="width: 13%; flex: 0 0 13%;max-width: 13%;">
				<select class="form-select" id="addon-wrapping" v-model="searchStts" @change="search">
					<option value="">전체</option>
					<option value="e0">승인대기</option>
					<option value="e1">승인완료</option>
					<option value="e6">대여종료</option>
					<option value="e8">신청거절</option>
				</select>
			</div>
			<div class="col-1 fw-bolder lh-lg text-center">신청일</div>
			<div class="col-1" style="width: 14%; flex: 0 0 14%;max-width: 14%;">
				<input type="date" class="form-select" v-model="searchStr" @change="search">
			</div>
		</div>
		<hr>
		
		<div class="row">
			<div class="col"></div>
			<div class="col-1 text-end fw-bolder lh-lg"  style="width: 14%; flex: 0 0 14%;max-width: 14%;">선택한 신청을</div>
			<div class="col-1" style="width: 13%; flex: 0 0 14%;max-width: 13%;">
				<select class="form-select"  v-model="stts">
					<option selected value="">선택</option>
					<option v-for="reqStts in regSttsList" :value="reqStts.cmmnDetaCode">{{reqStts.codeDesct}}</option>
				</select>
			</div>
			<div class="col-1">
				<button @click="changeBtn" class="btn btn-success mb-3">변경</button>
			</div>
		</div>
		<v-data-table
				v-model="selected"
				v-model:page="page"
    		v-model:items-per-page="itemsPerPage"
    		:headers="headers"
    		:items="boardList"
   		  item-value="boardNo"
				 no-data-text="조회된 농지대여가 없습니다"
				return-object
    		show-select
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
        <div v-if="Object.keys(board).length>0">
          <div >
            <div class="row">
              <div class="col-5 row"><span class="col-3">신청번호</span><span class="col-5">{{board.boardNo}}</span></div>
              <div class="col-5 row"><span class="col-3">신청일자</span><span class="col-5">{{board.regDate}}</span></div>
            </div>
            <div class="row">
              <div class="col-5 row"><span class="col-3">신청인</span><span class="col-5">{{board.memNo}}</span></div>
              <div class="col-5 row"><span class="col-3">가격(년)</span><span class="col-5">{{board.lendPrice}}</span></div>
            </div>
            <div class="row">
              <div class="col-5 row"><span class="col-3">시작일</span><span class="col-5">{{board.lendStrDate}}</span></div>
              <div class="col-5 row"><span class="col-3">종료일</span><span class="col-5">{{board.lendEndDate}}</span></div>
            </div>
            <div class="row">
              <div class="col-3">주소</div><div class="col-8">{{board.addr}}</div>
            </div>
            <div class="row">
              <div class="col-5 row">
                <span class="col-3">크기</span>
                <span class="col-5">{{board.area}}</span></div>
              <div class="col-5 row">
                <span class="col-5">주요제배작물</span>
                <span class="col-5"><span v-for="mcrp in board.mcrpList">{{mcrp}}, </span></span></div>
            </div>
            <div class="row" v-if="board.regStts=='승인 대기'" style="width: 600px; height: 300px;">
              <div class="col-3" style="padding-left: 20px;">제출서류</div>
                  <div class="col-5" v-if="board.files !=null">
                    <ul>
                      <li v-for="(file,i) in board.files">
                        <a download :href="file.atchFileName">첨부파일{{ i+1 }}</a>
                      </li>
                    </ul>
                  </div>
									<span class="col-5" v-else="board.files==null">제출서류가 없습니다</span>
                </div>
          </div>
          <div class="text-end">
						<div v-if="board.regStts=='승인 대기' && board.files !=null" >
							<button v-show="btnShow" class="btn btn-success mb-3 mx-3" @click="apply">승인</button>
							<select v-model="reason" v-show="!btnShow">
								<option value="제출서류가 정확하지 않습니다" selected>제출서류가 정확하지 않습니다</option>
								<option value="제출서류가 부족합니다">제출서류가 부족합니다</option>
							</select>
							<button class="btn btn-primary mb-3 mx-3" @click="refusal1" v-show="btnShow">승인거부</button>
							<button class="btn btn-primary mb-3 mx-3" @click="refusal2" v-show="!btnShow">승인거부</button>
						</div>
						<div v-else>
							<button class="btn btn-primary mb-3 mx-3" @click="back">돌아가기</button>
						</div>
          </div>
        </div>
      </div>
    </div>

		
	</div>

</template>


<script>

import { VDataTable } from 'vuetify/labs/VDataTable'
import axios from 'axios'
export default{
  data(){
    return{
			searchStts:'e0',
			searchStr:'',searchEnd:'',
			btnShow:true,reason:'',
			page:1,selected:[],
			itemsPerPage: 10,
			board:{}, boardList:[],
			dst1:'',dst1List:[],
			dst2:'',dst2List:[],
      dst2All:{}, regSttsList:[],stts:'',
      headers:[
	        {title: '번호',key: 'boardNo',},
	        {title: '주소', key: 'addr'},
					{title: '면적',key: 'area'},
					{title: '가격',key: 'lendPrice' },
					{title: '신청일자', key: 'regDate'},
	        {title: '상태',key: 'regStts'}
	      ]
    }
  },
	components: { VDataTable,},
methods:{
	search(){
		let obj={}
		obj.stts=this.searchStts;
		obj.str=this.searchStr;
		obj.end=this.searchEnd;
		this.callList(obj);
	},
	changeBtn(){
		console.log(this.selected);
		if(this.stts==''){
			this.$swal({
      	title: "상태를 선택하세요",
      	icon: "warning",
      	showConfirmButton: false,
				timer: 1500
    	});
			return;
		}
		let list =[];
		this.selected.forEach(item => {
			let obj={boardNo: item.boardNo, 
          reqStts:this.stts,
					tableName:'farm_lend'}
			    list.push(obj);
		  });
		console.log(list);
		this.modify(list)
	},
	modify(list){
  	axios.put('/admin/chageRegStatus', list)
  	.then(response => {
			console.log(response.data);
			let stts= this.searchStts
			this.callList({stts})
			this.selected = [];
    	this.stts = '';
    	this.board = {};
  	})
  	.catch(err => console.log(err))
  	.finally(() => this.back())
	},
	info(event,item){
		let index=item.item.index;
		this.board= this.boardList[index];
		this.onpenModal()
	},
	onpenModal(){
		document.getElementById("myModal").style.display = "block";	
	},
	apply(){
		let list=[];
		list.push({boardNo:this.board.boardNo,reqStts:'e1',tableName:'farm_lend'});
		this.modify(list);
	},
	refusal1(){
		this.btnShow=false;
	},
	refusal2(){
		let obj ={
			boardNo : this.board.boardNo,
			memNo: this.board.memNo,
			alertTitle: '신청이 거부되었습니다',
			alrtDesct: this.reason,
			boardCtg: 'g00',
			tableName:'farm_lend',
			reqStts:'e8',
		}
		let list = [obj];
		this.modify(list)
		axios.post('/admin/rejectAlert', obj)
		.then((response) => {
  		console.log(response.data);
  		if (response.data.retCode == "Success") {
    		this.$swal({
					title: "신청이 거부가 성공적으로 이루졌습니다.",
					icon: "success",
					showConfirmButton: false,
					timer: 1500
				});
  		} else {
    		this.$swal({
      		title: "알림을 보내지 못하였습니다.",
      		icon: "error",
      		showConfirmButton: false,
      		timer: 1500
    		});
  		}})
			.catch((err) => console.log(err))
			.finally(() => this.back());
	},
	back(){
		document.getElementById("myModal").style.display = "none";
	},
	callList(vo){
		axios.get("/admin/farms",{params: vo})
		.then(response => {
			console.log(response.data);
			this.boardList = response.data;
		})
		.catch(err => console.log(err));
	}
},
  mounted(){
  	// Vuex에서 데이터 가져오기
		this.dst1List = this.$store.state.dst1;
		this.regSttsList = this.$store.state.regSttsList;
		this.dst2All = this.$store.state.des2All;
		console.log(this.$store.state.regSttsList)
		this.callList({stts:'e0'});
			
		//모달 닫기
		
		window.onclick = function(event) {
  		if (event.target == document.getElementById("myModal")) {
				document.getElementById("myModal").style.display = "none";
				this.board={};
  		}
		}
		document.getElementsByClassName("close")[0].addEventListener('click',function(){
			document.getElementById("myModal").style.display = "none";
			this.board={};
		})
  },
	computed: {
      pageCount () {
        return Math.ceil(this.boardList.length / this.itemsPerPage)
      },
    }
}

</script>

<style>

</style>