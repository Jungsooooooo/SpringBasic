<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="layoutTag" tagdir="/WEB-INF/tags" %>

<layoutTag:layout>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 상세 정보</title>
</head>
<body>

<div class="container">
	<h3>게시글 상세 정보</h3>
	<div class="btn-group btn-group-sm col-sm-6" role="group" style="float:right;">
		<!--  
		<button class="btn btn-warning" onclick="location.href='/board2/update/${detail.bno}'">수정</button>
		<button class="btn btn-danger"  onclick="location.href='/board2/delete/${detail.bno}'">삭제</button>
		-->
		<h4><a class="col-sm-2 btn btn-info" 	href="/board2/list">목록</a></h4>
		<h4><a class="col-sm-2 btn btn-success" href="/board2/update/${detail.bno}">수정</a></h4>
		<h4><a class="col-sm-2 btn btn-danger"	href="/board2/delete/${detail.bno}">삭제</a></h4>
	</div>
	<br><br><br>
		
	<form class="form-horizontal" action="/board2/insert" method="post">
		<div class="form-group">
			<label class="control-label col-xs-2">제  목</label>
			<div class="col-xs-5">
				<input class="form-control" value="${detail.subject}" readonly="readonly" />
			</div>		
		</div>
		<div class="form-group">
			<label class="control-label col-xs-2">작성자</label>
			<div class="col-xs-2">
				<input class="form-control" value="${detail.writer}" readonly="readonly" />
			</div>		
		</div>
		<div class="form-group">
			<label class="control-label col-xs-2">작성날짜</label>
			<div class="col-xs-3">
				<input class="form-control" value="${detail.reg_date}" readonly="readonly" />
			</div>		
		</div>
			<div class="form-group">
			<label class="control-label col-xs-2">첨부파일</label>
			<div class="col-xs-3">
				<a href="/board2/fileDown/${files.bno}">${files.fileOriName}</a>
			</div>		
		</div>
		<div class="form-group">
			<label class="control-label col-xs-2">내  용</label>
			<div class="col-xs-8">
				<textarea rows="4" cols="100" class="form-control" disabled>${detail.content}</textarea>
			</div>		
		</div>
		<div class="form-group">
			<div class="col-xs-offset-4 col-xs-3">
				<button type="submit" class="btn btn-primary">글쓰기</button>
			</div>
		</div>
	</form>
	
</div>

</body>

<!-- layoutTag를 적용하므로 bootstrap.jsp 파일이 필요 업어졌다. -->
</html>

</layoutTag:layout>



































