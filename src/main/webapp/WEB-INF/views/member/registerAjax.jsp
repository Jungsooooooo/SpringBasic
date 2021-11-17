<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"			uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"			uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="layoutTag"	tagdir="/WEB-INF/tags" %>

<layoutTag:layout>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입(Ajax)</title>
</head>
<body>

<div class="container">
	<form class="form-horizontal" method="post" action="/member/registerAjax">
		<div class="form-group">
			<div class="col-sm-2"></div>
			<div class="col-sm-6">
				<h2 align="center">회원가입(실시간 아이디 검사)</h2>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">아이디</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="userID" name="id" maxlength="16" placeholder="아이디를 입력하십시오."/>
			</div>
			<div class="col-sm-2">
				<button class="btn btn-warning idCheck" type="button" id="idCheck" onclick="fn_idCheck();" value="N">중복검사</button>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">비밀번호</label>
			<div class="col-sm-3">
				<input type="password" class="form-control" id="passwd" name="passwd" maxlength="16" placeholder="비밀번호를 입력하십시오."/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">이  름</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="name" name="name" maxlength="16" placeholder="이름을 입력하십시오."/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">전화번호</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="tel" name="tel" placeholder="전화번호를 입력하십시오."/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">전화번호</label>
			<div class="col-sm-2">
				<select class="form-control" id="tel1" name="tel1">
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="017">017</option>
					<option value="018">018</option>
					<option value="019">019</option>
				</select>
			</div>
			<div class="input-group col-sm-3">
				<div class="input-group-addon">-</div>
				<div>
					<input type="text" class="form-control col-sm-1" id="tel2" name="tel2" maxlength="4" placeholder="Tel"/>
				</div>
				<div class="input-group-addon">-</div>
				<div>
					<input type="text" class="form-control col-sm-1" id="tel3" name="tel3" maxlength="4" placeholder="Tel"/>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">주  소</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="address" name="address" placeholder="주소를 입력하십시오."/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2">우편번호</label>
			<div class="col-sm-2">
				<input type="text" class="form-control" id="zipcode" name="zipcode" readonly />
				<input type="button" class="form-control btn btn-success" onclick="daumZipCode()" value="우편번호검색" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2">주  소</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="address01" id="address01" readonly />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2">상세주소</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="address02" id="address02" />
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-6">
				<button class="btn btn-info" type="submit" id="submit0" disabled="disabled">회원가입</button>
				<button class="btn btn-primary signUpBtn" type="submit" id="submit" disabled="disabled">회원가입</button>
				<button class="cancel btn btn-danger" type="button">취소</button>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
				<input type="text" class="form-control msg" id="msg" name="msg" placeholder="Message" />
			</div>
			<div id="msg2"></div>
		</div>
		
	</form>

</div>

<script>
//-------------------------------------------------------------------------------------------------
// 아이디 중복검사 버튼을 눌렀을 겨우
//-------------------------------------------------------------------------------------------------
function fn_idCheck() {
	var result = spaceCheck(); // 회원아이디 공백 검사
	
	// 회원 아이디에 공백을 입력하면 않된다.
	if(result == true) {
		$.ajax({
			url:		"/member/idCheck",
			type:		"post",
			dataType:	"json",
			data:		{ "id":	$("#userID").val() },
			success:	function(data) {
				if(data == 1) {
					$("#userID").focus();
					alert("이미 사용 중인 아이디입니다.");
				} else if(data == 0) {
					$("#idCheck").attr("value", "Y");
					alert("사용 가능한 아이디입니다.");
				}
			}
		});
	} else {
		alert("회원 아이디에는 공백을 사용할 수 없습니다.");
		$("#userID").focus();
	}
}

//-------------------------------------------------------------------------------------------------
// 공백 검사
//-------------------------------------------------------------------------------------------------
function spaceCheck(){

	var str = document.getElementById('userID');

	if( str.value == '' || str.value == null ){
	    alert( '값을 입력해주세요' );
	    return false;
	}

	var blank_pattern = /^\s+|\s+$/g;
	if( str.value.replace( blank_pattern, '' ) == "" ){
	    alert(' 공백만 입력되었습니다 ');
	    return false;
	}

	//공백 금지
	//var blank_pattern = /^\s+|\s+$/g;(/\s/g
	var blank_pattern = /[\s]/g;
	if( blank_pattern.test( str.value) == true){
	    alert(' 공백은 사용할 수 없습니다. ');
	    return false;
	}

	var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;

	if( special_pattern.test(str.value) == true ){
	    alert('특수문자는 사용할 수 없습니다.');
	    return false;
	}

	alert( '최종 : ' + str.value );

	/*
	if( str.value.search(/\W|\s/g) > -1 ){
	    alert( '특수문자 또는 공백을 입력할 수 없습니다.' );
	    str.focus();
	    return false;
	}*/
	return true;
}

//-------------------------------------------------------------------------------------------------
// jQuery 를 이용하여 페이지가 로딩되면 할 수 있는 것들을 만든다.
//-------------------------------------------------------------------------------------------------
$(document).ready(function() {
	
	//-------------------------------------------------------------------------------------------------
	// 취소 버튼을 누르면
	//-------------------------------------------------------------------------------------------------
	$(".cancel").on("click", function() {
		location.href="/member/login";
	});

	//-------------------------------------------------------------------------------------------------
	// 아이디 입력란에 글자를 쓰면 실시간으로 사용이 가능한지 아닌지 검사한다.
	//-------------------------------------------------------------------------------------------------
	$("#userID").on("input", function() {
		var inputID = $('#userID').val();
		// alert(inputID);
		
		$.ajax({
			url:		"/member/idCheck",
			type:		"post",
			dataType:	"json",
			data:		{"id" : $("#userID").val()},
			success:	function(data) {
				if(inputID == "" && data == '0') {
					$(".signUpBtn").prop("disabled", true);
					$(".signUpBtn").css("background-color", "#AAAAAA");
					$("#idCheck").css("background-color",   "FFCECE");
					document.getElementById("msg2").innerHTML = "아이디를 입력하십시요.";
					document.getElementById("msg").value = "아이디를 입력하십시요.";
				} else if(inputID != "" && inputID.length >= 4 && data == '0') {
					$("#idCheck").css("background-color", "#B0F6AC");
					document.getElementById("msg2").innerHTML = "사용이 가능한 아이디입니다.";
				    document.getElementById("msg").value = '사용이 가능한 아이디입니다.';
					$("#msg").css("background-color", "#B0F6AC");
					$(".signUpBtn").prop("disabled", false);
					$(".signUpBtn").css("background-color", "#4CAF50");
				} else if(data == '1') {
					$(".signUpBtn").prop("disabled", true);
					$(".signUpBtn").css("background-color", "#AAAAAA");
					$("#idCheck").css("background-color",   "FFCECE");
					document.getElementById("msg2").innerHTML = "이미 사용 중인 아이디입니다.";
					document.getElementById("msg").value = "이미 사용 중인 아이디입니다.";
					$("#msg").css("background-color", "#FFCECE");
				}
			}
		});
		
	});
});

</script>

</body>
</html>

</layoutTag:layout>




