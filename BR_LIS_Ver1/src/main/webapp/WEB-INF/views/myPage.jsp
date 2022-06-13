<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>woowang Library MyPage</title>
</head>
<%@ include file="./header.jsp"%>
<link	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"	rel="stylesheet"	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"	crossorigin="anonymous">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" />
<script type="text/javascript"	src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script type="text/javascript"	src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js"></script>
<link rel="stylesheet" type="text/css"	href="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.css" />


<!-- css넣어라 -->
<link rel="stylesheet" href="./khu_css/myPage.css"/>
<link rel="stylesheet" href="./hik_css/myPageHIK.css"/>
<link rel="stylesheet" href="./hsg_css/myPageReq.css"/>

<!-- css끝 -->
<!-- js 넣어라 -->
<script type="text/javascript" src="./khu_js/myPage.js"></script>
<script type="text/javascript" src="./BR_js/myPageHIK.js"></script>



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
				<li id="lnb_1" class=""><a href="./memberInfoUpdatePage.do" >내 정보 변경</a></li> 
				<li id="lnb_2" class=""><a href="./memberQuitRequestPage.do" >회원탈퇴 신청</a></li>
				<li id="lnb_3" class=""><a href="./memberInfoUpdate.do" >알림 수신 변경</a></li>
				<li id="lnb_4" class=""><a href="./lendingBookListUser.do" >도서대출 현황</a></li>
				<li id="lnb_5" class=""><a href="./lendingBookMem.do" >도서대출 이용 현황</a></li>
                <li id="lnb_6" class=""><a href="./reserveBook.do" >예약 현황</a></li> 
				<li id="lnb_7" class=""><a href="./myRequestBookList.do" >신청도서 조회</a></li>
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
				<h3>회원정보변경</h3>
				</div>
				
				<!-- 내용 영역 넣고싶은거 넣으셈 -->
				<form class="form-inline" method="GET" action="#" id="khu_updateInfoForm">
				<div class="container">
	
		<ul class="list-group" id="khu_ulForm">
			
		<li class="list-group-item" id="khu_idInput">
		<label id="khu_label">아이디(ID는 변경이 불가능합니다)</label>
		<input type="text" class="form-control" id="khu_id_textbox" value="${member.member_id}" name="member_id"  readonly>
		</li>
		<li class="list-group-item"><span id="khu_resultId"><a></a></span></li>
		
		
		<li class="list-group-item" id="khu_idInput">
		<div id="eye">
		<label>비밀번호</label><input type="password" class="form-control" placeholder="PASSWORD" id="khu_pw_textbox" name="password" required>
		<i class="fa-solid fa-eye fa-lg"></i> 
		<button type="button" id="#" onclick="updatePw()"class="btn btn-outline-primary">변경</button>
		</div>
		</li>
		<li class="list-group-item"><span id="khu_resultPw"><a></a></span></li>
		
				
		<li class="list-group-item" id="khu_idInput">
		<label id="khu_label">이름</label><input type="text" class="form-control"  id="khu_name_textbox" name="name" value="${member.name}" required>
		<button type="button" id="#" onclick="updateName()"class="btn btn-outline-primary">변경</button>
		</li>
		
		<li class="list-group-item" id="khu_idInput">
		
		<label id="khu_label">전화번호</label><input type="text" class="form-control" placeholder="ex)01012345678" id="khu_phone_textbox" name="phone" maxlength="11" value="${member.phone}" required>
		<input type="button" class="btn btn-outline-primary" value="인증번호 전송" id="khu_sendPhoneNumber">
		</li>
		
		
		
		<li class="list-group-item" id="khu_idInput">
		<label id="khu_label">인증번호</label><input type="text" class="form-control" id="khu_phoneCheck_textbox" name="phoneCheckNum" maxlength="8" required>
		<input type="button" id="khu_checkBtn" value="확인" class="btn btn-outline-primary">
		<button type="button" id="khu_btnUpdatePhone" onclick="updatePhone()"class="btn btn-outline-primary" disabled>변경</button>
		<div class="khu_time"></div>
		</li>
		
		
		
		<li class="list-group-item" id="khu_idInput">
		<label id="khu_label">주소</label><input type="text" class="form-control" id="khu_detailAddress_textbox" name="address" autocomplete="name" value="${member.address}" required>
		<input type="button" class="btn btn-outline-primary" value="우편번호 찾기" id="khu_zipSearch_textbox" onclick="kakaopost()">
		<button type="button" id="#" onclick="updateAddress()"class="btn btn-outline-primary">변경</button>
		</li>
		
		<c:if test="${member.auth == 'GH'}">
		<li class="list-group-item" id="khu_idInput">
		<label id="khu_label">이메일</label><input type="text" class="form-control" id="khu_email_textbox" name="email" value="${member.email}" autocomplete="name"  required>
		<input type="button" class="btn btn-outline-primary" value="인증번호 전송" id="sendEmailNumber">
		</li>
		</c:if>
		
		<c:if test="${member.auth == 'NM'}">
		<li class="list-group-item" id="khu_idInput">
		<label id="khu_label">이메일</label><input type="text" class="form-control" id="khu_emails_textbox" name="email"  value="${member.email}"  autocomplete="name"  readonly>
		</li>
		</c:if>
		
		<c:if test="${member.auth == 'GH'}">
		<li class="list-group-item" id="khu_idInput">
		<label id="khu_label">인증번호</label><input type="text" class="form-control" id="khu_emailCheck_textbox" name="emailCheckNum" maxlength="8" required>
		<input type="button" id="khu_btnEmailCheck" value="확인" class="btn btn-outline-primary">
		<button type="button" id="khu_btnUpdateEmail" onclick="updateEmail()"class="btn btn-outline-primary" disabled>변경</button>
		<div class="khu_time2"></div>
		</li>
		</c:if>
			
	</ul>
	</div>
				</form>	
			</div>
				</c:if>
				
				
	<c:if test="${page=='quitRequest'}">
			<div id="contentcore">
			
		
				<!-- 제목 영역 -->
				<div class="naviandtitle"> 
				<h3>탈퇴 신청</h3>
				</div>
				
				<!-- 내용 영역 넣고싶은거 넣으셈 -->
				<form class="form-inline" method="GET" action="#" id="khu_quitRequestForm">
				<div class="container">
				<input type="text" value="${member.member_id}"  id="khu_hiddenId" name="member_id" hidden="true">
				<label id="khu_label" >휴대폰 인증</label>
				<input type="password" id="khu_pw" name="password" required>
				<input type="text" value="${member.rental_count}"  id="khu_hiddenRentalCount" name="rental_count" hidden="true">
				<input type="submit" class="btn btn-outline-danger" value="탈퇴" id="khu_quitRequestBtn" onclick="khu_quitRequest()">
	</div>
				</form>	
			</div>
				</c:if>


		<!-- 휘웅 영역 끝 -->
		
			
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
		<table id="dataTableHIK" class="table-bordered">
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
			<input type="hidden" name="lending_seq" id="lend" value="${lists.lending_seq}">
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
						<a href="./delayLendingBook.do" class="btn">버튼</a>
