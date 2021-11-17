<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   		uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="layoutTag" 	tagdir="/WEB-INF/tags" %>
<%@ taglib  prefix="spring" 	uri="http://www.springframework.org/tags" %>

<layoutTag:layout>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 목록 보기</title>
	<style>
	.navbar-inverse .navbar-nav > .active > a, 
	.navbar-inverse .navbar-nav > .active > a:focus, 
	.navbar-inverse .navbar-nav > .active > a:hover {
	    color: rgb(255, 255, 255);
	    background-color: red
	}
	</style>
</head>
<body>

<div class="container">

	<div align="center">
		<h2>게시글 목록 (Paging)</h2>
	<button class="btn btn-primary" onclick="location.href='/board2/insert'">글쓰기</button>
	</div>
	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th>No</th>
				<th>Subject</th>
				<th>Content</th>
				<th>Writer</th>
				<th>Date</th>
				<th>댓글</th>
			</tr>
		</thead>
			<c:forEach var="board" items="${list}">
			<tr>
				<td class="info" onclick="location.href='/board2/detail/${board.bno}'">${board.bno}</td>
				<td>${board.subject}</td>
				<td>${board.content}</td>
				<td>${board.writer}</td>
				<td><fmt:formatDate value="${board.reg_date}" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초"/></td>
				<td class="warning" onclick="location.href='/board2/detailComment/${board.bno}'">댓글</td>
			</tr>
			</c:forEach>
		<tbody>
		</tbody>
	</table>
	
	<div class="col-sm-offset-3">
		<h3><a class="label label-info">검색조건</a></h3>
	
		<select id='searchType'>
			<option>검색종류</option>
			<option value='t' th:selected="${pageVO.type} =='t'" >제목</option>
			<option value='c' th:selected="${pageVO.type} =='c'">내용</option>
			<option value='w' th:selected="${pageVO.type} =='w'">글쓴이</option>
		</select>
	  <input type='text' id='searchKeyword' th:value="${pageVO.keyword}">
	  <button id='searchBtn'>Search</button> 
	</div>
	
	<div class="col-sm-offset-3">
		<ul class="btn-group pagination">
		    <c:if test="${pageMaker.prev }">
		    <li>
		        <a href='<c:url value="/board2/boardList?page=${pageMaker.startPage-1 }"/>'><span class="glyphicon glyphicon-chevron-left"></span></a>
		    </li>
		    </c:if>
		    <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
		    <li>
		        <a href='<c:url value="/board2/boardList?page=${pageNum }"/>'><i>${pageNum }</i></a>
		    </li>
		    </c:forEach>
		    <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
		    <li>
		        <a href='<c:url value="/board2/boardList?page=${pageMaker.endPage+1 }"/>'><span class="glyphicon glyphicon-chevron-right"></span></a>
		    </li>
		    </c:if>
		</ul>
	</div>
</div>

</body>

</html>

</layoutTag:layout>





















