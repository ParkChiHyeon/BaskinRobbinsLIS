<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="./header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.css"/>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js"></script>
</head>
<body>
<div class="container">
<!-- https://datatables.net/examples/styling/ 
해당 페이지에 들어가면 부트스트랩처럼 class로 테이블 디자인 설정 가능 -->
	<table id="noticeBoardTable" class="cell-border"> 
		<thead>
			<tr>
				<td style="width: 50px">글번호</td>
				<td>제목</td>
				<td>내용</td>
				<td>등록날짜</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${lists}"  varStatus="vs">
			<tr>
				<td>${vo.notice_seq}</td>
				<td>${vo.title}</td>
				<td>${vo.content}</td>
				<td>${vo.regdate}</td>
			</tr>
			</c:forEach>
		</tbody>
		</table>
	<button class="btn btn-info" onclick="javascript:location.href='./editor.do'">돌아가기</button>
</div>

<script type="text/javascript">
$(document).ready(function(){
    $("#noticeBoardTable").DataTable({
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
<%@include file="./footer.jsp" %>
</html>