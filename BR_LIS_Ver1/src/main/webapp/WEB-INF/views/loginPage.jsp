<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
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
</style>
<body class="body">
	<div id="forms">
    <section class="login-form">
        <h1>Login</h1>
 
        <form action="#" method="post" id="login">
            <div class="int-area1">
                <input type="text" name="member_id" id="member_id" autocomplete="off" value="user001" required>
                <label for="id">USER NAME</label>
            </div>
            <div class="int-area2">
                <input type="text" name="password" id="password" autocomplete="off" value="Xptmxm@12" required>
                <label for="pw">PASSWORD</label>
            </div>
            <div class="btn-area">
                <button id="btn" type="button" name="signUp" onclick="loginCheck()">LOGIN</button>
            </div> 
            <div class="caption">
            <a href="">Forgot Password?</a>
            <a href="./signUpPage.do">회원가입</a>
        </div>
       </form>
        
         
        
    </section>
	</div>
	  
    <script type="text/javascript">
    function loginCheck(){
    	
         var id = document.getElementById("member_id");
         var pw = document.getElementById("password");
         var frm = document.forms[0];
         
         
    		console.log("member_id",id.value);
    		console.log("password",pw.value);
    		
    		frm.action = "./loginMember.do"; 	
    		
    		//빈칸 유효성
    		if(id.value=="" || id.value.trim()==""){
    			swal("로그인 실패","아이디를 입력해 주세요");
    			id.value="";
    			id.focus();
    		}else if(pw.value=="" || pw.value.trim()==""){
    			swal("로그인 실패","비밀번호를 입력해 주세요");
    			pw.value="";
    			pw.focus();
    		}else{
    			//로그인 비동기식 처리
    			$.ajax({
    				url:"./loginCheck.do",
    				type:"post",
    				data:"member_id="+id.value+"&password="+pw.value,
    				success:function(msg){     			
    					console.log(msg.isc , typeof msg);
    					if (msg.isc=="성공") {
    						swal({
    					title : "로그인 성공\n게시판으로 이동합니다",					
        				icon  : "success",
        				closeOnClickOutside : false
    					}, function(){
    						console.log(msg.isc);
    						frm.submit();
    					});
    					}else{
    						swal("로그인 실패","존재하지 않는 사용자입니다");
    						
    					}
    				},
    				error:function(){
    					swal("로그인 실패","에러가 발생하였습니다")
    					console.log(id.value);
    	     			console.log(pw.value);
    				}
    			});
    		}
    	}
    
    
   
    
    
    
//     	//회원가입
//     	window.onload = function(){
//     			document.getElementById("register").onclick = function(){
//     				location.href = "./registerForm.do";
//     			}
//     		} 
    </script>           
</body>
<%@ include file="./footer.jsp" %>
</html>
