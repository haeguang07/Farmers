<template>

	<div class="body">
		<h3>농촌 체험하기</h3>
		<br>
		<div class="row">
			<div class="col-1 fw-bolder lh-lg text-center">상태</div>
			<div class="col-1" style="width: 13%; flex: 0 0 13%;max-width: 13%;">
				<select class="form-select" id="addon-wrapping" v-model="searchStts" @change="search">
					<option value="">전체</option>
					<option value="e0">승인대기</option>
					<option value="e1">승인완료</option>
					<option value="e2">체험종료</option>
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
				 no-data-text="조회된 체험이 없습니다"
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
				
			<!-- The Modal -->
  <div id="myModal" class="modal">
      <!-- Modal content -->
      <div class="modal-content">
        <span class="close">&times;</span>
        <div v-if="Object.keys(board).length>0">
          <div >
            <div class="row">
              <div class="col-4 row"><span class="col-3">신청번호</span><span class="col-5">{{board.boardNo}}</span></div>
              <div class="col-4 row"><span class="col-3">신청일자</span><span class="col-5">{{board.regDate}}</span></div>
              <div class="col-3 row"><span class="col-4">신청인</span><span class="col-5">{{board.memNo}}</span></div>
            </div>
            <div class="row">
              <div class="col-5 row">
								<span class="col-3">제목</span><span class="col-8">{{board.title}}</span>
							</div>
              <div class="col-5 row"><span class="col-3">지역</span><span class="col-5">{{board.dst1}}</span></div>
            </div>
            <div class="row">
              <div class="col-3">주소</div>
							<div class="col-8">{{board.addr}}</div>
            </div>
            <div class="row">
              <div class="col-5 row"><span class="col-4">체험시작시간</span><span class="col-5">{{board.exStrDate}}</span></div>
              <div class="col-5 row"><span class="col-4">체험종료시간</span><span class="col-5">{{board.exEndDate}}</span></div>
            </div>

            <div class="row">
              <div class="col-3" style="padding-left: 20px;">상세내용</div>
                  <div class="col-8" style="overflow: auto; height: 350px;" v-html="board.detaDesct"></div>
            </div>
            <div class="text-end">
              <div v-if="board.regStts=='승인 대기'">
                <button v-show="btnShow" class="btn btn-success mb-3 mx-3" @click="apply">승인</button>
                <select v-model="reason" v-show="!btnShow">
                  <option value="체험제목과 상세내용이 일치하지않습니다">일치하지않습니다</option>
                  <option value="이유2">이유2</option>
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
			board:{},  boardList:[],
			dst1:'',dst1List:[],
			dst2:'',dst2List:[],
      dst2All:{}, regSttsList:[],stts:'',
      headers:[
	        {title: '번호',key: 'boardNo',},
	        {title: '제목',key: 'title'},
					{title: '시작시간',key: 'exStrDate' },
					{title: '종료시간',key: 'exEndDate'},
          {title: '신청일자',key: 'regDate' },
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
		this.callList(obj)
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
					tableName:'experience'}
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
		list.push({boardNo:this.board.boardNo,reqStts:'e1',tableName:'experience'});
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
			boardCtg: 'g08',
			tableName:'EXPERIENCE',
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
		console.log(vo)
		axios.get("/admin/exps",{params: vo})
		.then(response => {
			console.log(response.data);
			this.boardList = response.data;
		})
		.catch(err => console.log(err));
	}
},
  mounted(){
		this.dst1List = this.$store.state.dst1;
		this.regSttsList = this.$store.state.regSttsList;
		this.dst2All = this.$store.state.des2All;
		console.log(this.regSttsList);

		this.callList({stts:'e0'})
			
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