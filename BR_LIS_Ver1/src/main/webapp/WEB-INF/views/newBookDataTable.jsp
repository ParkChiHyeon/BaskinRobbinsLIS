<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>woowang Library</title>
<%@ include file="/WEB-INF/views/header.jsp" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js"></script>
<!-- <script type="text/javascript" src="./BR_js/regularPurchaseDataTable.js"></script> -->


</head>
<body>
<div class="container">
<h2>신규도서 조회</h2>
	<form action="./newBookList.do">
		<table id="dataTable" class="cell-border"> <!-- https://datatables.net/examples/styling/ -->
			<thead>
				<tr>
					<th>제목</th>
					<th>출판사</th>
					<th>지은이</th>
					<th>보유량</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${newBookList}" var="newBookList" varStatus="vs">
					<tr>
						<td>${newBookList.title}</td>
						<td>${newBookList.publisher}</td>
						<td>${newBookList.author}</td>
						<td>${newBookList.total_count}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
</div>

<script type="text/javascript">
$(document).ready( function () {
    $('#dataTable').DataTable({
		//https://datatables.net/reference/option/language
		// DataTable은 기본적으로 영어로 표시되기 때문에 별도로 language를 통해서 변경해줘야 함
    	"language": { 
            "emptyTable": "데이터가 없어요.",
            "lengthMenu": "페이지당 _MENU_ 개씩 보기",
//             "info": "현재 _START_ - _END_ / _TOTAL_건",
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
        info: false, // 정보 표시 숨기기
        paging:true, // 페이징 기능 숨기기
//         order: [ [ 3, "asc" ], [ 1, "desc"] ], //초기표기시 정렬, 만약 정렬을 안하겠다 => order: []
        order: [ [ 1, "desc" ] ], //초기표기시 정렬, 만약 정렬을 안하겠다 => order: []
//      columnDefs: [{ targets: 1, width: 100 }] //열의 넓이 조절 
		lengthMenu: [ 5, 15, 30 ], //표시건수 
//      displayLength: 50, //기본표시건수 설정
        pagingType: "simple_numbers" // 페이징 타입 설정 : simple, simple_numbers, full_numbers 등
    
    });
} );
</script>



</body>
<%@ include file="./footer.jsp"%>
</html>
