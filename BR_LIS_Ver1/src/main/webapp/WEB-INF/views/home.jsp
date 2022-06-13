<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta charset="UTF-8">
</head>
<%@ include file="./header.jsp"%>
<script>
$(document).ready(function(){
	var today = new Date();
	var year = today.getFullYear(); // 년도
	var month = today.getMonth() + 1;  // 월
	
	$(".HolYear").text(year);
	$(".HolMonth").text(month);
noticeList(); //홈화면 공지사항 목록
faqList(); // 홈화면 FAQ 목록
homeNewBook(); // 홈화면 신간도서 url
calList();//도서관일정 목록
})
	$(function() {
		$('#searchBoxArea').css('height', '73px');
		$('#searchBoxArea').css('opacity', '1');
		$('#searchBoxArea').show();
	});
</script>
<script type="text/javascript" src="./BR_js/home.js"></script>
<body class="body">
	<!-- <div id="wrap"> -->
	<script>
		$(function() {
			$("#topSearchKeyword").keypress(function(e) {
				var code = e.keyCode || e.which;
				if (code == 13) {
					fnTopSearch();
					return false;
				}
			});
		});

		function fnTopSearch() {
			var form = document.topSearchForm;
			if (isEmpty(form.searchKeyword.value)) {
				alert("검색어를 입력하세요.");
				form.searchKeyword.focus();
				return;
			}
			form.action = "./homeSearch.do";
			form.submit();
		}
		function isEmpty(str){
			if(str.length==0){
				return true;
			}else{
				return false;
			}
		}
	</script>

	<div id="mobileIndex"></div>
	<div>
		<a href="#link" id="blackBG">이전 상태로 변경</a>
	</div>

	<!--Top SerachBox ~~~-->
	<div id="searchBoxArea" style="height: 0px; display: none">
		<div id="searchBox">
			<div class="searchBox">
				<form name="topSearchForm" id="topSearchForm" method="post"
					action="/jungang/10010/booksearch/searchSimple.do">
					<input type="hidden" name="searchLibraryArr" value="MA" />
					<div class="bgWhite">
						<select name="searchKey" title="검색방식선택">
							<option value="title">서명</option>
							<option value="author">저자</option>
							<option value="publisher">출판사</option>
							<option value="isbn">ISBN</option>
						</select> <input type="text" name="searchKeyword" id="topSearchKeyword"
							class="schInput1" title="검색어 입력" />
					</div>
					<div class="btnRight">
						<span class="themeBtn"><a href="javascript:fnTopSearch();">도서검색</a></span>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!--Top SerachBox -->

	<div id="middle">
		<div id="contbodym">
			<div id="section1">
				<div class="fl">
					<script>
						$(function() {
							var popupSwiper = new Swiper('.swiper-popup', {
								pagination : '.pagination5',
								loop : true,
								grabCursor : true,
								autoplay : 4000,
								keyboardControl : true,
								mousewheelControl : true,
								paginationElement : 'a',
								paginationClickable : true,
								preventLinks : false,
								resizeReInit : true,
								paginationClickable : true
							});
							$('#popZoneStop').show();
							$('#popZoneStart').hide();

							$('#popZoneStart').click(function() {
								$('#popZoneStart').hide();
								$('#popZoneStop').show();
								popupSwiper.startAutoplay();
								return false;
							});

							$('#popZoneStop').click(function() {
								$('#popZoneStop').hide();
								$('#popZoneStart').show();
								popupSwiper.stopAutoplay();
								return false;
							});

							$('#popZoneBWD').click(function() {
								popupSwiper.swipePrev();
								return false;
							});

							$('#popZoneFWD').click(function() {
								popupSwiper.swipeNext();
								return false;
							});
						});

						window.onresize = function() {
							popResize();
						}

						$(document)
								.ready(
										function(e) {
											popResize();
											var tmpNo = 0;
											$('.popupZone .swiper-slide a')
													.each(
															function(index,
																	element) {
																var tmpNo2 = tmpNo;
																var tmpTxt = '<span class="popNo mobileHide">'
																		+ tmpNo2
																		+ '</span>'
																		+ $(
																				this)
																				.attr(
																						'title');

																if (tmpNo == 0) {
																	var tmpNo3 = $('.pagination5 > a').length - 1;
																} else {
																	var tmpNo3 = tmpNo - 1;
																}

																$(
																		'.pagination5 > a:eq('
																				+ tmpNo3
																				+ ')')
																		.html(
																				tmpTxt);
																$(
																		'.pagination5 > a')
																		.attr(
																				'href',
																				'#link');
																tmpNo++;
															});
											$('.popupZoneCnt .totalCnt')
													.text(
															$('.pagination5 > a').length);

											setInterval(function() {
												checkActive(5);
											}, 200);
										});

						function popResize() {
							var tmpHgt = parseInt($(
									".swiper-popup .swiper-slide img").css(
									'width')) / 550 * 240;
							$('.swiper-popup .swiper-slide').css('height',
									tmpHgt);
							$('.swiper-popup').css('height', tmpHgt);
							$('.device-popup').css('height', tmpHgt);
							$('.popupZone .pagination').css('height', tmpHgt);
							var tmpHgtLI = tmpHgt / 6 + "px";
							$('.popupZone .swiper-pagination-switch').css(
									'line-height', tmpHgtLI);
						}

						function checkActive(e) {
							var p2end = $('.swiper-pagination-switch').length;
							$('.swiper-pagination-switch').slice(0, e)
									.addClass('vPage1');
							$('.swiper-pagination-switch').slice(e, p2end)
									.addClass('vPage2');
							if (parseInt($('.swiper-active-switch .popNo')
									.text()) > e) {
								$('.vPage2').show();
								$('.vPage1').hide();
							} else {
								$('.vPage2').hide();
								$('.vPage1').show();
							}
						}
					</script>

					<div class="popupZone">
						<ul class="controlArea">
							<li><a id="popZoneStop" href="#none"><img
									src="./include/image/jungang/main/btn_rollStop.png" alt="팝업존멈춤" /></a></li>
							<li><a id="popZoneStart" href="#none"><img
									src="./include/image/jungang/main/btn_rollPlay.png"
									alt="팝업존 시작" /></a></li>
							<li><a id="popZoneBWD" href="#none"><img
									src="./include/image/jungang/main/btn_rollBWD.png"
									alt="팝업존 뒤 항목으로 이동" /></a></li>
							<li><a id="popZoneFWD" href="#none"><img
									src="./include/image/jungang/main/btn_rollFWD.png"
									alt="팝업존 앞 항목으로 이동" /></a></li>
						</ul>
						<p class="popupZoneCnt">
							<span class="nowCnt"></span><span> / </span><span
								class="totalCnt"></span>
						</p>
						<div class="device-popup">
							<div class="swiper-container swiper-popup">
								<div class="swiper-wrapper">
									<div class="swiper-slide">
										<a href="#" class="img"
											title="6-7월 모두북큐레이션(오디오북)"> <img
											src="./attach/popupzone/jungang20220602155212422.jpg"
											alt="6-7월 모두북큐레이션(오디오북)" />
										</a>
									</div>

									<div class="swiper-slide">
										<a
											href="#"
											class="img" title="지역서점 지역화폐 소비지원금 안내"> <img
											src="./attach/popupzone/jungang20220531134600746.jpg"
											alt="지역서점 지역화폐" />
										</a>
									</div>

									<div class="swiper-slide">
										<a href="#" class="img"
											title="의왕고천택지조성 지장전주 이설공사에 따른 의왕시 도서관 서비스 전면 중단 안내"> <img
											src="./attach/popupzone/jungang20220526143257122.jpg"
											alt="의왕고천택지조성 지장전주 이설공사에 따른 의왕시 도서관 서비스 전면 중단 안내" />
										</a>
									</div>

									<div class="swiper-slide">
										<a
											href="#"
											class="img" title="독서동아리 기본교육과정 모락모락"> <img
											src="./attach/popupzone/jungang20220526142425989.jpg"
											alt="독서동아리 기본교육과정 모락모락" />
										</a>
									</div>

									<div class="swiper-slide">
										<a
											href="#"
											class="img" title="메타버스로 만나는 독서동아리"> <img
											src="./attach/popupzone/jungang20220526142350635.jpg"
											alt="메타버스로 만나는 독서동아리" />
										</a>
									</div>

									<div class="swiper-slide">
										<a
											href="#"
											class="img" title="6월 번개독서토론 운영 안내"> <img
											src="./attach/popupzone/jungang20220524114320398.jpg"
											alt="모집기간 5월 30일 오전 9시부터 선착순 접수 운영일시 6월 29일 오후 7시부터 8시 30분까지" />
										</a>
									</div>

									<div class="swiper-slide">
										<a
											href="#"
											class="img" title="행복한 노후 아카데미 운영 안내"> <img
											src="./attach/popupzone/jungang20220520172851343.jpg"
											alt="운영일시: 2022. 6. 20.(월) ~ 7. 18.(월)매주 월,수 10:00 ~ 12:00  대상: 행복한 노후를 준비하는 의왕시민 20명 장소: 중앙도서관 문화교실 1 내용: 노후 대비 프로그램(총9회) 접수: 5.30.(월) 9시부터 선착순 접수" />
										</a>
									</div>

									<div class="swiper-slide">
										<a
											href="#"
										    class="img" title="6월 포스트잇 독서토론 운영안내"> <img
											src="./attach/popupzone/jungang20220520091826107.jpg"
											alt="누구나 참여하는 포스트잇 독서토론" />
										</a>
									</div>

									<div class="swiper-slide">
										<a
											href="#"
											class="img" title="테마가 있는 작은도서관 6월 강좌 접수 안내"> <img
											src="./attach/popupzone/jungang20220516111822114.jpg"
											alt="테마가 있는 작은도서관 6월 강좌 접수 안내" />
										</a>
									</div>



									<div class="swiper-slide">
										<a
											href="#"
											class="img" title="의왕역 스마트도서관 운영안내"> <img
											src="./attach/popupzone/jungang20220303171205121.jpg"
											alt="의왕역 스마트도서관 운영안내" />
										</a>
									</div>



									<div class="swiper-slide">
										<a
											href="#"
											class="img" title="2022년 이동도서관 운행일정 안내"> <img
											src="./attach/popupzone/jungang20220216161703083.jpg"
											alt="- 운행기간: 2022. 2. 7. ~ 12. 16. - 운행장소: 18개소(시간표 참조) - 이용대상: 의왕시민(1인 5권/14일간)" />
										</a>
									</div>



									<div class="swiper-slide">
										<a href="#" class="img" title="공터 주차 불가 안내"> <img
											src="./attach/popupzone/jungang20220401133240661.jpg"
											alt="중앙도서관 앞 공터를  공사로 인해 폐쇄하여 주차가 불가하오니 도서관 이용에 참고하시기 바랍니다. ※ 중앙도서관 내 주차장은 이용 가능" />
										</a>
									</div>



									<div class="swiper-slide">
										<a href="#"
											class="img" title="전자책도서관 신착도서를 만나보세요!"> <img
											src="./attach/popupzone/jungang20220414161422687.jpg"
											alt="전자책 135종, 오디오북 26종 이용방법: 도서관홈페이지 전자책도서관 or 리브로피아 모바일전자책" />
										</a>
									</div>



									<div class="swiper-slide">
										<a
											href="#"
											class="img" title="의왕역 스마트도서관 신착도서 안내"> <img
											src="./attach/popupzone/jungang20220427094931629.jpg"
											alt="의왕역 스마트도서관 신간도서 안내" />
										</a>
									</div>


									<div class="swiper-slide">
										<a
											href="#"
											class="img" title="의왕시 지역서점도서관 안내"> <img
											src="./attach/popupzone/jungang20220217144741386.jpg"
											alt="의왕시 지역서점도서관 안내" />
										</a>
									</div>




								</div>
							</div>
							<div class="pagination pagination5"></div>

						</div>
					</div>
				</div>
				<div class="fr">
					<div class="upper mobileHide">
						<!-- after Login -->
						<div class="innerWrap">
							<c:if test="${member.auth eq null && admin.admin_id == null }"> 
							<span class="txtBtn_darkGray sizeSmall">
							<a
								href="./loginPage.do">로그인</a></span>
								
								 <span
								class="txtBtn_darkGray sizeSmall ml10"><a
								href="./signUpPage.do">회원가입</a></span>
								</c:if>
							<c:if test="${member.auth eq 'NM'|| member.auth eq 'GH'}">
							<span
								class="txtBtn_darkGray sizeSmall ml10"><a
								href="./myPage.do">마이 페이지</a></span>	
								</c:if>
							<c:if test="${member.auth eq null && admin.admin_id == null }"> 
							<span
								class="txtBtn_darkGray sizeSmall ml10"><a
								href="./adminCertification.do">관리자</a></span>	
								</c:if>	
								
								<c:if test="${admin.admin_id != null }"> 
							<span
								class="txtBtn_darkGray sizeSmall ml10"><a
								href="./adminPage.do">관리자 페이지</a></span>	
								</c:if>	
								
						</div>
					</div>
					<div class="downer" id="downer">
						<div class="innerWrap">
							<ul class="mySvcList">
								<li><img src="./include/image/jungang/main/icon_msvc01.png"
									alt="">
									 <a href="/jungang/10075/30014/program.do">
