<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="UTF-8">
<title>대출현황조회</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js" defer></script>
<style type="text/css">


</style>
<%@ include file="./header.jsp"%>
<body>

	
<h2><a href="./adminLenList.do">■ 예약자 대출신청</a></h2>
<hr>
**해야할것 <p>
book_serial 조회시 결과값안나오면 alert ->예약중인 도서입니다  <p>
대출 신청 누르면 신청되고 에러 뜸 -> 대출화면 or 신청확인 화면으로  <p>
초기화버튼 안눌림.. <p>
<hr>
<h1>■즉시대출 신청</h1>
<form action="./lendingBookAdmin.do" method="post" >
	<h3>BOOK_SERIAL 입력하세요</h3>
	<input id="book" name="book_serial" type="text" >
	
	<h3>회원아이디를 입력하세요</h3>
	<input id="book" name="member_id" type="text" >
	<input type="submit" value="확인" onclick="a()">
</form>
<br>
<br>

<form action="./fastLending.do">
	<div class="container">
		<h3>도서정보</h3>
		<table  id="dataTable" class="table table-bordered">
			<thead>
				<tr>
					<th>도서코드</th>
					<th>ISBN</th>
					<th>도서제목</th>
					<th>저자</th>
				</tr>
			</thead>
			<tbody>
			
				<c:forEach var="lBean" items="${listBean}" varStatus="vs">
<%-- 				<c:if test="${lBean eq null}"> --%>
				
<%-- 				</c:if> --%>
				<tr id="s">
					<td><input type="hidden" id="book_serial" value=" ${lBean.book_serial}">${lBean.book_serial}</td>
					<td>${lBean.isbn}</td>
					<td>${lBean.title}</td>
					<td>${lBean.author}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
<!-- 	</div>	 -->

<!-- 	<div class="container">	 -->
	<h3>회원정보</h3>
		<table  id="dataTable" class="table table-bordered">
			<thead>
				<tr>
					<th>회원아이디</th>
					<th>회원코드</th>
					<th>이름</th>
					<th>핸드폰번호</th>
					<th>주소</th>
					<th>이메일</th>
					<th>대여가능권수</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${lVo.member_id}</td>
					<td><input type="hidden" id="member_code" value=" ${lVo.member_code}"> ${lVo.member_code}</td>
					<td>${lVo.name}</td>
					<td>${lVo.phone}</td>
					<td>${lVo.address}</td>
					<td>${lVo.email}</td>
					<td><input type="hidden" id="rental_count" value=" ${lVo.rental_count}" onload="rentalCount()">${lVo.rental_count}</td>
				</tr>
			</tbody>
		</table>
	</div>
	<c:if test="${lVo.rental_count ne 0}">
		<button class="btn btn-outline-secondary" type="button" onclick="fastLending()">대출 신청</button>
		<button class="btn btn-outline-secondary" type="button" onclick="con()">신청 확인</button>
	</c:if>
	<button class="btn btn-outline-secondary" type="button" onclick="location.href='./home.do'">돌아가기</button>
	<input class="btn btn-outline-secondary"  type="reset" value="초기화">
	<div id="result"></div>
</form>	


<script type="text/javascript">
function fastLending() {
	var member_code  = document.getElementById("member_code").value;
	var book_serial  = document.getElementById("book_serial").value;
	var rental_count  = document.getElementById("rental_count").value;
	console.log(member_code,book_serial);
	console.log(rental_count);
	$.ajax({
		url : "./fastLending.do",
		data : {
			"bookserial" : book_serial,
			"member" : member_code,
			"vo" : member_code,book_serial
		},
		type:"get",
		async:true,
		success : function(data){
			console.log("성공");
			console.log(data);
			if(data == "true"){
				alert("성공~~대출되었습니다");
				location.href="./lendingBookAdmin.do";
			}
		}
	});
}
function a() {
	var s= document.getElementById("s")
	if(s.value == null){
		alert("예약중인 도서입니다~~");
		console.log("x")
	}else{
		alert("출력해드렸습니다")
		console.log("o")
	}
	
} 

function rentalCount(){
	var rental =document.getElementById("rental_count").value; 
	document.getElementById("result").innerText = rental;
	console.log(rental);
	if(rental==0){
		$("#result").css("color","red");
		$("#result").html("대출 가능한 권수는 0권입니다.");
		alert("대출 가능한 권수는 0권입니다.");
	}
}

	
</script>

</body>
<%@include file="./footer.jsp"%>
</html>