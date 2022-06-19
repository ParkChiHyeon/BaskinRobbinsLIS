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
					<li id="lnb6_4" class=""><a href="./informationLib.do?kind=useguide">이용안내</a></li>
					<li id="lnb6_5" class=""><a href="./informationLib.do?kind=locguide">찾아 오시는길</a></li>
					<li id="lnb6_4" class=""><a href="./informationLib.do?kind=siteMap" class="current">사이트맵</a></li>
				</ul>
				<div class="lnbBottom"></div>
			</div>
		</div>
 
 
 <div id="contentcore">
				



				<div class="naviandtitle">
					<h3>사이트맵</h3>
					<div class="navi"></div>
				</div>

				<div class="contents" id="contents">
					


			<div class="ttabWrap">
				<ul id="ttab3" class="tabType5">
					<li></li>
				</ul>
			</div>

					<!--Real Contents Start-->
					
							
									
					<div id="sitemap"></div>
					<div class="themeFC themeColor" style="display:none;"></div>
					<script type="text/javascript">
					$(document).ready(function(e) {
						var tmpHtml = $('#indexNavi').html();

						$('#sitemap').html($('#indexNavi').html());
						$('#sitemap').html($('#sitemap').html().replace('topmenu','smapmenu'));
						$('#sitemap').html($('#sitemap').html().replace(/gnb/g,'smb'));

						$('#sitemap > ul > li > a').each(function(index, element) {
							//$(this).text($(this).find('img').attr('alt'));
							$(this).css('color',$('.themeFC').css('color'));
							$(this).css('border-bottom-color',$('.themeColor').css('background-color'));
						});
						$('#sitemap .fbC').removeClass('fbC');
					});
					</script>	

				</div>
			</div>
 
	</div>
</body>
<%@include file="../footer.jsp"%>
</html>