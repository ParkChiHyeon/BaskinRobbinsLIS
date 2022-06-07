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

<script type="text/javascript">
 window.onload=function(){
	var kind = '<c:out value="${kind}"/>'
		console.log(kind)
	if(kind=="notice"){
		$("#lnb4_1_a").css("color","#E2427F");
		$("#lnb4_1_a").css("background","#fff url(./include/image/jungang/common/bg_lnbSubOn.png) 170px 50% no-repeat");
	}else if(kind=="faq"){
		$("#lnb4_2_a").css("color","#E2427F");
		$("#lnb4_2_a").css("background","#fff url(./include/image/jungang/common/bg_lnbSubOn.png) 170px 50% no-repeat");
	}else{
		$("#lnb4_3_a").css("color","#E2427F");
		$("#lnb4_3_a").css("background","#fff url(./include/image/jungang/common/bg_lnbSubOn.png) 170px 50% no-repeat");
	}
 }
 
</script>
<!-- https://datatables.net/examples/styling/ 
해당 페이지에 들어가면 부트스트랩처럼 class로 테이블 디자인 설정 가능 -->
<div id="middle" >
		<div id="contbody">
		
		<!-- 왼쪽 메뉴바 -->
			<div id="contleft" class="contleft">
				<h2>정보마당</h2>
				<div class="lnbBody" style="min-height: 550px;">
					<ul id="lnb" class="lnbul">
						<li id="lnb4_1"><a id="lnb4_1_a" href="./noticeboard.do">공지사항</a></li>
						<li id="lnb4_2"><a id="lnb4_2_a" href="./faqboard.do">FAQ</a></li>
						<li id="lnb4_3"><a id="lnb4_3_a">도서관일정</a></li>
					</ul>
					<div class="lnbBottom"></div>
				</div>
			</div>
			
			<!-- 테이블 -->
			<div id="contentcore" style="margin-bottom: 30px">
		<table id="noticeBoardTable" class="cell-border" style="float:right;"> 
			<thead>
				<tr>
					<td>
					<input type="checkbox" id="allCheck" onclick="checkAll(this.checked)" class="allCheckBox">
					</td>
					<td style="width: 50px">글번호</td>
					<td>제목</td>
					<c:if test="${kind=='notice'}">
					<td>등록일</td>
					</c:if>
				</tr>
			</thead>
				
			<tbody id="test-idkd">
				<c:forEach var="vo" items="${lists}"  varStatus="vs">
					<c:set var="i" value="${i+1}"/>
				<tr>
					<c:if test="${kind == 'faq'}">
					<td><input type="checkbox" name="ch" class="ch" value="${vo.faq_seq}"></td>
						<td>${i}</td>
					</c:if>
					<c:if test="${kind=='notice'}">
						<td><input type="checkbox" name="ch" class="ch" value="${vo.notice_seq}"></td>
					<td>${vo.notice_seq}</td>
					</c:if>
					<td><a href="#">${vo.title}</a></td>
					<c:if test="${kind=='notice'}">
					<td>${vo.regdate}</td>
					</c:if>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<c:if test="${kind == 'notice' }">
				<button class="btn btn-primary" onclick="javascript:location.href='./modifynotice.do'">공지작성</button>
				<input class="btn btn-info btn-primary" type="submit" value="다중삭제">
			</c:if>
		
			<c:if test="${kind == 'faq' }">
				<button class="btn btn-primary" onclick="javascript:location.href='./modifynotice.do'">faq작성</button>
				<input class="btn btn-info btn-primary" type="submit" value="다중삭제">
			</c:if>
			<button class="btn btn-info" onclick="javascript:location.href='./home.do'"  style="float:right;">돌아가기</button>
		</div>
	
	</div>
</div>
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
        responsive: true,
        lengthChange: true, // 표시 건수기능 숨기기
        searching: true, // 검색 기능 숨기기
        ordering: true, // 정렬 기능 숨기기
        info: true, // 정보 표시 숨기기
        paging:true, // 페이징 기능 숨기기
        order: [], //초기표기시 정렬, 만약 정렬을 안하겠다 => order: []
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