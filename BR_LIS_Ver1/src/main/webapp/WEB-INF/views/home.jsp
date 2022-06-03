<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
	<%@ include file="./header.jsp" %>
<body>
<P>  The time on the server is ${serverTime}. </P>
<a href="./test.do">이동</a>
<a href="./loginPage.do">로그인 폼 이동</a>

<div>
	<fieldset>
		<legend>git Test</legend>
	</fieldset>
</div>
</body>
<%@ include file="./footer.jsp" %>
</html>
