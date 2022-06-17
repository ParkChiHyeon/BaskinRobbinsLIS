<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경 페이지</title>
<style type="text/css">
div #eye {
    position: relative;
}

div#eye i{
	position :absolute;
    left: 90%;
    top : 45px;
    color: black;
}


#pw_textbox{
	width: 300px;
	height: 40px;	
}

#pwChk_textbox{
	width: 300px;
	height: 40px;	
}

#ulForm{
	margin : 40px auto; display: table; border: 2px solid;
}


</style>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="https://kit.fontawesome.com/37d6185271.js" crossorigin="anonymous"></script>
</head>    
<!--         <link rel="icon" href="images/favicon.png"/> -->
 <link href="./khu_css/style.css" rel="stylesheet">
<%@ include file="./header.jsp" %>

<body class="body">    
	<input type="text" value="0" id="pwChkVal">
	<input type="text" value="0" id="pwChkVal2" >
			<form action="./memberInfoUpdatePw.do" method="get" id="khu_updateInfoForm"  onsubmit="return frmsubmit()">
	<div class="container">
	<input type="text" class="form-control" name="member_id" value="${mVo.member_id }" maxlength="8" hidden="true">
	<input type="text" class="form-control"  name="phone" value="${mVo.phone}" maxlength="8" id="phone_textbox" hidden="true">
	
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
		<label>비밀번호 확인</label><input type="password" class="form-control"  id="pwChk_textbox"  placeholder="PASSWORD CHK" required>
		<i class="fa-solid fa-eye fa-lg"></i> 
		</div>
		</li>
		<li class="list-group-item"><span id="resultPw2"><a></a></span></li>
		<li class="list-group-item"><input type="submit" class="btn btn-outline-primary" value="수정완료"  style="width: 100%" onclick="updatePw()"></li>
          </ul>
          </div>
		</form>
		
      		
</body>
<%@ include file="./footer.jsp" %>
<script type="text/javascript">


function updatePw() {
	var chk2 = document.getElementById("pwChkVal");
	var chk3 = document.getElementById("pwChkVal2");
	if(chk2.value == 1 && chk3.value == 1){
		location.href = "./memberInfoUpdatePw.do";	
	}
	else{
		swal("에러","작성한 정보를 다시 확인하세요");
		return false;
	}
	
}

$(document).ready(function(){
	$("#pw_textbox").keyup(function(){
		var pwVal = $(this).val();
		var reg = "^(?=.*[a-zA-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$";
		console.log(pwVal);
		if(pwVal.match(reg)){	
			$("#resultPw").html("")
			$("#pwChkVal").val(1);
		}else{
			$("#resultPw").css("color","red");
			$("#resultPw").html("특/영 대소문/숫자 혼합 8~20자리");
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
			$("#resultPw2").html("")
			$("#pwChkVal2").val(1);
		}else{
			$("#resultPw2").css("color","red");
			$("#resultPw2").html("비밀번호를 다시 확인해주세요");
			$("#pwChkVal2").val(0);

		}
	});
});


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
