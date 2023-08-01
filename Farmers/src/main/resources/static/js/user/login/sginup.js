// dateInput 꾸미기
			dateInput("birth",0)
			//정규식
			let regId = /^[a-zA-Z0-9]{6,15}$/; // 6자~15자리의 영문자, 숫자
			let reqNick = /^[가-힣a-zA-Z0-9]{2,8}$/g; // 2~8자리  영문자, 숫자 특수문자와 공백/[^a-z|A-Z|0-9|ㄱ-ㅎ|가-힣]/g
			let regEmail = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');//(randomString)@(randomString2).(2-3 characters)
			//이메일 인증번호 보내기
			let num;
			let btn = $('#sendEmail');
			let email = $('#joinEmail');
			btn.on('click', function (e) {
				let emailText = email.val();
				if (emailText == "") {
					alert('이메일을 입력하세요');
					return;
				}else if(!regEmail.test(emailText)){
					alert('정확한 이메일을 입력하세요');
					return;
				}
				$.ajax({
					url: 'emailCheck',
					method: 'get',
					data: {
						emailText
					},
					success(data) {
						if(data.retCode=="Success"){
							alert("해당 이메일로 성공적으로 인증번호를 보냈습니다")
							$.ajax('sendEmail?emailText='+emailText)
							.done(data=>{
								num = data.random;
								console.log(num);															
							})
							.catch(err=> console.log(err))
							
						}else if(data.retCode=="Fail"){
							alert('이미 있는 이메일입니다')
						}
					},
					error(err) {
						console.log(err)
					}
				})

			});

			let cBtn = $('#certify');
			cBtn.on('click', function (e) {
				let cNum = $('#certifytext').val();
				if (cNum == num) {
					alert('인증성공');
					email.data('set', email.val());
				} else {
					alert('인증번호를 정확히 입력하세요');
				}
			})
			


			let id = $('#id');
			let post = $('#post');
			let adr = $('#adr');
			let adr2 = $('#adrDetail');
			let email2 = $('#joinEmail');
			let certifytext = $('#certifytext');
			let nickname = $('#nickname');
			let name = $('#name');
			let mbl = $('#joinPhone');
			let gen = $('#gender');
			let birth = $('#birth');

			//아이디 체크
			let idCheckBtn = $('#idCheck');
			idCheckBtn.on('click', function () {
				if (!regId.test(id.val())) {
					alert('아이디는 6자~15자리의 영문자, 숫자를 입력하세요');
					return;
				} else {
					let uid = id.val();
					$.ajax({
						url: "userIdCheck",
						data: {
							uid
						},
						method: 'get',
						success(data) {
							if (data.retCode == 'Success') {
								alert('중복체크 성공');
								id.data('set', uid);
								console.log(id.data('set'));
							} else if (data.retCode == 'Fail') {
								alert('이미 있는 아이디 입니다. 다시 입력해주세요');
								id.val('');
								if (id.name) {
									id.removeData('set');
								}
								id.focus();
							} else {
								alert('알 수 없는 오류');
							}
						},
						error(err) {
							console.log(err)
						}

					})


				}
			})

			//닉네임 중복체크
			let nickcheckBtn = $('#nickcheck');
			nickcheckBtn.on('click', function () {
				if (!reqNick.test(nickname.val())) {
					alert('닉네임는 2~8자리 입력하세요');
					return;
				} else {
					let nick = nickname.val();
					$.ajax({
						url: "nickCheck",
						methods: 'get',
						data: {
							nick
						},
						success(data) {
							if (data.retCode == 'Success') {
								alert('중복체크 성공');
								nickname.data('set', nick);
								console.log(nickname);
							} else if (data.retCode == 'Fail') {
								alert('이미 있는 닉네임 입니다. 다시 입력해주세요');
								nickname.val('')
								if (nickname.data('set')) {
									nickname.removeData('set');
								}
								nickname.focus();
							} else {
								alert('알 수 없는 오류');
							}
						},
						error(err) {
							console.log(err)
						}
					})



				}
			})
			

			let nextBtn = $('#nextBtn');
			nextBtn.on('click', step1)

			function step1() {
				if (!regId.test(id.val())) {
					alert('아이디는 6자~15자리의 영문자, 숫자를 입력하세요');
					return false;
				} else if (!id.data('set')) {
					alert('아이디 중복체크를 해주세요');
					return false;
				} else if (!pwCheck('pw','pwpwCheck','id')) {
					$('#pw2').val('');
					$('#pwpwCheck').val('');
					return false;
				} else if (!nickname.data('set')) {
					alert('닉네임 중복체크를 해주세요');
					return false;
				} else if (!email.data('set')) {
					alert('이메일 인증을 해주세요');
					return false;
				}

				$('#step1').css({"display":"none"});
				$('#step2').css({"display":"block"});
				return true;

			}


			$('#frm').on('submit', function () {
				event.preventDefault();
				if (!step1()) {
					return;
				};
				let zip =post.val();
				let mblVal= mbl.val();
				if(zip=='' || mblVal==''){
					if(!confirm('아직 입력하지 않은 정보가 있습니다\n그대로 회원가입을 하시겠습니까?')){
						return;
					}
					zip=0;

				}

				let member = {
					id: $('#id').data('set'),
					pw: $('#pw').val(),
					email: $('#joinEmail').data('set'),
					nick: $('#nickname').data('set'),
					name: $('#name').val(),
					zip,
					addr: adr.val(),
					detaAddr: adr2.val(),
					mbl:mbl.val(),
					bDate: birth.val(),
					gen: gen.val()
				};

				$.ajax({
					url: 'signup',
					method: 'POST',
					data: member,
					success(data) {
						if (data.retCode == "Success") {
							alert('회원가입 성공')
							location.href = "/login";
						} else if (data.retCode == "Fail") {
							alert('회원가입 실패')
							location.reload();
						} else {
							alert('알 수 없는 오류')
						}
					},
					error(err) {
						console.log(err)
					}
				})
			})

