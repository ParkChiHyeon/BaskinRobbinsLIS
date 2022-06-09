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
<script type="text/javascript" src="./noticeboard.js"></script>
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
						<li id="lnb4_1"><a id="lnb4_1_a" href="./noticeboard.do?kind=notice">공지사항</a></li>
						<li id="lnb4_2"><a id="lnb4_2_a" href="./faqboard.do?kind=faq">FAQ</a></li>
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

										
					<c:if test="${kind=='faq'}">
					<td><a onclick="location.href='./detailfaq.do?seq='+${vo.faq_seq}">${vo.title}</a></td>
					</c:if>

					
					<c:if test="${kind=='notice'}">
					<td><a onclick="location.href='./detailnotice.do?seq='+${vo.notice_seq}">${vo.title}</a></td>
					</c:if>
					
					
					
					<c:if test="${kind=='notice'}">
					<td>${vo.regdate}</td>
					</c:if>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<c:if test="${kind == 'notice' }">
				<button class="btn btn-primary" onclick="javascript:location.href='./editor.do?kind=notice'">공지작성</button>
				<input class="btn btn-info btn-primary" type="submit" value="다중삭제">
			</c:if>
		
			<c:if test="${kind =='faq'}">
				<button class="btn btn-primary" onclick="javascript:location.href='./editor.do?kind=faq'">FAQ작성</button>
				<input class="btn btn-info btn-primary" type="submit" value="다중삭제">
			</c:if>
			<button class="btn btn-success" onclick="javascript:location.href='./home.do'"  style="float:right;">HOME</button>
		</div>
	
	</div>
</div>
	</div>	

</body>
<%@include file="./footer.jsp" %>
</html>