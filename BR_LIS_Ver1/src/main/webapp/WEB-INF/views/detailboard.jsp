<%@page import="com.br.lis.vo.Notice_FAQBoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
						<li id="lnb4_1"><a id="lnb4_1_a" href="./noticeboard.do">공지사항</a></li>
						<li id="lnb4_2"><a id="lnb4_2_a" href="./faqboard.do">FAQ</a></li>
						<li id="lnb4_3"><a id="lnb4_3_a">도서관일정</a></li>
					</ul>
					<div class="lnbBottom"></div>
				</div>
			</div>
			
			<div id="contentcore">

				<div class="naviandtitle">
					<h3>공지사항</h3>
					<div class="navi"><a href="./home.do"><img src="/include/image/global/common/icon_home.gif" alt="홈"></a> 정보마당<span class="mobileHide"> &gt; <strong>공지사항</strong></span></div>
				</div>
				<div class="contents" id="contents">

			<div class="ttabWrap">
				<ul id="ttab3" class="tabType5">
					<li class="ttabLast"></li>
				</ul>
			</div>

					<!--Real Contents Start-->
					<div class="boardWrap">
						<table class="bd00view">
							<caption>게시물 내용 상세보기 : 제목, 등록일, 조회수, 첨부파일, 내용의 상세보기표</caption>
							<colgroup>
							<col style="width:15%;">
							<col>
							<col style="width:15%;">
							<col style="width:35%;">
							</colgroup>
							<tbody><tr>
								<th class="bd01th" scope="row">제목</th>
								<td colspan="3" class="bd01td">${dto.title}</td>
							</tr>
							<tr>
								<th class="bd01th" scope="row">등록일</th>
								<td class="bd01td">${dto.regdate}</td>
								<th class="bd01th">작성자</th>
								<td class="bd01td">${dto.admin_id}</td>
							</tr>
							
							<tr>
								<th class="bd01th" scope="row">첨부파일</th>
								<td colspan="3" class="bd01td">
									<a href="javascript:fnBbsFileDownload('5969');">${dto.file_path}<img src="./include/image/jungang/board/btn_down.gif" alt="첨부파일 다운로드"></a>
								</td>
							</tr>
							
							<tr>
								<td colspan="4" class="bd01tdC"><p><br></p><p style="">${dto.content}</p><p><br></p></td>
							</tr>
						</tbody></table>
					</div>
					<div class="btn_farm2 mt20">
						<!-- Text Button -->
						<span class="themeBtn sizeMiddle"><a href="./noticeboard.do" id="listBtn">목록</a></span>
						<!-- Text Button -->
					</div>



				</div>
			</div>
			
			
			
			
			
			
			
			<div class="naviandtitle">
					<h3>자주 묻는 질문</h3>
					<div class="navi"><a href="./home.do"><img src="/include/image/global/common/icon_home.gif" alt="홈"></a> 정보마당<span class="mobileHide"> &gt; <strong>FAQ</strong></span></div>
				</div>

				<div class="contents" id="contents">
					


			<div class="ttabWrap">
				<ul id="ttab3" class="tabType5">
					<li class="ttabLast"></li>
				</ul>
			</div>
			
			
			
			
			
			
			
			
			
			<!--Real Contents2 Start-->
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
								<td colspan="3" class="bd01td">${fvo.title}</td>
							</tr>
							<tr>
								<th class="bd01th" scope="row">내용</th>
								<td colspan="4" class="bd01tdC"><p><br></p><p style="">${fvo.content}</p><p><br></p></td>
							</tr>
						</tbody>
						</table>
					</div>
					<div class="btn_farm2 mt20">
						<!-- Text Button -->
						<span class="themeBtn sizeMiddle"><a href="./faqboard.do" id="listBtn">목록</a></span>
						<!-- Text Button -->
					</div>



				</div>
			</div>
			
			
<!-- 		<div id="contentcore" style="margin-bottom: 30px"> -->
<!-- 			<form action="#" class="form-horizontal"> -->
<%-- 			<input class="form-control" type="text" value="${dto.title}">  --%>
<%-- 			<input class="form-control" type="text" value="${dto.regdate}"> --%>
<%-- 			<a><input class="form-control" type="text" value="${dto.file_path}"></a> --%>
<!-- 			</form> -->
<!-- 			<div class="form-group"> -->
<%-- 			<textarea class="form-control" rows="30" id="comment">${dto.content}</textarea> --%>
<!-- 			</div> -->
<!-- 		<table class="bd00view"> -->
<!-- 			<tbody> -->
<!-- 				<tr> -->
<!-- 					<td>제목</td> -->
<%-- 					<td>${dto.title}</td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>등록일</td> -->
<%-- 					<td>${dto.regdate}</td> --%>
<!-- 					<td>작성자</td> -->
<%-- 					<td>${dto.admin_id}</td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>첨부파일</td> -->
<%-- 					<td><a>${dto.file_path}</a></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td> -->
<%-- 						${dto.content} --%>
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</tbody> -->
<!-- 		</table>	 -->
		
<!-- 	</div> -->
</div>
	</div>

<script type="text/javascript">

</script>
</body>
<%@include file="./footer.jsp" %>
</html>