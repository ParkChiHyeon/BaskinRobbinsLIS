<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<%@include file="./header.jsp"%>
<link rel="stylesheet" href="./include/booksearch/css/booksearch.css">
<link rel="stylesheet" href="./include/css/button.css">

<script type="text/javascript">
	window.onload = function() {
		$("#btnSearchTop").css("display", "block");
		$("#lnb1_1_a").css("color","#E2427F");
	}
</script>
<style>
</style>
<body>

	<div id="middle">
		<div id="contbody">

			<div id="contleft" class="contleft">
				<h2>자료검색</h2>
				<div class="lnbBody" style="min-height: 825px;">
					<ul id="lnb" class="lnbul">
						<li id="lnb1_1"><a id="lnb1_1_a" href="./booksearch.do">통합검색</a></li>
						<li id="lnb1_2"><a id="lnb1_2_a">상세검색</a></li>
						<li id="lnb1_3"><a id="lnb1_3_a">희망도서신청</a></li>
					</ul>
					<div class="lnbBottom"></div>
				</div>
			</div>

			<div id="contentcore">
				<div class="naviandtitle">
					<h3>통합검색</h3>
					<div class="navi"></div>

					<select id="searchKey" name="searchKey" title="검색 선택">
						<option value="ALL">전체</option>
						<option value="TITLE">서명</option>
						<option value="AUTHOR">저자</option>
						<option value="PUBLISHER">발행자</option>
						<option value="KEYWORD">키워드</option>
					</select>
					 <input	type="text" id="searchKeyword" name="searchKeyword"	autocomplete="off" placeholder="검색어 입력">
					 <button class="w-btn w-btn-green" onclick="">검색</button>
			</div>
		</div>
	</div>
</div>



</body>

<%@include file="./footer.jsp"%>
</html>