<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>woowang Library MyPage</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit" async defer></script>
<script src="https://kit.fontawesome.com/37d6185271.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js" defer></script>
<%@ include file="./header.jsp" %>









<!-- css넣어라 -->
<link rel="stylesheet" href="./khu_css/myPage.css"/>
<link rel="stylesheet" href="./hik_css/myPageHIK.css"/>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.css"/>





<!-- css끝 -->


<!-- js 넣어라 -->


<script type="text/javascript" src="./BR_js/myPageHIK.js"></script> 
<script type="text/javascript" src="./BR_js/myPageJSJ.js"></script> 








<!-- js 끝 -->




<body class="body">
		<!-- 마이페이지 시작 -->
		<div id="contbody">
		
		<!-- 메뉴탭 -->
		<div id="contleft" class="contleft">
			<h2>마이페이지</h2>
			<div class="lnbBody" style="min-height: 550px;">
				<ul id="lnb" class="lnbul">
				<!-- 각자 메뉴 -->
				<li id="lnb_1" class=""><a href="./memberInfoUpdate.do" >내 정보 수정</a></li> 
				<li id="lnb_2" class=""><a href="./membertt.do" >회원탈퇴 신청</a></li>
				<li id="lnb_3" class=""><a href="./memberInfoUpdate.do" >알림 수신 변경</a></li>
				<li id="lnb_4" class=""><a href="./lendingBookListUser.do" >도서대출 현황</a></li>
				<li id="lnb_5" class=""><a href="./lendingBookMem.do" >도서대출 이용 현황</a></li>
				<li id="lnb_6" class=""><a href="./reserveBook.do" >예약 현황</a></li>
				<li id="lnb_7" class=""><a href="./memberInfoUpdate.do" >신청도서 조회</a></li>
				<li id="lnb_7" class=""><a href="./memberInfoUpdate.do" >8</a></li>
				<li id="lnb_7" class=""><a href="./memberInfoUpdate.do" >9</a></li>
				<li id="lnb_7" class=""><a href="./memberInfoUpdate.do" >10</a></li>
				</ul>
				<div class="lnbBottom"></div>
			</div>
		</div>
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			


























































































							
			<!--  휘웅영역-->
			<!-- 컨텐츠 영역 -->
			
			<!-- 모델 정보 -->
			<c:if test="${page=='update'}">
			<div id="contentcore">
			
		
				<!-- 제목 영역 -->
				<div class="naviandtitle"> 
				<h3>회원정보수정</h3>
				</div>
				
				<!-- 내용 영역 넣고싶은거 넣으셈 -->
				<form class="form-inline" onsubmit="return false">
				<div class="container">
	
		<ul class="list-group" id="ulForm">
			
		<li class="list-group-item" id="idInput">
		<label>아이디</label>
		<input type="text" class="form-control" id="id_textbox" name="member_id" value="${member.member_id}" disabled>
		</li>
		<li class="list-group-item"><span id="resultId"><a></a></span></li>
		
		
		
		<li class="list-group-item" id="idInput">
		<div id="eye">
		<label>비밀번호</label><input type="password" class="form-control" placeholder="PASSWORD" id="pw_textbox" name="password" required>
		<i class="fa-solid fa-eye fa-lg"></i> 
		</div>
		</li>
		<li class="list-group-item"><span id="resultPw"><a></a></span></li>
				
		<li class="list-group-item" id="idInput">
		<label>이름</label><input type="text" class="form-control"  id="name_textbox" name="name" disabled>
		</li>
		
		
	
		
		<li class="list-group-item" id="idInput">
		<label>주민번호 앞 6자리 + 뒤 1자리</label><input type="text" class="form-control" id="birth_textbox" name="birth" maxlength="7" required>
		</li>
		<li class="list-group-item"><span id="resultBirth"><a></a></span></li>
			
		
		
		
		<li class="list-group-item" id="idInput">
		
		<label>전화번호</label><input type="text" class="form-control" placeholder="ex)01012345678" id="phone_textbox" name="phone" maxlength="11" required>
		
		<input type="button" class="btn btn-outline-primary" value="인증번호 전송" id="sendPhoneNumber" onclick="a()">
		</li>
		
		
		
		<li class="list-group-item" id="idInput">
		<label>인증번호</label><input type="text" class="form-control" id="phoneCheck_textbox" name="phoneCheckNum" maxlength="8" required>
		<input type="button" id="checkBtn" value="확인" class="btn btn-outline-primary">
		<div class="time"></div>
		</li>
		
		
		
		<li class="list-group-item" id="idInput">
		<label>주소</label><input type="text" class="form-control" id="detailAddress_textbox" name="address" autocomplete="name" required>
		<input type="button" class="btn btn-outline-primary" value="우편번호 찾기" id="zipSearch_textbox" onclick="kakaopost()">
		</li>
		
		
		
		<li class="list-group-item"><div id="recaptcha_render" ></div></li>
		<li class="list-group-item"><input type="submit" class="btn btn-outline-primary" value="가입완료" onclick="signUp()" id="btnSignUp" ></li>
				

	</ul>
	</div>
				</form>	
			</div>
				</c:if>
			
			
			
			
			
			
			
			
			
			
			

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
















	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
			
			
			<!-- 휘웅 영역 끝 -->	
			<!-- 인경영역 -->
	<c:if test="${page=='lendingBookListUserHIK'}">
	<div id="contentcore">
			
		
	<!-- 제목 영역 -->
	<div class="naviandtitle"> 
	<h3>대출현황</h3>
	</div>
	
	<!-- 내용 영역 넣고싶은거 넣으셈 -->
	<form class="form-inline">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th><input type="checkbox" id="chkAllHIK"></th>
					<th>번호</th>
					<th>책번호</th>
					<th>제목</th>
					<th>대출일</th>
					<th>반납예정일</th>
					<th>연체일</th>
					<th>연장</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="lists" items="${lists}" varStatus="vs">
					<tr>
						<td><input type="checkbox" ></td>
						<td>${vs.count}</td>
						<td>${lists.book_serial}</td>
						<td>${lists.title}</td>
						<td>${lists.lending_date}</td>
						<td>${lists.end_date}</td>
						<td><c:choose>
						<c:when test="${lists.back_date eq 0}">

						</c:when>
						<c:otherwise>
						${lists.back_date}
						</c:otherwise>
						</c:choose> 
						</td>
						<td>
						<c:if test="${empty lists.lending_delay && lists.back_date eq 0}">
						<input type="button" value="연장하기" onclick="./">
						</c:if></td>
					</tr>
			</c:forEach>
			</tbody>
		</table>			
	</form>	
	</div>
			</c:if>
			
			
			
			
			




















































































































































































































































































































































