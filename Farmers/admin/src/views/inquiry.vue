<template>  
<div>
  <v-data-table
    v-model:page="page"
    v-model:items-per-page="itemsPerPage"
    :headers="headers"
    :items="inquiryList"
    item-value="inqNo"
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
        <div v-if="Object.keys(inquiry).length>0">
          <div >
            <div class="row">
              <div class="col-5 row"><span class="col-3">문의번호</span><span class="col-5">{{inquiry.inqNo}}</span></div>
              <div class="col-5 row"><span class="col-3">문의일자</span><span class="col-5">{{inquiry.inqDate}}</span></div>
            </div>
            <div class="row">
              <div class="col-5 row"><span class="col-3">회원번호</span><span class="col-5">{{inquiry.memNo}}</span></div>
              <div class="col-5 row"><span class="col-3">닉네임</span><span class="col-5">{{inquiry.name}}</span></div>
            </div>
            <div class="row" style="padding-left: 17px;">
              <span class="col-3">제목</span>
              <span class="col-5">{{inquiry.inqTitle}}</span>
            </div>
            <div class="row">
              <div class="col-3" style="line-height: 200px;">문의내용</div>
                <div class="col-5" v-html="inquiry.inqDesct" style=" margin-left: 12px; width: 600px; height: 200px;overflow: auto; border: 1px solid lightgray;">
                </div>
            </div>
            <div class="row">
              <div class="col-3">답변</div>
                <div v-if="inquiry.replStts=='답변 대기'" style="width: 600px; height: 300px;">
                  <ckeditor :editor="editor" v-model="inquiry.replDesct" :config="editorReplyConfig"></ckeditor>
                </div>
                <div v-else style="width: 500px; height: 300px;" v-html="inquiry.replDesct"> </div>
            </div>
          </div>
          <div class="text-end">
            <button class="btn btn-primary mb-3 mx-3" @click="reply">답변보내기</button>
            <button class="btn btn-primary mb-3 mx-3" @click="back">돌아가기</button>
          </div>
        </div>
      </div>
    </div>
	
</div>


</template>


<script>
import { VDataTable } from 'vuetify/labs/VDataTable'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

export default {
  data() {
    return {
      editor: ClassicEditor,
      editorData: '<p>Content of the editor.</p>',
      editorDisabled: true,
      editorReplyConfig: {
        width: '100%',
        height: '400px'
      },
      idx:0,
      page:1,
			itemsPerPage: 10,
      inquiry:{}, 
      inquiryList:[],
      headers:[
	        {title: '문의번호',
					key: 'inqNo',},
	        { title: '카테고리',
						key: 'inqCtg'
						},
	        {title: '제목',
	          key: 'inqTitle'},
					{title: '작성일',
	          key: 'inqDate'
	        },
					{title: '작성자',
	          key: 'name'
	        },
	        {title: '답변여부',
	          key: 'replStts'}
	      ],
        multiSearch: {},
    };
  },
  components:{
    VDataTable
  },
  methods:{
    info(event,inq){
     //console.log(inq.item.index,inq.item.key)
     this.idx=inq.item.index
     this.inquiry=this.inquiryList[this.idx];
     console.log(this.inquiry);
     this.onpenModal()
    },
    onpenModal(){
		  document.getElementById("myModal").style.display = "block";
	  },
    reply(){
      console.log(this.inquiry.replDesct)
      fetch('admin/inquiryReply',{
        method: 'PUT',  
        headers: {
          'Content-Type': 'application/json', 
        },
        body:JSON.stringify(this.inquiry)
      })
      .then(result=>result.json())
      .then(result=> {
        if(result.retCode=='Success'){
          alert('답변성공');
          this.inquiryList[this.idx].replStts="답변 완료"
        }else{
          alert('답변실패');
        }
      })
      .catch(err=>console.log(err))
      .finally(()=>this.back())
      
    },
    back(){
      document.getElementById("myModal").style.display = "none";
    }
  },
  
  mounted(){
    fetch('admin/inquiryAdmin')
    .then(result=> result.json())
    .then(result=>{
      console.log(result);
      this.inquiryList=result;
    })
    .catch(err => console.log(err))
    ClassicEditor.builtinPlugins.map( plugin => console.log(plugin.pluginName));
		window.onclick = function(event) {
  		if (event.target == document.getElementById("myModal")) {
				document.getElementById("myModal").style.display = "none";
				this.inquiry={};
  		}
		}
		document.getElementsByClassName("close")[0].addEventListener('click',function(){
			document.getElementById("myModal").style.display = "none";
			this.inquiry={};
		})
  },
  computed: {
      pageCount () {
        return Math.ceil(this.inquiryList.length / this.itemsPerPage)
      },
    }
};
</script>


<style>
.ck-editor__editable {
  width: 500px;
	height: 200px;
}
.ck-toolbar_grouping{
  width: 500px;
}
.row{
  margin: 10px;
}

</style>