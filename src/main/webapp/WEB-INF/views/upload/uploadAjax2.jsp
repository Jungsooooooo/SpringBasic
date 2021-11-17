<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>파일 올리기 (Ajax)</title>
	<style>
	.fileDrop {
		width:		100%;
		height:		200px;
		border:		1px dotted blue;
	}
	</style>
	<%@ include file = "../include/header.jsp" %>
</head>
<body>
<%@ include file = "../include/topMenu.jsp" %>

<div class="container">
	<h2>Ajax File Upload</h2>
	<!-- 파일을 업로드할 영역 -->
	<div class="fileDrop"></div>
	<!-- 업로드된 파일들의 목록을 보여줄 영역 -->
	<div class="uploadList"></div>
</div>

<%@ include file="../include/footer.jsp" %>

<script>
$(function() {
	// Drag의 기본 효과를 막는다.
	// 기본효과를 막지 않으면 Drag된 곳에 이미지가 보인다.
	// preventDefault() : 기본적으로 표시된 효과를 막는다.
	$(".fileDrop").on("dragenter dragover", function(event) {
		event.preventDefault();
		
	});
	
	$(".fileDrop").on("drop", function(event) {
		alert("파일을 떨어뜨렸습니다.");
		
		// Drop이 될 때 기본효과를 막는다.
		event.preventDefault()j;
		
		// 첨부파일 배열 : drag된 파일의 정보
		// Ctrl + 클릭으로 여러개의 파일을 동시에 올릴 수 있다.
		var files = event.originalEvent.dataTransfer.files;
		
		var file = files[0]; // 첫번째 첨부파일
		
		// 파일정보를 콘솔에서 볼 수 있다.
		// 웹 브라우저에서 F12 키를 누르면 어떤 파일인지 알 수가 있다.
		console.log(file);
		
		// Ajax로 파일을 전달할 때에 폼이 별도로 존재하지 않기 때문에 폼 객체를 만들어 주어야 한다.
		var formData = new FormData();	// 폼 객체 생성
		formData.append("file", file);	// 폼에 file변수를 추가하고, 값을 넣는다.
		
		// 서버에 파일 업로드(백그라운드에서 실행이 된다.)
		// contentType 	: false => multipart/form-data 로 처리된다.
		// dataType		: 사용자가 보내는 데이터 타입이 아니고 서버가 응답(response)할 때 보내줄 데이터 타입이다.
		//				  이것은 success function에 전달될 argument의 형태를 지정하는데 사용된다.
		// processData	: 일반적으로 서버에 전달되는 데이터는 Query String 형태로 저장된다. (num=10&subNum=101)
		// contentType	: 기본 값이 "application/x-www-form-urlencoded;charset=UTF-8"인데
		//				  "multipart/form-data"로 전송이 되게 false로 설정한다.
		
		$.ajax({
			type:			"post",
			url:			"${path}/upload/uploadAjax",
			data:			formData,
			dataType:		"text",
			processData:	false,
			contentType:	false,
			success:		function(data, status, req) {
				console.log("data : "	+ data);		// 업로드된 파일이름
				console.log("status: "	+ status);		// 성공, 실패 여부
				console.log("req: "		+ req.status);	// 요청 코드값
				
				
				
				
			}
		});
		
	});
});
</script>
</body>
</html>













