<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<%@ include file="./header.jsp" %>
</head>
<body>
<P>  The time on the server is ${serverTime}. </P>
<a href="./test.do">이동</a>

<div>
	<fieldset>
		<legend>git Test</legend>
	</fieldset>
</div>
<%@ include file="./footer.jsp" %>
</body>
</html>
