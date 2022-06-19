<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>woowang Library</title>
<link rel="icon" href="./include/image/favicon.ico" type="image/x-icon">
<link href="./include/css/custom.css" rel="stylesheet" type="text/css" />
<link href="./include/css/jungang/sub.css" rel="stylesheet"	type="text/css" />
<link href="./include/css/jungang/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="./include/js/gnb.js"></script>
<script src="./include/js/idangerous.swiper.js"></script>
<!-- <script src="./include/js/jquery-1.12.2.min.js"></script> -->
<!-- <script src="./include/js/jquery.stringify.js"></script> -->
<!-- <script src="./include/js/common.js"></script> -->
<!-- <script src="./include/js/jquery.cookie.js"></script> -->
</head>
<body class="body">
	<div id="wrap">
		<div id="topheader">
			<div id="indexColumn">
				<div id="siteIndex">
					<ul class="siteRole clearfix">
						<li class="mobileHide"><a href="./home.do">HOME</a></li>
						<li><a href="https://search.daum.net/search?w=book"	target="_blank">다음 책</a></li>
						<li><a href="https://www.aladin.co.kr/" target="_blank">알라딘</a></li>
					</ul>
				</div>
				<div id="siteMenu" class="siteMenu">
					<ul class="memUtil">
						<c:if test="${member.auth eq 'NM'|| member.auth eq 'GH'}">
							<li><a>로그인 정보 :${member.member_id}[${member.auth == 'NM'?"일반회원":"관내회원"}]</a></li>
							<li><a href="./logout.do"><span
									class="glyphicon glyphicon-log-out"></span> Logout</a></li>
						</c:if>

						<c:if test="${admin.admin_id != null}">
							<li><a>로그인 정보 :${admin.admin_id}[관리자]</a></li>
							<li><a href="./logout.do"><span	class="glyphicon glyphicon-log-out"></span> Logout</a></li>
						</c:if>

						<c:if test="${member.auth eq null && admin.admin_id == null }">
							<li><a href="./loginPage.do">로그인</a></li>
						</c:if>

						<c:if test="${member.auth eq null && admin.admin_id==null }">
							<li class="mobileHide"><a href="./signUpPage.do">회원가입</a></li>
						</c:if>

						<li class="mobileHide"><a
							href="./informationLib.do?kind=siteMap">사이트맵</a></li>
					</ul>
				</div>

				<div id="BtnmobileIndex">
					<a href="#llink" id="BtnmobileIndexa"><img
						src="./include/image/common/btn_menu.png" alt="메뉴 열기"><span>메뉴</span></a>
				</div>
				<div id="btnSearchTop">
					<a href="#link"><img
						src="./include/image/common/btn_serachTop.png" alt="검색창 열기"><span>검색</span></a>
				</div>
				<div id="indexNavi">
					<ul id="topmenu">


						<li id="gnb1"><a href="./bookSearch.do?kind=total">자료검색</a>
							<ul>
								<li id="gnb1_1" class=""><a	href="./bookSearch.do?kind=total">통합검색</a></li>
								<li id="gnb1_2" class=""><a	href="./bookSearch.do?kind=detail">상세검색</a></li>
								<li id="gnb1_3" class=""><a	href="./bookSearch.do?kind=request">희망도서신청</a></li>
							</ul>
						</li>

						<li id="gnb2"><a href="./viewAllBoard.do?kind=notice">정보마당</a>
							<ul>
								<li id="gnb2_1" class=""><a	href="./viewAllBoard.do?kind=notice">공지사항</a></li>
								<li id="gnb2_2" class=""><a	href="./viewAllBoard.do?kind=faq">FAQ</a></li>
								<li id="gnb2_3" class=""><a	href="./viewAllBoard.do?kind=calendar">도서관일정</a></li>
							</ul>
						</li>
						<c:if test="${admin.admin_id != null }">
							<li id="gnb3"><a href="/jungang/10028/30004/program.do">도서구매</a>
								<ul>
									<li id="gnb3_1" class=""><a href="./purchRegulList.do">정기도서	구매관리</a></li>
									<li id="gnb3_2" class=""><a href="./requestBookList.do">신청도서 구매관리</a></li>
									<li id="gnb3_3" class=""><a href="./purchaseCodeList.do">발주관리</a></li>
									<li id="gnb3_5" class=""><a href="./newBookList.do">신규도서조회</a>
									</li>
								</ul>
							</li>
						</c:if>
						<c:if test="${admin.admin_id != null }">
							<li id="gnb4"><a href="/jungang/10048/20026/bbsList.do">대출/반납</a>
								<ul>
									<li id="gnb4_1" class=""><a href="./lendMainpage.do">대출</a></li>
									<li id="gnb4_2" class=""><a href="./returnBookPage.do">반납</a></li>
									<li id="gnb4_3" class=""><a href="./returnBookList.do">대출목록</a></li>
									<li id="gnb4_4" class=""><a href="./possessingBookList.do">보유도서	목록</a></li>
								</ul>
							</li>
						</c:if>

						<li id="gnb5"><a href="./informationLib.do?kind=libguide">도서관소개</a>
							<ul>
								<li id="gnb5_1" class=""><a	href="./informationLib.do?kind=libguide">도서관안내</a></li>
								<li id="gnb5_2" class=""><a	href="./informationLib.do?kind=useguide">이용안내</a></li>
								<li id="gnb5_3" class=""><a	href="./informationLib.do?kind=locguide">찾아오시는길</a></li>
								<li id="gnb5_4" class=""><a	href="./informationLib.do?kind=siteMap">사이트맵</a>
							</ul>
						</li>

						<c:if test="${admin.admin_id != null }">
							<li id="gnb6"><a href="./signUpPage.do">회원관리</a>
								<ul>
									<c:if test="${member.auth eq null}">
										<li id="gnb6_1" class=""><a	href="./quitMemberManagePage.do">조회 및 승인</a></li>
									</c:if>
								</ul>
							</li>
						</c:if>

						<c:if test="${member.member_id != null }">
							<li id="gnb7"><a href="./myPage.do">마이페이지</a>
								<ul>
									<li id="gnb7_1" class=""><a	href="./memberInfoUpdatePage.do">내 정보 변경</a></li>
									<li id="gnb7_2" class=""><a href="./memberQuitRequestPage.do">회원탈퇴 신청</a></li>
									<c:if test="${member.auth == 'NM'}">
										<li id="gnb7_3" class=""><a href="./updateToGhPage.do">관내회원	등업</a></li>
									</c:if>
									<li id="gnb2_1" class=""><a	href="./reserveBook.do">도서예약</a></li>
									<li id="gnb7_4" class=""><a href="./lendingBookListUser.do">도서대출 현황</a></li>
									<li id="gnb7_5" class=""><a href="./lendingBookMem.do">도서대출	이용 현황</a></li>
									<li id="gnb7_6" class=""><a href="./reserveBookList.do">예약 현황</a></li>
									<li id="gnb7_7" class=""><a href="./myRequestBookList.do">신청도서	조회</a></li>
									<c:if test="${member.auth == 'GH'}">
										<li id="gnb7_8" class=""><a href="./memberCard.do">회원증 출력</a></li>
									</c:if>
								</ul>
							</li>
						</c:if>
					</ul>
					<div id="submenuArea">
						<div id="subvisuala"></div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script>initMenu(0,0,0,0);</script>
</body>
</html>