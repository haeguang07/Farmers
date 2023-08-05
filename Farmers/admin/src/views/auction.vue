<template>

	<div class="body">
		<h3>경매장</h3>
		<br>
		<div class="row">
			<div class="col-1 fw-bolder lh-lg text-center">상태</div>
			<div class="col-2" style="width: 13%; flex: 0 0 13%;max-width: 13%;">
				<select class="form-select" id="addon-wrapping" v-model="searchStts"  @change="search">
					<option value="">전체</option>
					<option value="e0">승인대기</option>
					<option value="e1">승인완료</option>
					<option value="e5">경매종료</option>
					<option value="e8">신청거절</option>
				</select>
			</div>
			<div class="col-1 fw-bolder lh-lg text-center">경매일</div>
			<div class="col-2" style="width: 13%; flex: 0 0 13%;max-width: 13%;">
				<select class="form-select" id="addon-wrapping" v-model="searchStr"  @change="search">
					<option value="">전체</option>
					<option v-for="date in dateList" :value="date" v-text="date"></option>
				</select>
			</div>
			<div class="col-auto"></div>
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
				no-data-text="조회된 경매가 없습니다"
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
              <div class="col-4 row"><span class="col-3">신청인</span><span class="col-5">{{board.memNo}}</span></div>
            </div>
            <div class="row">
              <div class="col-10 row"><span class="col-1">제목</span><span class="col-7">{{board.title}}</span></div>
            </div>
            <div class="row">
							<div class="col-4 row"><span class="col-3">경매수량</span><span class="col-5">{{board.saleQty}}</span></div>
              <div class="col-4 row"><span class="col-4">즉시구매가</span><span class="col-5">{{board.bnp}}</span></div>
              <div class="col-4 row"><span class="col-3">배송비</span><span class="col-5">{{board.shipPrice}}</span></div>
            </div>
            <div class="row">
              <div class="col-5 row"><span class="col-3">경매시간</span><span class="col-5">{{board.actDate}}</span></div>
              <div class="col-5 row"><span class="col-3">경매기간</span><span class="col-5">{{board.actTrm}}</span></div>
            </div>

            <div class="row">
              <div class="col-3" style="padding-left: 20px;">상세내용</div>
							<div class="col-8" style="overflow: auto; height: 350px;" v-html="board.desct"></div>
            </div>
            <div class="text-end">
              <div v-if="board.regStts=='승인 대기;'">
                <button v-show="btnShow" class="btn btn-success mb-3 mx-3" @click="apply">승인</button>
                <select v-model="reason" v-show="!btnShow">
                  <option value="부적절한 경매품목입니다">부적절한 경매품목입니다</option>
                  <option value="경매정보가 정확하지 않습니다">경매정보가 정확하지 않습니다</option>
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
			searchStts:'e0',searchStr:'',dateList:[],
			btnShow:true,reason:'',
			page:1,selected:[],itemsPerPage: 10,
			board:{},  boardList:[],
			dst1:'',dst1List:[],
			dst2:'',dst2List:[],
      dst2All:{},regSttsList:[], stts:'',
      headers:[
	        {title: '번호',key: 'boardNo',},
	        {title: '제목', key: 'title'},
					{title: '시작시간', key: 'actDate' },
					{title: '경매기간',key: 'actTrm'},
	        {title: '상태',key: 'regStts'}
	      ]
    }
  },
	components: {
      VDataTable,
    },
methods:{
	search(){
		let obj={}
		obj.stts=this.searchStts;
		obj.str=this.searchStr;
		obj.end=this.searchEnd;
		console.log(obj);
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
					tableName:'auction'}
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
		list.push({boardNo:this.board.boardNo,reqStts:'e1',tableName:'auction'});
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
			boardCtg: 'g10',
			tableName:'acution',
			reqStts:'e8',

		}
		let list = [obj];
		this.modify(list)
		axios.post('/admin/rejectAlert', vo, {
  		headers: {
    		'Content-Type': 'application/json',
  		}
		})
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
		axios.get("/admin/auctions",{params: vo})
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
		//시간 구하기
		let thisDate = new Date();
		let day = thisDate.getDay();
		let calcDate = thisDate.getDate() - day + ((day == 0 ? 1 : 8) + 0);
		let nextDate = new Date(thisDate.setDate(calcDate)).toISOString().substring(0, 10);
		let nextNextDate = new Date(nextDate);
		nextNextDate.setDate(nextNextDate.getDate() + 7);
		nextNextDate = nextNextDate.toISOString().substring(0, 10);
		this.dateList= [nextDate,nextNextDate];

		let obj={stts:'e0'}
		this.callList(obj);
		
			
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



