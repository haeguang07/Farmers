<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>

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
        desct: '',
        editor: null
      }
    },
    mounted() {
      this.initializeEditor();
    },
    methods: {
      initializeEditor() {
        ClassicEditor.create(document.querySelector('#editor'), {
          removePlugins: ['Heading'],
          language: 'ko',
          uploadUrl: '/image/upload'
        })
          .then(editor => {
            this.editor = editor;
          })
          .catch(error => {
            console.error(error);
          });
        }
    },
    computed: {
      // mem() {
      //   return this.$store.getters.getMemNo;
      // }
    }
  }
</script>

<template>
  <section>
    <form action="" method="POST" id="myForm">
      <!-- {{ mem }} -->
      <h4>공지사항 등록</h4>
      <table>
        <tr>
          <th>글제목</th>
          <td><input class="form-control" type="text" v-model="title"></td>
        </tr>
        <tr>
          <th>작성자</th>
          <td><input class="form-control" type="text" name="nick" readonly></td>
        </tr>
        <tr>
          <th>내용</th>
          <td><textarea name="text" id="editor"></textarea></td>
        </tr>
      </table>
      <button type="button">취소</button>
      <button id="addBtn" type="submit">등록</button>
    </form>
  </section>
</template>

<script>

</script>