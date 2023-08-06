<template>

	<div class="body">
		<h1>회원 관리</h1>
		<br>
		
		<div class="row">
			<div class="col-1 lh-lg text-center" style="margin-left: 15px;"><b>검색조건</b></div>
			<div class="col-1" style="width: 10%; flex: 0 0 10%;max-width: 10%;">
				<select class="form-select form-group"  id="addon-wrapping" v-model="searchType" >
					<option value="아이디" selected><b>아이디</b></option><option value="닉네임"><b>닉네임</b></option>
				</select>
			</div>
			<div class="col-2">
				<input type="text" class="form-control"  aria-describedby="addon-wrapping" v-model="searchText" @change="search">
			</div>
		</div>
				
		<div class="row">
			<div class="col-1 lh-lg text-center" style="margin-left: 15px;"><b>회원등급</b></div>
				<div class="col-1" style="width: 10%; flex: 0 0 10%;max-width: 10%;">
					<select class="form-select" id="addon-wrapping" v-model="searchGrd" @change="search" >
						<option selected value="">선택</option>
						<option v-for="grd in gradeList" :value="grd.cmmnDetaCode">{{grd.codeDesct}}</option>
					</select>
				</div>
				
				<div class="col-1 lh-lg"><b>활동상태</b></div>
				<div class="col-1 text-center" style="width: 10%; flex: 0 0 10%;max-width: 10%;">
					<select class="form-select col" id="addon-wrapping" v-model="searchStts" @change="search">
						<option selected value="">선택</option>
						<option v-for="status in sttsList " :value="status.cmmnDetaCode">{{status.codeDesct}}</option>
					</select>
			</div>
		</div>
				
		
		<div class="row">
			<div class="col-1 lh-lg text-center" style="margin-left: 15px;"><b>가입경로</b></div>
				<div class="col-1" style="width: 10%; flex: 0 0 10%;max-width: 10%;">
					<select class="form-select" id="addon-wrapping" v-model="searchLogin" @change="search">
						<option selected value="">전체</option>
						<option value="일반">일반</option><option value="카카오">카카오</option>
						<option value="구글">구글</option><option value="네이버">네이버</option>
					</select>
				</div>

				<div class="col-1 lh-lg"><b>가입일</b></div>
				<div class="col-2" style="width: 14%; flex: 0 0 14%;max-width: 14%;">
					<input type="date" class="form-select" v-model="searchStr" @change="search">
				</div>
		</div>
		<hr>
		<div style="width: 1000px; float: right;" class="row"> 
			<div class="col-4"></div>
			<div class="col-2 text-end lh-lg" >📌<b>선택한 회원</b></div>
			<div class="col-2">
				<select class="form-select"  v-model="grade">
					<option selected value="">등급선택</option>
					<option v-for="grd in gradeList" :value="grd.cmmnDetaCode">{{grd.codeDesct}}</option>
				</select>
			</div>
			<div class="col-2">
				<select class="form-select"  v-model="stts">
					<option selected value="">활동선택</option>
					<option v-for="status in sttsList" :value="status.cmmnDetaCode">{{status.codeDesct}}</option>
				</select>
			</div>
			<div class="col-2  text-center">
				<button @click="changeBtn" class="btn btn-success">변경하기</button>
			</div>
		</div>
		<div style="clear: both;"></div>
		<v-data-table
			v-model="selected"
			v-model:page="page"
    	v-model:items-per-page="itemsPerPage"
    	:headers="headers"
    	:items="memberList"
			no-data-text="조회된 회원이 없습니다"
			return-object
    	show-select
			hide-default-footer
			@click:row=info
   		class="elevation-1">
			 <template v-slot:bottom>
      <div class="text-center pt-2">
        <v-pagination
          v-model="page"
          :length="pageCount"
        ></v-pagination>
				</div>
				</template>
			
			
		</v-data-table>
				
			<!-- 모달창 -->
  	<div id="myModal" class="modal">
      <!-- 모달창 내용 -->
      <div class="modal-content">
        <span class="close">&times;</span>
        <div v-if="Object.keys(member).length>0">
          <div>
						<table class="table" style="margin-bottom: 0px;">
							<tbody>
								<tr>
									<th>회원번호</th><td v-text="member.memNo"></td>
									<th>가입일자</th><td v-text="member.signDate"></td>
								</tr>
								<tr>
									<th>아이디</th><td v-text="member.id"></td>
									<th>닉네임</th><td v-text="member.nick"></td>
								</tr>
								<tr v-if="member.memGrd=='준회원'">
									<th>제출서류</th><td colspan="3" v-if="member.grdAtchFile !=null"><img class="col-5" :src="member.grdAtchFile"></td>
									<td colspan="3" v-else="member.grdAtchFile==null"> 제출 서류가 없습니다</td>
								</tr>
							</tbody>
						</table>
            
          </div>
          <div class="text-end">
						<div v-if="member.memGrd=='준회원' && member.grdAtchFile !=null" >
							<button v-show="btnShow" class="btn btn-success mb-3 mx-3" @click="apply">승인</button>
							<select v-model="reason" v-show="!btnShow">
								<option value="이미지가 정확하지 않습니다">이미지가 정확하지 않습니다</option>
								<option value="주소지와 등본의 주소가 일치하지 않습니다">주소지와 등본의 주소가 일치하지 않습니다</option>
							</select>
							<button class="btn btn-danger mb-3 mx-3" @click="refusal1" v-show="btnShow">승인거부</button>
							<button class="btn btn-danger mb-3 mx-3" @click="refusal2" v-show="!btnShow">승인거부</button>
						</div>
						<div v-else>
							<button class="btn btn-primary mb-3 mx-3" @click="back">돌아가기</button>
						</div>
          </div>
        </div>
      </div>
    </div>

		
	</div>

