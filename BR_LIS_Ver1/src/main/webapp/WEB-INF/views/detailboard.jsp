<%@page import="com.br.lis.vo.Notice_FAQBoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
<%@include file="./header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.css"/>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js"></script>
</head>
<style>
a:link{
	color: tomato;

}

</style>
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
<body>
<div id="middle" >
		<div id="contbody">
		
		<!-- 왼쪽 메뉴바 -->
			<div id="contleft" class="contleft">
				<h2>정보마당</h2>
				<div class="lnbBody" style="min-height: 550px;">
					<ul id="lnb" class="lnbul">
						 <li id="lnb4_1"><a id="lnb4_1_a" href="./viewAllBoard.do?kind=notice">공지사항</a></li>
		                 <li id="lnb4_2"><a id="lnb4_2_a" href="./viewAllBoard.do?kind=faq">FAQ</a></li>
		                 <li id="lnb4_3"><a id="lnb4_3_a" href="./viewAllBoard.do?kind=calendar">도서관일정</a></li>
					</ul>
					<div class="lnbBottom"></div>
				</div>
			</div>
			
		<!-- 1.공지사항& 2.FAQ 3.도서관일정 상세보기 -->
		<div id="contentcore">
				
				<!-- 1.공지사항 상세보기 -->
				<c:if test="${kind=='notice'}">
					<div class="naviandtitle">
						<h3>공지사항</h3>
						<div class="navi"><a href="./home.do"><img src="./include/image/global/common/icon_home.gif" alt="홈"></a> 정보마당<span class="mobileHide"> &gt; <strong>공지사항</strong></span></div>
					</div>
					
					<div class="contents" id="contents">
	
						<!--Real Contents Start-->
						<div class="boardWrap">
							<table class="bd00view">
								<tbody>
									<tr>
										<th class="bd01th" scope="row">제목</th>
										<td colspan="3" class="bd01td">${dto.title}</td>
									</tr>
									
									<tr>
										<th class="bd01th" scope="row">등록일</th>
										<td class="bd01td">${fn:substring(dto.regdate,0,10)}</td>
										<th class="bd01th">작성자</th>
										<td class="bd01td">${dto.admin_id}</td>
									</tr>
									
									<tr>
										<th class="bd01th" scope="row">첨부파일</th>
										<td colspan="3" class="bd01td">
											<a href="./downloadFile.do?${dto.file_path}">${fn:substring(dto.file_path,9,fn:indexOf(dto.file_path,'&'))}<img src="./include/image/jungang/board/btn_down.gif" alt="첨부파일 다운로드"></a>
										</td>
									</tr>
									
									<tr>
										<td colspan="4" class="bd01tdC"><p><br></p><p style="">${dto.content}</p><p><br></p></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="btn_farm2 mt20">
							<span class="themeBtn sizeMiddle"><a href="./viewAllBoard.do?kind=notice" id="listBtn" >목록</a></span>
							<c:if test="${kind=='notice' && admin.admin_id!=null}">
					 		<button class="btn btn-primary" onclick="javascript:location.href='./modifynotice.do?kind=notice&seq='+${dto.notice_seq}">수정</button>
	            			<button class="btn btn-info btn-primary" onclick="multiDeleteNotice(${dto.notice_seq})" style="width: 60px;">삭제</button>
	            			</c:if>
						</div>
	
					</div><!-- contents -->
			</c:if><!-- 공지사항상세보기 끝 -->
			
<script type="text/javascript">
function multiDeleteNotice(seq){
	 location.href="multiDelNotice.do?chkBox="+seq;
}


function modifyNotice(seq){
	 location.href="modifynotice.do?seq="+seq;
}
</script>
			
			<!-- faq상세보기 상단 -->
			<c:if test="${kind=='faq'}">
			<div class="naviandtitle">
					<h3>자주 묻는 질문</h3>
					<div class="navi"><a href="./home.do"><img src="./include/image/global/common/icon_home.gif" alt="홈"></a> 정보마당<span class="mobileHide"> &gt; <strong>FAQ</strong></span></div>
			</div>
				
