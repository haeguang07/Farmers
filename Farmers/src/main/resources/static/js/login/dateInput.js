function dateInput(id,max,min){
				let obj={
						dateFormat: 'yy-mm-dd' //Input Display Format 변경,
						,prevText: '이전달'
						,nextText: '다음달'
						,yearRange: 'c-50:c+50'//년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 표시할것인가.
						,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
						,showMonthAfterYear: true //년도 먼저 나오고, 뒤에 월 표시
						,changeYear: true //콤보박스에서 년 선택 가능
						,changeMonth: true //콤보박스에서 월 선택 가능                
						,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
						,buttonImage: "/img/icon/icon-calendar.png" //버튼 이미지 경로
						,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
						,buttonText: "날짜선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
						,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
						,monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'] //달력의 월 부분 텍스트
						,monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'] //달력의 월 부분 Tooltip 텍스트
						,dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'] //달력의 요일 부분 텍스트
						,dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'] //달력의 요일 부분 Tooltip 텍스트
					}
						//,maxDate: "+0" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후, 0:오늘까지)          
				if(typeof max =='number'){
					obj.maxDate= "+"+max
				}
				if(typeof min =='number'){
					obj.minDate= "-"+min
				}
				$("#"+id).datepicker(obj);
				
				//초기값을 오늘 날짜로 설정해줘야 합니다.
				$("#"+id).datepicker('setDate', "today"); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)            
				//이미지 설정
				$('.ui-datepicker-trigger').css({
					'width': "25px"
				})
			}