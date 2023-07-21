<template>
  <div id="memberTable"></div>


</template>


<script>
export default{
  data(){
    return{
      memberList:[],
      columns:[
	        {
	          header: '회원번호',
	          name: 'memNo',
	          sortable: true,
	          
	        },
	        {
	          header: '성별',
	          name: 'gen',
	          sortable: true,
	          filter: {
                type: 'select',
                showApplyBtn: true,
                showClearBtn: true
                }
	        },
	        {
	          header: '닉네임',
	          name: 'nick',
	          sortable: true,
	        },
	        {
	          header: '상태',
	          name: 'stts',
	          sortable: true,
	          filter: {
                type: 'select',
                showApplyBtn: true,
                showClearBtn: true
                }
	        }
	      ]
    }
  },
  mounted(){
 
    fetch("/admin/members")
    .then(result=> result.json())
    .then(result=>{
      console.log(result)
      this.memberList=result.memList;
	    const vue= this;
	    const Grid = tui.Grid;
	     Grid.applyTheme('default');
	      Grid.setLanguage('ko');
	    const grid = new Grid({
	      el: document.getElementById('memberTable'),
	      data: vue.memberList,
	      scrollX: false,
	      scrollY: false,
	      pageOptions: {
          perPage: 10,
          useClient: true
          },
	      columns: this.columns ,
	      rowHeaders: [
          {
            type: 'checkbox',
            header: "",
            width: 50,
          }
        ]
        
	    });
   		grid.on('click', () => {
  			console.log();
		})
    })
    .catch(err=> console.log(err))
  }
}

</script>