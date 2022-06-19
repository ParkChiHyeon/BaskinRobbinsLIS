<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인 페이지</title>


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
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

div.int-area2{
    position: relative;
}

div.int-area2 i{
	position :absolute;
    left: 92%;
    top : 40px;
    color: black;
}

</style>
<body class="body">
	<div id="forms">
	<input type="hidden" id="chkval" value="0">
    <section class="login-form">
        <h1>Admin Login</h1>
 
        <form action="#" method="post" id="login" style="padding-top: 100px;">
            <div class="int-area1">
                <input type="text" name="admin_id" id="admin_id" autocomplete="off" value="admin001" required>
                <label for="id">Admin Id</label>
            </div>
            <div class="int-area2">
                <input type="password" name="password" id="password" autocomplete="off" value="Clsclffk@12" required>
                <i class="fa-solid fa-eye fa-lg"></i>
                <label for="pw">PASSWORD</label>
            </div>
            <div class="btn-area">
                <button id="btn" type="button" name="signUp" onclick="adminLoginCheck()">LOGIN</button>
            </div> 
       </form>
        
         
        
    </section>
	</div>
	  
    <script type="text/javascript">  
    
    function adminLoginCheck(){
    	
         var id = document.getElementById("admin_id");
         var pw = document.getElementById("password");
         var frm = document.getElementById("login");
         
         
    		console.log("admin_id",id.value);
    		console.log("password",pw.value);
    		
    		frm.action = "./loginAdmin.do"; 	
    		
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
    				url:"./adminLoginCheck.do",
    				type:"post",
    				data:"admin_id="+id.value+"&password="+pw.value,
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
    
    
   
    $(document).ready(function(){
        $('.int-area2 i').on('click',function(){
            $('input').toggleClass('active');
            if($('input').hasClass('active')){
                $(this).attr('class',"fa fa-eye-slash fa-lg")
                .prev('input').attr('type',"text");
            }else{
                $(this).attr('class',"fa fa-eye fa-lg")
                .prev('input').attr('type','password');
            }
        });
    });

   
    
    
  
    </script>           
</body>
<%@ include file="./footer.jsp" %>
</html>
