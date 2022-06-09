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

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button */
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

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
			
        <div class="container">
        <h1>아이디 찾기</h1>
		<form action="#" id="findIdForm" method="get">
            <ul class = "list-group">
            <li class="list-group-item">
            <label for="pw">사용자 아이디</label>
                <input type="text" name="name" id="name" autocomplete="off" value="테스트이름" placeholder="찾을 아이디의 사용자 이름을 입력해주세요" required>
                <input type="text" name="phone" id="phone" autocomplete="off" value="01085439648" placeholder="찾을 아이디의 전화번호를 입력해주세요" required maxlength="11">
                <input type="button" name="btnAdminLogin" id="btn" class="btn btn-outline-primary" value="아이디 찾기" onclick="find()"><label><a id="result">안녕</a></label>
                </li>
      		</ul>	 
      	</form> 
      </div>
      
<!-- Trigger/Open The Modal -->
<!-- The Modal -->

      		
</body>
<%@ include file="./footer.jsp" %>
	<script type="text/javascript">
	
		function find() {
			var name = document.getElementById("name");
			var phone = document.getElementById("phone");
			var frm = document.getElementById("findIdForm");
			var result = document.getElementById("result");
			
			console.log("name",name.value);
			console.log("phone",phone.value);
			
			frm.action = "./findId.do"
			
			if(name.value =="" || name.value.trim()==""){
				swal("사용자의 이름을 입력해주세요");
				name.value="";
				name.focus();
			}else if(phone.value=="" || phone.value.trim()==""){
				swal("전화번호를 입력해 주세요");
				phone.value="";
				phone.focus();
			}else{
				
				console.log("name",name.value);
				console.log("phone",phone.value);
				$.ajax({
					url :"./findIdChk.do",
					type:"get",
					data:"name="+name.value+"&phone="+phone.value,
					success:function(msg){
						console.log(msg.isc, typeof msg);
						if(msg.isc =="성공"){
							swal({
								title: "아이디 찾기 성공",
								icon : "success",
								closeOnClickOutside: false
								}, function(){
 									frm.submit();		
									console.log(msg.isc);
								});
						}else{
							swal("올바른 정보를 입력해주세요");
						}
					},
					error:function(){
						swal("에러발생");
						console.log(name.value);
    	     			console.log(phone.value);
					}
				});
			}
			
			
		}
	
	
	</script>


</html>
