<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"			uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="layoutTag" 	tagdir="/WEB-INF/tags" %>

<layoutTag:layout>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>우편번호 검색</title>
	<style>
	.navbar-inverse .navbar-nav > .active > a,
	.navbar-inverse .navbar-nav > .active > a:focus,
	.navbar-inverse .navbar-nav > .active > a:hover {
		color:				rgb(255, 255, 255);
		background-color:	red;
	}
	</style>
</head>
<body>

<div class="container">
	<form class="form-horizontal" name="zipForm" method="post">
		<div class="form-group">
			<label class="col-sm-2">우편번호</label>
			<div class="col-sm-6">
				<input type="text" 		class="form-control" name="zipcode" id="zipcode" readonly />
				<input type="button"	class="form-control" onclick="daumZipCode()" value="우편번호검색"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2">주    소</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="address01" id="address01"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2">상세주소</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="address02" id="address02"/>
			</div>
		</div>
	</form>
</div>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function daumZipCode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업창에서 검색한 결과 중 항목을 클릭하였을 경우에 실행할 코드를 이곳에 작성한다.
			
			// 각 주소의 노출규칙에 따라서 주소를 조합한다.
			// 내려오는 변수의 값이 업을 경우에는 공백('')값을 가지므로, 이름 참조하여 분기한다.
			var	fullAddress	= '';	// 최종   주소 변수
			var	subAddress  = '';	// 조합형 주소 변수
			
			// 사용자가 선택한 주소의 타입에 따라서 해당 주소값을 가져온다.
			if(data.userSelectedType == 'R') {	// 도로명 주소를 선택한 경우
				fullAddress = data.roadAddress;
			} else {	// 지번 주소를 선택한 경우
				fullAddress = data.jibunAddress;
			}
			
			// 사용자가 선택한 주소가 도로명 타입일 때 주소를 조합한다.
			if(data.userSelectedType == 'R') {
				// 법정동명이 있을 경우 추가한다.
				if(data.bname != '') {
					subAddress += data.bname;
				}
				// 건물명이 있을 경우 추가한다.
				if(data.buildingName != '') {
					subAddress += (subAddress != '' ? ', ' + data.buildingName : data.buildingName);
				}
				
				// 조합형 주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
				fullAddress += (subAddress != '' ? '(' + subAddress + ')' : '');
			}

			// 우편번호와 ㅏ주소 정보를 해당 필드에 나타낸다.
			document.getElementById('zipcode').value	= data.zonecode; // 우편번호
			document.getElementById('address01').value	= fullAddress;	 // 주소내용
			
			// 커서를 상세주소 입력 필드로 이동시킨다.
			document.getElementById('address02').focus();
		}
	// }).open(); // open()만 기술하면 팝업 창이 여러개 띄울 수 있다.
	}).open({
			// 우편번호 파업 창이 여러개 뜨는 것을 방지하기 위해서 popupName을 사용한다.
			popupName:	'postcodePopup'
	});
}
</script>
</body>
</html>

</layoutTag:layout>










