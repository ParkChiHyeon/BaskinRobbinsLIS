<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="의왕시중앙도서관">
<meta name="description" content="의왕시중앙도서관 소개, 자료검색, 프로그램신청 등">
<meta name="generator" content="jnet co. ltd.">
<meta property="og:type" content="website">
<meta property="og:title" content="의왕시중앙도서관">
<meta property="og:description" content="의왕시중앙도서관 정보 및 도서검색 제공">
<meta property="og:url" content="http://www.uwlib.or.kr/jungang/main.do">
<title>의왕시중앙도서관 책마루</title>
<link href="./include/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="./include/css/jungang/sub.css" rel="stylesheet" type="text/css"/>
<script src="./include/js/jquery-1.12.2.min.js"></script>
<script src="./include/js/jquery.stringify.js"></script>
<script src="./include/js/common.js"></script>
<script src="./include/js/gnb.js"></script>
<script src="./include/js/jquery.cookie.js"></script>
<script>
	var $ctx = "";
</script>

<link rel="stylesheet" href="./include/booksearch/css/search.css" type="text/css" />
<link rel="stylesheet" href="./include/booksearch/css/jquery-ui.css" type="text/css" />

<script src="./include/booksearch/js/jquery-ui.min.js"></script>
<script src="./include/booksearch/js/jquery.ui.datepicker-ko.js"></script>

<%@ include file="./header.jsp" %>
</head>
<body class="body">

	<ul id="topmenu">
				
							
	<li id="gnb7"><a href="./jungang/10075/30014/program.do" >내서재</a>
			<ul>
			<li id="gnb7_1" class=""><a href="/jungang/10075/30014/program.do" >대출/예약/이력</a>
					<ul class="MkSub">
					<li id="gnb7_1_1" class=""><a href="./jungang/10075/30014/program.do" >대출현황</a>
							</li>
						<li id="gnb7_1_2" class=""><a href="./jungang/10076/30015/program.do" >예약현황</a>
							</li>
						<li id="gnb7_1_3" class=""><a href="./jungang/10077/30016/program.do" >대출이력</a>
							</li>
						</ul>
					</li>
				<li id="gnb7_2" class=""><a href="./jungang/10079/30017/program.do" >희망도서신청</a>
					<ul class="MkTab">
					<li id="gnb7_2_1" class=""><a href="./jungang/10079/30017/program.do" >신청하기</a>
							</li>
						<li id="gnb7_2_2" class=""><a href="./jungang/10080/30018/program.do" >신청조회</a>
							</li>
						</ul>
					</li>
				<li id="gnb7_3" class=""><a href="./jungang/10081/30019/program.do" >관심도서</a>
					</li>
				<li id="gnb7_4" class=""><a href="./jungang/10286/30088/program.do" >책드림현황</a>
					<ul class="MkSub">
					<li id="gnb7_4_1" class=""><a href="./jungang/10286/30088/program.do" >신청조회</a>
							</li>
						<li id="gnb7_4_2" class=""><a href="./jungang/10287/30089/program.do" >신청이력조회</a>
							</li>
						</ul>
					</li>
				<li id="gnb7_5" class=""><a href="./jungang/10084/30021/program.do" >온라인신청</a>
					<ul class="MkSub">
					<li id="gnb7_5_1" class=""><a href="./jungang/10084/30021/program.do" >문화강좌</a>
							</li>
						<li id="gnb7_5_2" class=""><a href="./jungang/10085/30022/program.do" >도서관견학</a>
							</li>
						<li id="gnb7_5_3" class=""><a href="./jungang/10086/30023/program.do" >체험형동화구연</a>
							</li>
						</ul>
					</li>
				<li id="gnb7_6" class="mobileHide"><a href="./jungang/10088/30024/program.do" >나의게시글</a>
					<ul class="MkTab">
					<li id="gnb7_6_1" class="mobileHide"><a href="./jungang/10088/30024/program.do" >질문및답변</a>
							</li>
						<li id="gnb7_6_2" class="mobileHide"><a href="./jungang/10089/30025/program.do" >도서관에바란다</a>
							</li>
						<li id="gnb7_6_3" class="mobileHide"><a href="./jungang/10090/30026/program.do" >자유게시판</a>
							</li>
						</ul>
					</li>
				<li id="gnb7_11" class=""><a href="./jungang/10389/30101/program.do" >도서추천서비스</a>
					<ul class="MkSub">
					<li id="gnb7_11_1" class=""><a href="./jungang/10389/30101/program.do" >나만의 인기도서</a>
							</li>
						<li id="gnb7_11_2" class=""><a href="./jungang/10390/30102/program.do" >나만의 추천도서</a>
							</li>
						</ul>
					</li>
				</ul>
			</li>
	<li id="gnb8"><a href="./jungang/10091/40013/content.do" >이용안내</a>
			<ul>
			<li id="gnb8_1" class=""><a href="./jungang/10091/40013/content.do" >사이트맵</a>
					</li>
				<li id="gnb8_2" class=""><a href="./jungang/10092/40014/content.do" >개인정보처리방침</a>
					</li>
				<li id="gnb8_3" class=""><a href="./jungang/10093/40015/content.do" >이메일무단수집거부</a>
					</li>
				</ul>
			</li>
	<li id="gnb9"><a href="./jungang/10094/30027/program.do" >회원</a>
			<ul>
			<li id="gnb9_1" class=""><a href="./jungang/10094/30027/program.do" >로그인</a>
					</li>
				<li id="gnb9_2" class=""><a href="./member/join.do"  target="_self">회원가입</a>
					</li>
				<li id="gnb9_3" class=""><a href="./member/findId.do"  target="_self">아이디찾기</a>
					</li>
				<li id="gnb9_4" class=""><a href="./member/findPwd.do"  target="_self">비밀번호 재발급</a>
					</li>
				</ul>
			</li>
			</ul>	
				
	<div id="middle">
		<div id="contbody">
		
		<div id="contleft" class="contleft">
			<h2>1뎁스메뉴</h2>
			<div class="lnbBody">
				<ul id="lnb" class="lnbul">
					<li></li>
				</ul>
				<div class="lnbBottom"></div>
			</div>
		</div>
			<div id="contentcore">
			
				<div class="naviandtitle">
					<h3>페이지타이틀</h3>
					<div class="navi"></div>
				</div>
				<ul class="snsFarm">
					<li><a href="#sns4" id="sns4" onclick="shareTwitter('공유하기','http://www.uwlib.or.kr/jungang/10075/mylibrary/loan/loanStatusList.do'); return false;" title="새 창 열림"><img src="./include/image/common/twitter.png" alt="트위터 글보내기" /></a></li>
					<li><a href="#sns3" id="sns3" onclick="shareFaceBook('공유하기','http://www.uwlib.or.kr/jungang/10075/mylibrary/loan/loanStatusList.do'); return false;" title="새 창 열림"><img src="./include/image/common/facebook.png" alt="페이스북 글보내기" /></a></li>
				</ul>

				<div class="contents" id="contents">
					


			<div class="ttabWrap">
				<ul id="ttab3" class="tabType5">
					<li></li>
				</ul>
			</div>
					<!--Real Contents Start-->
					<script type="text/javascript" src="./include/booksearch/js/iframeResizer.min.js"></script>
					<!--[if lte IE 8]>
					    <script type="text/javascript" src="./include/booksearch/js/ie8.polyfils.min.js"></script>
					<![endif]-->
					<script type="text/javascript">
					$(document).ready(function(e) {
						$("iframe").iFrameResize({
							log: false,
							autoResize: true,
							heightCalculationMethod: 'grow',
							doHeight: true,
							doWidth: false,
							enablePublicMethods: true,
							interval: 0,
							scrolling: false,
							callback:function(messageData){
							}
						});
					});
					</script>
					<iframe src="./kolaseek/mylibrary/loan/loanStatusList.do" style="width:100%" scrolling="no" frameborder="0" title="대출현황"></iframe>

					


