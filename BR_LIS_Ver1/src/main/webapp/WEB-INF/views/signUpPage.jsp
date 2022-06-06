<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>

<style type="text/css">
#id_textbox{
	width: 300px;
	height: 40px;
}

#pw_textbox{
	width: 300px;
	height: 40px;	
}

label{
	font-size: 20px;
}



</style>
</head>    
<!--         <link rel="icon" href="images/favicon.png"/> -->
 <link href="./khu_css/style.css" rel="stylesheet">
<%@ include file="./header.jsp" %>
<body>
	<form action="./signUp.do" method="POST">
	<div id="1">
		<div id="2" class="row">		
		<label>아이디</label>		
		<div class="col">
		<input type="text" class="form-control" placeholder="ID" id="member_id" name="member_id">
		</div>
		<span><a>사용 가능한 아이디 입니다</a></span>
		</div>
		
		<div id="3" class="row">
		<div class="col">
		<label>비밀번호</label><input type="text" class="form-control" placeholder="PASSWORD" id="pw_textbox" name="password">
		</div>
		<span><a>비밀번호를 입력해주세요</a></span>
		</div>
		
		<div id="4" class="row">
		<label>비밀번호 확인</label><input type="text" class="form-control"  id="pwChk_textbox">
		<span><a></a></span>
		</div>
		
		<div id="5" class="row">
		<label>이름</label><input type="text" class="form-control"  id="name_textbox" name="name">
		</div>
		
		<div id="6" class="row">
		
		<div class="col">
		<label>주민번호 앞 6자리</label><input type="text" class="form-control" id="birth_textbox" name="birth">
		</div>
		
<!-- 		<div class="col"> -->
<!-- 		<label>주민번호 뒤 1자리</label><input type="text" class="form-control" id="birth2_textbox" name="birth"> -->
<!-- 		</div> -->
		
		</div>
		
		<div id="7">
		<label>전화번호</label><input type="text" class="form-control" placeholder="ex)01012345678" id="phone_textbox" name="phone">
		</div>
		
		<div id="8" class="row">
		
<!-- 		<div class="col"> -->
<!-- 		<label>우편번호</label><input type="text" class="form-control"  id="zipCode_textbox" name="address" readonly> -->
<!-- 		</div> -->
		
		<div class="row">
		
		</div>
		
		<div class="col">
		<input type="button" class="btn btn-primary" value="우편번호 찾기" id="zipSearch_textbox">
		</div>
		
		</div>
		<div id="9" class="row">
		<label>상세주소</label><input type="text" class="form-control" id="detailAddress_textbox" name="address">
		</div>
		
		<div id="10" class="row">
		<input type="button" class="btn btn-primary" value="가입완료" onclick="signUp()">
		</div>	
	</div>
	</form>
</body>
<%@ include file="./footer.jsp" %>

<script type="text/javascript">

function signUp() {
	location.href = "./signUp.do";
}

</script>
</html>
