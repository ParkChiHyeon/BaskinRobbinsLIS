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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/37d6185271.js" crossorigin="anonymous"></script>
</head>    
<!--         <link rel="icon" href="images/favicon.png"/> -->
 <link href="./khu_css/style.css" rel="stylesheet">
<%@ include file="./header.jsp" %>
<style>
#footer{
  	position:absolute;
  	bottom : 0;
	width: 100% ;
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

#myBtn {
  width: 300px;
  padding: 10px;
  font-size:20px;
  position: absolute;
  margin: 0 auto;
  right: 0;
  left: 0;
  bottom: 50px;
  z-index: 9999;
}


/* The Modal (background) */
.modal {
  width : 800px; 
  height : 600px;
  margin : 20px auto;
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
.close,.close2 {
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

.close2:hover,
.close2:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

.body{
height: 100%;
}


</style>
<body class="body">
	<div id="forms">
	<input type="hidden" id="chkval" value="0">
    <section class="login-form">
        <h1>Login</h1>
 
        <form action="post" method="post" id="login">
            <div class="int-area1">
                <input type="text" name="member_id" id="member_id" autocomplete="off" value="${mVo.member_id }" required>
                <label for="id">USER NAME</label>                
            </div>
            <div class="int-area2">
                <input type="password" name="password" id="password" autocomplete="off" value="Xptmxm@12" required>
                <i class="fa-solid fa-eye fa-lg"></i>
                <label for="pw">PASSWORD</label>
            </div>
            <div class="btn-area">
                <button id="btnLogin" type="button" name="signUp" onclick="loginCheck()">LOGIN</button>
            </div> 
            <div class="caption">
            <button id="btnFindId" type="button" onclick="findIdForm()" class="btn btn-outline-dark">아이디 찾기</button>
            <button id="#" type="button" onclick="findPwForm()" class="btn btn-outline-dark">비밀번호 찾기</button>
            <button id="#" type="button" onclick="singUpPage()" class="btn btn-outline-dark">회원가입</button>            
        </div>
       </form>
             
    </section>
	</div>
	
	<!-- Trigger/Open The Modal -->
<!-- The Modal -->
<div id="idModal" class="modal">

  <!-- findId Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>
     <div class="container">
        <h1>아이디 찾기</h1>
		<form action="#" id="findIdForm" method="get">
            <ul class = "list-group">
            <li class="list-group-item">
            <label>사용자 이름</label>
                <input type="text" name="name" id="name" autocomplete="off" value="테스트이름" placeholder="찾을 아이디의 사용자 이름을 입력해주세요" required>
            <label>사용자 전화번호</label>    
                <input type="text" name="phone" id="phone" autocomplete="off" value="01085439648" placeholder="찾을 아이디의 전화번호를 입력해주세요" required maxlength="11">
                <input type="button" id="btnFindForm" class="btn btn-outline-primary" value="아이디 찾기" onclick="find()">
                </li>
      		</ul>	 
      	</form> 
      </div>
  </div>
</div>	

	<!-- Trigger/Open The Modal -->
<!-- The Modal -->
<div id="pwModal" class="modal">

  <!-- findPw Modal content -->
  <div class="modal-content">
    <span class="close2">&times;</span>
     <div class="container">
        <h1>비밀번호 재발급</h1>
		<form action="" id="findPwForm" method="POST">
            <ul class = "list-group">
            <li class="list-group-item">
            <label>사용자 아이디</label>
                <input type="text" name="member_id" id="form_member_id" autocomplete="off" value="user002" placeholder="찾을 아이디의 사용자 이름을 입력해주세요" required>
            <label>사용자 전화번호</label>    
                <input type="text" name="phone" id="phone_textbox" autocomplete="off" value="01042935376" placeholder="찾을 아이디의 전화번호를 입력해주세요" required maxlength="11">
                <input type="button" class="btn btn-outline-primary" value="인증번호 전송" id="sendPhoneNumber">
                <li class="list-group-item" id="idInput">
				<input type="text" class="form-control" id="phoneCheck_textbox" name="phoneCheckNum" maxlength="8" placeholder="인증번호를 입력해 주세요" style="margin: 20px auto;" required>
				<input type="button" id="checkBtn" value="확인" class="btn btn-outline-primary" style="width: 100%">
				<div class="time"></div>
				</li>
				<li class="list-group-item" id="idInput">
                <input type="submit" id="btnPwFindForm" class="btn btn-outline-primary" value="재발급 받기" onclick="findPw()" style="width: 100%;" disabled>
                </li>
      		</ul>	 
      	</form> 
      </div>
  </div>
</div>	
<%-- <input type="text" name="phone" id="test" value="${numStr}"> --%>
	<%@ include file="./footer.jsp" %>
	  </body>
    <script type="text/javascript">
    
    function singUpPage() {
			location.href='./signUpPage.do';
	}
    
    var result = document.getElementById("result");
    // Get the modal
    var idModal = document.getElementById("idModal");
    var pwModal = document.getElementById("pwModal");
    
    function findIdForm(){
    	idModal.style.display = "block";
	}
    
    function findPwForm(){
    	pwModal.style.display = "block";
	}
    
    
    var timer = null;
    var isRunning = false;
    
    $('#sendPhoneNumber').click(function(){
        let phoneNumber = $('#phone_textbox').val();
        swal("인증번호 발송 완료");
        var display = $('.time');
        var leftSec = 180;
        
        if(isRunning){
        	clearInterval(timer);
        	display.html("");
        	startTimer(leftSec, display);
        }else{
        	startTimer(leftSec, display);
        }
        
        var docum = document.getElementById("checkBtn").disabled;
        var btnPwUpdatePage = document.getElementById("btnPwFindForm") 
        $.ajax({
            type: "POST",
            url: "./sendSMSmyPage.do",
            data: {"phone" : phoneNumber}, // 핸드폰 값이 넘어감
            success: function(res){ // 인증번호 값이 넘어옴
            	$('#checkBtn').click(function(){
            		if($('#phoneCheck_textbox').val()=='') {
                		swal('값을 입력하세요.');
                	}else if(isRunning && $.trim(res)==$('#phoneCheck_textbox').val()){
                		swal('인증 성공');
                		btnPwUpdatePage.disabled = false;
                		clearInterval(timer);
                		display.html("");
                	}else{
                		if(isRunning){
                			swal('인증번호가 맞지 않습니다');
                		} else {
                			swal('시간이 초과되었습니다');
                		}
                	}
            	})
            }
        })
    });
    function startTimer(count, display) {
    	var minutes, seconds;
        timer = setInterval(
    function () {
        minutes = parseInt(count / 60, 10);
        seconds = parseInt(count % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.html(minutes + ":" + seconds);

        // 타이머 끝
        if (--count < 0) {
         clearInterval(timer);
         alert("시간초과");
         display.html("시간초과");
         $('#checkBtn').attr("disabled","disabled");
         isRunning = false;
        }
    }, 1000);
         isRunning = true;
    }
    
    
    
    
    
    
    function findPw(){
    	var id = document.getElementById("form_member_id");
		var phone = document.getElementById("phone_textbox");
		var frm = document.getElementById("findPwForm");
		var frm2 = document.getElementById("findPwForm");
		
		console.log(id.value);
		console.log(phone.value);
		
		if(id.value =="" || id.value.trim() ==""){
			swal("사용자의 아이디를 입력해주세요");
			id.value="";
			id.focus();
		}else if(phone.value =="" || phone.value.trim() ==""){
			swal("사용자의 전화번호를 입력해주세요");
			phone.value="";
			phone.focus();
		}else{
			console.log(id.value);
			console.log(phone.value);
			
			frm.action = "./updatePwPage.do"
// 			frm2.action = "./encrypt.do"
			
			$.ajax({
				url :"./findPwChk.do",
				type:"POST",
				data:"member_id="+id.value+"&phone="+phone.value,
				success:function(msg){
					if(msg.isc =="성공"){
						swal({
							title: "비밀번호 변경 페이지로 넘어갑니다",
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
					swal("입력하신 전화번호로 비밀번호를 전송하였습니다");
				}
			});
				
		}
		
    }
    
    
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
							title: "아이디를 찾았습니다\n찾은 아이디가 입력됩니다",
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
    
    
    
   
    
    
    


    // Get the button that opens the modal

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];
    var span2 = document.getElementsByClassName("close2")[0];


    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
      idModal.style.display = "none";
    }
    
    span2.onclick = function() {
        pwModal.style.display = "none";
      }
    
    

    // When the user clicks anywhere outside of the modal, close it
//     window.onclick = function(event) {
//       if (event.target == idModal || event.target == pwModal) {
//         idModal.style.display = "none";
//         pwModal.style.display = "none";
//       }
//     }
    
   
    
    
    function loginCheck(){
    	
         var id = document.getElementById("member_id");
         var pw = document.getElementById("password");
         var frm = document.getElementById("login");
         
         
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
    						frm.submit();
//     						location.href="./loginMember.do"
    						console.log(msg.isc);
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

</html>
