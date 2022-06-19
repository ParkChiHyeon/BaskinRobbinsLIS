<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page import="java.net.*, java.io.*"%>  
<%
    // 1회성 토큰 생성
    String g_recaptcha_response = request.getParameter("g-recaptcha-response");
    
    //URL로 쿼리 매개 변수 secret, response 및 remoteip를 담아 HTTP 요청을 하면, json 응답을 받는다.
    HttpURLConnection conn = (HttpURLConnection) new URL("https://www.google.com/recaptcha/api/siteverify").openConnection();
    // 토큰과 보안키로 성공 여부 확인
    String params = "secret=6LcgedMfAAAAAJZAB1Tr-0FBoVzizuQ94YHat2TD" + "&response=" + g_recaptcha_response;
    // 전송 방식 : POST
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    
    DataOutputStream dos = new DataOutputStream(conn.getOutputStream()); 
    dos.writeBytes(params);  
    dos.flush();   
    dos.close();
    
    // 결과코드 확인(200 : 성공)
    int responseCode = conn.getResponseCode();
    StringBuffer responseBody = new StringBuffer();
    if (responseCode == 200) {
        
        // 데이터 추출
        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
        String line;
        while ((line = reader.readLine()) != null) {
            responseBody.append(line);
        }
        bis.close();
        
      
    }
    
%>

<html>


<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit" async defer></script>
<script src="https://kit.fontawesome.com/37d6185271.js" crossorigin="anonymous"></script>

<style type="text/css">
#id_textbox{
	width: 300px;
	height: 40px;
}

#pw_textbox{
	width: 300px;
	height: 40px;	
}

#pwChk_textbox{
	width: 300px;
	height: 40px;	
}

#name_textbox{
	width: 300px;
	height: 40px;	
}

label{
	font-size: 20px;
}

#birth_textbox{
	width : 100px;
	height: 40px;
	margin-right: 0 auto;
}

#birth2_textbox{
	width : 40px;
	height: 40px;
}

#btnSignUp{
	width : 800px;
	margin-top: 100px;
	margin: 20px auto;
	margin-bottom: 30px;	
	display: inline-block;
	text-align: center;
}

#zipSearch_textbox{
}

#phone_textbox{
	width: 300px;
	height: 40px;
}

#detailAddress_textbox{
	width: 600px;
	height: 40px;
		
}

#phoneCheck_textbox{
	width : 100px;
	height: 40px;
}

#idInput{
/* 	display: flex; */
}

#ulForm{
	margin : 40px auto; display: table; border: 2px solid;
}

div #eye {
    position: relative;
}

div#eye i{
	position :absolute;
    left: 35%;
    top : 53px;
    color: black;
}





</style>
</head>    
<!--         <link rel="icon" href="images/favicon.png"/> -->
 <link href="./khu_css/style.css" rel="stylesheet">
<%@ include file="./header.jsp" %>
<body>
	
	<form action="./signUp.do" method="POST" id="testForm" name="isAgree">
	<div class="container">
	
	<input type="text" value="0" id="idChkVal" hidden="true">
	<input type="text" value="0" id="pwChkVal" hidden="true">
	<input type="text" value="0" id="pwChkVal2" hidden="true">
	<input type="text" value="0" id="nameChkVal" hidden="true">
	<input type="text" value="0" id="birthChkVal" hidden="true">
	<input type="text" value="0" id="phoneChkVal" hidden="true">
	<input type="text" value="0" id="phoneChkVal2" hidden="true">
	<input type="text" value="1" id="addrChkVal" hidden="true">
	
		<ul class="list-group" id="ulForm">	
		<li class="list-group-item" id="idInput">
		<label>* 아이디</label>
		<input type="text" class="form-control" placeholder="ID" id="id_textbox" name="member_id" required>
		<button type="button" id="btnIdDuplicateCheck" onclick="idDuplicateCheck()"class="btn btn-outline-primary">중복확인</button>
		</li>
		<li class="list-group-item"><span id="resultId"><a></a></span></li>
		
		
		
		<li class="list-group-item" id="idInput">
		<div id="eye">
		<label>* 비밀번호</label><input type="password" class="form-control" placeholder="PASSWORD" id="pw_textbox" name="password" required>
		<i class="fa-solid fa-eye fa-lg"></i> 
		</div>
		</li>
		<li class="list-group-item"><span id="resultPw"><a></a></span></li>
		
		
		
		
		<li class="list-group-item" id="idInput">
		<div id="eye">
		<label>* 비밀번호 확인</label><input type="password" class="form-control"  id="pwChk_textbox" required>
		<i class="fa-solid fa-eye fa-lg"></i> 
		</div>
		</li>
		<li class="list-group-item"><span id="resultPw2"><a></a></span></li>
		
		
		
		<li class="list-group-item" id="idInput">
		<label>* 이름</label><input type="text" class="form-control"  id="name_textbox" name="name" required>
		</li>
		<li class="list-group-item"><span id="resultName"><a></a></span></li>
		
	
		
		<li class="list-group-item" id="idInput">
		<label>* 주민번호 앞 6자리 + 뒤 1자리</label><input type="text" class="form-control" id="birth_textbox" name="birth" maxlength="7" required>
		</li>
		<li class="list-group-item"><span id="resultBirth"><a></a></span></li>
			
		
		
		
		<li class="list-group-item" id="idInput">
		
		<label>* 전화번호</label><input type="text" class="form-control" placeholder="ex)01012345678" id="phone_textbox" name="phone" maxlength="11" required>
		
		<input type="button" class="btn btn-outline-primary" value="인증번호 전송" id="sendPhoneNumber">
		</li>
		<li class="list-group-item"><span id="resultPhone"><a></a></span></li>
		
		
		
		<li class="list-group-item" id="idInput">
		<label>* 인증번호</label><input type="text" class="form-control" id="phoneCheck_textbox" name="phoneCheckNum" maxlength="8" required>
		<input type="button" id="checkBtn" value="확인" class="btn btn-outline-primary">
		<div class="time"></div>
		</li>
		
		
		
		<li class="list-group-item" id="idInput">
		<label>주소</label><input type="text" class="form-control" id="detailAddress_textbox" name="address" autocomplete="name" readonly>
		<input type="button" class="btn btn-outline-primary" value="우편번호 찾기" id="zipSearch_textbox" onclick="kakaopost()">
		</li>
		<li class="list-group-item"><span id="resultAddr"><a></a></span></li>
		
		
		<li class="list-group-item"><div id="recaptcha_render" ></div></li>
		<li class="list-group-item"><input type="submit" class="btn btn-outline-primary" value="가입완료" onclick="signUp()" id="btnSignUp" ></li>
				
			
				
	
	</ul>
	</div>
	</form>
