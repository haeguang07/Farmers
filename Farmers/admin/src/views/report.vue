<template>

	<div class="body">
		<div style="width: 1000px;" class="row"> 
			<div class="col-2">선택한 신고의 회원을 </div>
			<div class="col-2">
				<select class="form-select"  v-model="stts">
					<option value="">선택</option>
					<!--<option v-for="reqStts in reqSttsList" :value="reqStts.cmmnDetaCode">{{reqStts.codeDesct}}</option>-->
          <option value="1주일">1주일</option>
				</select>
			</div>
			<div class="col-2"> 
				<button @click="changeBtn" class="btn btn-primary mb-3">정지</button>
			</div>
		</div>
		<v-data-table
				v-model="selected"
				v-model:page="page"
    		v-model:items-per-page="itemsPerPage"
    		:headers="headers"
    		:items="warnList"
   		  item-value="rptNo"
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
				
	
	</div>

</template>


<script>

import { VDataTable } from 'vuetify/labs/VDataTable'
import axios from 'axios'
export default{
  data(){
    return{
			btnShow:true,
			reason:'',
			page:1,
			selected:[],
			itemsPerPage: 10,
			warn:{}, 
      warnList:[],
      warnReasonList:[],
      warnSttsList:[],
      stts:'',
      headers:[
	        {title: '신고번호',
					key: 'rptNo',},
	        {title: '게시물번호',
	          key: 'boardNo'},
					{title: '신고자',
	          key: 'memNo'
	        },
					/*{title: '피신고자',
	          key: 'price'
	        },*/ //어떻게 조인할지 고민
          {title: '사유',
	          key: 'rptRsn'
	        },
	        {title: '처리상태',
	          key: 'prcStts'}
	      ]
    }
  },
	components: {
      VDataTable,
    },
methods:{
	changeBtn(){
		console.log(this.selected);
		if(this.stts==''){
			this.$swal({
      	title: "기간을 선택하세요",
      	icon: "warning",
      	showConfirmButton: false,
				timer: 1500
    	});
			return;
		}
		let list =[];
		this.selected.forEach(item => {
			let obj={boardNo: item.boardNo, 
          memNo:item.memNo,
					}
			    list.push(obj);
		  });
		console.log(list);
		//this.modify(list)
	},
	modify(list){
  	axios.put('/admin/report', list)
  	.then(response => {
			console.log(response.data);
			this.boardList = response.data;
			this.selected = [];
    	this.stts = '';
    	this.warn = {};
  	})
  	.catch(err => console.log(err))
  	.finally(() => this.back())
	},
	info(event,item){
		let index=item.item.index;
		this.warn= this.warnList[index];
		console.log(warn)
	},
	
},
  mounted(){
		this.warnReasonList = this.$store.state.warnReasonList;
		this.warnSttsList = this.$store.state.warnSttsList;

		axios.get("/admin/warns")
		.then(response => {
			console.log(response.data);
			this.warnList = response.data;
		})
		.catch(err => console.log(err));
			
		
  },
	computed: {
      pageCount () {
        return Math.ceil(this.warnList.length / this.itemsPerPage)
      },
    }
}

</script>

<style>


</style>