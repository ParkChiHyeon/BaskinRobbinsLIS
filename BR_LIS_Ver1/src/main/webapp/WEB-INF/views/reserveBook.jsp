<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>도서 예약하기</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js" defer></script>
</head>
<%@ include file="./header.jsp"%>
<body>
<a href="./lendingBook2.do">test</a>

<div id="container_reserve">
		<div>제목 : 라만차의 비범한 이달고 돈키호테</div>
		<div>저자 : 미겔 데 세르반테스 지음;전기순 옮김</div>
		<div>출판사 : 웅진씽크빅</div>
		ISBN: <div id="isbn" >9788901208299</div>
		BOOK_SERIAL: <div id="book_serial">BKSR220912</div>
		<input type="button" value="예약하기" onclick="testResrve()">
</div>

<h1>예약선택한 도서</h1>
${chkBook}
<table class="table table-bordered">
			<thead>
				<tr>
					<th>ISBN</th>
					<th>도서코드</th>
					<th>도서제목</th>
					<th>저자</th>
					<th>예약</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="hidden" id="isbn" value=" ${po.isbn}">${po.isbn}</td>
					<td><input type="hidden" id="book_serial" value=" ${po.book_serial}">${po.book_serial}</td>
					<td>${po.title}</td>
					<td>${po.author}</td>
					<td><input class="checkReserve"type="button" value="예약하기"></td>
				</tr>
			</tbody>
		</table>


<h1>예약가능도서 조회</h1>
<div class="container">
		<form action="./checkReserve.do" id="oneBook" method="post">
			<table  id="dataTable" class="cell-border">
				<thead>
					<tr>
						<th>ISBN</th>
						<th>도서코드</th>
						<th>도서제목</th>
						<th>저자</th>
						<th>반납예정일</th>
						<th>예약</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="po" items="${poBook}" varStatus="vs">
					<tr>
						<td><input type="hidden" id="isbn" name="isbn" value=" ${po.isbn}">${po.isbn}</td>
						<td><input type="hidden" id="book_serial" name="book_serial" value=" ${po.book_serial}">${po.book_serial}</td>
						<td>${po.title}</td>
						<td>${po.author}</td>
						<td>${po.end_date}</td>
<!-- 						<td><button class="btn btn-info"type="button"  onclick="checkReserve()">예약하기</button> </td> -->
						<td><input class="checkReserve"type="button" onclick="checkReserve()"value="선택"></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
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
        order: [ [ 4, "desc"] ], //초기표기시 정렬, 만약 정렬을 안하겠다 => order: []
//      columnDefs: [{ targets: 1, width: 100 }] //열의 넓이 조절 
//		lengthMenu: [ 10, 20, 30, 40, 50 ], //표시건수 
//      displayLength: 50, //기본표시건수 설정
        pagingType: "simple_numbers" // 페이징 타입 설정 : simple, simple_numbers, full_numbers 등
    
    });
} );



$(".checkReserve").click(function(){
	var str =""
	var tdArr =  new Array();
	var checkReserve =$(this);
	
	var frm = document.getElementById("s")
	
// 	 frm.action = "./checkReserve.do"
	var tr = checkReserve.parent().parent();
	var td = tr.children();
	
	console.log("클릭한 버튼의 값 : " +tr.text());
	
	var isbn =td.eq(0).text();
	var book=td.eq(1).text();
	
	
	console.log(isbn,book);
	
	td.each(function(i){
		tdArr.push(td.eq(i).text());
	});
	console.log(tdArr);
	
	$("#oneBook").attr("action", "./checkReserve.do"); 
	$("#oneBook").attr("isbn",isbn); 
	$("#oneBook").attr("book",book); 
	$("#oneBook").submit();
// 	$.ajax({
// 		url:"./checkReserve.do",
// 		data:{"isbn":isbn,"book_serial":book},
// 		type:"post",
// 		async : true,
// 		success : function(data){
// 			frm.submit();
// 			console.log("성공");
// 			location.href="./checkReserve.do";
// 		}
// 	});
	
})
	


</script>
</body>
<%@include file="./footer.jsp"%>
</html>