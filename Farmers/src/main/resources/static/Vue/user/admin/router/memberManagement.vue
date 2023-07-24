<template>
	<div class="body">
		<div> 
			<span>선택한 회원을
			</span>
			<select v-model="grade">
				<option selected value="">선택</option>
				<option v-for="grd in gradeList" :value="grd.cmmnDetaCode">{{grd.codeDesct}}</option>
			</select>
			<select v-model="stts">
				<option selected value="">선택</option>
				<option v-for="status in sttsList" :value="status.cmmnDetaCode">{{status.codeDesct}}</option>

			</select>
			<span> 으로 </span>
			<button @click="change" class="button">변경</button>
		</div>
  	<div id="memberTable"></div>

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
							<td></td>
						</tr>
						<tr>
							<td>아이디</td><td><input :value="member.id" readonly></td>
							<td>닉네임</td><td><input :value="member.nick" readonly></td>
							<td></td>
						</tr>
						<tr v-if="member.memGrd=='준회원'">
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

export default{
  data(){
    return{
			member:{}, 
      memberList:[],
      checkedMembers:[],
			grade:'',
			gradeList:[],
			stts:'',
			sttsList:[],
      columns:[
	        {header: '아이디',
	          name: 'id',
	          sortable: true,},
	        { header: '성별',
	          name: 'gen',
	          sortable: true,
	          filter: {
                type: 'select',
                showApplyBtn: true,
                showClearBtn: true}
						},
	        {header: '등급',
	          name: 'memGrd',
	          sortable: true,},
					{header: '가입일',
	          name: 'signDate',
	          sortable: true,
	          filter: {
                type: 'select',
                showApplyBtn: true,
                showClearBtn: true}
	        },
					{header: '회원가입경로',
	          name: 'loginPath',
	          sortable: true,
	          filter: {
                type: 'select',
                showApplyBtn: true,
                showClearBtn: true}
	        },
	        
	        {header: '닉네임',
	          name: 'nick',
	          sortable: true,},
	        {header: '상태',
	          name: 'stts',
	          sortable: true,
	          filter: {
                type: 'select',
                showApplyBtn: true,
                showClearBtn: true}
	        }
	      ]
    }
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
		console.log(this.checkedMembers);
		let list =[];
		this.checkedMembers.forEach(item => {
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
			
		})
		
		.catch(err=> console.log(err))
	},
	createList(){
		const vue = this;
		const Grid = tui.Grid;
		Grid.applyTheme('default');
		Grid.setLanguage('ko');
		this.gridInstance = new Grid({
			el: document.getElementById('memberTable'),
			data: this.memberList,
			scrollX: false,
			scrollY: false,
			pageOptions: { perPage: 15, useClient: true },
			columns: this.columns,
			rowHeaders: [{ type: 'checkbox', header: "", width: 50 }]
		});
		this.gridInstance.on('click', (ev) => {
			if (ev.columnName != '_checked' && ev.rowKey >= 0) {
					console.log(ev.rowKey, this.memberList[ev.rowKey], ev.columnName);
					this.member=this.memberList[ev.rowKey];
					console.log(this.member);
					this.onpenModal();

			}
			
		});

		this.gridInstance.on('check', function (ev) {
			vue.toggle(vue.memberList[ev.rowKey].memNo)
			console.log(vue.checkedMembers);
		});

		this.gridInstance.on('uncheck', function (ev) {
			vue.toggle(vue.memberList[ev.rowKey].memNo)
			console.log(vue.checkedMembers);
		});
	},
	onpenModal(){
		document.getElementById("myModal").style.display = "block";
		
	}
},
  mounted(){
  	
    fetch("/admin/members")
    .then(result=> result.json())
    .then(result=>{
      console.log(result)
      this.memberList=result.memberList;
	  this.sttsList=result.code['0C']
	  this.gradeList=result.code['0B']
	  this.gradeList.splice(0,1);
	  console.log(this.sttsList,this.gradeList);
	  this.createList();
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