<template>
	<div>
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
			pageOptions: { perPage: 10, useClient: true },
			columns: this.columns,
			rowHeaders: [{ type: 'checkbox', header: "", width: 50 }]
		});
		this.gridInstance.on('click', (ev) => {
			if (ev.columnName != '_checked' && ev.rowKey >= 0) {
					console.log(ev.rowKey, this.memberList[ev.rowKey], ev.columnName);
					this.member=this.memberList[ev.rowKey];
					console.log(this.member);

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
  }
}

</script>