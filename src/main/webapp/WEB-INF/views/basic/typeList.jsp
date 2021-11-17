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
	<h1>도서 타입</h1>
	<table border="1">
		<thead>
			<tr>
				<th><h3>아이디</h3></th>
				<th><h3>이  름</h3></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${typeList}" var="bookType">
				<tr>
					<td><h3>${bookType.id}</h3></td>
					<td><h3>${bookType.name}</h3></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>





