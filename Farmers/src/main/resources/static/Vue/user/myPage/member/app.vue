<template>
  <div id="app">
    <div id="pwCheckDiv">
      <PwCheck/>
    </div>
  </div>
</template>


<script th:inline="javascript">
  import PwCheck from "./component/PwCheck.vue";

  export default {
    name: "app",
    components: { // 가져온 component 들을 등록합니다.
      PwCheck : PwCheck
    }
  };
</script>