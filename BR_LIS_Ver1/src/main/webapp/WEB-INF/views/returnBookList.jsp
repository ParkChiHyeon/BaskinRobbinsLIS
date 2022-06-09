<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>반납완료목록</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js"></script>
</head>
<%@ include file="./header.jsp"%>
<body>
<div class="container">
	<table id="dataTable" class="cell-border">
		<thead>
			<tr>
				<td>대출번호</td>
				<td>책제목</td>
				<td>책번호</td>
				<td>회원코드</td>
				<td>대출일</td>
				<td>반납예정일</td>
				<td>반납일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${lists}"  varStatus="vs">
			<tr>
				<td>${vo.lending_seq}</td>
				<td>${vo.title}</td>
				<td>${vo.book_serial}</td>
				<td>${vo.member_code}</td>
				<td>${vo.lending_date}</td>
				<td>${vo.end_date}</td>
				<td>${vo.back_date}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
<!-- 	<button onclick="createAjax()">AJAX</button> -->
</div>
<script type="text/javascript">
$(document).ready(function() {
    $('#dataTable').DataTable({
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
        
        lengthChange: true, 
        searching: true, 
        ordering: true, 
        info: true, 
        paging:true,
        order: [ [ 3, "asc" ], [ 1, "desc"] ], //초기표기시 정렬, 만약 정렬을 안하겠다 => order: []
//      columnDefs: [{ targets: 1, width: 100 }] //열의 넓이 조절 
		lengthMenu: [ 5, 10, 15, 50 ], //표시건수 
//      displayLength: 50, //기본표시건수 설정
        pagingType: "simple_numbers" // 페이징 타입 설정 : simple, simple_numbers, full_numbers 등
    
    });
} );

</script>
</body>
<%@include file="./footer.jsp"%>
</html>