<!-- 									 <a href="./lendingBookAdmin.do"> -->
										대출현황조회</a></li>
								<li><img src="./include/image/jungang/main/icon_msvc02.png"
									alt=""> <a href="/jungang/10076/30015/program.do">
										예약도서 </a></li>
								<li><img src="./include/image/jungang/main/icon_msvc03.png"
									alt=""> <a href="./requestPage.do">
										희망도서신청 </a> <!--<a href="#n" onclick="alert('코로나19 심각단계 격상으로 의왕시 도서관 휴관에 따라 희망도서 신청을 중단하니 양해부탁드립니다.');"> 희망도서신청
									</a>--></li>
								<li><img src="./include/image/jungang/main/icon_msvc04.png"
									alt=""> <a
									href="/jungang/10084/mypage/lecture/lectureList.do"> 문화강좌신청
								</a></li>
								<li><img src="./include/image/jungang/main/icon_msvc05.png"
									alt=""> <a
									href="./lendingBookListUser.do">대출현황(대출중)</a></li>
							</ul>
						</div>
					</div>
					<div class="bg_fold"></div>
				</div>
			</div>
			<div id="section2">
				<ul class="mainSvc">
				</ul>
			</div>
			<div id="section3">
				<div class="sec3_1">


					<div class="mainHoli">
						<div class="holiHead themeColor">
							<h2>도서관일정</h2>
							<p class="btnMore">
								<a href="./viewAllBoard.do?kind=calendar">더보기</a>
							</p>
						</div>
						<div class="holiBody">
							<div class="holYM">
								<p class="holYear"></p>
								<p class="holMonth"></p>
							</div>
							<!--VIEW-TODO001 for문 처리 해야 함 아래는 예시-->
							<ul id="calendarList">
								<li>매주 금 <a href="./viewAllBoard.do?kind=calendar" title="정기휴관일">정기휴관일</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="sec3_2">
					<div class="noticeArea">
						<h2 class="themeFC">공지사항</h2>
						<ul class="noticeList" id="noticeList">
						</ul>
						<p class="btnMore">
							<a href="./viewAllBoard.do?kind=notice">더 보기</a>
						</p>
					</div>
				</div>

				<div class="sec3_3">
					<div class="noticeArea">
						<h2 class="themeFC">FAQ</h2>
						<ul class="noticeList" id="faqList">
						</ul>
						<p class="btnMore">
							<a href="./viewAllBoard.do?kind=faq">더 보기</a>
						</p>
					</div>	
				</div>
			</div>
		</div>
	</div>
</body>
<%@include file="./footer.jsp"%>
</html>