<div class="box-area2 mt30">
	<p class="ta_c nanum fnt13px">
		<span class="mr40">
			<img src="./include/image/global/common/icon_fax.png"  alt="콘텐츠 관리부서" class="" /> 콘텐츠 관리부서 : 중앙도서관팀
			/ 담당자 : 문헌정보실
		</span>
		<span><img src="./include/image/global/common/icon_tel.png"  alt="전화번호" class=""/> 전화번호 : 031-345-3656</span>
	</p>
</div>

					<!--Real Contents End-->

				</div>
			</div>
		</div>
	</div>
	

	<iframe name="hiddenFrame" id="hiddenFrame" title="빈프레임" class="hide"></iframe>
<script>
$(function(){
	$("#goBtn").click(function(){
		var url = $("#relateSite").val();
		if(!$("#relateSite").val()){
			alert("관련기관을 선택하세요.");
			$("#relateSite").focus();
			return false;
		}else{
			window.open(url,"_blank");
		}
	});
});
</script>
	<!--footer-->
	<%@ include file="./footer.jsp" %>
	<!--footer-->
<script src="https://wcs.naver.net/wcslog.js"></script>
<script>
if(!wcs_add) var wcs_add = {};
wcs_add["wa"] = "4eae186ccb33d4";
wcs_do();
</script>
<script type="text/javascript">initMenu(7,1,1,0,0);</script>
</body>