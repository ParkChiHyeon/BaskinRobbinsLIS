<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인 페이지</title>


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="https://kit.fontawesome.com/37d6185271.js" crossorigin="anonymous"></script>
</head>    
<!--         <link rel="icon" href="images/favicon.png"/> -->
 <link href="./khu_css/style.css" rel="stylesheet">
<%@ include file="./header.jsp" %>
<style>
#footer{
 	position:absolute;
 	bottom: 0;
 	width: 100%
}

#liItem{
	width: 500px;
	height: 250px;
	margin: 0 auto;
}

#btn{
	width: 100%;
}

</style>
<body class="body">    
            <div class="container">       
            <ul class = "list-group" style="margin-top: 200px;">
            <li class="list-group-item" id="liItem">
            <h1>관리자 인증</h1>
                <input type="password" class="form-control" name="password" id="password" autocomplete="off" value="dndhkdehtjrhks" placeholder="관리자 번호를 입력해주세요" required>
                <input type="button"  name="btnAdminLogin" id="btn" class="btn btn-outline-primary" value="확인" onclick="certification()">
                <a>관리자 문의 010-4293-5376</a>
                </li>
      		</ul>
      		  
      		</div>
      		
</body>
<%@ include file="./footer.jsp" %>

<script type="text/javascript">
	function certification() {

	var pw = document.getElementById("password").value;
	
	if(pw == "dndhkdehtjrhks"){
		swal("인증 성공")
		location.href="./adminLoginPage.do"
	}else{
		swal("인증 실패")
		return false;
	}
};
</script>

</html>
