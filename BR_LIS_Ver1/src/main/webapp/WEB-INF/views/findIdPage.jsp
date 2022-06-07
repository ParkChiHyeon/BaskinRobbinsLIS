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

ul{
	display: inline-block;
}

li{
	text-align: center;
}

.container{
	margin: 0 auto;
}

#password{
	width: 600px;
	height: 40px;
}

h1{
	text-align: center;
}
</style>
<body class="body">    
			<form action="./findId.do" method="POST">
            <div class="container">
            <h1>아이디 찾기</h1>
            <ul class = "list-group">
            <li class="list-group-item">
            <label for="pw">사용자 아이디</label>
                <input type="text" name="name" id="name" autocomplete="off" value="user001" placeholder="찾을 아이디의 사용자 이름을 입력해주세요" required>
                <input type="text" name="phone" id="phone" autocomplete="off" value="01085439648" placeholder="찾을 아이디의 전화번호를 입력해주세요" required maxlength="11">
                <input type="submit" name="btnAdminLogin" id="btn" class="btn btn-outline-primary" value="아이디 찾기" ><label><a>찾은아이디 : ${mId}</a></label>
                </li>
      		</ul>	  
      	</div>
      		</form>
</body>
<%@ include file="./footer.jsp" %>
	<script type="text/javascript">
		alert("찾은 아이디", ${mId});
	
	</script>


</html>