<!-- 						<input type="button" value="연장하기" onclick="delayLendingBook()"> -->
						</c:if>
						</td>
					</tr>
			</c:forEach>
			</tbody>
		</table>			
	</form>	
	</div>
		<input type="button" value="일괄연장하기">
			</c:if>



						<!-- 인경 영역 끝 -->
						<!-- 수진 영역-->
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
	<c:if test="${reqPage=='userRequestBook'}">
	<div id="contentcore">
	<h1 id="myRequestBookH1">나의 신청도서</h1>
	<div id="myRequestBookDiv">
		<!-- 사용자의 신청도서 승인여부를 볼 수 있는 리스트 -->
		<table id="myRequestBookTable">
			<thead id="myRequestBookThead">
				<tr>
					<th>도서명</th>
					<th>지은이</th>
					<th>출판사</th>
					<th>승인여부</th>
				</tr>			
			</thead>
			<tbody id="">
				<c:forEach var="myRequestBookList" items="${myRequestBookList}" varStatus="vs">
					<tr>
						<td>${myRequestBookList.title}</td>
						<td>${myRequestBookList.author}</td>
						<td>${myRequestBookList.publisher}</td>
						<!-- 승인여부 조회결과가 Y면 승인, N이면 미승인 -->
						<c:choose>
							<c:when test="${myRequestBookList.confirm == 'Y'}">
								<td>승인</td>
							</c:when>
							<c:when test="${myRequestBookList.confirm == 'N'}">
								<td>미승인</td>
							</c:when>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</div>	
	</c:if>
	
</div>

<!-- 슬기영역 끝 -->
						
</body>

