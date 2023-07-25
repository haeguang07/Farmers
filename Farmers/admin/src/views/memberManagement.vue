<template>

	<div class="body">
		<div style="width: 1000px;" class="row"> 
			<div class="col-2">선택한 회원을 </div>
			<div class="col-2">
				<select class="form-select"  v-model="grade">
					<option selected value="">선택</option>
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
				<button @click="change" class="btn btn-primary mb-3">변경</button>
			</div>
		</div>
		<v-data-table
				v-model="selected"
				v-model:page="page"
    		v-model:items-per-page="itemsPerPage"
    		:headers="headers"
    		:items="memberList"
   		  item-value="memNo"
				return-object
    		show-select
				hide-default-footer
				@click:row=createList
   		 class="elevation-1"
				>
				<template v-slot:item="{ item }">
        <tr @click="">
          <td><input type="checkbox" :value="item.key"></td>
					<td>{{ item.value.id }}</td>
					<td>{{ item.value.gen }}</td>
					<td>{{ item.value.memGrd }}</td>
					<td>{{ item.value.signDate }}</td>
					<td>{{ item.value.loginPath }}</td>
					<td>{{ item.value.nick }}</td>
					<td>{{ item.value.stts }}</td>
        </tr>
      </template>
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
					<table class="table">
						<tr>
							<td>회원번호</td><td><input :value="member.memNo" readonly></td>
							<td>가입일자</td><td><input :value="member.signDate" readonly></td>
							<td></td>``
						</tr>
						<tr>
							<td>아이디</td><td><input :value="member.id" readonly></td>
							<td>닉네임</td><td><input :value="member.nick" readonly></td>
							<td></td>
						</tr>
					``	<tr v-if="member.memGrd=='준회원'">
							<td>제출서류</td>
							<td><img v-if="member.grdAtchFile !=null" :src="member.grdAtchFile"><span  v-if="member.grdAtchFile==null">제출서류가 없습니다</span></td>
						</tr>
					</table>
					<button>승인</button>
					<select>
						<option>이미지가 보이지 않습니다</option>
					</select>
					<button>거부</button>
				</div>
			</div>
		</div>
	</div>

</template>


<script>

import { VDataTable } from 'vuetify/labs/VDataTable'

export default{
  data(){
    return{
			page:1,
			selected:[],
			itemsPerPage: 10,
			member:{}, 
      memberList:[],
      checkedMembers:[],
			grade:'',
			gradeList:[],
			stts:'',
			sttsList:[],
      headers:[
	        {title: '아이디',
					key: 'id',},
	        { title: '성별',
						key: 'gen'
						},
	        {title: '등급',
	          key: 'memGrd'},
					{title: '가입일',
	          key: 'signDate'
	        },
					{title: '회원가입경로',
	          key: 'loginPath'
	        },
	        {title: '닉네임',
	          key: 'nick'},
	        {title: '상태',
	          key: 'stts'}
	      ]
    }
  },
	components: {
      VDataTable,
    },
methods:{
	removeAtIndex (arr, index)  {
		const copy = [...arr];
		copy.splice(index, 1);
		return copy;
	},
	toggle (item, getValue = item => item) {
  		const index = this.checkedMembers.findIndex(i => getValue(i) === getValue(item));
  		if (index === -1) return this.checkedMembers.push({memNo:item});
  		return this.checkedMembers.splice(index, 1);
	},
	change(){
		console.log(this.selected);
		let list =[];
		this.selected.forEach(item => {
			let obj={memNo: item.memNo, stts:this.stts,memGrd:this.grade}
			list.push(obj);
		});
		console.log(list);
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
			this.memberList=result;
			this.selected=[];
			this.grade=''; this.stts='';
		})
		.catch(err=> console.log(err))
	},
	createList(){
		console.log(event.target);
		//document.getElementById("myModal").style.display = "none";
	},
	onpenModal(){
		document.getElementById("myModal").style.display = "block";
		
	}
},
  mounted(){
  	let vue= this;
    fetch("/admin/members")
    .then(result=> result.json())
    .then(result=>{
      console.log(result)
      vue.memberList=result.memberList;
	  	vue.sttsList=result.code['0C']
	  	vue.gradeList=result.code['0B']
	  	vue.gradeList.splice(0,1);
	  	console.log(vue.sttsList,vue.gradeList);
    })
    .catch(err=> console.log(err))
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
  z-index: 1; /* Sit on top */
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

</style>