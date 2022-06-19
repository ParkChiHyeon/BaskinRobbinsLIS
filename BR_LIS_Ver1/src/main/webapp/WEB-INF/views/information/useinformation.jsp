<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
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
	<div id="contbody">
		<div id="contleft" class="contleft">
			<h2>도서관소개</h2>
			<div class="lnbBody" style="min-height: 529px;">
				<ul id="lnb" class="lnbul">
					<li id="lnb6_1" class=""><a href="./informationLib.do?kind=libguide" >도서관안내</a></li>
					<li id="lnb6_4" class=""><a href="./informationLib.do?kind=useguide" class="current">이용안내</a></li>
					<li id="lnb6_5" class=""><a href="./informationLib.do?kind=locguide">찾아 오시는길</a></li>
					<li id="lnb6_4" class=""><a href="./informationLib.do?kind=siteMap">사이트맵</a></li>
				</ul>
				<div class="lnbBottom"></div>
			</div>
		</div>

		<div id="contentcore">

			<div class="naviandtitle" style="padding-bottom: 0px;">
				<h3>이용시간/휴관일</h3>
				<div class="navi">
					<a href="./home.do"><img src="./include/image/global/common/icon_home.gif" alt="홈"></a>
										도서관소개<span class="mobileHide"> &gt; <strong>이용안내</strong></span>
				</div>
			</div>

			<div class="ttabWrap">
				<ul id="ttab3" class="tabType5">
					<li class="ttabLast"></li>
				</ul>
			</div>

			<!--Real Contents Start-->


			<div class="timeWrap">
				<img src="./img/booktree.png" style="float: right; width: 400px;">
				<h4 class="htitle"> <strong>이용시간</strong></h4>
				<ul class="guide">
					<li><strong class="themeFC">자료실</strong> : 09:00 ~ 21:00
						(토,일요일 : 09:00 ~ 18:00)</li>
				</ul>
				<h4 class="htitle"> <strong>휴관일</strong></h4>
				<ul class="guide">
					<li><strong class="themeFC">자료실</strong>: 매주 금요일, 일요일을 제외한 법정공휴일 <br> 
					&emsp;&emsp;&emsp;&emsp;&emsp;(일요일과 중복되는 경우 휴관)</li>
				</ul>
			</div>

		</div>
	</div>
</body>
<%@include file="../footer.jsp"%>
</html>