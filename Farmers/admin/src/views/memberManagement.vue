<template>

	<div class="body">
		<h1>회원 관리</h1>
		<br>
		
		<div class="row">
			<div class="col-1 lh-lg text-center" style="margin-left: 15px;"><b>검색조건</b></div>
			<div class="col-1" style="width: 10%; flex: 0 0 10%;max-width: 10%;">
				<select class="form-select form-group"  id="addon-wrapping" v-model="searchType" >
					<option value="아이디" selected><b>아이디</b></option><option value="닉네임"><b>닉네임</b></option>
				</select>
			</div>
			<div class="col-2">
				<input type="text" class="form-control"  aria-describedby="addon-wrapping" v-model="searchText" @change="search">
			</div>
		</div>
				
		<div class="row">
			<div class="col-1 lh-lg text-center" style="margin-left: 15px;"><b>회원등급</b></div>
				<div class="col-1" style="width: 10%; flex: 0 0 10%;max-width: 10%;">
					<select class="form-select" id="addon-wrapping" v-model="searchGrd" @change="search" >
						<option selected value="">선택</option>
						<option v-for="grd in gradeList" :value="grd.cmmnDetaCode">{{grd.codeDesct}}</option>
					</select>
				</div>
				
				<div class="col-1 lh-lg"><b>활동상태</b></div>
				<div class="col-1 text-center" style="width: 10%; flex: 0 0 10%;max-width: 10%;">
					<select class="form-select col" id="addon-wrapping" v-model="searchStts" @change="search">
						<option selected value="">선택</option>
						<option v-for="status in sttsList " :value="status.cmmnDetaCode">{{status.codeDesct}}</option>
					</select>
			</div>
		</div>
				
		
		<div class="row">
			<div class="col-1 lh-lg text-center" style="margin-left: 15px;"><b>가입경로</b></div>
				<div class="col-1" style="width: 10%; flex: 0 0 10%;max-width: 10%;">
					<select class="form-select" id="addon-wrapping" v-model="searchLogin" @change="search">
						<option selected value="">전체</option>
						<option value="일반">일반</option><option value="카카오">카카오</option>
						<option value="구글">구글</option><option value="네이버">네이버</option>
					</select>
				</div>

				<div class="col-1 lh-lg"><b>가입일</b></div>
				<div class="col-2" style="width: 14%; flex: 0 0 14%;max-width: 14%;">
					<input type="date" class="form-select" v-model="searchStr" @change="search">
				</div>
		</div>
		<hr>
		<div style="width: 1000px; float: right;" class="row"> 
			<div class="col-4"></div>
			<div class="col-2 text-end lh-lg" >📌<b>선택한 회원</b></div>
			<div class="col-2">
				<select class="form-select"  v-model="grade">
					<option selected value="">등급선택</option>
					<option v-for="grd in gradeList" :value="grd.cmmnDetaCode">{{grd.codeDesct}}</option>
				</select>
			</div>
			<div class="col-2">
				<select class="form-select"  v-model="stts">
					<option selected value="">활동선택</option>
					<option v-for="status in sttsList" :value="status.cmmnDetaCode">{{status.codeDesct}}</option>
				</select>
			</div>
			<div class="col-2  text-center">
				<button @click="changeBtn" class="btn btn-success">변경하기</button>
			</div>
		</div>
		<div style="clear: both;"></div>
		<v-data-table
			v-model="selected"
			v-model:page="page"
    	v-model:items-per-page="itemsPerPage"
    	:headers="headers"
    	:items="memberList"
			no-data-text="조회된 회원이 없습니다"
			return-object
    	show-select
			hide-default-footer
			@click:row=info
   		class="elevation-1">
			<template v-slot:bottom>
				<div class="text-center pt-2">
					<v-pagination
						v-model="page" :length="pageCount">
					</v-pagination>
				</div>
			</template>
			
		</v-data-table>
				
			<!-- 모달창 -->
  	<div id="myModal" class="modal">
      <!-- 모달창 내용 -->
      <div class="modal-content">
        <span class="close">&times;</span>
        <div v-if="Object.keys(member).length>0">
          <div>
						<table class="table" style="margin-bottom: 0px;">
							<tbody>
								<tr>
									<th>회원번호</th><td v-text="member.memNo"></td>
									<th>가입일자</th><td v-text="member.signDate"></td>
								</tr>
								<tr>
									<th>아이디</th><td v-text="member.id"></td>
									<th>닉네임</th><td v-text="member.nick"></td>
								</tr>
								<tr v-if="member.memGrd=='준회원'">
									<th>제출서류</th><td colspan="3" v-if="member.grdAtchFile !=null"><img class="col-5" :src="member.grdAtchFile"></td>
									<td colspan="3" v-else="member.grdAtchFile==null"> 제출 서류가 없습니다</td>
								</tr>
							</tbody>
						</table>
            
          </div>
          <div class="text-end">
						<div v-if="member.memGrd=='준회원' && member.grdAtchFile !=null" >
							<button v-show="btnShow" class="btn btn-success mb-3 mx-3" @click="apply">승인</button>
							<select v-model="reason" v-show="!btnShow">
								<option value="이미지가 정확하지 않습니다">이미지가 정확하지 않습니다</option>
								<option value="주소지와 등본의 주소가 일치하지 않습니다">주소지와 등본의 주소가 일치하지 않습니다</option>
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

