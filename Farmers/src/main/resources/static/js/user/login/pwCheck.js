let regPw = /^[a-zA-Z0-9!@#$%^&*()?_~]{8,15}$/; // 8~15자리 숫자, 영문, 특수문자 조합

	//비밀번호 체크
	function pwCheck(pw1,pwCheck,id) {
		let finalId = $('#'+id);
		let password = $('#'+pw1);
		let password2 = $('#'+pwCheck);
		if (!regPw.test(password.val())) {
			alert("비밀번호는 숫자, 영문, 특수문자 조합으로 8~15자리를 사용해야 합니다.");
			return false;
		}
		let chk = 0;
		if (password[0].value.search(/[0-9]/g) != -1) chk++;
		if (password[0].value.search(/[a-z]/ig) != -1) chk++;
		if (password[0].value.search(/[!@#$%^&*()?_~]/g) != -1) chk++;
		if (chk < 2) {
			alert("비밀번호는 숫자, 영문, 특수문자를 두가지이상 혼용하여야 합니다.");
			return false;
		}
		// 동일한 문자/숫자 4이상, 연속된 문자
		if (/(\w)\1\1\1/.test(password.val()) || isContinuedValue(password.val())) {
			alert("비밀번호에 4자 이상의 연속 또는 반복 문자 및 숫자를 사용하실 수 없습니다.");
			return false;
		}
		// 아이디 포함 여부
		if (password[0].value.search(finalId.val()) > -1) {
			alert("ID가 포함된 비밀번호는 사용하실 수 없습니다.");
			return false;
		}
		// 기존 비밀번호와 새 비밀번호 일치 여부
		if (password[0].value != password[0].value) {
			alert("기존 비밀본호와 새 비밀번호가 일치하지 않습니다.");
			return false;
		}
		return true;
	}
	function isContinuedValue(value) {
		var intCnt1 = 0;
		var intCnt2 = 0;
		var temp0 = "";
		var temp1 = "";
		var temp2 = "";
		var temp3 = "";
		for (var i = 0; i < value.length - 3; i++) {
			temp0 = value.charAt(i);
			temp1 = value.charAt(i + 1);
			temp2 = value.charAt(i + 2);
			temp3 = value.charAt(i + 3);
			if (temp0.charCodeAt(0) - temp1.charCodeAt(0) == 1 &&
				temp1.charCodeAt(0) - temp2.charCodeAt(0) == 1 &&
				temp2.charCodeAt(0) - temp3.charCodeAt(0) == 1) {
				intCnt1 = intCnt1 + 1;
			}
			if (temp0.charCodeAt(0) - temp1.charCodeAt(0) == -1 &&
				temp1.charCodeAt(0) - temp2.charCodeAt(0) == -1 &&
				temp2.charCodeAt(0) - temp3.charCodeAt(0) == -1) {
				intCnt2 = intCnt2 + 1;
			}
		}
		return (intCnt1 > 0 || intCnt2 > 0);
	}