</template>


<script>

import { VDataTable } from 'vuetify/labs/VDataTable'
import axios from 'axios'
export default{
  data(){
    return{
			btnShow:true,reason:'',
			searchType:'아이디',searchText:'',
			searchStts:'',searchLogin:'',
			searchStr:'',searchEnd:'',
			searchGrd:"",page:1,
			selected:[],itemsPerPage: 10,member:{}, 
      memberList:[
    {
        "memNo": "MEM000344",
        "id": "BestWoong",
        "pw": null,
        "email": "NVfd2Hrjl4GgeVtcoGVHukGQsqWotGWVZvjBhLCr8y2NGkJkVFskcU+w4ms0EWbNGvtURb71LU2gJqVLelbFZw==",
        "nick": "웅이짱",
        "loginPath": "일반",
        "rptCnt": 0,
        "memGrd": "준회원",
        "name": null,
        "zip": "GMsWnYLFHbAyT3xnCpURhaBaGqNnIXDEUdAFcksf5moe9Nsa/VSR5kCpU77L/VQ8",
        "addr": "LLmG22VT9UVxH4el3A+hujX7vjLqJBrKshHodV8T0Dzn74DGyXr6rKR9YFHegDkH",
        "detaAddr": "kKAtRhNHVn+2BXy3AulWzC/bVwN1vl2BKhJyC42ylOFJaShh96lav5vRyhmdDvdi",
        "mbl": "OG3gQx9Tdm+QHjBYf8mOqflJ3neZ6jfeKQRhVyY+B06NNPPonrjinzlhFOSD9l4z",
        "bDate": "2023-08-05",
        "gen": "남",
        "pnt": 0,
        "stts": "정상",
        "signDate": "2023-08-06",
        "prof": null,
        "grdAtchFile": null,
        "wdrAplDate": null,
        "blckDate": null,
        "bdate": "2023-08-05"
    },
    {
        "memNo": "MEM000321",
        "id": "rlatjdgus",
        "pw": null,
        "email": "YYeUraugRbKMHShRdipmdPG0pRG3HyWmY3ma3x14R7KAJKG3QamOU9bMlhcSiOMMhtJpFf+OXd2uOiR3q8dnIg==",
        "nick": "정해광멍충멍충",
        "loginPath": "일반",
        "rptCnt": 0,
        "memGrd": "정회원",
        "name": null,
        "zip": "Vqito4pt3ZY+cqEnwqD6sqET9MsZTCRgzBY4g7lfrlKKH4szJWamhrtqIQuE4UfI",
        "addr": "WZ1I3TJ3k9Efb68lt2a5K8L5Aft/tqHIj5x9cHgrxae1vObApWNQF+r8VC3jVsdLuuISfw+Ej5/rKTiaFSl5phkrNh2EFEOrkArlJtROUc8=",
        "detaAddr": "wLwvFLmNCDMWOXsuMklpI2Z0dIZNtL2hUejb5E/iPLHe1ukiT6mifuz5WOngZgi1ajlIttC0RrkjbWXTFUxEzA==",
        "mbl": null,
        "bDate": "2023-08-04",
        "gen": "여",
        "pnt": 0,
        "stts": "정상",
        "signDate": "2023-08-05",
        "prof": null,
        "grdAtchFile": null,
        "wdrAplDate": null,
        "blckDate": null,
        "bdate": "2023-08-04"
    },
    {
        "memNo": "MEM000307",
        "id": "na_aFzLO3icN86JJ-rc3HgrPP",
        "pw": null,
        "email": "X/d99+/eVHJ2OaLLWZAX/aep6rgHVWrdvw34YeTHDka06RMyuYV9RmNaP8+FVUhgsckBRPlmXKZ0Q2PDfYdQwQ==",
        "nick": "정해광",
        "loginPath": "네이버",
        "rptCnt": 0,
        "memGrd": "정회원",
        "name": "정해광",
        "zip": "C4kja0t7RUuEu3zUF78FpxKiagBOzH1OwB482lM32C22TXI7aVFia8sJT7EHwtOV",
        "addr": "HHqugxPiPhyNnCtWsMw27x8NpUDFz8YxXQUn9nBPTVmSAkdEndrv6Q7nRlEzH21F",
        "detaAddr": "/n4wDp60aa22hss7ALtDCTIoy5XqsNYZTOSdAfMriV5gVQv/RPAkh+5OxglrAq3R",
        "mbl": "IyJE/cuPnvqi/LPnyGig+0SENmFUfsxQVnu0ww3I9mfJjy7wLZ06GMI5s2D+ge+R",
        "bDate": null,
        "gen": "남",
        "pnt": 0,
        "stts": "정상",
        "signDate": "2023-08-04",
        "prof": "https://ssl.pstatic.net/static/pwe/address/img_profile.png",
        "grdAtchFile": null,
        "wdrAplDate": null,
        "blckDate": null,
        "bdate": null
    },
    {
        "memNo": "MEM000306",
        "id": "salt",
        "pw": null,
        "email": "3UCu38IpWYuGmqJaP2EX6L9Vr4rO1WRxQiNxf9NvLEIiC+LNh3pn6SEJDt4WHh4Q",
        "nick": "소금쟁이",
        "loginPath": "일반",
        "rptCnt": 0,
        "memGrd": "정회원",
        "name": "정해광",
        "zip": "tmZSFE8reoeNAshss9MPNcV5loLnzFRbmgpJljPDgNRmjRuyJMGzL27k3+BnNuFw",
        "addr": "lPba9Ki1Rb+qTU0aMn1Z7/Z+Nvg9xMjPVDF+uCkbPlxDE4IP37o+yRUGxGWS/QnU",
        "detaAddr": "QTHCOjmCvjVJ2EvadTVxj6/WRNySfDr22Md+vJnaZehSN5hlcojYJPpr7zLTHVet",
        "mbl": "pHw6Fj67rO1Pu5rL0JRB83oOTDgcRkh4SMOeExe25rPXilFWlvTwHts6T4vtxXmS",
        "bDate": null,
        "gen": "남",
        "pnt": 0,
        "stts": "정상",
        "signDate": "2023-08-04",
        "prof": "http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg",
        "grdAtchFile": null,
        "wdrAplDate": null,
        "blckDate": null,
        "bdate": null
    },
    {
        "memNo": "MEM000304",
        "id": "ka_2946582561",
        "pw": null,
        "email": "iclSrFPraIiRcCMb1ebHgkkdMsdM8VhKEfN6prL70ZMlyq1risBghe50dwPJ3SHG/vIn5KpODJnzn2CCsJtZPw==",
        "nick": "김승환",
        "loginPath": "카카오",
        "rptCnt": 0,
        "memGrd": "준회원",
        "name": "김승환",
        "zip": "qKS+i3WwkR9zQOuGz9CGKMMDgbrho8yc74+OZ+rz9w9q4asNqLGFUkCi4+mcr5PG",
        "addr": "uAsqNyHHY2Be9UMwNexyk/VMfjW5On7wdQXa7EO54lPyLnQZgEtpzot8PWb4O8+GvJbx4zSJjxyA+wup1bG6E3E2EzZN2gTSEGkERpjw4Bs=",
        "detaAddr": "1r46LKWhQGlx5EuvOGq25xjUp5qe2Y/1LjVjI779cSMULTEKDRnTS7sBN1dbkidc2/6LTfzfj4iDmROAcSYdvw==",
        "mbl": "SCJHcq7iAVCopBWOifwPkkxeCoaeukAhaW0/JIJxocuInmEbxSbHDQ2F0t+exQ+N",
        "bDate": "1993-06-06",
        "gen": "남",
        "pnt": 0,
        "stts": "정상",
        "signDate": "2023-08-04",
        "prof": "http://k.kakaocdn.net/dn/05XQQ/btqLeUuoAou/aMW8G20A3xfuoNX6ZFkCvK/img_640x640.jpg",
        "grdAtchFile": null,
        "wdrAplDate": null,
        "blckDate": null,
        "bdate": "1993-06-06"
    },
    {
        "memNo": "MEM000301",
        "id": "ksw1111",
        "pw": null,
        "email": "EOVR4KYw4CMZ4gLGQMJkcEzfvf2OWOA0TTFK083OpRIvNjVIBzPGh2HCMfW2rGmg2zNsoaQkMXbmiQBsN586Ew==",
        "nick": "www",
        "loginPath": "일반",
        "rptCnt": 0,
        "memGrd": "준회원",
        "name": null,
        "zip": "shLCVCzULQ3EF23h02FYL1VY1dpCfW1xQK/HRmydqgVzpSPvh1ZPaxYD3DUrLi+a",
        "addr": "CHDRTeXVMvrzzR13h1OHjmiNuedcdlSHkB234Yvm2+1E1hKqGoaqg9xCvCDzo32/",
        "detaAddr": "fL66AuOPGwn0/gg//zK3OhVAuB1O5pPtqnDtVzTzlzxHe72i5+1G71bTrfuCHeyj",
        "mbl": "QMd0JiEsA5GlzcwoMTPceO6/ph8iPGb+7rnC/FdbeSuEsN/umPuN+cF4dUIorPDO",
        "bDate": "2023-08-03",
        "gen": "남",
        "pnt": 0,
        "stts": "정상",
        "signDate": "2023-08-04",
        "prof": null,
        "grdAtchFile": null,
        "wdrAplDate": null,
        "blckDate": null,
        "bdate": "2023-08-03"
    },
    {
        "memNo": "MEM000265",
        "id": "na__75OxNNpNKlvIj4VF3tZEr",
        "pw": null,
        "email": "P400uZRJ1pPxPZW6WNJfHTZ8dUuETK7mLLN4Ru7tDf01Q71VnQUV6SVi7sVvfZsFmlha9dVk8dBaAVwGiq08Dg==",
        "nick": "woogie",
        "loginPath": "네이버",
        "rptCnt": 0,
        "memGrd": "준회원",
        "name": "김성욱",
        "zip": "HBmyMWCy/MDH2V8nKu5GFSY9V/97mtoXlwyYXUK1TBag4mbxy++D2nv6RVYZrexZ",
        "addr": "X1sNVWfXTUTMSYMNTo+RVTgizjJfLFfMihfTIaCnowrVjjl/GHWz303VNkGDjP1z",
        "detaAddr": "ekRbukBHqdD7r9MN5x6jNl+0WT1juiIS9jkgPAR2WlR+YxkFKR6SvuYwfqjvZ9eD",
        "mbl": "rtG4EO+EdBoizo5XPzpzs2VS2q2+kt7WMetSvrlPbN1+dc30JcIz0Wk8iMlx74p2",
        "bDate": null,
        "gen": "남",
        "pnt": 0,
        "stts": "정상",
        "signDate": "2023-08-03",
        "prof": "https://ssl.pstatic.net/static/pwe/address/img_profile.png",
        "grdAtchFile": null,
        "wdrAplDate": null,
        "blckDate": null,
        "bdate": null
    },
    {
        "memNo": "MEM000262",
        "id": "ksw0000",
        "pw": null,
        "email": "kY6nP04i01fJgHFDRMErA4TLiInyfLVWqTukZZQGMX3lwRotODiIIVOuS4svFdKFChubj2qu74dfNohsgtTAQA==",
        "nick": "asdasd",
        "loginPath": "일반",
        "rptCnt": 0,
        "memGrd": "준회원",
        "name": "김성욱",
        "zip": "CzHOZVVP9TNT6F8HmvLDrkG8OFKuKnhjXgJjL6jS+EJ7SV4qyZ2hzEvkuz39Ejbg",
        "addr": "nysrcH/NcYWm/iH3jaQwMkGV2Dmj/BBW2xdhdP/9cD9C9QOqwk8mgJOhQVco6vS4OC5TGvbJ9M1gmYv/cSHrrjvh6s9GiEra7uGQn7/GAW4=",
        "detaAddr": "3NsQKIydzPtuOBwzF/GVQAxfyKTynSXNFzKLi7PKKAQfrVFg1Nbh3+D1dSNcFgGO",
        "mbl": "wTqZq60xqcViEVOr6aL6pSqDeBLuEkx+wAjyXwMUy834GwINqXBqMxVqeenzoAiv",
        "bDate": "2023-08-02",
        "gen": "남",
        "pnt": 0,
        "stts": "정상",
        "signDate": "2023-08-03",
        "prof": null,
        "grdAtchFile": null,
        "wdrAplDate": null,
        "blckDate": null,
        "bdate": "2023-08-02"
    },
    {
        "memNo": "MEM000225",
        "id": "light",
        "pw": null,
        "email": "fQVF2thxkhmduyR/Ayc/ItqYSc1YvPYswCod8hq8NER0qZy8F1h4DA0Ofoy+PPCx",
        "nick": "홍길동",
        "loginPath": "일반",
        "rptCnt": 0,
        "memGrd": "정회원",
        "name": "홍길동",
        "zip": "fKm/bEOnpVDGS23+WLoKL+7M0RdQgdM1s+frPIZ7zRZtbqfU65FmqcsywC1TwkI7",
        "addr": "6tjm4+KG26INlJyuBwj2TGAGgkgTIgAu2gIBjF9545bPF1qQLxjf28z7QbPDrV5Tg1W5dxBU5fmN4NAP52OD8Nn3u07N+yn1T8hwQ9fK8SA=",
        "detaAddr": "75zjuDiRd3AWbIlAJB2U77LaBjwwO1rw4EMidMBAlqTUKGanc5YzlVZzQha0m5gzOB6Iny6NZNtUREYGAcqalQ==",
        "mbl": "PWrnp11TLHg0t7KZZTuSUeRyW3HTunn+DjL/LxKD7GeGIzeaZly8Vmq2fL3eqG2O",
        "bDate": "1995-07-16",
        "gen": "남",
        "pnt": 0,
        "stts": "정상",
        "signDate": "2023-08-02",
        "prof": null,
        "grdAtchFile": null,
        "wdrAplDate": null,
        "blckDate": null,
        "bdate": "1995-07-16"
    },
    {
        "memNo": "MEM000224",
        "id": "hyoung",
        "pw": null,
        "email": "jrqA2Z12XQ7VrcnMu/cmvv2o4kDSiA3yleV8UY+wV4WSLoLvoWNnZWrJeiTm90Yr",
        "nick": "빵빵이",
        "loginPath": "일반",
        "rptCnt": 0,
        "memGrd": "준회원",
        "name": "손희영",
        "zip": "RYU6OWuHxIDUKh9QNPi6kcftSZKJsjiuXCAiukOSw25bvXZ5+mOf368IvAjckHrg",
        "addr": "d3o7wkcmLeKC5W2DNIsO8x04B2wms9nFgg1UdrCRaL9Nm9eqPFlzzVo4d/YkhJcEFI/MR6HYQDlHK6b0BhVGoTeSYwvfy7P0JaVosBbZaGk=",
        "detaAddr": "34N9OwQ7rAZUeTtwvR5fuMbCi/yLiSObxdihcY9OpOS8fsR+UKDXtnWZVOcJKpnI",
        "mbl": "l4Q7BEZz7HmMaXuAbyIGdoos/+JnROBQYyPJAXP388cuVbOSQCD7WlwASvd0ptk3",
        "bDate": "1993-07-31",
        "gen": "여",
        "pnt": 0,
        "stts": "정상",
        "signDate": "2023-08-02",
        "prof": null,
        "grdAtchFile": null,
        "wdrAplDate": null,
        "blckDate": null,
        "bdate": "1993-07-31"
    },
    {
        "memNo": "MEM000222",
        "id": "suink222",
        "pw": null,
        "email": "mleWf4S17dKd3/9xU5UGEIqkBrjAXN29FZP2DEcAso7dwIZv91G6o034JkJ0wddp",
        "nick": "킹제리",
        "loginPath": "일반",
        "rptCnt": 0,
        "memGrd": "정회원",
        "name": "김수힌",
        "zip": "k0sMepjzwHA+jrMlxNR/nyWEMshwQ8pmPjAICYQw+AfNaICRtOWZlmGA0XN8UsIO",
        "addr": "Nn5BGiYfHwFkukXi4iDNEF/iu4pg2WuvvU7C1yk4mmu3YI54NiWjrSe30iCGeIAhVbRtSXPtX65a7KZ5z7jxi5gqakXVimnHKQcsxAYU2KI=",
        "detaAddr": "26BxL7q0glqMT8cZQllD2JDCgrdoirn+RfVOSp4XVzvSK+q6X6eJBeNxdGdPdoXv",
        "mbl": "qP3e9rOPaNoNvD4P7/TTSiy/3KfNoTGBUubl2AV44Mbb4B8U+Q8CORNY1RhQV9IW",
        "bDate": "1992-02-21",
        "gen": "여",
        "pnt": 0,
        "stts": "정상",
        "signDate": "2023-08-02",
        "prof": null,
        "grdAtchFile": null,
        "wdrAplDate": null,
        "blckDate": null,
        "bdate": "1992-02-21"
    },
    {
        "memNo": "MEM000221",
        "id": "ka_2944899473",
        "pw": null,
        "email": "TRvbbTuGqeaKe5Fa7uHX9TjL4TKvPFv3Ymb7tDvUxVQ8wMTtKqjTtQNp0iPsD9uzuN28wQVZktQEETyZwOb68g==",
        "nick": "K",
        "loginPath": "카카오",
        "rptCnt": 0,
        "memGrd": "정회원",
        "name": "K",
        "zip": "NWPOYOAAX6O1M3j68MDxIUOJqaiSxFDN9XlLfeX86HRHTuWS7s6P0zQ8950/vWgh",
        "addr": "atCvAikhyVHAT3IOGXrfcQTxswv0oiYJbvPxqw/Az0kyh2Hg+BsYjt7uJpEE26Ru",
        "detaAddr": "FTk1ww0iyD/KLVGJAXRPTDS7l5vY1A47QcS/hO6AqVmZRhVPNtkx//NO/vt+fONr",
        "mbl": "0NJrm2sKtfn4uH6A4WJrm2LVMFkjEjaP5zu6DcJtStfyoRWlt2S1Al5bDqeeHldY",
        "bDate": null,
        "gen": "여",
        "pnt": 0,
        "stts": "정상",
        "signDate": "2023-08-02",
        "prof": "http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg",
        "grdAtchFile": null,
        "wdrAplDate": null,
        "blckDate": null,
        "bdate": null
    },
    {
        "memNo": "MEM000182",
        "id": "jsjtwo280",
        "pw": null,
        "email": "HEusUC0Pxu1YHpJDhqlrZngIC9VRdhCaTXImgt7F+7dJehzJ6nNL4OQI7faiCknSUDtA0pbISE5blyadNeyxEw==",
        "nick": "촉촉한초코칩",
        "loginPath": "일반",
        "rptCnt": 0,
        "memGrd": "정회원",
        "name": "김승환",
        "zip": "sg48eKXnyjdlwPhGDQA8RlPD30GpPuQqfNslgsXZ4k4pd+DOajPD/mX2e96UpX5g",
        "addr": "XObkX8t4wBWoj5U3ISDaGDRRjt+5xXZl/ZbpEHPzuFYDNP6peu6e5vXu4MD4fOG+yqZryh4GWhdyrqimlX9a2fhJ58EU2C8/izsXzq0ujCU=",
        "detaAddr": "OwiLrClQNVyuP4mqkEmfmbVzfaPcLB5H6M0Y/7qEwvHvdV29cX/wkNJ9duR9BlqV",
        "mbl": "ds0TS3B9HVxFMshoht5vRQ4DsQunIRWdzrt6joUPTjKrjK9o4B3gDXhyb6O0kYgR",
        "bDate": "1996-02-08",
        "gen": "남",
        "pnt": 0,
        "stts": "정상",
        "signDate": "2023-08-01",
        "prof": null,
        "grdAtchFile": null,
        "wdrAplDate": null,
        "blckDate": null,
        "bdate": "1996-02-08"
    },
    {
        "memNo": "MEM000181",
        "id": "dnwn38",
        "pw": null,
        "email": "3J3/QUxnx6MCLeQfZ+Kkyr6MAB73dpvRQ55lbYttabkA+++vpOsygVD/NUSZe3AZgO7xANMt75Rcb9D/mQ4lFg==",
        "nick": "배고프당완전",
        "loginPath": "일반",
        "rptCnt": 0,
        "memGrd": "정회원",
        "name": "김성욱",
        "zip": "jsWDbaXUZqP1cD66Wqfqix9aiNzlEeMd1ZKvzVKf7ck5Rb2fa05qW/7Kvt31HhwC",
        "addr": "ZVOSmCI1/KwTA/8QyvM0nRtCEuIzVUDmGIPfOO2fJMLMO2MrIvS2VNywfxPv6kRpSdzSxMzmdJ0cEbv0bgDiw4rE4K+YYOKwNfVTC6xeNMg=",
        "detaAddr": "sCejYmaQFSTx8jDodu+Hmmut3n8Nk40YSI2ld1Yw8UvXUsRrwxvFc0l45WRvT4Tr",
        "mbl": "SDTvDFRiJx70zpH9OzIuoIQx21Re/wTxUF5+b9/VoxmtdA/1L0R1OGDZeEJ8TReB",
        "bDate": "2023-07-31",
        "gen": "남",
        "pnt": 0,
        "stts": "정상",
        "signDate": "2023-08-01",
        "prof": null,
        "grdAtchFile": null,
        "wdrAplDate": null,
        "blckDate": null,
        "bdate": "2023-07-31"
    },
    {
        "memNo": "MEM000141",
        "id": "2919432571",
        "pw": null,
        "email": "80sz2dLb8lswz6dWy6dkRKMpXYHz1C6uCh/VT47BwumWyiowqimwQcP0FBaWM5KjU/WA7+v68CktRX6NXu/4xw==",
        "nick": "형이",
        "loginPath": "카카오",
        "rptCnt": 0,
        "memGrd": "정회원",
        "name": "형이",
        "zip": "DUOkqYQ7cif9wi+r6FeeWYrGjSOc9uMU3tFXKAFtCovn3lw3CbQxfk2UGQHyYudl",
        "addr": "KAuE4w0wD/vumzXr/+tmVTyfF2y1ATomUOvm52pubcs2V7/8W25v6LIN3akxCDBI",
        "detaAddr": "IaLX9677bsSt6O95OUjQVBmXtSqPTlnTD/FU3DMXnIZ/8GPyY4GHx7VVuSpRRmCx",
        "mbl": "tzYs4ivtSSWyJ+yFVft00aLhrukR5EIWiwZkGtCZla9u8ppwGzAgc3Jnz02fE7Tt",
        "bDate": null,
        "gen": "여",
        "pnt": 0,
        "stts": "탈퇴",
        "signDate": "2023-07-26",
        "prof": "http://k.kakaocdn.net/dn/b3Amc5/btsl0mhu9lg/WUALiTKJFxv2W3VkRAKuk1/img_640x640.jpg",
        "grdAtchFile": "/image/2023/07/27/등본.jpg",
        "wdrAplDate": null,
        "blckDate": null,
        "bdate": null
    }
],checkedMembers:[],
			grade:'',gradeList:[],
			stts:'',sttsList:[],
      headers:[{title: '아이디',key: 'id',},
	        { title: '성별',key: 'gen',align: 'center'},
	        {title: '등급',key: 'memGrd',align: 'center'},
					{title: '가입일', key: 'signDate',align: 'center'},
					{title: '회원가입경로',key: 'loginPath'},
	        {title: '닉네임', key: 'nick'},
	        {title: '상태',key: 'stts',align: 'center'}
	      ]
    }
  },
	components: {
      VDataTable,
    },
methods:{
	search(){
		let obj={}
		if(this.searchType=='아이디'){
			obj.id=this.searchText;
		}else{
			obj.nick=this.searchText;
		}
		obj.stts=this.searchStts;
		obj.grd=this.searchGrd;
		obj.login =this.searchLogin;
		obj.str=this.searchStr;
		obj.end=this.searchEnd;
		this.callList(obj)
	},
	changeBtn(){
		console.log(this.selected);
		let list =[];
		this.selected.forEach(item => {
			let obj={memNo: item.memNo, stts:this.stts,memGrd:this.grade}
			list.push(obj);
		});
		console.log(list);
		this.modify(list)
	},
	modify(list){
		fetch("/admin/members/update",{
			method:"PUT",
			headers: {
        "Content-Type": "application/json",
      },
			body: JSON.stringify(list)
		})
		.then(result=>result.json())
		.then(result=> {
			console.log(result);
			this.$swal({
              title: "회원 상태를 변경하였습니다",
              icon: "success",
              showConfirmButton:false,
              timer:1500
            });
			this.memberList=result;
			this.selected=[];
			this.grade=''; this.stts='';
			this.member={};
		})
		.catch(err=> console.log(err))
		.finally(()=>this.back())
	}
	,
	info(event,item){
		let index=item.item.index;
		this.member= this.memberList[index];
		this.onpenModal()
	},
	onpenModal(){
		document.getElementById("myModal").style.display = "block";	
	},
	apply(){
		let list=[];
		list.push({memNo:this.member.memNo,memGrd:'b1'});
		this.modify(list);
	},
	refusal1(){
		this.btnShow=false;
	},
	refusal2(){
		let obj ={
			memNo : this.member.memNo,
			alrtTitle: '등업신청이 거부되었습니다',
			alrtDesct: this.reason,
			boardCtg: 'g16'
		}
		console.log(obj)
		fetch('/admin/rejectAlert',{
			method:'POST',
			headers: {'Content-Type': 'application/json',  },
			data:JSON.stringify(obj)
		})
		.then(result=> result.json())
		.then(result=> console.log(reuslt))
		.catch(err=> console.log(err))
		.finally(()=> this.back())
	},
	back(){
		document.getElementById("myModal").style.display = "none";
	},
	callList(vo){
		axios.get("/admin/members",{params: vo})
		.then(response => {
			console.log(response.data);
			this.memberList = response.data.memberList;
			this.sttsList=response.data.code['0C']
	  	this.gradeList=response.data.code['0B']
	  	this.gradeList.splice(0,1);
		})
		.catch(err => console.log(err));

	}
},
  mounted(){
		//this.sttsList = this.$store.state.allCode['0C'];
		
		//this.allCode = this.$store.state.allCode;
		//console.log(this.allCode);
  	//this.callList()
		//모달 닫기
		
		window.onclick = function(event) {
  		if (event.target == document.getElementById("myModal")) {
				document.getElementById("myModal").style.display = "none";
				this.member={};
  		}
		}
		document.getElementsByClassName("close")[0].addEventListener('click',function(){
			document.getElementById("myModal").style.display = "none";
			this.member={};
		})
  },
	computed: {
      pageCount () {
        return Math.ceil(this.memberList.length / this.itemsPerPage)
      },
    }
}

</script>

<style>
.body{
	padding: 10px;
}
img{
	max-width: 600px;
}
/* 모달안 크기 제한 */
.desct{
	max-width: 600px;
	overflow: auto;
}
/* 글자수 */
.text-overflow {
  width: 200px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
/* 모달 */
.modal {
  display: none; 
  position: fixed; 
  z-index: 100000000; 
  padding-top: 100px; 
  left: 0;
  top: 0;
  width: 100%; 
  height: 100%; 
  overflow: auto; 
  background-color: rgb(0,0,0); 
  background-color: rgba(0,0,0,0.4);
}

/* Modal Content */
.modal-content {
  background-color: #fefefe;
  margin: auto;
  padding: 20px;
  border: 1px solid #888;
  width: 60%;
}
table{
	margin: 0 auto;
}

/* X  버튼 */
.close {
  color: #aaaaaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}
ck-sticky-panel__content {
  width: 600px;
	height: 200px;
}
.ck-toolbar_grouping{
  width: 600px;
}
.row{
  margin: 10px;
}
</style>