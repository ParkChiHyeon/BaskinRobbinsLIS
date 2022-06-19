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

<div class="container">
<h2><a href="./adminLenList.do"> 예약자 대출신청</a></h2>
</div>
<hr>

<div class="container">
	<h2>즉시대출 신청</h2>
	
<!-- 	<form action="./lendingBookAdmin.do/ method="post"  class="form-inline"> -->
	<form action="./lendingBookAdmin.do" class="form-inline">
		<div class="form-group">
			<label>도서코드 :</label>
			<input id="book" name="book_serial" type="text" class="form-control">
		</div>
		
		<div class="form-group">
			<label>회원아이디 :</label>
		<input id="book" name="member_id" type="text" class="form-control">
		</div>
		<div class="form-group">
		<input type="submit" class="btn btn-default" value="확인" onclick="a()">
		</div>
	</form>
</div>
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
				<tr id="selectList">
				<c:forEach var="lBean" items="${listBean}" varStatus="vs">
					<td><input type="hidden" id="book_serial" value=" ${lBean.book_serial}">${lBean.book_serial}</td>
					<td >${lBean.isbn}</td>
					<td>${lBean.title}</td>
					<td>${lBean.author}</td>
				</c:forEach>
				</tr>
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
		<c:choose>
			<c:when test="${lVo.rental_count eq 0}">
				<p style="color: red; font-size: medium;" >대출이 불가능 합니다. 대출 가능 잔여권수가 없습니다</p>
			</c:when>
			<c:otherwise>
					<P style="color: blue; font-size: medium;"> 대여가능 권수는  ${lVo.rental_count}  권 입니다</P> <br>
			</c:otherwise>
		</c:choose><br>
		
	</div>
	
	<c:if test="${listBean eq null}">
				
  	</c:if>
	
	
	<div class="container">
	<c:if test="${lVo.rental_count ne 0}">
		<button class="btn btn-outline-secondary" type="button" onclick="fastLending()">대출 신청</button>
		<button class="btn btn-outline-secondary" type="button" onclick="location.href='./returnBookList.do'">신청 확인</button>
	</c:if>
	
	<button class="btn btn-outline-secondary" type="button" onclick="location.href='./home.do'">돌아가기</button>
	<input type="button" class="btn btn-default" value="초기화"  onclick="javascript:history.go(0)">
	<div id="result"></div>
	</div>
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
				alert("대출이 되었습니다");
				location.href="./lendMainpage.do";
			}
		}
	});
}

	$(document).ready(function() {
		 $("#btnReset").click(function() {
		 		$("form").each(function() {
					 this.reset();
				 });
			 });
		});
	
// function a() {
// 	var s= document.getElementById("selectList")
// 	if(s.value == null){
// 		alert("예약중인 도서입니다~~");
// 		console.log("x")
// 	}else{
// 		alert("출력해드렸습니다")
// 		console.log("o")
// 	}
	
// } 

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