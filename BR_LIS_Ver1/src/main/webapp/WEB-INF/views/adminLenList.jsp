<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>관리자_예약목록</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js" defer></script>
</head>
<%@ include file="./header.jsp"%>
<body>
<div class="container">
	<br>
	<input class="btn btn-default" type="button" onclick="location.href='./lendMainpage.do'" value="뒤로가기">
	<hr>
	<br>
	<h1>예약목록전체조회(관리자)</h1>
	<br><br>
</div>

<div class="container">	
	<form action="./confrimReserve.do">
		<table  id="dataTable" class="cell-border">
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
					<td><input type="hidden" id="lending_seq" value=" ${lVo.lending_seq}"> ${lVo.lending_seq}</td>
					<td><input type="hidden" id="book_serial" value=" ${lVo.book_serial}">${lVo.book_serial}</td>
					<td><input type="hidden" id="member_code" value=" ${lVo.member_code}">${lVo.member_code}</td>
					<td>${lVo.isbn}</td>
					<td>${lVo.title}</td>
					<td>${lVo.publisher}</td>
<!-- 					<td><button class="btn btn-warning"type="button" onclick="frmReserve()">대출신청</button> </td> -->
					<td><input class="frmReserve"type="button" value="대출신청"></td>
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
        order: [ [ 3, "asc" ], [ 1, "desc"] ], //초기표기시 정렬, 만약 정렬을 안하겠다 => order: []
//      columnDefs: [{ targets: 1, width: 100 }] //열의 넓이 조절 
//		lengthMenu: [ 10, 20, 30, 40, 50 ], //표시건수 
//      displayLength: 50, //기본표시건수 설정
        pagingType: "simple_numbers" // 페이징 타입 설정 : simple, simple_numbers, full_numbers 등
    
    });
} );
$(".frmReserve").click(function(){
	var str =""
	var tdArr =  new Array();
	var frmReserve =$(this);
	console.log(frmReserve);
	var tr = frmReserve.parent().parent();
	var td = tr.children();
	console.log("클릭한 버튼의 값 : " +tr.text());
	
	var lending_seq =td.eq(0).text();
	var book_serial=td.eq(1).text();
	var member_code=td.eq(2).text();
	
	console.log(lending_seq,book_serial,member_code);
// function frmReserve(){
// 	var lending_seq = document.getElementById("lending_seq").value;
// 	var book_serial = document.getElementById("book_serial").value;
// 	var member_code = document.getElementById("member_code").value;
// 	console.log(lending_seq,book_serial,member_code);
	
	$.ajax({
		url : "./confrimReserve.do?",
		data : {
			"seq" : lending_seq,
			"code" : book_serial,
			"member" : member_code
		},
		type : "get",
		async : true,
		success : function(data){
			console.log("성공");
			console.log(data);
			if(data == "true"){
				alert("대출이 되었습니다");
				location.href="./adminLenList.do";
			}else{
			alert("가능 대여 권수가 없습니다. \n 반납후 이용하세요");
			}
		},
		error:function(){
			alert("에러입니다");
// 			alert("가능 대여 권수가 없습니다. \n 반납후 이용하세요");
		}
		
	});

})
</script>

</body>
<%@include file="./footer.jsp"%>
</html>