<!-- 			<div class="contents" id="contents"> -->
<!-- 			<div class="ttabWrap"> -->
<!-- 				<ul id="ttab3" class="tabType5"> -->
<!-- 					<li class="ttabLast"></li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
			
			<!--faq상세보기 테이블-->
					<div class="boardWrap">
						<table class="bd00view">
							<caption> 자주묻는 질문 내용 상세보기 : 제목, 내용의 상세보기표</caption>
							<colgroup>
							<col style="width:15%;">
							<col>
							<col style="width:15%;">
							<col style="width:35%;">
							</colgroup>
							
							<tbody>
								<tr>
									<th class="bd01th" scope="row">제목</th>
									<td colspan="3" class="bd01td">${vo.title}</td>
								</tr>
								<tr>
									<th class="bd01th" scope="row">내용</th>
									<td colspan="3" class="bd01tdC"><p><br></p><p style="">${vo.content}</p><p><br></p></td>
								</tr>
							</tbody>
						</table>
					</div>
					
					<div class="btn_farm2 mt20">
						<span class="themeBtn sizeMiddle"><a href="./viewAllBoard.do?kind=faq" id="listBtn">목록</a></span>
						<c:if test="${kind=='faq' && admin.admin_id!=null}">
						<button class="btn btn-primary" onclick="javascript:location.href='./modifyFAQ.do?kind=faq&seq='+${vo.faq_seq}">수정</button>
            			<input class="btn btn-info btn-primary" onclick="multiDeleteFAQ(${vo.faq_seq})" style="width: 60px;" value="삭제">
            			</c:if>
					</div>
			</c:if> <!-- FAQ 상세보기 끝 -->

<script type="text/javascript">
function multiDeleteFAQ(seq){
	 location.href="multiDelFAQ.do?chkBox="+seq;
}





</script>

			
			<!-- 도서관 일정 상세보기 -->
			<c:if test="${kind=='calendar'}">
				<div class="naviandtitle">
					<h3>도서관 일정</h3>
					<div class="navi"><a href="./home.do"><img src="./include/image/global/common/icon_home.gif" alt="홈"></a> 정보마당<span class="mobileHide"> &gt; <strong>도서관일정</strong></span></div>
				</div>
				
<!-- 			<div class="contents" id="contents"> -->
				<div class="ttabWrap">
					<ul id="ttab3" class="tabType5">
						<li class="ttabLast"></li>
					</ul>
				</div>
				
					<div class="boardWrap">
						<table class="bd00view">
							<tbody>
								<tr>
									<th class="bd01th" scope="row">제목</th>
									<td colspan="3" class="bd01td">${vo.title}</td>
								</tr>
								<tr>
									<th class="bd01th" scope="row">일정 시작일</th>
									<td class="bd01td">${fn:substring(vo.start_date,0,10)}</td>
									<th class="bd01th">일정 종료일</th>
									<td class="bd01td">${fn:substring(vo.end_date,0,10)}</td>
								</tr>
								<tr>
									<th class="bd01th" scope="row">내용</th>
									<td colspan="3" class="bd01tdC"><p><br></p><p style="">${vo.content}</p><p><br></p></td>
								</tr>
							</tbody>
						</table>
					</div> 
					
					<div class="btn_farm2 mt20">
						<span class="themeBtn sizeMiddle"><a href="./viewAllBoard.do?kind=calendar" id="listBtn">목록</a></span>
						<c:if test="${kind=='calendar' && admin.admin_id!=null}">
						<button class="btn btn-primary" onclick="javascript:location.href='./modifyCalendar.do?kind=calendar&seq='+${vo.calendar_seq}">수정</button>
						<button class="btn btn-primary" onclick="javascript:location.href='./multiDelCalendar.do?kind=calendar&seq='+${vo.calendar_seq}">삭제</button>
						</c:if>
					</div>
			</c:if><!-- 일정상세보기 -->
			
		</div> <!-- divcontcore 1.공지사항& 2.FAQ 3.도서관일정 상세보기 끝 -->
		</div> <!-- divcontbody chkBox-->
	</div>
</body>
<script type="text/javascript">
function multiDeleteNotice(seq){
	 location.href="multiDelNotice.do?chkBox="+seq;
}
</script>
<%@include file="./footer.jsp" %>
</html>