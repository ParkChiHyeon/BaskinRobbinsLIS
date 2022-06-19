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
			<div id="contentcore">
				<div class="naviandtitle">
					<h3>찾아오시는길</h3>
					<div class="navi">
						<a href="/jungang/main.do"><img
							src="/include/image/global/common/icon_home.gif" alt="홈"></a>
						도서관소개<span class="mobileHide"> &gt; <strong>찾아오시는길</strong></span>
					</div>
				</div>
				<div class="contents" id="contents">
					<div class="ttabWrap">
						<ul id="ttab3" class="tabType5">
							<li class="ttabLast"></li>
						</ul>
					</div>
					<!--Real Contents Start-->
					<!--<div class="mapWrap" id="map">
						<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=da1930d361ad70f935207e05ac22d913"></script>
						<script>
							var mapContainer = document.getElementById('map'), // 지도를 표시할 div
							    mapOption = {
							        center: new daum.maps.LatLng(37.3422301, 126.9694485), // 지도의 중심좌표
							        level: 3 // 지도의 확대 레벨
							    };
							var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
							// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
							var mapTypeControl = new daum.maps.MapTypeControl();
							// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
							// daum.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
							map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);
							// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
							var zoomControl = new daum.maps.ZoomControl();
							map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
							map.setZoomable(false);
							// 마커가 표시될 위치입니다
							var markerPosition  = new daum.maps.LatLng(37.3422301, 126.9694485);
							// 마커를 생성합니다
							var marker = new daum.maps.Marker({
							    position: markerPosition
							});
							// 마커가 지도 위에 표시되도록 설정합니다
							marker.setMap(map);
						</script>
					</div>-->
					<!-- * 카카오맵 - 지도퍼가기 -->
					<!-- 1. 지도 노드 -->
					<div id="daumRoughmapContainer1655614610181"
						class="root_daum_roughmap root_daum_roughmap_landing"></div>
					<!--
						2. 설치 스크립트
						* 지도 퍼가기 서비스를 2개 이상 넣을 경우, 설치 스크립트는 하나만 삽입합니다.
					-->
					<script charset="UTF-8" class="daum_roughmap_loader_script"
						src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>
					<script charset="UTF-8"
						src="https://t1.daumcdn.net/kakaomapweb/place/jscss/roughmap/b88f2e2f/roughmapLander.js"></script>
					<!-- 3. 실행 스크립트 -->
					<script charset="UTF-8">
						new daum.roughmap.Lander({
							"timestamp" : "1655614610181",
							"key" : "2aoz5",
							"mapWidth" : "780",
							"mapHeight" : "360"
						}).render();
					</script>
					<h4 class="htitle">오시는 길</h4>
					<ul class="guide">
						<li><strong class="themeFC">주소</strong> : [16075] 경기도 의왕시
							골우물길 49 (고천동)</li>
						<li><strong class="themeFC">연락처</strong> : 031)345-3642, 3662
							/ FAX 031)345-3649</li>
						<li><strong class="themeFC">버스노선</strong> : <span
							class="txtBtn_darkGray sizeSmall"><a
								href="http://www.gbis.go.kr/gbis2014/schBus.action?mapTabCd=1"
								title="새 창 열림" target="_blank" rel="noopener" class="newWindow">버스노선검색</a></span>
							1-1, 1-2, 1-5, 441, 502, 마을버스 1, 2, 5 (중앙도서관 정류장 하차)</li>
					</ul>
					<!--Real Contents End-->
					<div class="box-area2 mt30">
						<p class="ta_c nanum fnt13px">
							<span class="mr40"> <img
								src="/include/image/global/common/icon_fax.png" alt="콘텐츠 관리부서"
								class=""> 콘텐츠 관리부서 : / 담당자 :
							</span> <span><img
								src="/include/image/global/common/icon_tel.png" alt="전화번호"
								class=""> 전화번호 : </span>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@include file="../footer.jsp"%>
</html>