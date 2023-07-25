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