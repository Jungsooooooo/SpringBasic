<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>파일 올리기</title>
	<%@ include file = "../include/header.jsp" %>
	<style>
	iframe {
		width:			1000px;
		height:			500px;
		border:			1px;
		border-style:	solid;
	}
	</style>
</head>
<body>
<%@ include file = "../include/topMenu.jsp" %>
<br>

<div class="container">

	<!-- 파일을 올리려면 원래는 action="${path}/upload/uploadFile"으로 이동해야 하는데
		 페이지를 넘어가지 않기 위해서 제출된 결과를 iframe으로 보낸다. -->
	<form action="${path}/upload/uploadFile" method="post" enctype="multipart/form-data" target="iframeScope">
		<div class="form-group">
			<div class="col-sm-10">
				<input class="form-control-file" type="file" name="file"/>
				<input class="btn btn-primary" type="submit" value="파일 올리기"/>
			</div>
		</div>
	</form>

	<!-- ifrmae에 업로드한 결과를 출력한다. -->
	<!-- HTML Inline Frame의 요소이면, inline frame의 약자. -->
	<iframe name="iframeScope"></iframe>

</div>

<%@ include file = "../include/footer.jsp" %>
</body>
</html>













