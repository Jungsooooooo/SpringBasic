<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-inverse navbar-stikcy-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-targer="#MyMenu">
				<span class="sr-only">Toggle Menu</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/board2/list">HOME</a>
		</div>
		<!-- 실질적인 메뉴를 나열한다. -->
		<div class="collapse navbar-collapse" id="MyMenu">
			<ul class="nav navbar-nav navbar-right">

				<li class="dropdown">
					<a href="/board/list">게시글(include형태)로 가기</a>
				</li>

				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">기본예제 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${path}/basic/doA">doA</a></li>
						<li><a href="${path}/basic/doB">doB</a></li>
						<li><a href="${path}/basic/doC">doC</a></li>
						<li><a href="${path}/basic/gugu.doit">구구단</a></li>
						<li><a href="${path}/basic/booktype">도서종류 목록</a></li>
					</ul>
				</li>
					
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">게시글 관리 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${path}/board/list">게시글 목록 (include)</a></li>
						<li><a href="${path}/board/write">게시글 쓰기 (include)</a></li>
						<li class="divier"></li>
						<li><a href="${path}/board2/list">게시글 목록 (tags)</a></li>
						<li><a href="${path}/board2/insert">게시글 쓰기 (tags)</a></li>
						<li class="divier"></li>
						<li><a href="${path}/board2/boardList">게시글 목록 (Paging)</a></li>
						<li><a href="${path}/board2/listDate">달력 불러오기</a></li>
					</ul>
				</li>
			
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">파일 올리기 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${path}/upload/uploadFile">파일 올리기</a></li>
						<li><a href="${path}/upload/uploadAjax">파일 올리기(Ajax)</a></li>
					</ul>
				</li>
			
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">영화관 좌석 예약 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${path}/movie/seatReservation?movieID=1">영화관 좌석 예약</a></li>
					</ul>
				</li>
				
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">로그인 관리 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<!-- 세션 값이 있으면 로그아웃과 회원탈퇴를 활성화 시킨다. -->
						<c:if test="${member != null}">
							<li><a href="${path}/member/logout">로그아웃</a></li>
							<li><a href="${path}/member/memberDelete">회원탈퇴</a></li>
						</c:if>
						<!-- 세션 값이 없으면 로그인과 회원가입을 활성화 시킨다. -->
						<c:if test="${member == null}">
							<li><a href="${path}/member/login">로그인</a></li>
							<li><a href="${path}/member/registerAjax">회원가입(Ajax)</a></li>
						</c:if>
					</ul>
				</li>
				
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">기타 등등 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${path}/basic/address">우편번호 검색</a></li>
					</ul>
				</li>
				
			</ul>
		</div>
	</div>

</nav>


















