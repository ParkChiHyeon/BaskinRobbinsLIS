<%@ page language="java" contenttype="text/html; charset=EUC-KR" pageencoding="EUC-KR" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div id="contentcore">
	<div class="naviandtitle">
		<h3>ã�ƿ��ô±�</h3>
		<div class="navi">
			<a href="/jungang/main.do"><img src="/include/image/global/common/icon_home.gif" alt="Ȩ"></a> �������Ұ�<span class="mobileHide"> &gt; <strong>ã�ƿ��ô±�</strong></span>
		</div>
	</div>
	<ul class="snsFarm">
		<li><a href="#sns4" id="sns4" onclick="shareTwitter('�����ϱ�','http://uwlib.or.kr/jungang/10073/40035/content.do'); return false;" title="�� â ����"><img src="/include/image/common/twitter.png" alt="Ʈ���� �ۺ�����"></a></li>
		<li><a href="#sns3" id="sns3" onclick="shareFaceBook('�����ϱ�','http://uwlib.or.kr/jungang/10073/40035/content.do'); return false;" title="�� â ����"><img src="/include/image/common/facebook.png" alt="���̽��� �ۺ�����"></a></li>
	</ul>
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
							var mapContainer = document.getElementById('map'), // ������ ǥ���� div
							    mapOption = {
							        center: new daum.maps.LatLng(37.3422301, 126.9694485), // ������ �߽���ǥ
							        level: 3 // ������ Ȯ�� ����
							    };
							var map = new daum.maps.Map(mapContainer, mapOption); // ������ �����մϴ�
							// �Ϲ� ������ ��ī�̺�� ���� Ÿ���� ��ȯ�� �� �ִ� ����Ÿ�� ��Ʈ���� �����մϴ�
							var mapTypeControl = new daum.maps.MapTypeControl();
							// ������ ��Ʈ���� �߰��ؾ� �������� ǥ�õ˴ϴ�
							// daum.maps.ControlPosition�� ��Ʈ���� ǥ�õ� ��ġ�� �����ϴµ� TOPRIGHT�� ������ ���� �ǹ��մϴ�
							map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);
							// ���� Ȯ�� ��Ҹ� ������ �� �ִ�  �� ��Ʈ���� �����մϴ�
							var zoomControl = new daum.maps.ZoomControl();
							map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
							map.setZoomable(false);
							// ��Ŀ�� ǥ�õ� ��ġ�Դϴ�
							var markerPosition  = new daum.maps.LatLng(37.3422301, 126.9694485);
							// ��Ŀ�� �����մϴ�
							var marker = new daum.maps.Marker({
							    position: markerPosition
							});
							// ��Ŀ�� ���� ���� ǥ�õǵ��� �����մϴ�
							marker.setMap(map);
						</script>
					</div>-->
		<!-- * īī���� - �����۰��� -->
		<!-- 1. ���� ��� -->
		<div id="daumRoughmapContainer1557974893443" class="root_daum_roughmap root_daum_roughmap_landing os_mac" style="width: 780px;">
		</div>
		<!--
						2. ��ġ ��ũ��Ʈ
						* ���� �۰��� ���񽺸� 2�� �̻� ���� ���, ��ġ ��ũ��Ʈ�� �ϳ��� �����մϴ�.
					-->
		<script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>
		<script charset="UTF-8" src="https://t1.daumcdn.net/kakaomapweb/place/jscss/roughmap/b88f2e2f/roughmapLander.js"></script>
		<!-- 3. ���� ��ũ��Ʈ -->
		<script charset="UTF-8">
						new daum.roughmap.Lander({
							"timestamp" : "1557974893443",
							"key" : "ti24",
							"mapWidth" : "780",
							"mapHeight" : "360"
						}).render();
					</script>
		<h4 class="htitle">���ô� ��</h4>
		<ul class="guide">
			<li><strong class="themeFC">�ּ�</strong> : [16075] ��⵵ �ǿս� ��칰�� 49 (��õ��)</li>
			<li><strong class="themeFC">����ó</strong> : 031)345-3642, 3662 / FAX 031)345-3649</li>
			<li><strong class="themeFC">�����뼱</strong> : <span class="txtBtn_darkGray sizeSmall"><a href="http://www.gbis.go.kr/gbis2014/schBus.action?mapTabCd=1" title="�� â ����" target="_blank" rel="noopener" class="newWindow">�����뼱�˻�</a></span> 1-1, 1-2, 1-5, 441, 502, �������� 1, 2, 5 (�߾ӵ����� ������ ����)</li>
		</ul>
		<!--Real Contents End-->
		<div class="box-area2 mt30">
			<p class="ta_c nanum fnt13px">
				<span class="mr40">
				<img src="/include/image/global/common/icon_fax.png" alt="������ �����μ�" class=""> ������ �����μ� : / ����� : </span>
				<span><img src="/include/image/global/common/icon_tel.png" alt="��ȭ��ȣ" class=""> ��ȭ��ȣ : </span>
			</p>
		</div>
	</div>
</div>
</body>
</html>