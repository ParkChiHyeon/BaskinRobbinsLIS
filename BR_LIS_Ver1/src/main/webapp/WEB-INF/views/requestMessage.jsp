<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>woowang알림메세지</title>
</head>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="./BR_js/phoneNumberSearch.js"></script>

<body>
<h3>메시지 보내기</h3>
	<div>
		회원 아이디<input type="text" id="searchPhoneNumber" name="searchPhoneNumber" title="회원아이디 입력">
		<div class="btnRight">
			<span class="themeBtn"><a href="javascript:requestMessage();">조회</a></span>
		</div>
		
		<!-- 조회된 결과를 div태그 안에 넣어줌 -->
		<div id="phoneNumResult"></div>
		
<!-- 		<form action="./sendMessageNumber.do" method="post"> -->
<%-- 			휴대폰 번호 : <p>${phoneNumResult}</p> --%>
<!-- 			<textarea rows="10" cols="50"></textarea> -->
<!-- 		</form> -->
	</div>
 
</body>
</html>