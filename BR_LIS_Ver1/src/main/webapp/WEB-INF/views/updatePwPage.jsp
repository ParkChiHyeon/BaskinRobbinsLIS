<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경 페이지</title>


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="https://kit.fontawesome.com/37d6185271.js" crossorigin="anonymous"></script>
</head>    
<!--         <link rel="icon" href="images/favicon.png"/> -->
 <link href="./khu_css/style.css" rel="stylesheet">
<%@ include file="./header.jsp" %>

<body class="body">    
			<form action="./updatePw.do" method="get" id="khu_updateInfoForm" >
	<div class="container">
	<label>아이디</label><input type="text" class="form-control" name="member_id" value="${mVo.member_id }" maxlength="8" hidden="true">
	<label>전화번호</label><input type="text" class="form-control"  name="phone" value="${mVo.phone}" maxlength="8" id="phone_textbox" hidden="true">
	
	<ul class="list-group" id="ulForm">	

            <li class="list-group-item" id="idInput">
		<div id="eye">
		<label>비밀번호</label><input type="password" class="form-control" placeholder="PASSWORD" id="pw_textbox" name="password" required>
		<i class="fa-solid fa-eye fa-lg"></i> 
		</div>
		</li>
		<li class="list-group-item"><span id="resultPw"><a></a></span></li>
		
		
		
		
		<li class="list-group-item" id="idInput">
		<div id="eye">
		<label>비밀번호 확인</label><input type="password" class="form-control"  id="pwChk_textbox" required>
		<i class="fa-solid fa-eye fa-lg"></i>
		<button type="button" id="khu_btnUpdateEmail" onclick="updatePw()"class="btn btn-outline-primary">변경</button> 
		</div>
		</li>
		<li class="list-group-item"><span id="resultPw2"><a></a></span></li>
		
		</ul>
		</div>
		</form>
		
      		
</body>
<%@ include file="./footer.jsp" %>
<script type="text/javascript">
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


$(document).ready(function(){
	$("#pw_textbox").keyup(function(){
		var pwVal = $(this).val();
		var reg = "^(?=.*[a-zA-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$";
		console.log(pwVal);
		if(pwVal.match(reg)){	
			$("#resultPw").html("")
			$("#chkVal").val(1);
		}else{
			$("#resultPw").css("color","red");
			$("#resultPw").html("최소 8 자 및 최대 20 자, 하나 이상의 대문자, 하나의 소문자, 하나의 숫자 및 하나의 특수 문자 정규식");
			$("#chkVal").val(0);
		}
	});
});

$(document).ready(function(){
	$("#pwChk_textbox").keyup(function(){
		var pwVal = $(this).val();
		var exPwVal = document.getElementById("pw_textbox").value;
		console.log(exPwVal);
		if(pwVal.match(exPwVal)){
			$("#resultPw2").html("")
			$("#chkVal").val(1);
		}else{
			$("#resultPw2").css("color","red");
			$("#resultPw2").html("비밀번호를 다시 확인해주세요");
			$("#chkVal").val(0);

		}
	});
});

function updatePw(){
	var frm = document.getElementById("khu_updateInfoForm")
	var pw = document.getElementById("pw_textbox").value;
	frm.action = "./memberInfoUpdatePw.do"
	var reg = "^(?=.*[a-zA-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$";
	if(pw.match(reg)){
		swal({
				title : "성공\n비밀번호를 변경하였습니다",					
				icon  : "success",
				closeOnClickOutside : false
				}, function(){
				frm.submit();
					}
			)}else{
				swal('실패','8~20자리 대소문자 특수문자 숫자 포함');
				return false;
			}
	}
</script>
</html>
