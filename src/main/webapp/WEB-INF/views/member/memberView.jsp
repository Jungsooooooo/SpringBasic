<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%! SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 상세 정보</title>
	<%@ include file="../include/header.jsp" %>
</head>
<body>
<%@ include file="../include/topMenu.jsp" %>

<div class="container">
	<form class="form-horizontal" action="/member/memberUpdate" method="post">
		<div class="form-group">
			<div class="col-sm-2"></div>
			<div class="col-sm-6">
				<h2 align="center">회원 상세 정보</h2>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">아 이 디</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" name="id" value="${view.id}" readonly="readonly"/>
			</div>
		</div> 
		
		<div class="form-group">
			<label class="control-label col-sm-2">비밀번호</label>
			<div class="col-sm-3">
				<input type="password" class="form-control" id="passwd" name="passwd" maxlength="16"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">비밀번호확인</label>
			<div class="col-sm-3">
				<input type="password" class="form-control" id="repasswd" name="repasswd" maxlength="16"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">이    름</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="name" name="name" maxlength="20" value="${view.name}"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">가입일자</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="reg_date" maxlength="40 
					value="<fmt:formatDate value="${view.reg_date}" pattern="yyyy년 MM월 dd일 HH시 mm분"/>"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">전화번호</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="tel" name="tel" value="${view.tel}"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">주    소</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="address" name="address" value="${view.address}"/>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-3 col-sm-2 center">
				<button type="submit" class="btn btn-primary">수정</button>
				<input type="submit" value="삭제" onclick="javascript: form.action='/member/delete';"/>


				<input class="btn btn-primary" type="submit" value="수정" onclick='btn_click("update");'/>
				<input class="btn btn-danger"  type="submit" value="삭제" onclick='btn_click("delete");'/>
				<a class="btn btn-danger"  href="/member/delete?id=${view.id}">삭제</a>
			</div>
		</div>
	</form>
</div>

<script>
function btn_click(str) {
	if(str == "update") {
		form.action = "/member/memberUpdate";
	} else if(str == "delete") {
		form.action = "/member/delete";
	} else {
		alert("키를 잘못 누르셨습니다.");
	}
}
</script>

</body>
</html>

