</body>
<%@ include file="./footer.jsp" %>

<script type="text/javascript">

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
            		$("#phoneChkVal2").val(1);
            	}else{
            		if(isRunning){
            			swal('인증번호가 맞지 않습니다');
            			$("#phoneChkVal2").val(0);
            		} else {
            			swal('시간이 초과되었습니다');
            			$("#phoneChkVal2").val(0);
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


var onloadCallback = function() {
    grecaptcha.render('recaptcha_render', {
    	/* 사이트 키 */
        'sitekey' : '6LcgedMfAAAAAA-6iUavQAVF8Vg5FrObAvKk3XEd',
        /* recaptcha 테마 */
        'theme' : 'dark'
//      'theme' : 'light'
    });
};



function signUp() {
	var chk = document.getElementById("idChkVal");
	var chk2 = document.getElementById("pwChkVal");
	var chk3 = document.getElementById("pwChkVal2");
	var chk4 = document.getElementById("nameChkVal");
	var chk5 = document.getElementById("birthChkVal");
	var chk6 = document.getElementById("phoneChkVal");
	var chk7 = document.getElementById("phoneChkVal2");
	var chk8 = document.getElementById("addrChkVal");
	var frm = document.getElementById("testForm");
	var isCheck = document.frm.isAgree.checked;
	
	
	console.log(chk)
	if(chk.value == 1 && chk2.value == 1 && chk3.value == 1 && chk4.value == 1 && chk5.value == 1 && chk6.value == 1 &&
			chk7.value == 1 && chk8.value == 1 && isCheck){
		location.href = "./signUp.do";	
	}
	else{
		swal("에러","작성한 정보를 다시 확인하세요");
		return false;
	}
	
}


function kakaopost() {
    new daum.Postcode({
        oncomplete: function(data) {
           document.querySelector("#detailAddress_textbox").value ='('+ data.zonecode + ')' + data.address
           $("#detailAddress_textbox").focus();	
        }
    }).open();
}

function idDuplicateCheck() {
	var id = document.getElementById("id_textbox");
	$.ajax({
		async: true,
		type : 'post',
		data:"member_id="+id.value,
		url : "./idDuplicateCheck.do",
		success: function(msg){
			var reg = /^[A-Za-z]{1}[A-Za-z0-9]{5,19}$/;
			if(msg.isc=="성공"){
				swal("중복된 아이디입니다");
				$("#id_textbox").focus();	
				$("#idChkVal").val(0);
				
			}else if(msg.isc=="실패" && id.value.match(reg)){
				swal("성공","중복된 아이디가 없습니다");
				$("#resultId").css("color","blue");
				$("#resultId").html("해당 아이디를 사용합니다");
				$("#pw_textbox").focus();
				$("#idChkVal").val(1);
				$("#pw_textbox").focus();
				$("#id_textbox").attr("readonly",true);	
			}else{
				swal("실패","다시 확인해주세요");
				$("#idChkVal").val(0);
			}
		},
		error :function(){
			swal("회원가입 실패","에러가 발생하였습니다")
			$("#idChkVal").val(0);
		
		}
		
	})
	
}


$(document).ready(function(){
		
	$("#id_textbox").keyup(function(){
		var idVal = $(this).val();
		var reg = /^[A-Za-z]{1}[A-Za-z0-9]{5,19}$/;
		
		console.log(idVal);
		
		if(!idVal.match(reg)){
			$("#resultId").css("color","red");
			$("#resultId").html("아이디의 길이는 6~20 영어 숫자포함 입니다.");
			$("#idChkVal").val(0);
		}else if(idVal.indexOf(" ") != -1){
			$("#resultId").css("color","red");
			$("#resultId").html("공백이 포함된 아이디는 사용할 수 없습니다.");
			$("#idChkVal").val(0);
		}else{
			$("#resultId").css("color","blue");
			$("#resultId").html("사용하실려면 중복확인을 해주세요")
		}
	});
});


$(document).ready(function(){
	$("#pw_textbox").keyup(function(){
		var pwVal = $(this).val();
		var reg = "^(?=.*[a-zA-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$";
		console.log(pwVal);
		if(pwVal.match(reg)){
			$("#resultPw").css("color","blue");
			$("#resultPw").html("사용 가능한 비밀번호입니다")
			$("#pwChkVal").val(1);
		}else{
			$("#resultPw").css("color","red");
			$("#resultPw").html("최소 8 자 및 최대 20 자, 하나 이상의 대문자, 하나의 소문자, 하나의 숫자 및 하나의 특수 문자 정규식");
			$("#pwChkVal").val(0);
		}
	});
});

$(document).ready(function(){
	$("#pwChk_textbox").keyup(function(){
		var pwVal = $(this).val();
		var exPwVal = document.getElementById("pw_textbox").value;
		var reg = "^(?=.*[a-zA-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$";
		console.log(exPwVal);
		if(pwVal == exPwVal && pwVal.match(reg)){
			$("#resultPw2").html("비밀번호가 확인되었습니다")
			$("#resultPw2").css("color","blue");
			$("#pwChkVal2").val(1);
		}else{
			$("#resultPw2").css("color","red");
			$("#resultPw2").html("비밀번호를 다시 확인해주세요");
			$("#pwChkVal2").val(0);

		}
	});
});


$(document).ready(function(){
	$("#name_textbox").keyup(function(){
		var reg = /^[가-힣a-zA-Z]+$/;
		var nameVal = $(this).val();
		if(nameVal.match(reg)){
			$("#resultName").html("")
			$("#nameChkVal").val(1);
		}else{
			$("#resultName").css("color","red");
			$("#resultName").html("이름엔 영어와 한글만 들어갑니다");
			$("#nameChkVal").val(0);

		}
	});
});



$(document).ready(function(){
	$("#phone_textbox").keyup(function(){
		var phoneVal = $(this).val();
		
		var reg = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})";
		if(phoneVal.match(reg)){
			$("#resultPhone").html("")
			$("#phoneChkVal").val(1);
		}else{
			$("#resultPhone").css("color","red");
			$("#resultPhone").html("핸드폰 번호를 다시 확인해주세요");
			$("#phoneChkVal").val(0);

		}
	});
});