<script type="text/javascript">
	var khu_timer = null;
	var khu_isRunning = false;
	var khu_btn = document.getElementById("khu_btnUpdatePhone");
	var khu_btn2 = document.getElementById("khu_btnUpdateEmail");
	$('#khu_sendPhoneNumber')
			.click(
					function() {

						let phoneNumber = $('#khu_phone_textbox').val();
						swal("인증번호 발송 완료");
						var display = $('.khu_time');
						var leftSec = 180;

						if (khu_isRunning) {
							clearInterval(khu_timer);
							display.html("");
							startkhu_timer(leftSec, display);
						} else {
							startkhu_timer(leftSec, display);
						}

						var docum = document.getElementById("khu_checkBtn").disabled;

						$
								.ajax({
									type : "POST",
									url : "./sendSMS.do",
									data : {
										"phone" : phoneNumber
									}, // 핸드폰 값이 넘어감
									success : function(res) { // 인증번호 값이 넘어옴
										$('#khu_checkBtn')
												.click(
														function() {
															if ($(
																	'#khu_phoneCheck_textbox')
																	.val() == '') {
																swal('값을 입력하세요.');
															} else if (khu_isRunning
																	&& $
																			.trim(res) == $(
																			'#khu_phoneCheck_textbox')
																			.val()) {
																swal('인증 성공');
																khu_btn.disabled = false;
																clearInterval(khu_timer);
																display
																		.html("");
															} else {
																if (khu_isRunning) {
																	swal('인증번호가 맞지 않습니다');
																} else {
																	swal('시간이 초과되었습니다');
																}
															}
														})
									}
								})
					});
	function startkhu_timer(count, display) {
		var minutes, seconds;
		khu_timer = setInterval(function() {
			minutes = parseInt(count / 60, 10);
			seconds = parseInt(count % 60, 10);

			minutes = minutes < 10 ? "0" + minutes : minutes;
			seconds = seconds < 10 ? "0" + seconds : seconds;

			display.html(minutes + ":" + seconds);

			// 타이머 끝
			if (--count < 0) {
				clearInterval(khu_timer);
				alert("시간초과");
				display.html("시간초과");
				$('#khu_checkBtn').attr("disabled", "disabled");
				khu_isRunning = false;
			}
		}, 1000);
		khu_isRunning = true;
	}

	$('#sendEmailNumber')
			.click(
					function() {
						let emailNumber = $('#khu_email_textbox').val();
						swal("인증번호 발송 완료");
						var display = $('.khu_time2');
						var leftSec = 180;

						if (khu_isRunning) {
							clearInterval(khu_timer);
							display.html("");
							startkhu_timer(leftSec, display);
						} else {
							startkhu_timer(leftSec, display);
						}

						var docum = document
								.getElementById("khu_btnEmailCheck").disabled;

						$
								.ajax({
									type : "POST",
									url : "./memberInfoUpdateEmailChk.do",
									data : {
										"email" : emailNumber
									}, // 핸드폰 값이 넘어감
									success : function(res) { // 인증번호 값이 넘어옴
										$('#khu_btnEmailCheck')
												.click(
														function() {
															if ($(
																	'#khu_emailCheck_textbox')
																	.val() == '') {
																swal('값을 입력하세요.');
															} else if (khu_isRunning
																	&& $
																			.trim(res) == $(
																			'#khu_emailCheck_textbox')
																			.val()) {
																swal('인증 성공');
																khu_btn2.disabled = false;
																clearInterval(khu_timer);
																display
																		.html("");
															} else {
																if (khu_isRunning) {
																	swal('인증번호가 맞지 않습니다');
																} else {
																	swal('시간이 초과되었습니다');
																}
															}
														})
									}
								})
					});
	function startkhu_timer(count, display) {
		var minutes, seconds;
		khu_timer = setInterval(function() {
			minutes = parseInt(count / 60, 10);
			seconds = parseInt(count % 60, 10);

			minutes = minutes < 10 ? "0" + minutes : minutes;
			seconds = seconds < 10 ? "0" + seconds : seconds;

			display.html(minutes + ":" + seconds);

			// 타이머 끝
			if (--count < 0) {
				clearInterval(khu_timer);
				alert("시간초과");
				display.html("시간초과");
				$('#khu_btnEmailCheck').attr("disabled", "disabled");
				khu_isRunning = false;
			}
		}, 1000);
		khu_isRunning = true;
	}
</script>
<%@ include file="./footer.jsp"%>
</html>