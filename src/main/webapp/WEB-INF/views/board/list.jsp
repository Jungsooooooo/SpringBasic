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
	<div align="center">
		<h2>게시글 목록</h2>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-10">
			<a href="${path}/board/write" class="btn btn-primary">게시글 쓰기</a>
		</div>
	</div>
	<table class="table table-bordered table-striped table-hover">
		<thead>
			<tr>
				<th>번호</th>
				<th>제    목</th>
				<th>글쓴이</th>
				<th>작성일자</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
			<tr>
				<td>${list.bno}</td>
				<td><a href="/board/view?bno=${list.bno}">${list.title}</a></td>
				<td>${list.writer}</td>
				<td><fmt:formatDate value="${list.regDate}" pattern="yyyy년 MM월 dd일"/></td>
				<td>${list.viewCnt}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>	
	
<%@ include file="../include/footer.jsp" %>
</body>
</html>








