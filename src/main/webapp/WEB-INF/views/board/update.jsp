<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<%@ include file="../include/header.jsp" %>
</head>
<body>
<%@ include file="../include/topMenu.jsp" %>

<div class="container">
	<form class="form-horizontal" method="post">
		<div class="form-group">
			<div class="col-sm-offset-3">
				<h1>게시글 수정</h1>
			</div>
		</div>

		<!-- 게시글 번호를 숨겨서 넘겨준다. -->
		<input type="hidden" name="bno" value="${view.bno}"/>
		
		<div class="form-group">
			<label class="control-label col-sm-2">제  목</label>
			<div class="col-sm-4">
				<!-- value="${view.title}"에 쌍따옴표를 제거하면 데이터에서 공백이 있는 부분까지만 출력된다. -->
				<input type="text" class="form-control" name="title" maxlength="50" value="${view.title}"/>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-sm-2">글쓴이</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" name="writer" maxlength="30" value="${view.writer}"/>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-sm-2">작성일자</label>
			<div class="col-sm-3">
				<!-- 날짜정보를 컨트롤러에 보내려면 데이터타입을 BoardVO에 맞게 만들어야 한다. -->
				<!-- pattern="yyyy년 MM월 dd일" 으로 사용하면 않되고 java.sql.Timestamp에 맞게 pattern="yyyy-MM-dd hh:mm:ss"를 사용해야 한다. -->
				<input type="text" class="form-control" name="regDate" value="<fmt:formatDate value="${view.regDate}" pattern="yyyy-MM-dd hh:mm:ss"/>" readonly="readonly"/>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-sm-2">내  용</label>
			<div class="col-sm-4">
				<textarea rows="10" cols="120" name="content">${view.content}</textarea>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-3">
				<button type="submit" class="btn btn-primary">수정</button>
			</div>
		</div>
	</form>
</div>

<%@ include file="../include/footer.jsp" %>
</body>
</html>