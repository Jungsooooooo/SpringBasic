<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%! SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 가입</title>
	<%@ include file="../include/header.jsp" %>
</head>
<body>
<%@ include file="../include/topMenu.jsp" %>

<div class="container">
	<form class="form-horizontal" action="/member/memberInsert" method="post">
		<div class="form-group">
			<div class="col-sm-2"></div>
			<div class="col-sm-6">
				<h2 align="center">회원 가입</h2>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">아 이 디</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="userId" name="id" maxlength="16" placeholder="Enter ID"/>
			</div>
			<div class="col-sm-2">
				<button class="idCheck btn btn-warning" type="button" id="idCheck" onclick="fn_idCheck();" value="N">중복확인</button>
			</div>
		</div> 
		
		<div class="form-group">
			<label class="control-label col-sm-2">비밀번호</label>
			<div class="col-sm-3">
				<input type="password" class="form-control" id="passwd" name="passwd" maxlength="16" placeholder="Enter Password"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">비밀번호확인</label>
			<div class="col-sm-3">
				<input type="password" class="form-control" id="repasswd" name="repasswd" maxlength="16" placeholder="Enter Password"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">이    름</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="name" name="name" maxlength="20" placeholder="Enter Name"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">전화번호</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="tel" name="tel" placeholder="Enter Telephone"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">주    소</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="address" name="address" placeholder="Enter Address"/>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-3 col-sm-3 center">
				<button class="btn btn-success" type="submit" id="submit">회원가입</button>
				<button class="btn btn-danger cancel" type="button">가입취소</button>
			</div>
		</div>
	</form>
</div>

<script>
$(document).ready(function() {
	//취소
	$(".cancel").on("click", function() {
		location.href = "/member/memberList";
	});
	//전송
	$("#submit").on("click", function() {
		if($("#userId").val() == "") {
			alert("아이디를 입력하십시오.");
			$("#userId").focus();
			return false;
		}
		if($("#passwd").val() == "") {
			alert("비밀번호를 입력하십시오.");
			$("#passwd").focus();
			return false;
		}
		if($("#repasswd").val() == "") {
			alert("비밀번호확인을 입력하십시오.");
			$("#repasswd").focus();
			return false;
		}
		if($("#name").val() == "") {
			alert("이름를 입력하십시오.");
			$("#name").focus();
			return false;
		}
		if($("#tel").val() == "") {
			alert("전화번호를 입력하십시오.");
			$("#tel").focus();
			return false;
		}
		if($("#address").val() == "") {
			alert("주소를 입력하십시오.");
			$("#address").focus();
			return false;
		}
	});
});

function fn_idCheck() {
	$.ajax({
		url :		"/member/idCheck",
		type:		"post",
		dataType:	"json",
		data:		{"id" : $("#userId").val() },
		success:	function(data) {
			//alert("Return : " + data);
			if(data == 1) {
				alert("이미 사용중인 아이디입니다.\n다른 아이디를 입력하십시요.");
			} else if(data == 0) {
				$("#idCheck").attr("value", "Y");
				alert("사용가능한 아이디입니다.");
			}
		}
	});
}





</script>

</body>
</html>

















