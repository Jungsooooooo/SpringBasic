<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%! SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 목록</title>
	<%@ include file="../include/header.jsp" %>
</head>
<body>
<%@ include file="../include/topMenu.jsp" %>
	
<div class="container">
	<h2 align="center">회원목록</h2>
	<table class="table table-bordered table-striped table-hover">
		<thead>
			<tr>
				<td align="center">아이디</td>
				<td align="center">이  름</td>
				<td align="center">가입일자</td>
				<td align="center">전화번호</td>
				<td align="center">주  소</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="list" items="${memberList}">
			<tr>
				<td align="center"><a href="/member/memberView?id=${list.id}">${list.id}</a></td>
				<td align="center">${list.name}</td>
				<td align="center"><fmt:formatDate value="${list.reg_date}" pattern="yyyy년 MM월 dd일 HH시 mm분"/></td>
				<td align="center">${list.tel}</td>
				<td>${list.address}</td>
			</tr>	
		</c:forEach>
		</tbody>
	</table>
</div>	
	
</body>
</html>









