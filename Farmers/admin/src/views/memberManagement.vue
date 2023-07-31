<template>

	<div class="body">
		<h1>회원 관리</h1>
		<br>

		<div class="row ">
			<div class="col-4 row " >
				<select class="form-select col mx-3"  id="addon-wrapping" v-model="searchType">
					<option value="아이디" selected>아이디</option><option value="닉네임">닉네임</option></select>
					<input type="text" class="form-control col"  aria-describedby="addon-wrapping" v-model="searchText" @change="search">
			</div>
			<div class="col-3 row">
				<div class="col">회원등급</div>
				<select class="form-select col" id="addon-wrapping" v-model="searchGrd" @change="search">
					<option selected value="">선택</option>
					<option v-for="grd in gradeList" :value="grd.cmmnDetaCode">{{grd.codeDesct}}</option>
				</select>
			</div>
			<div class="col-4 row">
				<div class="col text-center" >상태</div>
				<select class="form-select col" id="addon-wrapping" v-model="searchStts" @change="search">
					<option selected value="">선택</option>
					<option v-for="status in sttsList " :value="status.cmmnDetaCode">{{status.codeDesct}}</option>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col-4 row">
				<div class="col">회원가입경로</div>
				<select class="form-select col " id="addon-wrapping" v-model="searchLogin" @change="search">
					<option selected value="">전체</option>
					<option value="일반">일반</option><option value="카카오">카카오</option>
					<option value="구글">구글</option><option value="네이버">네이버</option>
				</select>
			</div>
			<div class="col-6 row">
				<div class="col">가입일</div>
				<input type="date" class="form-select col" v-model="searchStr" @change="search">&nbsp;-&nbsp; <input type="date" class="form-select col " v-model="searchEnd" :max="new Date()" @change="search">
			</div>
		</div>

		<div style="width: 1000px;" class="row"> 
			
			<div class="col-2">선택한 회원을 </div>
			<div class="col-2">
				<select class="form-select"  v-model="grade">
					<option selected value="" >선택</option>
					<option v-for="grd in gradeList" :value="grd.cmmnDetaCode">{{grd.codeDesct}}</option>
				</select>
			</div>
			<div class="col-2">
				<select class="form-select"  v-model="stts">
					<option selected value="">선택</option>
					<option v-for="status in sttsList" :value="status.cmmnDetaCode">{{status.codeDesct}}</option>
				</select>
			</div>
			<div  class="col-2"> 으로
				<button @click="changeBtn" class="btn btn-primary mb-3">변경</button>
			</div>
		</div>
		<v-data-table
				v-model="selected"
				v-model:page="page"
    		v-model:items-per-page="itemsPerPage"
    		:headers="headers"
    		:items="memberList"
   		  item-value="memNo"
				 no-data-text="조회된 회원이 없습니다"
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
        <div v-if="Object.keys(member).length>0">
          <div >
            <div class="row">
              <div class="col-5 row"><span class="col-3">회원번호</span><span class="col-5">{{member.memNo}}</span></div>
              <div class="col-5 row"><span class="col-3">가입일자</span><span class="col-5">{{member.sginDate}}</span></div>
            </div>
            <div class="row">
              <div class="col-5 row"><span class="col-3">아이디</span><span class="col-5">{{member.id}}</span></div>
              <div class="col-5 row"><span class="col-3">닉네임</span><span class="col-5">{{member.nick}}</span></div>
            </div>
            
            <div class="row" v-if="member.memGrd=='준회원'" style="width: 600px; height: 300px;">
              <div class="col-3" style="padding-left: 20px;">제출서류</div>
                  <img class="col-5" v-if="member.grdAtchFile !=null" :src="member.grdAtchFile">
									<span class="col-5" v-else="member.grdAtchFile==null">제출서류가 없습니다</span>
                </div>
          </div>
          <div class="text-end">
						<div v-if="member.memGrd=='준회원' && member.grdAtchFile !=null" >
							<button v-show="btnShow" class="btn btn-primary mb-3 mx-3" @click="apply">승인</button>
							<select v-model="reason" v-show="!btnShow">
								<option value="이미지가 정확하지 않습니다">이미지가 정확하지 않습니다</option>
								<option value="주소지와 등본의 주소가 일치하지 않습니다">주소지와 등본의 주소가 일치하지 않습니다</option>
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
			btnShow:true,reason:'',
			searchType:'아이디',searchText:'',
			searchStts:'',searchLogin:'',
			searchStr:'',searchEnd:'',
			searchGrd:"",page:1,
			selected:[],itemsPerPage: 10,member:{}, 
      memberList:[],checkedMembers:[],
			grade:'',gradeList:[],
			stts:'',sttsList:[],
      headers:[{title: '아이디',key: 'id',},
	        { title: '성별',key: 'gen'},
	        {title: '등급',key: 'memGrd'},
					{title: '가입일', key: 'signDate'},
					{title: '회원가입경로',key: 'loginPath'},
	        {title: '닉네임', key: 'nick'},
	        {title: '상태',key: 'stts'}
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
		console.log(this.selected);
		let list =[];
		this.selected.forEach(item => {
			let obj={memNo: item.memNo, stts:this.stts,memGrd:this.grade}
			list.push(obj);
		});
		console.log(list);
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
			console.log(result);
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
			alertTitle: '등업신청이 거부되었습니다',
			alrtDesct: this.reason,
			boardCtg: 'g16'
		}
		console.log(obj)
		fetch('/admin/rejectAlert',{
			method:'POST',
			headers: {'Content-Type': 'application/json',  },
			data:JSON.stringify(obj)
		})
		.then(result=> result.json())
		.then(result=> console.log(reuslt))
		.catch(err=> console.log(err))
		.finally(()=> this.back())
	},
	back(){
		document.getElementById("myModal").style.display = "none";
	},
	callList(vo){
		axios.get("/admin/members",{params: vo})
		.then(response => {
			console.log(response.data);
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
		console.log(this.allCode);
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
    }
}

</script>

<style>
.body{
	padding: 10px;
}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 100000000; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  background-color: #fefefe;
  margin: auto;
  padding: 20px;
  border: 1px solid #888;
  width: 80%;
}

/* The Close Button */
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