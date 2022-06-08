<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
   
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>woowang Library</title>
<link rel="icon" href="./include/image/favicon.ico" type="image/x-icon">
<link href="./include/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="./include/css/jungang/sub.css" rel="stylesheet" type="text/css"/>
<link href="./include/css/jungang/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"  src="https://code.jquery.com/jquery-3.6.0.js"></script>
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
					<li><a href="https://search.daum.net/search?w=book" target="_blank">다음 책</a></li>
					<li ><a href="https://www.aladin.co.kr/" target="_blank">알라딘</a></li>
				</ul>
			</div>
			<div id="siteMenu" class="siteMenu">
				<ul class="memUtil">
							<c:if test="${member.auth eq 'NM'|| member.auth eq 'GH'}">
								<li>
								<a>로그인 정보 :${member.member_id}[${member.auth == 'NM'?"일반회원":"관내회원"}]</a>
								<li><a href="./logout.do"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
								</li>
							</c:if>
							
							<c:if test="${admin.admin_id != null}">
								<li>
								<a>로그인 정보 :${admin.admin_id}[관리자]</a>
								<li><a href="./logout.do"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
								</li>
							</c:if>
							
							<c:if test="${member.auth eq null && admin.admin_id == null }"> 
							<li><a href="./loginPage.do">로그인</a></li>
							</c:if>
							
							<c:if test="${member.auth eq null && admin.admin_id==null }"> 
							<li class="mobileHide"><a href="./signUpPage.do">회원가입</a></li>
							</c:if>
							
					<li class="mobileHide"><a href="/jungang/10091/40013/content.do">사이트맵</a></li>
				</ul>
			</div>
			
			<div id="BtnmobileIndex"><a href="#llink" id="BtnmobileIndexa"><img src="./include/image/common/btn_menu.png" alt="메뉴 열기"><span>메뉴</span></a></div>
			<div id="btnSearchTop"><a href="#link"><img src="./include/image/common/btn_serachTop.png" alt="검색창 열기"><span>검색</span></a></div>
			<div id="indexNavi">
	<ul id="topmenu">
	<li id="gnb1"><a href="./bookSearch.do" >자료검색</a>
			<ul>
			<li id="gnb1_1" class=""><a href="./bookSearch.do?kind=total" >통합검색</a>
					</li>
				<li id="gnb1_2" class=""><a href="./bookSearch.do?kind=detail" >상세검색</a>
					</li>
				<li id="gnb1_3" class=""><a href="./bookSearch.do?kind=request" >희망도서신청</a>
					</li>
				</ul>
			</li>
	<li id="gnb2"><a href="/jungang/10018/40002/content.do" >도서관서비스</a>
			<ul>
			<li id="gnb2_1" class=""><a href="/jungang/10018/40002/content.do" >책드림서비스</a>
					</li>
				<li id="gnb2_2" class=""><a href="/jungang/10362/40165/content.do" >무인예약대출서비스</a>
					</li>
				<li id="gnb2_3" class=""><a href="/jungang/10592/40213/content.do" >스마트도서관</a>
					<ul class="MkTab">
					<li id="gnb2_3_1" class=""><a href="/jungang/10592/40213/content.do" >이용안내</a>
							</li>
						<li id="gnb2_3_2" class=""><a href="/jungang/10593/30162/program.do" >자료검색</a>
							</li>
						</ul>
					</li>
				<li id="gnb2_4" class=""><a href="/jungang/10294/40071/content.do" >책이음서비스</a>
					</li>
				<li id="gnb2_5" class="mobileHide"><a href="/jungang/10297/20033/bbsList.do" >학교도서관상호대차</a>
					</li>
				<li id="gnb2_6" class=""><a href="/jungang/10019/40003/content.do" >내생애첫도서관</a>
					</li>
				<li id="gnb2_7" class=""><a href="/jungang/10020/40073/content.do" >두루두루서비스</a>
					</li>
				<li id="gnb2_8" class=""><a href="/jungang/10021/40005/content.do" >책바다서비스</a>
					</li>
				<li id="gnb2_9" class=""><a href="/jungang/10024/40021/content.do" >도서관시상제도</a>
					<ul class="MkTab">
					<li id="gnb2_9_1" class=""><a href="/jungang/10024/40021/content.do" >책읽는가족</a>
							</li>
						<li id="gnb2_9_2" class=""><a href="/jungang/10025/40022/content.do" >다독상</a>
							</li>
						</ul>
					</li>
				<li id="gnb2_11" class=""><a href="/jungang/10600/40229/content.do" >무인도서반납서비스</a>
					</li>
				</ul>
			</li>
	<li id="gnb3"><a href="/jungang/10028/30004/program.do" >문화마당</a>
			<ul>
			<li id="gnb3_1" class=""><a href="/jungang/10028/30004/program.do" >문화강좌신청</a>
					<ul class="MkTab">
					<li id="gnb3_1_1" class=""><a href="/jungang/10028/30004/program.do" >중앙</a>
							</li>
						<li id="gnb3_1_2" class=""><a href="/jungang/10029/30004/program.do" >내손</a>
							</li>
						<li id="gnb3_1_3" class=""><a href="/jungang/10030/30004/program.do" >글로벌</a>
							</li>
						<li id="gnb3_1_4" class=""><a href="/jungang/10526/30004/program.do" >포일어울림</a>
							</li>
						<li id="gnb3_1_5" class=""><a href="/jungang/10703/30004/program.do" >백운호수</a>
							</li>
						</ul>
					</li>
				<li id="gnb3_2" class=""><a href="/jungang/10031/30005/program.do" >체험형동화구연</a>
					</li>
				<li id="gnb3_3" class=""><a href="/jungang/10032/30006/program.do" >어린이책마루 견학</a>
					</li>
				<li id="gnb3_4" class=""><a href="/jungang/10034/30083/program.do" >토요동화구연</a>
					</li>
				<li id="gnb3_5" class=""><a href="/jungang/10035/30007/program.do" >영화상영</a>
					</li>
				</ul>
			</li>
	<li id="gnb4"><a href="./noticeboard.do" >정보마당</a>
			<ul>
			<li id="gnb4_1" class=""><a href="./noticeboard.do" >공지사항</a>
					</li>
				<li id="gnb4_2" class=""><a href="./faqboard.do" >FAQ</a>
					</li>
				<li id="gnb4_3" class=""><a href="./calendarboard.do" >도서관일정</a>
					</li>
				</ul>
			</li>
	<li id="gnb5"><a href="/jungang/10048/20026/bbsList.do" >참여마당</a>
			<ul>
			<li id="gnb5_1" class=""><a href="/jungang/10048/20026/bbsList.do" >도서관에바란다</a>
					</li>
				<li id="gnb5_2" class=""><a href="/jungang/10049/20028/bbsList.do" >질문및답변</a>
					</li>
				<li id="gnb5_3" class=""><a href="/jungang/10050/20010/bbsList.do" >자주하는질문</a>
					</li>
				<li id="gnb5_4" class=""><a href="/jungang/10268/40006/content.do" >도서관동아리</a>
					<ul class="MkTab">
					<li id="gnb5_4_1" class=""><a href="/jungang/10268/40006/content.do" >중앙</a>
							</li>
						<li id="gnb5_4_2" class=""><a href="/jungang/10269/40007/content.do" >내손</a>
							</li>
						<li id="gnb5_4_3" class=""><a href="/jungang/10270/40008/content.do" >글로벌</a>
							</li>
						</ul>
					</li>
				<li id="gnb5_5" class=""><a href="/jungang/10052/20011/bbsList.do" >자유게시판</a>
					</li>
				</ul>
			</li>
	<li id="gnb6"><a href="/jungang/10054/40025/content.do" >도서관소개</a>
			<ul>
			<li id="gnb6_1" class=""><a href="/jungang/10054/40025/content.do" >도서관안내</a>
					<ul class="MkSub">
					<li id="gnb6_1_1" class=""><a href="/jungang/10054/40025/content.do" >인사말</a>
							</li>
						<li id="gnb6_1_2" class=""><a href="/jungang/10055/40026/content.do" >연혁</a>
							</li>
						<li id="gnb6_1_3" class=""><a href="/jungang/10056/30012/program.do" >조직도</a>
							</li>
						<li id="gnb6_1_4" class=""><a href="/jungang/10057/40009/content.do" >도서관행정서비스헌장</a>
							</li>
						</ul>
					</li>
				<li id="gnb6_2" class=""><a href="/jungang/10343/40027/content.do" >시설현황</a>
					<ul class="MkSub">
					<li id="gnb6_2_1" class=""><a href="/jungang/10343/40027/content.do" >전체 안내</a>
							<ul class="MkTab">
							<li id="gnb6_2_1_1" class="mobileHide"><a href="/jungang/10343/40027/content.do" >층별안내</a></li>
								<li id="gnb6_2_1_2" class="mobileHide"><a href="/jungang/10344/40143/content.do" >자료실현황</a></li>
								</ul>
							</li>
						<li id="gnb6_2_2" class=""><a href="/jungang/10061/40028/content.do" >향토사료관</a>
							<ul class="MkTab">
							<li id="gnb6_2_2_1" class=""><a href="/jungang/10061/40028/content.do" >안내</a></li>
								<li id="gnb6_2_2_2" class=""><a href="/jungang/10062/40029/content.do" >전시정보</a></li>
								</ul>
							</li>
						<li id="gnb6_2_3" class=""><a href="/jungang/10063/40030/content.do" >숲속도서관</a>
							</li>
						</ul>
					</li>
				<li id="gnb6_3" class=""><a href="/jungang/10278/30013/program.do" >자료현황</a>
					<ul class="MkTab">
					<li id="gnb6_3_1" class="mobileHide"><a href="/jungang/10278/30013/program.do" >중앙도서관</a>
							</li>
						</ul>
					</li>
				<li id="gnb6_4" class=""><a href="/jungang/10066/40147/content.do" >이용안내</a>
					<ul class="MkSub">
					<li id="gnb6_4_1" class=""><a href="/jungang/10066/40147/content.do" >이용시간/휴관일</a>
							</li>
							<c:if test="${member.auth eq null}"> 
						<li id="gnb6_4_2" class=""><a href="/jungang/10067/40081/content.do" >회원가입</a>
							</li>
							</c:if>
						<li id="gnb6_4_3" class=""><a href="/jungang/10068/40011/content.do" >관외대출</a>
							</li>
						<li id="gnb6_4_4" class=""><a href="/jungang/10069/40032/content.do" >반디움</a>
							</li>
						<li id="gnb6_4_5" class=""><a href="/jungang/10070/40033/content.do" >자동화기기사용법</a>
							</li>
						<li id="gnb6_4_6" class=""><a href="/jungang/10071/40012/content.do" >도서관예절</a>
							</li>
						<li id="gnb6_4_7" class=""><a href="/jungang/10072/40034/content.do" >편의시설</a>
							</li>
						</ul>
					</li>
				<li id="gnb6_5" class=""><a href="/jungang/10073/40035/content.do" >찾아오시는길</a>
					</li>
				</ul>
			</li>
	<li id="gnb7"><a href="/jungang/10075/30014/program.do" >내서재</a>
			<ul>
			<li id="gnb7_1" class=""><a href="/jungang/10075/30014/program.do" >대출/예약/이력</a>
					<ul class="MkSub">
					<li id="gnb7_1_1" class=""><a href="/jungang/10075/30014/program.do" >대출현황</a>
							</li>
						<li id="gnb7_1_2" class=""><a href="/jungang/10076/30015/program.do" >예약현황</a>
							</li>
						<li id="gnb7_1_3" class=""><a href="/jungang/10077/30016/program.do" >대출이력</a>
							</li>
						</ul>
					</li>
				<li id="gnb7_2" class=""><a href="/jungang/10079/30017/program.do" >희망도서신청</a>
					<ul class="MkTab">
					<li id="gnb7_2_1" class=""><a href="/jungang/10079/30017/program.do" >신청하기</a>
							</li>
						<li id="gnb7_2_2" class=""><a href="/jungang/10080/30018/program.do" >신청조회</a>
							</li>
						</ul>
					</li>
				<li id="gnb7_3" class=""><a href="/jungang/10081/30019/program.do" >관심도서</a>
					</li>
				<li id="gnb7_4" class=""><a href="/jungang/10286/30088/program.do" >책드림현황</a>
					<ul class="MkSub">
					<li id="gnb7_4_1" class=""><a href="/jungang/10286/30088/program.do" >신청조회</a>
							</li>
						<li id="gnb7_4_2" class=""><a href="/jungang/10287/30089/program.do" >신청이력조회</a>
							</li>
						</ul>
					</li>
				<li id="gnb7_5" class=""><a href="/jungang/10084/30021/program.do" >온라인신청</a>
					<ul class="MkSub">
					<li id="gnb7_5_1" class=""><a href="/jungang/10084/30021/program.do" >문화강좌</a>
							</li>
						<li id="gnb7_5_2" class=""><a href="/jungang/10085/30022/program.do" >도서관견학</a>
							</li>
						<li id="gnb7_5_3" class=""><a href="/jungang/10086/30023/program.do" >체험형동화구연</a>
							</li>
						</ul>
					</li>
				<li id="gnb7_6" class="mobileHide"><a href="/jungang/10088/30024/program.do" >나의게시글</a>
					<ul class="MkTab">
					<li id="gnb7_6_1" class="mobileHide"><a href="/jungang/10088/30024/program.do" >질문및답변</a>
							</li>
						<li id="gnb7_6_2" class="mobileHide"><a href="/jungang/10089/30025/program.do" >도서관에바란다</a>
							</li>
						<li id="gnb7_6_3" class="mobileHide"><a href="/jungang/10090/30026/program.do" >자유게시판</a>
							</li>
						</ul>
					</li>
				<li id="gnb7_11" class=""><a href="/jungang/10389/30101/program.do" >도서추천서비스</a>
					<ul class="MkSub">
					<li id="gnb7_11_1" class=""><a href="/jungang/10389/30101/program.do" >나만의 인기도서</a>
							</li>
						<li id="gnb7_11_2" class=""><a href="/jungang/10390/30102/program.do" >나만의 추천도서</a>
							</li>
						</ul>
					</li>
				</ul>
			</li>
	<li id="gnb8"><a href="/jungang/10091/40013/content.do" >이용안내</a>
			<ul>
			<li id="gnb8_1" class=""><a href="/jungang/10091/40013/content.do" >사이트맵</a>
					</li>
				<li id="gnb8_2" class=""><a href="/jungang/10092/40014/content.do" >개인정보처리방침</a>
					</li>
				<li id="gnb8_3" class=""><a href="/jungang/10093/40015/content.do" >이메일무단수집거부</a>
					</li>
				</ul>
			</li>
	<li id="gnb9"><a href="/jungang/10094/30027/program.do" >회원</a>
			<ul>
			<c:if test="${member.auth eq null}"> 
			<li id="gnb9_1" class=""><a href="/jungang/10094/30027/program.do" >로그인</a>
					</li>
					</c:if>
				<c:if test="${member.auth eq null}"> 	
				<li id="gnb9_2" class=""><a href="/member/join.do"  target="_self">회원가입</a>
					</li>
					</c:if>
				<li id="gnb9_3" class=""><a href="/member/findId.do"  target="_self">아이디찾기</a>
					</li>
				<li id="gnb9_4" class=""><a href="/member/findPwd.do"  target="_self">비밀번호 재발급</a>
					</li>
				</ul>
			</li>
	</ul>
	<div id="submenuArea">
		<div id="subvisuala"></div>
	</div>
</div>
		</div>
	</div>
	
	
	<!--Top SerachBox -->
	<div id="searchBoxArea" style="height:0px; display:none">
		<div id="searchBox">
			<div class="searchBox">
				<form name="topSearchForm" id="topSearchForm" method="post" action="/jungang/10010/booksearch/searchSimple.do">
					<input type="hidden" name="searchLibraryArr" value="MA" />
					<div class="bgWhite">
						<select name="searchKey" title="검색방식선택">
							<option value="ALL" selected="selected">전체</option>
							<option value="TITLE">서명</option>
							<option value="AUTHOR">저자</option>
							<option value="PUBLISHER">출판사</option>
							<option value="KEYWORD">키워드</option>
						</select>
						<input type="text" name="searchKeyword" id="topSearchKeyword" class="schInput1" title="검색어 입력" />
					</div>
					<div class="btnRight"> <span class="themeBtn"><a href="javascript:fnTopSearch();">도서검색</a></span> </div>
				</form>
			</div>
		</div>
	</div>
	<!--Top SerachBox -->
	
</div>
<script>initMenu(0,0,0,0);</script>
</body>
</html>