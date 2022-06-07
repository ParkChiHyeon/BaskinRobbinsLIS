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
        
        // JSON으로 변환 
        if(responseBody.toString().indexOf("\"success\": true") > -1)
            out.println("인증 되었습니다");
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
	margin : 0 auto; display: table; border: 2px solid;
}


</style>
</head>    
<!--         <link rel="icon" href="images/favicon.png"/> -->
 <link href="./khu_css/style.css" rel="stylesheet">
<%@ include file="./header.jsp" %>
<body>
	<form action="./signUp.do" method="POST">
	<div class="container">
	
		<ul class="list-group" id="ulForm">				
		<li class="list-group-item" id="idInput">
		<label>아이디&nbsp;</label>
		<input type="text" class="form-control" placeholder="ID" id="id_textbox" name="member_id" required>
		<button type="button" id="btnIdDuplicateCheck" onclick="idDuplicateCheck()"class="btn btn-primary">중복확인</button>
		</li>
		<li class="list-group-item"><span id="resultId"><a></a></span></li>
		
		
		
		
		<li class="list-group-item" id="idInput">
		<label>비밀번호&nbsp;</label><input type="password" class="form-control" placeholder="PASSWORD" id="pw_textbox" name="password" required> 
		</li>
		<li class="list-group-item"><span id="resultPw"><a></a></span></li>
		
		
		
		
		<li class="list-group-item" id="idInput">
		<label>비밀번호 확인&nbsp;</label><input type="password" class="form-control"  id="pwChk_textbox" required>
		</li>
		<li class="list-group-item"><span id="resultPw2"><a></a></span></li>
		
		
		
		<li class="list-group-item" id="idInput">
		<label>이름&nbsp;</label><input type="text" class="form-control"  id="name_textbox" name="name" required>
		</li>
		
		
	
		
		<li class="list-group-item" id="idInput">
		<label>주민번호 앞 6자리 + 뒤 1자리</label><input type="text" class="form-control" id="birth_textbox" name="birth" maxlength="7" required>
		</li>
		<li class="list-group-item"><span id="resultBirth"><a></a></span></li>
			
		
		
		
		<li class="list-group-item" id="idInput">
		<label>전화번호</label><input type="text" class="form-control" placeholder="ex)01012345678" id="phone_textbox" name="phone" maxlength="11" required>
		<input type="button" class="btn btn-primary" value="인증번호 전송" id="btnPhoneChk" onclick="#">
		</li>
		<li class="list-group-item"><span id="resultPhone"><a>하이픈(-) 제외</a></span></li>
		
		
		
		<li class="list-group-item" id="idInput">
		<label>인증번호</label><input type="text" class="form-control" id="phoneCheck_textbox" name="phoneCheckNum" maxlength="6" required>
		</li>
		
		
		
		<li class="list-group-item" id="idInput">
		<label>주소</label><input type="text" class="form-control" id="detailAddress_textbox" placeholder="상세주소" name="address" autocomplete="name" required>
		<input type="button" class="btn btn-primary" value="우편번호 찾기" id="zipSearch_textbox" onclick="kakaopost()">
		</li>
		
		
		
		<li class="list-group-item"><div id="recaptcha_render" ></div></li>
		<li class="list-group-item"><input type="submit" class="btn btn-success" value="가입완료" onclick="signUp()" id="btnSignUp" ></li>
				
			
				
	
	</ul>
	</div>
	</form>
</body>
<%@ include file="./footer.jsp" %>

<script type="text/javascript">
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
	var chk = document.getElementById(chkVal).value;
	if(chk == 1){
		location.href = "./signUp.do";	
	}
	else{
		console.log(chkVal)
		swal("에러","작성한 정보를 다시 확인하세요");
		return false;
	}
	
}

function kakaopost() {
    new daum.Postcode({
        oncomplete: function(data) {
           document.querySelector("#detailAddress_textbox").value ='('+ data.zonecode + ')' + data.address
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
			if(msg.isc=="성공"){
				swal("중복된 아이디입니다");
				$("#chkval").val(0);
				$("#id_textbox").focus();
				
			}else{
				swal("사용 가능한 아이디 입니다");
				$("#chkval").val(1);
				$("#pw_textbox").focus();
			}
		},
		error :function(){
			swal("회원가입 실패","에러가 발생하였습니다")
		}
		
	})
	
}


$(document).ready(function(){
	$("#id_textbox").keyup(function(){
		var idVal = $(this).val();
		console.log(idVal);
		
		if(idVal.length <6 || idVal.length >20){
			$("#resultId").css("color","red");
			$("#resultId").html("아이디의 길이는 5~20 입니다.");
		}else if(idVal.indexOf(" ") != -1){
			$("#resultId").css("color","red");
			$("#resultId").html("공백이 포함된 아이디는 사용할 수 업습니다.");
			$("#chkval").val(0);
		}else{
			$("#resultId").html("")
			$("#chkval").val(1);
		}
	});
});


$(document).ready(function(){
	$("#pw_textbox").keyup(function(){
		var pwVal = $(this).val();
		var reg = "^(?=.*[a-zA-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$";
		console.log(pwVal);
		if(pwVal.match(reg)){	
			$("#resultPw").html("")
			$("#chkval").val(1);
		}else{
			$("#resultPw").css("color","red");
			$("#resultPw").html("최소 8 자 및 최대 20 자, 하나 이상의 대문자, 하나의 소문자, 하나의 숫자 및 하나의 특수 문자 정규식");
			$("#chkval").val(0);	
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
			$("#chkval").val(1);
		}else{
			$("#resultPw2").css("color","red");
			$("#resultPw2").html("비밀번호를 다시 확인해주세요");
			$("#chkval").val(0);
		}
	});
});
$(document).ready(function(){
	$("#phone_textbox").keyup(function(){
		var phoneVal = $(this).val();
		
		var reg = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})";
		if(phoneVal.match(reg)){
			$("#resultPhone").html("")
			$("#chkval").val(1);
		}else{
			$("#resultPhone").css("color","red");
			$("#resultPhone").html("핸드폰 번호를 다시 확인해주세요");
			$("#chkval").val(0);
		}
	});
});

// /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/


$(document).ready(function(){
	$("#birth_textbox").keyup(function(){
		var birthVal = $(this).val();
		
		//주민번호 정규화 다시
// 		var reg = "^d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|[3][01])[1-4]";
		var reg = "^[0-9]{7,7}";
		if(birthVal.match(reg)){
			$("#resultBirth").html("")
			$("#chkval").val(1);
		}else{
			console.log(birthVal.match(reg))
			$("#resultBirth").css("color","red");
			$("#resultBirth").html("주민번호를 다시 확인해주세요");
			$("#chkval").val(0);
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
			  swal("회원가입 성공","로그인페이지로 이동합니다");
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




</script>
</html>
