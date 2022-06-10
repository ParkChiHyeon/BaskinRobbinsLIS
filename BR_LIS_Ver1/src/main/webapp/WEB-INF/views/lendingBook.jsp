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

  <style>
/*     .row.content {height: 1500px} */
    
    .sidenav {
      background-color: #f1f1f1;
/*       height: 100%; */
    }
    
    
  </style>

<%@ include file="./header.jsp"%>
<body>
<div class="container-fluid">
  <div class="row content">
    <div class="col-sm-3 sidenav">
			<h2>나의 도서관</h2>
			<ul class="nav nav-pills nav-stacked">
				<li class="side2m1 sel">	<a href="./adminLenList.do">예약목록전체조회(관리자)</a></li>
				<li class="side2m1 sel">	<a href="./reserveBook.do">예약조회(회원)</a></li>
				<li class="side2m1 sel">	<a href="./lendingBook.do">이전대출도서내역</a></li>
				<li class="side2m1 sel">	<a href="./lendingBook.do">예약내역</a></li>
				<li class="side2m3 "><a href="./index.php?g_page=mypage&amp;m_page=mypage03">대출신청</a></li>
				<li class="side2m4 "><a href="/dls_lu/member/index.php?act=joinAct&step=userJoin">기본정보</a></li>
			</ul>
		</div>		
	 </div>
	</div> 

${mb}
<h1>대출도서내역</h1>
<div class="container">		
		<table  id="dataTable" class="cell-border">
		<thead>
			<tr>
				<th>대출코드</th>
				<th>회원번호</th>
				<th>도서제목</th>
				<th>저자</th>
				<th>연장여부</th>
				<th>대출일</th>
				<th>반납예정일</th>
				<th>반납일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="lBean" items="${listBean}" varStatus="vs">
			<tr>
				<td>${lBean.lending_seq}</td>
				<td>${lBean.member_code}</td>
				<td>${lBean.title}</td>
				<td>${lBean.author}</td>
				<td>${lBean.lending_delay}</td>
				<td>${lBean.lending_date}</td>
				<td>${lBean.end_date}</td>
				<td>${lBean.back_date}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
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
        order: [ [ 5, "desc" ] ], //초기표기시 정렬, 만약 정렬을 안하겠다 => order: []
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