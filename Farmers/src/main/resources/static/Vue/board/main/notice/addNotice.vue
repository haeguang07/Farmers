<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>
<script src="https://ckeditor.com/apps/ckfinder/3.5.0/ckfinder.js"></script>

<script>
  export default {
    data() {
      return {
        memNo: '',
        postCtg: 'i2',
        dst1: '',
        dst2: '',
        title: '',
        img: '',
        desct: ''
      }
    },
    mounted() {
      //ckEditor 생성
			let editor;
			ClassicEditor.create(document.querySelector('#editor'), {
					language: "ko",
					ckfinder: {
                      uploadUrl: '/image/upload'
                    }
				})
				.then(newEditor => {
					editor = newEditor;
				})
				.catch(error => {
					console.error(error);
				});

        let vue = this;

			//ckEditor 데이터 가져오기
			$('#addBtn').on('click', function () {

				//데이터 입력 확인
				if (!$('[name="title"]').val()) {
					alert('제목을 입력하세요');
					return;
				}  else {
					let ckData = editor
					let formData = {
            memNo: vue.$store.getters.getMemNo,
            postCtg: vue.postCtg,
            dst1: vue.dst1,
            dst2: vue.dst2,
            title: vue.title,
            img: vue.img,
            desct: ckData.getData()
          };
          console.log(formData);

					//상세 설명 존재하지 않을 시 출력 내용 설정
					if (ckData.getData() == '') {
						ckData.setData('<p>상세 설명이 존재하지 않습니다</p>')
						formData.desct = ckData.getData()
					} else {
						formData.desct = ckData.getData()
					}
					formData.rep = dbPath
					console.log(formData);

					//ajax 공지사항 등록
					$.ajax({
							url: "addNotice",
							method: "POST",
							data: formData
						})
						.done(function (result) {
							alert(result.retCode);
							// location.href = "auctionList"
						})
						.fail(function (result) {
							alert(result.body);
							// location.href = "auctionList"
						}) 
				}
			})
    },
    computed: {
      mem() {
        return this.$store.getters.getMemNo;
      }
    }
  }
</script>

<template>
  <section>
    <form id="myForm">
      {{ mem }}
      <table> 
        <tr>
          <th>글제목</th>
          <td><input v-model="title"></td>
        </tr>
        <tr>
          <th>작성자</th>
          <td><input name="nick" readonly></td>
        </tr>
        <tr>
          <th>내용</th>
          <td><textarea v-model="desct" name="text" id="editor"></textarea></td>
        </tr>
      </table>
      <button type="button">취소</button>
      <button id="addBtn" type="button">등록</button>
    </form>
  </section>
</template>

<script>

</script>