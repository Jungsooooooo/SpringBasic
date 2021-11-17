<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
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
				<h1>게시글 작성</h1>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">제  목</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="title" maxlength="50" placeholder="Enter Title"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">글쓴이</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" name="writer" maxlength="30" placeholder="Enter Writer"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">내  용</label>
			<div class="col-sm-4">
				<textarea rows="10" cols="120" name="content"></textarea>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-3">
				<button type="reset"  class="btn btn-warning btn-sm">다시 작성하기</button>
				<button type="submit" class="btn btn-primary btn-sm">글 올리기</button>
			</div>
		</div>
	</form>
</div>

<%@ include file="../include/footer.jsp" %>
</body>
</html>














