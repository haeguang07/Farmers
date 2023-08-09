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
				
			<!-- 모달창 -->
  	<div id="myModal" class="modal">
      <!-- 모달창 내용 -->
      <div class="modal-content">
        <span class="close">&times;</span>
        <div v-if="Object.keys(board).length>0">
					<table class="table"  style="margin-bottom: 0px;">
							<tbody>
								<tr>
									<th>신청번호</th><td v-text="board.boardNo"></td>
									<th>신청일자</th><td v-text="board.regDate"></td>
									<th>신청인</th><td v-text="board.memNo"></td>
								</tr>
							</tbody>
						</table>
						<table class="table" style="margin-bottom: 0px;">
							<tbody>
								<tr>
									<th>제목</th><td colspan="3" v-text="board.title" class="text-start"></td>
								</tr>
							</tbody>
							</table>
							<table class="table"  style="margin-bottom: 0px;">
								<tbody>
									<tr>
										<th>경매수량</th><td v-text="board.saleQty"></td>
										<th>즉시구매가</th><td v-text="formatNumber(board.bnp)"></td>
										<th>배송비</th><td v-text="formatNumber(board.shipPrice)"></td>
									</tr>
								</tbody>
							</table>
							<table class="table">
								<tbody>
									<tr>
										<th>경매시간</th><td v-text="board.actDate"></td>
										<th>경매기간</th><td v-text="board.actTrm"></td>
									</tr>
									<tr>
										<th>상세내용</th><td colspan="3" class="desct" v-html="board.desct"></td>
									</tr>
									
								</tbody>
							</table>
          <div>
            <div class="text-end">
              <div v-if="board.regStts=='승인 대기'">
                <button v-show="btnShow" class="btn btn-success mb-3 mx-3" @click="apply">승인</button>
                <select v-model="reason" v-show="!btnShow">
                  <option value="정보 누락 또는 오류">정보 누락 또는 오류</option>
                  <option value="부적절한 내용이 있습니다">부적절한 내용</option>
                </select>
                <button class="btn btn-danger mb-3 mx-3" @click="refusal1" v-show="btnShow">승인거부</button>
                <button class="btn btn-danger mb-3 mx-3" @click="refusal2" v-show="!btnShow">승인거부</button>
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
			btnShow:true,reason:'정보 누락 또는 오류',
			page:1,selected:[],itemsPerPage: 10,
			board:{},  boardList:[],
			dst1:'',dst1List:[],
			dst2:'',dst2List:[],
      dst2All:{},regSttsList:[], stts:'',
      headers:[
	        {title: '번호',key: 'boardNo',align: 'center'},
	        {title: '제목', key: 'title'},
					{title: '시작일', key: 'actDate',align: 'center' },
					{title: '종료일',key: 'actExpireDate',align: 'center'},
	        {title: '상태',key: 'regStts',align: 'center'}
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
		this.callList(obj)
	},
	changeBtn(){
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
		this.modify(list)
	},
	modify(list){
  	axios.put('/admin/chageRegStatus', list)
  	.then(response => {
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
			alrtTitle: '신청이 거부되었습니다',
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
		axios.get("/admin/auctions",{params: vo})
		.then(response => {
			this.boardList = response.data;
		})
		.catch(err => console.log(err));
	},
	formatNumber(number) {
		if (number === null || number === undefined) {
        return '';
    }
      return number.toLocaleString();
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
  },
	created() {
    document.title = "경매관리";
  }
}

</script>



