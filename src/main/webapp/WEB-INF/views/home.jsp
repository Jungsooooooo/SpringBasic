<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<title>Home</title>
	<%@ include file="include/header.jsp" %>
</head>
<body>
	<%@ include file="include/topMenu.jsp" %>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<P><h1>MyName is ${myName }</h1></P>
</body>
</html>
