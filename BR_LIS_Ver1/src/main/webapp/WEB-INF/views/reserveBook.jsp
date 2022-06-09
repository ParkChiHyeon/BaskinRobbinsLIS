<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대출신청하기</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
 <script type="text/javascript">
  		function cancel(){
  			console.log("회원 예약 취소하기 ");
//   			console.log(val);
  		
  			var i = document.getElementById("reserveListVal").innerText;
  			var j = document.getElementById("lending_seq").innerText;
  			console.log(i);
  			console.log(j);
  			
  			
  			$.ajax({
  				url:"./cancelReseve.do",
  				type:"post",
  				data:{"j":lending_seq},
  				success:function(msg){
  					console.log(msg);
  					console.log(msg.isc);
  					
//   					if(msg==""){
  						
//   					}
  					alert("취소 아작스 시작");
  				},
  				error:function(){
  					alert("취소 에러");
  				}
  			});
		
  			
  		}
  
  </script>
<%@ include file="./header.jsp"%>
<body>
		<div>회원번호 : </div>
		<div>회원이름 : </div>
		<div>ISBN : </div>
		<div>예약번호 ->대출번호 : </div>
		<div>도서코드 : </div>
		<div>도서제목: </div>
		<div>연장여부: </div>
		<div>반납시상태: </div>
		<div>대출일: </div>
		<div>반납예정일:</div>
		<div>반납일:</div>
		<div>예약일:</div>

<div id="container">
<h1>회원예약목록조회(회원)</h1>
<form action="./reserveBook.do" >
	<table  class="table table-bordered">
		<thead>
			<tr id="reserveList">
				<th>회원코드</th>
				<th>ISBN</th>
				<th>도서코드</th>
				<th>예약번호</th>
				<th>도서제목</th>
				<th>출판사</th>
				<th>저자</th>
				<th>예약일</th>
			</tr>
		</thead>
		<tbody>
<%-- 			<c:forEach var="a" items="${a}" varStatus="vs"> --%>
			<tr id="reserveListVal">
				<td id="mem">${a.MEMBER_CODE}</td>
				<td>${a.ISBN}</td>
				<td>${a.BOOK_SERIAL}</td>
				<td id="lending_seq">${a.LENDING_SEQ}</td>
				<td>${a.TITLE}</td>
				<td>${a.PUBLISHER}</td>
				<td>${a.AUTHOR}</td>
				<td>${a.RESERVE_DATE}</td>
			</tr>
<%-- 			</c:forEach> --%>
		</tbody>
	</table>
	<input type="button" class="btn btn-warning" value="예약취소" onclick="cancel()">
</form>	
<br>
<br>
		
	<h1>예약목록전체조회(관리자)</h1>
	<input type="text" placeholder="회원검색">
	<input type="button" value="검색">
	<table  class="table table-bordered">
		<thead>
			<tr>
				<th>예약코드</th>
				<th>도서코드</th>
				<th>회원번호</th>
				<th>ISBN</th>
				<th>도서제목</th>
				<th>출판사</th>
				<th>대출신청</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="lVo" items="${reBook}" varStatus="vs">
			<tr>
				<td><a href="./reserveBook.do">${lVo.lending_seq}</a></td>
				<td><a href="./reserveBook.do">${lVo.book_serial}</a></td>
				<td>${lVo.member_code}</td>
				<td>${lVo.isbn}</td>
				<td>${lVo.title}</td>
				<td>${lVo.publisher}</td>
				<td><button class="btn btn-warning">신청</button> </td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>		
		
</body>
<%@include file="./footer.jsp"%>
</html>