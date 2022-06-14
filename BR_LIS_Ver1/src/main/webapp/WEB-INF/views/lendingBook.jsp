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

<%@ include file="./header.jsp"%>
<body>

	
<h1><a href="./adminLenList.do">■ 예약자 대출신청</a></h1>
<hr>
<h1>■즉시대출 신청</h1>
<div class="naviandtitle"> 
	<h3>도서정보</h3>
</div>

<input id="book"  type="text" onchange="bookInfo()">
<div id="result2" hidden=""> </div>

<form action="">
	<div class="container">
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
				<tr>
					<td><input type="hidden" id="book_serial" value=" ${lBean.book_serial}">${lBean.book_serial}</td>
					<td>${lBean.isbn}</td>
					<td>${lBean.title}</td>
					<td>${lBean.author}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>	
<div class="naviandtitle"> 
<h3>회원정보</h3>
</div>
<h3>  ${lVo.member_id}</h3>
	<div class="container">	
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
        order: [ [ 5, "desc" ] ], //초기표기시 정렬, 만약 정렬을 안하겠다 => order: []
//      columnDefs: [{ targets: 1, width: 100 }] //열의 넓이 조절 
//		lengthMenu: [ 10, 20, 30, 40, 50 ], //표시건수 
//      displayLength: 50, //기본표시건수 설정
        pagingType: "simple_numbers" // 페이징 타입 설정 : simple, simple_numbers, full_numbers 등
    
    });
} );

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


function bookInfo(){
	  var name = document.getElementById("book").value;
	  document.getElementById("result2").innerText = name;
	  console.log(name);
	  $.ajax({
		 	 url : "./lendingBookAdmin.do",
			data : {
				"name" : name,
			},
			 type : "get",
			async : true,
			
			success : function(data){
// 				console.log(name);
// 				console.log("name");
// 			console.log("성공");
// 			}
	  });
	  }
	
</script>




</body>
<%@include file="./footer.jsp"%>
</html>