// /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/


$(document).ready(function(){
	$("#birth_textbox").keyup(function(){
		var birthVal = $(this).val();
		console.log(birthVal)
		//주민번호 정규화 다시
// 		var reg = "^d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|[3][01])[1-4]";
		var reg = "^[0-9]{7,7}";
		if(birthVal.match(reg)){
			$("#resultBirth").html("")
			$("#birthChkVal").val(1);
		}else{
			
			$("#resultBirth").css("color","red");
			$("#resultBirth").html("주민번호를 다시 확인해주세요");
			$("#birthChkVal").val(0);
		}
	});
});


$(document).ready(function(){
	$("#detailAddress_textbox").keyup(function(){
		var addrVal = $(this).val();		
		if(!addrVal ==""){
			$("#resultBirth").html("")
			$("#addrChkVal").val(1);
		}else{		
			$("#resultAddr").css("color","red");
			$("#resultAddr").html("주소 찾기를 진행해주세요");
			$("#addrChkVal").val(0);
		}
	});
});


document.getElementById("btnSignUp").addEventListener("click",function(evt)
		  {
		  let response = grecaptcha.getResponse();
          
		  if(response.length == 0) // 
		  {
		    swal("오류","자동가입 방지 체크를 해주세요");
		    evt.preventDefault();
		    
		    return false;
		  }
		  else{
			  
		     let msg = {
		        'response': response,
		    }
		     
		     
		    let options = {
		            method: 'POST',
		            headers: { 'Content-Type': 'application/json' },
		            body: JSON.stringify(msg)
		        }
		     
		     
		     //실패
		    fetch('/verify_grecaptcha', options)
		    .then(response => {
		        if(!response.ok) {
		            throw Error(response.status);
		        }
		        console.log(response);
		        return response.json()
		    })
		    //성공
		    .then(json => {
		        if(json.success === true){
		            console.log("Done completely");
		        }

		    })
		    //에러
		    .catch(err => {
		        console.log("Error", err)
		    })
		  }
		});

// \d{2}([0]\d|[1][0-2])([0][1-9]|[1-2]\d|[3][0-1])[-]*[1-4]\d{6}

 $(document).ready(function(){
        $('#eye i').on('click',function(){
            $('#eye input').toggleClass('active');
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