</template>


<script>

import { VDataTable } from 'vuetify/labs/VDataTable'
import axios from 'axios'
export default{
  data(){
    return{
			btnShow:true,reason:'이미지가 정확하지 않습니다',
			searchType:'아이디',searchText:'',
			searchStts:'',searchLogin:'',
			searchStr:'',searchEnd:'',
			searchGrd:"",page:1,
			selected:[],itemsPerPage: 10,member:{}, 
      memberList:[],checkedMembers:[],
			grade:'',gradeList:[],
			stts:'',sttsList:[],
      headers:[{title: '아이디',key: 'id',},
	        { title: '성별',key: 'gen',align: 'center'},
	        {title: '등급',key: 'memGrd',align: 'center'},
					{title: '가입일', key: 'signDate',align: 'center'},
					{title: '회원가입경로',key: 'loginPath'},
	        {title: '닉네임', key: 'nick'},
	        {title: '상태',key: 'stts',align: 'center'}
	      ]
    }
  },
	components: {
      VDataTable,
    },
methods:{
	search(){
		let obj={}
		if(this.searchType=='아이디'){
			obj.id=this.searchText;
		}else{
			obj.nick=this.searchText;
		}
		obj.stts=this.searchStts;
		obj.grd=this.searchGrd;
		obj.login =this.searchLogin;
		obj.str=this.searchStr;
		obj.end=this.searchEnd;
		this.callList(obj)
	},
	changeBtn(){
		let list =[];
		this.selected.forEach(item => {
			let obj={memNo: item.memNo, stts:this.stts,memGrd:this.grade}
			list.push(obj);
		});
		this.modify(list)
	},
	modify(list){
		fetch("/admin/members/update",{
			method:"PUT",
			headers: {
        "Content-Type": "application/json",
      },
			body: JSON.stringify(list)
		})
		.then(result=>result.json())
		.then(result=> {
			this.$swal({
              title: "회원 상태를 변경하였습니다",
              icon: "success",
              showConfirmButton:false,
              timer:1500
            });
			this.memberList=result;
			this.selected=[];
			this.grade=''; this.stts='';
			this.member={};
		})
		.catch(err=> console.log(err))
		.finally(()=>this.back())
	}
	,
	info(event,item){
		let index=item.item.index;
		this.member= this.memberList[index];
		this.onpenModal()
	},
	onpenModal(){
		document.getElementById("myModal").style.display = "block";	
	},
	apply(){
		let list=[];
		list.push({memNo:this.member.memNo,memGrd:'b1'});
		this.modify(list);
	},
	refusal1(){
		this.btnShow=false;
	},
	refusal2(){
		let obj ={
			memNo : this.member.memNo,
			alrtTitle: '등업신청이 거부되었습니다',
			alrtDesct: this.reason,
			boardCtg: 'g16'
		}
		fetch('/admin/rejectAlert',{
			method:'POST',
			headers: {'Content-Type': 'application/json',  },
			data:JSON.stringify(obj)
		})
		.then(result=> result.json())
		.then(result=> {	
			this.$swal({
					title: "성공적으로 거부되었습니다",
					icon: "success",
					showConfirmButton:false,
					timer:1500
				});
			}
		)
		.catch(err=> console.log(err))
		.finally(()=> this.back())
	},
	back(){
		document.getElementById("myModal").style.display = "none";
	},
	callList(vo){
		axios.get("/admin/members",{params: vo})
		.then(response => {
			this.memberList = response.data.memberList;
			this.sttsList=response.data.code['0C']
	  	this.gradeList=response.data.code['0B']
	  	this.gradeList.splice(0,1);
		})
		.catch(err => console.log(err));

	}
},
  mounted(){
		this.sttsList = this.$store.state.allCode['0C'];
		this.allCode = this.$store.state.allCode;
  	this.callList()

		//모달 닫기
		window.onclick = function(event) {
  		if (event.target == document.getElementById("myModal")) {
				document.getElementById("myModal").style.display = "none";
				this.member={};
  		}
		}
		document.getElementsByClassName("close")[0].addEventListener('click',function(){
			document.getElementById("myModal").style.display = "none";
			this.member={};
		})
  },
	computed: {
      pageCount () {
        return Math.ceil(this.memberList.length / this.itemsPerPage)
      },
  },
	created() {
    document.title = "회원관리";
  }
}

</script>

<style>
.body{
	padding: 10px;
}
img{
	max-width: 600px;
}
/* 모달안 크기 제한 */
.desct{
	max-width: 600px;
	overflow: auto;
}
/* 글자수 */
.text-overflow {
  width: 200px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
/* 모달 */
.modal {
  display: none; 
  position: fixed; 
  z-index: 100000000; 
  padding-top: 100px; 
  left: 0;
  top: 0;
  width: 100%; 
  height: 100%; 
  overflow: auto; 
  background-color: rgb(0,0,0); 
  background-color: rgba(0,0,0,0.4);
}

/* Modal Content */
.modal-content {
  background-color: #fefefe;
  margin: auto;
  padding: 20px;
  border: 1px solid #888;
  width: 60%;
}
table{
	margin: 0 auto;
}

/* X  버튼 */
.close {
  color: #aaaaaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}
ck-sticky-panel__content {
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