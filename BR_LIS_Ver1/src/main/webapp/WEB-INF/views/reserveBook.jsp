<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>대출신청하기</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js" defer></script>
</head>
<%@ include file="./header.jsp"%>
 <script type="text/javascript">
//   		function cancel(){
//   			console.log("회원 예약 취소하기 ");
//   			console.log(val);
  		
//   			var i = document.getElementById("reserveListVal").innerText;
//   			var seq = document.getElementById("lending_seq").innerText;
//   			var serial = document.getElementById("book_serial").innerText;
//   			console.log(i);
//   			console.log(seq);
//   			console.log(serial);
  			
//   			if(seq !=""){
  			
//   			
  			
//   			}
  			
//   		}
  		
  		
  		function testResrve() {
			console.log("예약테스트");
			var n = document.getElementById("isbn").innerText;
			console.log(n);
			
			
		}
  		
  </script>
  

<body>
	<div id="container_reserve">
		<div>제목 : 라만차의 비범한 이달고 돈키호테</div>
		<div>저자 : 미겔 데 세르반테스 지음;전기순 옮김</div>
		<div>출판사 : 웅진씽크빅</div>
		ISBN: <div id="isbn" >9788901208299</div>
		<input type="button" value="예약하기" onclick="testResrve()">
	</div>
	
	
		<h1>회원예약목록조회(회원)</h1>
<div id="container">
		<form action="./reserveBook.do" >
			<table  id="dataTable" class="cell-border">
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
						<td id="book_serial"><input type="hidden" name="book_serial" value=" ${a.BOOK_SERIAL}"> ${a.BOOK_SERIAL}</td>
						<td id="lending_seq"><input type="hidden" name="lending_seq" value="${a.LENDING_SEQ}">${a.LENDING_SEQ}</td>
						<td>${a.TITLE}</td>
						<td>${a.PUBLISHER}</td>
						<td>${a.AUTHOR}</td>
						<td>${a.reseveDate}</td>
					</tr>
		<%-- 			</c:forEach> --%>
				</tbody>
			</table>
<!-- 			<input type="button" class="btn btn-warning" value="예약취소" onclick="cancel()"> -->
<!-- 	<button class="w-btn-outline w-btn-gray-outline" type="submit" formaction="./cancelReseve.do" formmethod="get" formtarget="_self">예약취소</button> -->
		</form>	
</div>	
<script type="text/javascript">
$(document).ready(function () {
    $('#dataTable').DataTable({
    	//https://datatables.net/reference/option/language
		// DataTable은 기본적으로 영어로 표시되기 때문에 별도로 language를 통해서 변경해줘야 함
    		"language": {
            "emptyTable": "데이터가 없어요.",
            "lengthMenu": "페이지당 _MENU_ 개씩 보기",
            "info": "현재 _START_ - _END_ / _TOTAL_건",
            "infoEmpty": "데이터 없음",
            "infoFiltered": "( _MAX_건의 데이터에서 필터링됨 )",
            "search": "검색: ",
            "zeroRecords": "일치하는 데이터가 없어요.",
            "loadingRecords": "로딩중...",
            "processing":     "잠시만 기다려 주세요...",
            "paginate": {
                "next": "다음",
                "previous": "이전"
            }
        },
        
        lengthChange: true, // 표시 건수기능 숨기기
        searching: true, // 검색 기능 숨기기
        ordering: true, // 정렬 기능 숨기기
        info: true, // 정보 표시 숨기기
        paging:true, // 페이징 기능 숨기기
        order: [ [ 3, "asc" ], [ 1, "desc"] ], //초기표기시 정렬, 만약 정렬을 안하겠다 => order: []
//      columnDefs: [{ targets: 1, width: 100 }] //열의 넓이 조절 
//		lengthMenu: [ 10, 20, 30, 40, 50 ], //표시건수 
//      displayLength: 50, //기본표시건수 설정
        pagingType: "simple_numbers" // 페이징 타입 설정 : simple, simple_numbers, full_numbers 등
    
    });
} );
</script>
</body>
<%@include file="./footer.jsp"%>
</html>