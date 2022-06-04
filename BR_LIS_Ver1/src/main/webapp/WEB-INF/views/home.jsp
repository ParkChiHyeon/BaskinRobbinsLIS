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
	$(function() {
		$('#searchBoxArea').css('height', '73px');
		$('#searchBoxArea').css('opacity', '1');
		$('#searchBoxArea').show();
	});
</script>
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
			form.action = "/jungang/10010/booksearch/searchSimple.do";
			form.submit();
		}
	</script>

	<div id="mobileIndex"></div>
	<div>
		<a href="#link" id="blackBG">이전 상태로 변경</a>
	</div>

	<!--Top SerachBox -->
	<div id="searchBoxArea" style="height: 0px; display: none">
		<div id="searchBox">
			<div class="searchBox">
				<form name="topSearchForm" id="topSearchForm" method="post"
					action="/jungang/10010/booksearch/searchSimple.do">
					<input type="hidden" name="searchLibraryArr" value="MA" />
					<div class="bgWhite">
						<select name="searchKey" title="검색방식선택">
							<option value="ALL" selected="selected">전체</option>
							<option value="TITLE">서명</option>
							<option value="AUTHOR">저자</option>
							<option value="PUBLISHER">출판사</option>
							<option value="KEYWORD">키워드</option>
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
										<a href="http://cyber.uwlib.or.kr:81/" class="img"
											title="6-7월 모두북큐레이션(오디오북)"> <img
											src="./attach/popupzone/jungang20220602155212422.jpg"
											alt="6-7월 모두북큐레이션(오디오북)" />
										</a>
									</div>

									<div class="swiper-slide">
										<a
											href="https://www.uwlib.or.kr/jungang/10041/20006/bbsDetail.do?bbsIdx=16407"
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
											href="https://www.uwlib.or.kr/jungang/10041/20006/bbsDetail.do?bbsIdx=16398"
											class="img" title="독서동아리 기본교육과정 모락모락"> <img
											src="./attach/popupzone/jungang20220526142425989.jpg"
											alt="독서동아리 기본교육과정 모락모락" />
										</a>
									</div>

									<div class="swiper-slide">
										<a
											href="https://www.uwlib.or.kr/jungang/10041/20006/bbsDetail.do?bbsIdx=16397"
											class="img" title="메타버스로 만나는 독서동아리"> <img
											src="./attach/popupzone/jungang20220526142350635.jpg"
											alt="메타버스로 만나는 독서동아리" />
										</a>
									</div>

									<div class="swiper-slide">
										<a
											href="https://www.uwlib.or.kr/jungang/10041/20006/bbsDetail.do?bbsIdx=16386"
											class="img" title="6월 번개독서토론 운영 안내"> <img
											src="./attach/popupzone/jungang20220524114320398.jpg"
											alt="모집기간 5월 30일 오전 9시부터 선착순 접수 운영일시 6월 29일 오후 7시부터 8시 30분까지" />
										</a>
									</div>

									<div class="swiper-slide">
										<a
											href="https://www.uwlib.or.kr/jungang/10041/20006/bbsDetail.do?bbsIdx=16341"
											class="img" title="행복한 노후 아카데미 운영 안내"> <img
											src="./attach/popupzone/jungang20220520172851343.jpg"
											alt="운영일시: 2022. 6. 20.(월) ~ 7. 18.(월)매주 월,수 10:00 ~ 12:00  대상: 행복한 노후를 준비하는 의왕시민 20명 장소: 중앙도서관 문화교실 1 내용: 노후 대비 프로그램(총9회) 접수: 5.30.(월) 9시부터 선착순 접수" />
										</a>
									</div>

									<div class="swiper-slide">
										<a
											href="https://www.uwlib.or.kr/jungang/10041/20006/bbsDetail.do?bbsIdx=16321"
											target="_blank" class="img" title="6월 포스트잇 독서토론 운영안내"> <img
											src="./attach/popupzone/jungang20220520091826107.jpg"
											alt="누구나 참여하는 포스트잇 독서토론" />
										</a>
									</div>

									<div class="swiper-slide">
										<a
											href="https://www.uwlib.or.kr/jungang/10041/20006/bbsDetail.do?bbsIdx=16301"
											class="img" title="테마가 있는 작은도서관 6월 강좌 접수 안내"> <img
											src="./attach/popupzone/jungang20220516111822114.jpg"
											alt="테마가 있는 작은도서관 6월 강좌 접수 안내" />
										</a>
									</div>



									<div class="swiper-slide">
										<a
											href="https://www.uwlib.or.kr/jungang/10592/40213/content.do"
											class="img" title="의왕역 스마트도서관 운영안내"> <img
											src="./attach/popupzone/jungang20220303171205121.jpg"
											alt="의왕역 스마트도서관 운영안내" />
										</a>
									</div>



									<div class="swiper-slide">
										<a
											href="https://www.uwlib.or.kr/jungang/10041/20006/bbsDetail.do?bbsIdx=15544"
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
										<a href="http://cyber.uwlib.or.kr:81/Kyobo_T3/Default.asp"
											class="img" title="전자책도서관 신착도서를 만나보세요!"> <img
											src="./attach/popupzone/jungang20220414161422687.jpg"
											alt="전자책 135종, 오디오북 26종 이용방법: 도서관홈페이지 전자책도서관 or 리브로피아 모바일전자책" />
										</a>
									</div>



									<div class="swiper-slide">
										<a
											href="https://www.uwlib.or.kr/jungang/10041/20006/bbsDetail.do?bbsIdx=16191"
											target="_blank" class="img" title="의왕역 스마트도서관 신착도서 안내"> <img
											src="./attach/popupzone/jungang20220427094931629.jpg"
											alt="의왕역 스마트도서관 신간도서 안내" />
										</a>
									</div>



									<div class="swiper-slide">
										<a
											href="https://www.uwlib.or.kr/jungang/10041/20013/bbsDetail.do?bbsIdx=13508"
											target="_blank" class="img" title="의왕시 지역서점도서관 안내"> <img
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
						
							<c:if test="${member.auth eq null }">
							<span class="txtBtn_darkGray sizeSmall"><a href="./loginPage.do">로그인</a></span>
							</c:if>
							
							<c:if test="${member.auth eq 'NM'|| member.auth eq'GH'}">
							<span class="txtBtn_darkGray sizeSmall"><a href="./logout.do">로그아웃</a></span>
							</c:if>
							
								 <span
								class="txtBtn_darkGray sizeSmall ml10">
								
								<a
								href="/member/join.do">회원가입</a></span>
						</div>
					</div>
					<div class="downer" id="downer">
						<div class="innerWrap">
							<ul class="mySvcList">
								<li><img src="./include/image/jungang/main/icon_msvc01.png"
									alt=""> <a href="/jungang/10075/30014/program.do">
										대출도서/반납연기 </a></li>
								<li><img src="./include/image/jungang/main/icon_msvc02.png"
									alt=""> <a href="/jungang/10076/30015/program.do">
										예약도서 </a></li>
								<li><img src="./include/image/jungang/main/icon_msvc03.png"
									alt=""> <a href="/jungang/10079/30017/program.do">
										희망도서신청 </a> <!--<a href="#n" onclick="alert('코로나19 심각단계 격상으로 의왕시 도서관 휴관에 따라 희망도서 신청을 중단하니 양해부탁드립니다.');"> 희망도서신청
									</a>--></li>
								<li><img src="./include/image/jungang/main/icon_msvc04.png"
									alt=""> <a
									href="/jungang/10084/mypage/lecture/lectureList.do"> 문화강좌신청
								</a></li>
								<li><img src="./include/image/jungang/main/icon_msvc05.png"
									alt=""> <a
									href="/jungang/10085/mypage/visit/visitList.do"> 견학신청 </a></li>
							</ul>
						</div>
					</div>
					<div class="bg_fold"></div>
				</div>
			</div>
			<div id="section2">
				<ul class="mainSvc">
					<li><a href="/jungang/10018/40002/content.do"><img
							src="./include/image/jungang/main/icon_svc01.png" alt="책드림서비스"></a></li>
					<li><a href="/jungang/10362/40165/content.do"><img
							src="./include/image/jungang/main/icon_svc02.png" alt="무인예약대출"></a></li>
					<li><a href="#link" id="ebookBtn"><img
							src="./include/image/jungang/main/icon_svc03.png" alt="전자책도서관"></a></li>
					<li><a href="/jungang/10012/40001/content.do"><img
							src="./include/image/jungang/main/icon_svc09.png" alt="희망도서신청"></a></li>
					<li><a href="/jungang/10553/baro/baroLogin.do"><img
							src="./include/image/jungang/main/icon_svc10.png" alt="지역서점도서관"></a></li>
					<li><a href="/jungang/10066/40147/content.do"><img
							src="./include/image/jungang/main/icon_svc05.png" alt="이용안내"></a></li>
					<!--<li><a href="/jungang/10073/40035/content.do"><img src="/include/image/jungang/main/icon_svc06.png" alt="찾아오시는길"></a></li>-->
					<li><a href="javascript:fnSeatMatePop();" title="새 창 열림"><img
							src="./include/image/jungang/main/icon_svc07.png" alt="열람실좌석현황"></a></li>
					<li><a href="javascript:fnLibMatePop();"><img
							src="./include/image/jungang/main/icon_svc08.png" alt="디지털정보실현황"
							title="새 창 열림"></a></li>
				</ul>
			</div>

			<div id="section3">
				<div class="sec3_1">


					<div class="mainHoli">
						<div class="holiHead themeColor">
							<h2>휴관일</h2>
							<p class="btnMore">
								<a href="/jungang/10046/30011/program.do">더보기</a>
							</p>
						</div>
						<div class="holiBody">
							<div class="holYM">
								<!-- 값 받아 와야함 -->
								<p class="holYear">2022</p>
								<p class="holMonth">6</p>
							</div>
							<!--VIEW-TODO001 for문 처리 해야 함 아래는 예시-->
							<ul>
								<li>01(수) <a href="#" title="정기휴관일">정기휴관일</a></li>
								<li>01(수) <a href="#" title="휴관: 지방선거">휴관: 지방선거</a></li>
								<li>06(월) <a href="#" title="휴관: 현충일">휴관: 현충일</a></li>
								<li>09(목) <a href="#" title="북스타트2기: 꼼지락꼼지락 동화읽기A">북스타트2기:
										꼼지락꼼지락 동화읽기A</a></li>
								<li>13(월) <a href="#" title="정기휴관일">정기휴관일</a></li>
								<li>16(목) <a href="#" title="북스타트2기: 꼼지락꼼지락 동화읽기A">북스타트2기:
										꼼지락꼼지락 동화읽기A</a></li>
								<li>20(월) <a href="#" title="정기휴관일">정기휴관일</a></li>
								<li>22(수) <a href="#" title="북스타트2기: 그림책으로 떠나는 감정여행">북스타트2기:
										그림책으로 떠나는 감정여행</a></li>
								<li>27(월) <a href="#" title="정기휴관일">정기휴관일</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="sec3_2">

					<div class="noticeArea">
						<h2 class="themeFC">공지사항</h2>
						<ul class="noticeList">




							<li><span class="noticex"><a
									href="/jungang/10041/20006/bbsDetail.do?bbsIdx=16421">[중앙도서관]
										6월 그림책 연계 육아도서 전시(1층)</a></span> <span class="date">2022-06-02</span></li>


							<li><span class="noticex"><a
									href="/jungang/10041/20006/bbsDetail.do?bbsIdx=16411">[사립작은도서관]
										공공-작은도서관 상호대차시스템 구축 및 서비스 제공 도서관 모집 공고</a></span> <span class="date">2022-06-02</span>
							</li>


							<li><span class="noticex"><a
									href="/jungang/10041/20006/bbsDetail.do?bbsIdx=16410">2022년
										청계숲고운도서관 기간제근로자 채용 공고</a></span> <span class="date">2022-06-02</span></li>


							<li><span class="noticex"><a
									href="/jungang/10041/20006/bbsDetail.do?bbsIdx=16407">지역서점
										지역화폐 소비지원금 안내(사용금액의10%마일리지로~)</a></span> <span class="date">2022-05-31</span>
							</li>


							<li><span class="noticex"><a
									href="/jungang/10041/20006/bbsDetail.do?bbsIdx=16398">[중앙]
										독서동아리 기본교육과정 「모락모락」 운영</a></span> <span class="date">2022-05-26</span></li>


						</ul>
						<p class="btnMore">
							<a href="/jungang/10041/20006/bbsList.do">더 보기</a>
						</p>
					</div>
				</div>

				<div class="sec3_3">



					<script>
						$(function() {

							var mySwiper1 = new Swiper('.swiper-book1', {
								loop : true,
								grabCursor : true,
								slidesPerView : 3,
								slidesPerGroup : 3,
								paginationClickable : true,
								spaceBetween : 30
							});

							$('.swiper-button-prev1').click(function() {
								mySwiper1.swipePrev();
								return false;
							});

							$('.swiper-button-next1').click(function() {
								mySwiper1.swipeNext();
								return false;
							});

							var mySwiper2 = new Swiper('.swiper-book2', {
								loop : true,
								grabCursor : true,
								slidesPerView : 3,
								slidesPerGroup : 3,
								paginationClickable : true,
								spaceBetween : 30
							});

							$('.swiper-button-prev2').click(function() {
								mySwiper2.swipePrev();
								return false;
							});

							$('.swiper-button-next2').click(function() {
								mySwiper2.swipeNext();
								return false;
							});

							var mySwiper3 = new Swiper('.swiper-book3', {
								loop : true,
								grabCursor : true,
								slidesPerView : 3,
								slidesPerGroup : 3,
								paginationClickable : true,
								spaceBetween : 30
							});

							$('.swiper-button-prev3').click(function() {
								mySwiper3.swipePrev();
								return false;
							});

							$('.swiper-button-next3').click(function() {
								mySwiper3.swipeNext();
								return false;
							});

							var $bookTab = $('.bookTabList'), $tabLink = $bookTab
									.find('h2 a'), $toggTab = $('.bookZone');

							$toggTab.first().css('z-index', '888');
							$tabLink.first().addClass('on');

							$tabLink.click(function() {
								$toggTab.hide();
								$tabLink.removeClass('on');
								$(this).addClass('on');
								$($(this).attr('href')).show();
								return false;
							});
						});

						function fnSearchNewBookResultDetail(regKey, bookKey,
								publishFormCode) {
							var form = document.newBookForm;
							form.recKey.value = regKey;
							form.bookKey.value = bookKey;
							form.publishFormCode.value = publishFormCode;
							form.action = "/jungang/10011/booksearch/searchNew.do";
							form.submit();
						}

						function fnSearchLoanBestResultDetail(regKey, bookKey,
								publishFormCode) {
							var form = document.loanBestBookForm;
							form.recKey.value = regKey;
							form.bookKey.value = bookKey;
							form.publishFormCode.value = publishFormCode;
							form.action = "/jungang/10384/booksearch/loanBest.do";
							form.submit();
						}
					</script>

					<form id="newBookForm" name="newBookForm" method="get">
						<input type="hidden" name="searchType" value="NEW"> <input
							type="hidden" name="recKey"> <input type="hidden"
							name="bookKey"> <input type="hidden"
							name="publishFormCode">
					</form>
					<form id="loanBestBookForm" name="loanBestBookForm" method="get">
						<input type="hidden" name="searchType" value="LOANBEST"> <input
							type="hidden" name="recKey"> <input type="hidden"
							name="bookKey"> <input type="hidden"
							name="publishFormCode">
					</form>
					<div class="bookGroup">
						<ul class="bookTabList">
							<li>
								<h2>
									<a href="#newarrival">신간도서</a>
								</h2>
								<div id="newarrival" class="bookZone">
									<div class="device-book">
										<div class="swiper-container swiper-book1">
											<div class="swiper-wrapper">


												<div class="swiper-slide">
													<a href="#link"
														onclick="javascript:fnSearchNewBookResultDetail('978698023','978698025','BO'); return false;"
														class="img"> <img
														src="https://bookthumb-phinf.pstatic.net/cover/224/326/22432645.jpg?type=m1&udate=20220430"
														alt="(최고 전문가들이 추천하는) 돈 되는 해외 ETF">
													</a> <span class="txt">(최고 전문가들이 추천하는) 돈 되는 해외 ETF</span>
												</div>

												<div class="swiper-slide">
													<a href="#link"
														onclick="javascript:fnSearchNewBookResultDetail('978698018','978698020','BO'); return false;"
														class="img"> <img
														src="https://bookthumb-phinf.pstatic.net/cover/223/615/22361587.jpg?type=m1&udate=20220518"
														alt="(게으르게 투자하고 확실하게 수익 내는) 나의 첫 ETF 포트폴리오">
													</a> <span class="txt">(게으르게 투자하고 확실하게 수익 내는) 나의 첫 ETF
														포트폴리오</span>
												</div>

												<div class="swiper-slide">
													<a href="#link"
														onclick="javascript:fnSearchNewBookResultDetail('978698013','978698015','BO'); return false;"
														class="img"> <img
														src="https://bookthumb-phinf.pstatic.net/cover/150/703/15070398.jpg?type=m1&udate=20190710"
														alt="치과의 거짓말  : 과잉 진료 치과 의사가 절대 말하지 않는 영업의 기술">
													</a> <span class="txt">치과의 거짓말 : 과잉 진료 치과 의사가 절대 말하지 않는
														영업의 기술</span>
												</div>

												<div class="swiper-slide">
													<a href="#link"
														onclick="javascript:fnSearchNewBookResultDetail('978698126','978698128','BO'); return false;"
														class="img"> <img
														src="https://bookthumb-phinf.pstatic.net/cover/175/473/17547367.jpg?type=m1&udate=20201208"
														alt="어휘천재의 비법노트  : 2단계">
													</a> <span class="txt">어휘천재의 비법노트 : 2단계</span>
												</div>

												<div class="swiper-slide">
													<a href="#link"
														onclick="javascript:fnSearchNewBookResultDetail('978698081','978698083','BO'); return false;"
														class="img"> <img
														src="https://bookthumb-phinf.pstatic.net/cover/175/473/17547366.jpg?type=m1&udate=20201209"
														alt="어휘천재의 비법노트  : 1단계">
													</a> <span class="txt">어휘천재의 비법노트 : 1단계</span>
												</div>

												<div class="swiper-slide">
													<a href="#link"
														onclick="javascript:fnSearchNewBookResultDetail('978698151','978698153','BO'); return false;"
														class="img"> <img
														src="https://bookthumb-phinf.pstatic.net/cover/124/305/12430503.jpg?type=m1&udate=20211225"
														alt="세계사천재의 비법노트  : 원시와 고대">
													</a> <span class="txt">세계사천재의 비법노트 : 원시와 고대</span>
												</div>

												<div class="swiper-slide">
													<a href="#link"
														onclick="javascript:fnSearchNewBookResultDetail('978698205','978698207','BO'); return false;"
														class="img"> <img
														src="https://bookthumb-phinf.pstatic.net/cover/208/474/20847467.jpg?type=m1&udate=20210819"
														alt="한국사천재의 비법노트  : 선사 시대부터 남북국 시대">
													</a> <span class="txt">한국사천재의 비법노트 : 선사 시대부터 남북국 시대</span>
												</div>

												<div class="swiper-slide">
													<a href="#link"
														onclick="javascript:fnSearchNewBookResultDetail('978698220','978698222','BO'); return false;"
														class="img"> <img
														src="https://bookthumb-phinf.pstatic.net/cover/121/948/12194812.jpg?type=m1&udate=20191022"
														alt="과학천재의 비법노트  : 생물">
													</a> <span class="txt">과학천재의 비법노트 : 생물</span>
												</div>

												<div class="swiper-slide">
													<a href="#link"
														onclick="javascript:fnSearchNewBookResultDetail('978698141','978698143','BO'); return false;"
														class="img"> <img
														src="https://bookthumb-phinf.pstatic.net/cover/175/473/17547364.jpg?type=m1&udate=20201209"
														alt="어휘천재의 비법노트  : 4단계">
													</a> <span class="txt">어휘천재의 비법노트 : 4단계</span>
												</div>


											</div>
										</div>
										<!-- Add Arrows -->

										<div class="button-next swiper-button-next1"></div>
										<div class="button-prev swiper-button-prev1"></div>

										<!-- more -->
									</div>
									<p class="btnMore">
										<a href="/jungang/10011/30003/program.do">더 보기</a>
									</p>
								</div>
							</li>
							<li>
								<h2>
									<a href="#storeBest">추천도서</a>
								</h2>
								<div id="storeBest" class="bookZone">
									<div class="device-book">
										<div class="swiper-container swiper-book2">
											<div class="swiper-wrapper">








												<div class="swiper-slide">
													<a
														href="/jungang/10042/book/recommendBookDetail.do?bookIdx=13082"
														class="img"> <img
														src="./attach/book/recommend/jungang20220602141305949.jpg"
														alt="날씨가 좋으면 찾아가겠어요  : 이도우 장편소설">
													</a> <span class="txt">날씨가 좋으면 찾아가겠어요 : 이도우 장편소설</span>
												</div>


												<div class="swiper-slide">
													<a
														href="/jungang/10042/book/recommendBookDetail.do?bookIdx=13081"
														class="img"> <img
														src="./attach/book/recommend/jungang20220602140929989.jpg"
														alt="천장이 높은 식당  : 이정연 장편소설">
													</a> <span class="txt">천장이 높은 식당 : 이정연 장편소설</span>
												</div>


												<div class="swiper-slide">
													<a
														href="/jungang/10042/book/recommendBookDetail.do?bookIdx=13036"
														class="img"> <img
														src="./attach/book/recommend/jungang20220512142745571.jpg"
														alt="빌리 밀러">
													</a> <span class="txt">빌리 밀러</span>
												</div>


												<div class="swiper-slide">
													<a
														href="/jungang/10042/book/recommendBookDetail.do?bookIdx=13035"
														class="img"> <img
														src="./attach/book/recommend/jungang20220512142703496.jpg"
														alt="경옥">
													</a> <span class="txt">경옥</span>
												</div>


												<div class="swiper-slide">
													<a
														href="/jungang/10042/book/recommendBookDetail.do?bookIdx=13014"
														class="img"> <img
														src="./attach/book/recommend/jungang20220502090659832.jpg"
														alt="공부의 쓸모  : 서울대 의대 수석의 혼공 바이블">
													</a> <span class="txt">공부의 쓸모 : 서울대 의대 수석의 혼공 바이블</span>
												</div>


												<div class="swiper-slide">
													<a
														href="/jungang/10042/book/recommendBookDetail.do?bookIdx=13013"
														class="img"> <img
														src="./attach/book/recommend/jungang20220502090552343.jpg"
														alt="날마다 만우절 : 윤성희 소설">
													</a> <span class="txt">날마다 만우절 : 윤성희 소설</span>
												</div>


												<div class="swiper-slide">
													<a
														href="/jungang/10042/book/recommendBookDetail.do?bookIdx=12936"
														class="img"> <img
														src="./attach/book/recommend/jungang20220403113612447.jpg"
														alt="단어의 여왕">
													</a> <span class="txt">단어의 여왕</span>
												</div>


												<div class="swiper-slide">
													<a
														href="/jungang/10042/book/recommendBookDetail.do?bookIdx=12935"
														class="img"> <img
														src="./attach/book/recommend/jungang20220403113527843.jpg"
														alt="너는 나의 모든 계절이야">
													</a> <span class="txt">너는 나의 모든 계절이야</span>
												</div>


												<div class="swiper-slide">
													<a
														href="/jungang/10042/book/recommendBookDetail.do?bookIdx=12934"
														class="img"> <img
														src="./attach/book/recommend/jungang20220401173629433.jpg"
														alt="어느 날 멀쩡하던 행거가 무너졌다">
													</a> <span class="txt">어느 날 멀쩡하던 행거가 무너졌다</span>
												</div>


											</div>
										</div>
										<!-- Add Arrows -->

										<div class="button-next swiper-button-next2"></div>
										<div class="button-prev swiper-button-prev2"></div>

										<!-- more -->
										<p class="btnMore">
											<a href="/jungang/10042/30009/program.do">더 보기</a>
										</p>
									</div>
								</div>
							</li>
							<li>
								<h2>
									<a href="#cateBest">대출베스트</a>
								</h2>
								<div id="cateBest" class="bookZone">
									<div class="device-book">
										<div class="swiper-container swiper-book3">
											<div class="swiper-wrapper">


												<div class="swiper-slide">
													<a href="#link"
														onclick="javascript:fnSearchLoanBestResultDetail('962725979','962725981','BO'); return false;"
														class="img"> <img
														src="https://bookthumb-phinf.pstatic.net/cover/180/044/18004469.jpg?type=m1&udate=20210316"
														alt="(흔한남매)불꽃 튀는 우리말. 1">
													</a> <span class="txt">(흔한남매)불꽃 튀는 우리말. 1</span>
												</div>

												<div class="swiper-slide">
													<a href="#link"
														onclick="javascript:fnSearchLoanBestResultDetail('963059952','963059954','BO'); return false;"
														class="img"> <img
														src="https://bookthumb-phinf.pstatic.net/cover/179/367/17936710.jpg?type=m1&udate=20210607"
														alt="말이야와 친구들 : 본격 가족 명랑 시트콤 만화. 1">
													</a> <span class="txt">말이야와 친구들 : 본격 가족 명랑 시트콤 만화. 1</span>
												</div>

												<div class="swiper-slide">
													<a href="#link"
														onclick="javascript:fnSearchLoanBestResultDetail('972433660','972433662','BO'); return false;"
														class="img"> <img
														src="https://bookthumb-phinf.pstatic.net/cover/211/666/21166638.jpg?type=m1&udate=20211108"
														alt="비밀요원 레너드. 9, 산타클로스는 정말 있을까?">
													</a> <span class="txt">비밀요원 레너드. 9, 산타클로스는 정말 있을까?</span>
												</div>

												<div class="swiper-slide">
													<a href="#link"
														onclick="javascript:fnSearchLoanBestResultDetail('965033970','965033972','BO'); return false;"
														class="img"> <img
														src="https://bookthumb-phinf.pstatic.net/cover/170/131/17013150.jpg?type=m1&udate=20201203"
														alt="에그박사  : 자연 생물 관찰 만화. 2">
													</a> <span class="txt">에그박사 : 자연 생물 관찰 만화. 2</span>
												</div>

												<div class="swiper-slide">
													<a href="#link"
														onclick="javascript:fnSearchLoanBestResultDetail('968814981','968814983','BO'); return false;"
														class="img"> <img
														src="https://bookthumb-phinf.pstatic.net/cover/137/459/13745994.jpg?type=m1&udate=20191013"
														alt="(무엇이든 해결단)허팝 연구소. 1, 우당탕 시청 대소동">
													</a> <span class="txt">(무엇이든 해결단)허팝 연구소. 1, 우당탕 시청 대소동</span>
												</div>

												<div class="swiper-slide">
													<a href="#link"
														onclick="javascript:fnSearchLoanBestResultDetail('972434098','972434100','BO'); return false;"
														class="img"> <img
														src="https://bookthumb-phinf.pstatic.net/cover/212/922/21292291.jpg?type=m1&udate=20220106"
														alt="(흔한남매)겨울밤 대소동">
													</a> <span class="txt">(흔한남매)겨울밤 대소동</span>
												</div>

												<div class="swiper-slide">
													<a href="#link"
														onclick="javascript:fnSearchLoanBestResultDetail('972437388','972437390','BO'); return false;"
														class="img"> <img
														src="https://bookthumb-phinf.pstatic.net/cover/212/961/21296168.jpg?type=m1&udate=20211202"
														alt="웃소. 2">
													</a> <span class="txt">웃소. 2</span>
												</div>

												<div class="swiper-slide">
													<a href="#link"
														onclick="javascript:fnSearchLoanBestResultDetail('973288009','973266291','BO'); return false;"
														class="img"> <img
														src="https://bookthumb-phinf.pstatic.net/cover/207/679/20767908.jpg?type=m1&udate=20210729"
														alt="어몽어스 크루원의 일기. 2, 임포스터의 복수">
													</a> <span class="txt">어몽어스 크루원의 일기. 2, 임포스터의 복수</span>
												</div>

												<div class="swiper-slide">
													<a href="#link"
														onclick="javascript:fnSearchLoanBestResultDetail('930454522','930454524','BO'); return false;"
														class="img"> <img
														src="https://bookthumb-phinf.pstatic.net/cover/134/954/13495493.jpg?type=m1&udate=20180411"
														alt="Go Go 카카오프렌즈. 2, 영국">
													</a> <span class="txt">Go Go 카카오프렌즈. 2, 영국</span>
												</div>


											</div>
										</div>
										<!-- Add Arrows -->

										<div class="button-next swiper-button-next3"></div>
										<div class="button-prev swiper-button-prev3"></div>

										<!-- more -->
										<p class="btnMore">
											<a href="/jungang/10384/30097/program.do">더 보기</a>
										</p>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@include file="./footer.jsp"%>
</html>