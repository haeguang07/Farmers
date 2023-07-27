<template>

	<div class="body">
		<div style="width: 1000px;" class="row"> 
			<div class="col-2">선택한 신청을 </div>
			<div class="col-2">
				<select class="form-select"  v-model="stts">
					<option selected value="">선택</option>
					<option v-for="reqStts in reqSttsList" :value="reqStts.cmmnDetaCode">{{reqStts.codeDesct}}</option>
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
    		:items="boardList"
   		  item-value="boardNo"
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
              <span class="col-3">주소</span><span class="col-8">{{board.addr}}</span>
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
							<button v-show="btnShow" class="btn btn-primary mb-3 mx-3" @click="apply">승인</button>
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

export default{
  data(){
    return{
			btnShow:true,
			reason:'',
			page:1,
			selected:[],
			itemsPerPage: 10,
			board:{}, 
      boardList:[],
      checkedboards:[],
			des1:'',
			des1List:[],
			dest2:'',
			dest2List:[],
      dest2All:{},
      reqSttsList:[],
      stts:'',
      headers:[
	        {title: '번호',
					key: 'boardNo',},
	        {title: '주소',
	          key: 'addr'},
					{title: '면적',
	          key: 'area'
	        },
					{title: '가격',
	          key: 'lendPrice'
	        },
	        {title: '승인상태',
	          key: 'regStts'}
	      ]
    }
  },
	components: {
      VDataTable,
    },
methods:{
	changeBtn(){
		console.log(this.selected);
		let list =[];
		this.selected.forEach(item => {
			let obj={boardNo: item.boardNo, 
          reqStts:this.stts}
			    list.push(obj);
		  });
		console.log(list);
		this.modify(list)
	},
	modify(list){
		fetch("/admin/farms/update",{
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
			this.stts='';
			this.board={};
		})
		.catch(err=> console.log(err))
		.finally(()=>this.back())
	}
	,
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
		list.push({boardNo:this.board.boardNo,reqStts:'e1'});
		this.modify(list);
	},
	refusal1(){
		this.btnShow=false;
	},
	refusal2(){
		let obj ={
			memNo : this.member.memNo,
			alertTitle: '신청이 거부되었습니다',
			alrtDesct: this.reason,
			boardCtg: 'g00'
		}
		console.log(obj)
		fetch('admin/farms',{
			method:'POST',
			headers: {
          'Content-Type': 'application/json', 
      },
			data:JSON.stringify(obj)
		})
		.then(result=> result.json())
		.then(result=> console.log(reuslt))
		.catch(err=> console.log(err))
		.finally(()=> this.back())
	},
	back(){
		document.getElementById("myModal").style.display = "none";
	}
},
  mounted(){
  	let vue= this;
    fetch("/admin/farms")
    .then(result=> result.json())
    .then(result=>{
      console.log(result)
      vue.boardList=result.farms;
	  	vue.dest1List=result.code['0K'];
	  	vue.dest2All=result.code;
      vue.reqSttsList=result.code['0E'];
	  	console.log(vue.boardList);
	  	console.log(vue.dest1List);
	  	console.log(vue.dest2All);
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
        return Math.ceil(this.boardList.length / this.itemsPerPage)
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

</style>