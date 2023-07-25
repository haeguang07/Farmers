<template>
  <v-app>
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<!-- 홈으로 가는 버튼-->
		<a class="navbar-brand ps-3" href="/">유귀농? Yes!</a>
		<!-- 사이트바 토글-->
		<button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i
				class="fas fa-bars"></i></button>
		<!-- Navbar Search-->
		<div class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0"></div>
		<!-- Navbar-->
		<ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
			<li class="nav-item dropdown">
				<a href="/logout" class="nav-link collapsed">로그아웃</a>
			</li>
		</ul>
	</nav>
	<div id="admin">
		<div id="layoutSidenav">
			<div id="layoutSidenav_nav">
				<nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
					<div class="sb-sidenav-menu">
						<div class="nav">
							<router-link to="/" class="nav-link collapsed">
								<div class="sb-nav-link-icon"></div>회원관리
							</router-link>
							<a class="nav-link collapsed" href="layout-static.html" data-bs-toggle="collapse"
								data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
								<div class="sb-nav-link-icon"></div>승인관리
								<div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
							</a>
							<div class="collapse" id="collapseLayouts" aria-labelledby="headingOne"
								data-bs-parent="#sidenavAccordion">
								<nav class="sb-sidenav-menu-nested nav">
									<router-link to="farmLend" class="nav-link">농지대여</router-link>
									<router-link to="experience" class="nav-link">농지체험</router-link>
									<router-link to="equipment" class="nav-link">농기구</router-link>
									<router-link to="funding" class="nav-link">펀딩</router-link>
									<router-link to="jobVacancy" class="nav-link">일자리</router-link>
									<router-link to="market" class="nav-link">전원마켓</router-link>
									<router-link to="auction" class="nav-link">경매장</router-link>
									<router-link to="farmbnb" class="nav-link">농촌비엔비</router-link>
								</nav>
							</div>
							<router-link to="/inquiryAdmin" class="nav-link collapsed">
								<div class="sb-nav-link-icon"></div>문의관리
							</router-link>
							<a class="nav-link collapsed" href="/report">
								<div class="sb-nav-link-icon"></div>신고관리
							</a>
						</div>
					</div>
					<div class="sb-sidenav-footer">
						<div class="small">Logged in as:</div>
						Start Bootstrap
					</div>
				</nav>
			</div>
			<div id="layoutSidenav_content">
				<!-- 내용 -->
				<main>
					<div class="container-fluid px-4 py-5">
						<router-view></router-view>
					</div>
				</main>
				<!-- footer-->
				<footer class="py-4 bg-light mt-auto">
					<div class="container-fluid px-4">
						<div class="d-flex align-items-center justify-content-between small">
							<div class="text-muted">Copyright &copy; Your Website 2023</div>

						</div>
					</div>
				</footer>
			</div>
		</div>
	</div>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  data: () => ({
    //
  }),
}
</script>
