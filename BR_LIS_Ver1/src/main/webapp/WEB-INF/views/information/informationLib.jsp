<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="../header.jsp"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>


	<!--  -->

	<div id="contbody">



		<div id="contleft" class="contleft">
			<h2>도서관소개</h2>
			<div class="lnbBody" style="min-height: 529px;">
				<ul id="lnb" class="lnbul">
					<li id="lnb6_1" class=""><a
						href="./informationLib.do?kind=libguide" class="current">도서관안내</a></li>
					<li id="lnb6_4" class=""><a
						href="./informationLib.do?kind=useguide">이용안내</a></li>
					<li id="lnb6_5" class=""><a
						href="./informationLib.do?kind=locguide">찾아오시는길</a></li>
				</ul>
				<div class="lnbBottom"></div>
			</div>
		</div>

		<div id="contentcore">


			<div class="naviandtitle">
				<h3>인사말</h3>
				<div class="navi">
					<a href="/jungang/main.do"><img
						src="/include/image/global/common/icon_home.gif" alt="홈"></a>
					도서관소개<span class="mobileHide"> &gt; 도서관안내 &gt; <strong>인사말</strong></span>
				</div>
			</div>

			<div class="contents" id="contents">



				<div class="ttabWrap">
					<ul id="ttab3" class="tabType5">
						<li class="ttabLast"></li>
					</ul>
				</div>

				<!--Real Contents Start-->


				<img src="/img/img_greeting.jpg" alt=" 행복하고 새로운 우왕도서관 방문을 환영합니다.">
				<div class="greetingWrap">
					<p class="mb30">
						「우왕도서관」 방문을 환영합니다.<br>
						<br>책읽기 좋은 쾌적한 도서관, 온가족이 행복한 쉼터도서관, 꿈과 미래를 실현하는 공작소로 새롭게
						단장하였습니다.<br>
						<br>시민여러분이 도서관에서 다양한 정보와 문화를 향유하며, 함께 성장하고 동행할 수 있도록 노력하고
						있습니다.<br>
						<br>많은 관심과 이용을 부탁드립니다. 감사합니다.
					</p>
					<p class="ta_r">BaskinRobins31 우왕도서관장</p>
				</div>



				<!--Real Contents End-->






			</div>
		</div>
	</div>

</body>
<%@include file="../footer.jsp"%>
</html>