<!-- 인경 영역 끝 -->
<!-- 수진 영역  // 900~1599 -->
<c:if test="${page=='lendingLIst'}">
 <div id="contentcore">
	
		<!-- 제목 영역 -->
		<div class="naviandtitle"> 
			<h3>도서대출 이용현황</h3>
		</div>

		<!-- 내용 영역  -->
<%-- 		<c:if test="${member.member_id eq 'member_id'}"> --%>
		
		<table  id="dataTable" class="cell-border">
<!-- 		<table  id="dataTable" class="table table-hover"> -->
				<thead>
					<tr>
						<th>번호</th>
						<th>도서코드</th>
						<th>도서제목</th>
						<th>대출일</th>
						<th>반납일</th>
						<th>상태</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="lBean" items="${listBean}" varStatus="vs">
					<c:if test="${lBean.back_date ne null }">
					<tr>
						<td>${vs.count}</td>
						<td>${lBean.book_serial}</td>
						<td>${lBean.title}</td>
						<td>${lBean.lending_date}</td>
						<td>${lBean.back_date}</td>
						<td></td>
					</tr>
					</c:if>
					</c:forEach>
				</tbody>
			</table>
<%-- 		</c:if> --%>

	</div>
</c:if>





<c:if test="${page=='reserveListMem'}">
	<div id="contentcore">

		<!-- 제목 영역 -->
		<div class="naviandtitle"> 
			<h3>도서 예약 현황</h3>
		</div>
	
		<!-- 내용 영역  -->
		<form action="./reserveBook.do" >
			<table  id="dataTable" class="table table-hover">
					<thead>
						<tr id="reserveList">
							<th>예약번호</th>
							<th>ISBN</th>
							<th>도서코드</th>
							<th>도서제목</th>
							<th>예약일</th>
						</tr>
					</thead>
					<tbody>
				<%-- 			<c:forEach var="a" items="${a}" varStatus="vs"> --%>
						<tr id="reserveListVal">
							<td id="lending_seq"><input type="hidden" name="lending_seq" value="${a.LENDING_SEQ}">${a.LENDING_SEQ}</td>
							<td>${a.ISBN}</td>
							<td id="book_serial"><input type="hidden" name="book_serial" value=" ${a.BOOK_SERIAL}"> ${a.BOOK_SERIAL}</td>
							<td>${a.TITLE}</td>
							<td>${a.RESERVE_DATE}</td>
						</tr>
				<%-- 			</c:forEach> --%>
					</tbody>
				</table>
				<br>
				<br>
				<c:if test="${a ne null }">
					<button class="btn btn-outline-secondary" type="submit" formaction="./cancelReseve.do" formmethod="get" formtarget="_self">예약취소</button>
				</c:if>
			</form>
			

	</div>
</c:if>

































































































































































































































































































































































































































































































































































































































<!--수진 영역 끝  -->
<!-- 슬기 영역 -->















































































































































































































































































































































































































<!-- 슬기영역 끝 -->






















			
			
	<!--footer-->
	<!--footer-->
</div>
</body>
	<%@ include file="./footer.jsp" %>
</html>