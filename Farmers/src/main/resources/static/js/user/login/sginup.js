// dateInput 꾸미기
			dateInput("birth",0)
			//정규식
			// 6자~15자리의 영문자, 숫자
			let regId = /^[a-zA-Z0-9]{6,15}$/; 
			// 2~8자리  영문자, 숫자 특수문자와 공백/[^a-z|A-Z|0-9|ㄱ-ㅎ|가-힣]/g
			let reqNick = /^[가-힣a-zA-Z0-9]{2,8}$/g; 
			//(randomString)@(randomString2).(2-3 characters)
			let regEmail = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');
			//이메일 인증번호 보내기
			let num;
			let btn = $('#sendEmail');
			let email = $('#joinEmail');
			btn.on('click', function (e) {
				let emailText = email.val();
				if (emailText == "") {
					Swal.fire({ title: '이메일을 입력하세요' , icon: 'warning',confirmButtonText:"확인"});
					return;
				}else if(!regEmail.test(emailText)){
					Swal.fire({ title: '정확한 이메일을 입력하세요' , icon: 'warning',confirmButtonText:"확인"});
					return;
				}
				$.ajax({
					url: 'emailCheck',
					method: 'get',
					data: {emailText},
					success(data) {
						if(data.retCode=="Success"){
							Swal.fire({ title: '해당 이메일로 성공적으로 인증번호를 보냈습니다' , icon: 'success',confirmButtonText:"확인"});
							$.ajax('sendEmail?emailText='+emailText)
							.done(data=>{
								if(data.retCode=='Success'){
									num = data.random;												
								}
							})
							.catch(err=> console.log(err))
							
						}else if(data.retCode=="Fail"){
							Swal.fire({ title: '이미 있는 이메일입니다' , icon: 'warning',confirmButtonText:"확인"});
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
					Swal.fire({ title: '인증성공' , icon: 'success',confirmButtonText:"확인"});
					email.removeData('set');
					email.data('set', email.val());
				} else {
					Swal.fire({ title: '인증번호를 정확히 입력하세요' , icon: 'warning',confirmButtonText:"확인"});
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
					Swal.fire({ title: '아이디는 6자~15자리의 영문자, 숫자를 입력하세요' , icon: 'error',confirmButtonText:"확인"});
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
							Swal.fire({ title: '중복체크 성공' , icon: 'success',confirmButtonText:"확인"});
								id.data('set', uid);
							} else if (data.retCode == 'Fail') {
								Swal.fire({ title: '이미 있는 아이디 입니다. 다른 아이디를 입력해주세요' , icon: 'warning',confirmButtonText:"확인"});
								id.val('');
								if (id.name) {
									id.removeData('set');
								}
								id.focus();
							} else {
								Swal.fire({ title: '알 수 없는 오류' , icon: 'error',confirmButtonText:"확인"});
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
					Swal.fire({ title: '닉네임는 2~8자리 입력하세요' , icon: 'warning',confirmButtonText:"확인"});
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
								Swal.fire({ title: '중복체크 성공' , icon: 'success',confirmButtonText:"확인"});

								nickname.data('set', nick);
							} else if (data.retCode == 'Fail') {
								Swal.fire({ title: '이미 있는 닉네임 입니다. 다시 입력해주세요' , icon: 'warning',confirmButtonText:"확인"});
								nickname.val('')
								if (nickname.data('set')) {
									nickname.removeData('set');
								}
								nickname.focus();
							} else {
								Swal.fire({ title: '알 수 없는 오류' , icon: 'error',confirmButtonText:"확인"});
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
					Swal.fire({ title: '아이디는 6자~15자리의 영문자, 숫자를 입력하세요' , icon: 'warning',confirmButtonText:"확인"});
					return false;
				} else if (!id.data('set')) {
					Swal.fire({ title: '아이디 중복체크를 해주세요' , icon: 'warning',confirmButtonText:"확인"});
					return false;
				} else if (!pwCheck('pw','pwCheck','id')) {
					$('#pw2').val('');
					$('#pwpwCheck').val('');
					return false;
				} else if (!nickname.data('set')) {
					Swal.fire({ title: '닉네임 중복체크를 해주세요' , icon: 'warning',confirmButtonText:"확인"});
					return false;
				} else if (!email.data('set')) {
					Swal.fire({ title: '이메일 인증을 해주세요' , icon: 'warning',confirmButtonText:"확인"});
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
					Swal.fire({title: '입력하지 않은 정보가 있습니다.' ,
							text:"이대로 회원가입하시겠습니까?",
						  icon: 'warning',
						  confirmButtonText:"예", 
						  showCancelButton: true,
						  cancelButtonText: '취소'})
						  .then((result)=>{
							  if(result.isConfirmed){
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
									url: '/signup',
									method: 'POST',
									data: member,
									async: false,
									success(data) {
										if (data.retCode == "Success") {
											Swal.fire({ title: '회원가입이 성공적으로 진행되었습니다' , icon: 'success',confirmButtonText:"확인"})
											.then((result)=>{location.href = "/login"});
										} else if (data.retCode == "Fail") {
											Swal.fire({ title: '회원가입을 실패 했습니다.',text:'관리자에게 문의해주세요' , icon: 'error',confirmButtonText:"확인"})
											.then((result)=>{location.reload()});
										} else {
											Swal.fire({ title: '알 수 없는 오류.',text:'관리자에게 문의해주세요' , icon: 'error',confirmButtonText:"확인"});
										}
									},
									error(err) {
										console.log(err)
									}
								})
						  	 }
						  })
				}
					
				

				
				
			})

